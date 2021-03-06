package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import bmstu.iu9.lab4.message.StorageMessage;
import bmstu.iu9.lab4.message.Test;
import bmstu.iu9.lab4.message.TestMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {

    private final String LANGUAGE = "nashorn";

    private ActorSelection storageActor = getContext().actorSelection("/user/storageActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> storageActor.tell(new StorageMessage(msg.getPackageId(),
                        executeTest(msg)), self())).build();
    }

    private ArrayList<Test> executeTest(TestMessage msg) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        System.out.println(engine);
        System.out.println(msg.getScript());
        System.out.println(msg.getScript().getClass());

        /*engine.eval(msg.getScript());
        Invocable invocable = (Invocable) engine;
        String testResult = invocable.invokeFunction(msg.getFunctionName(), msg.getTest().getParams().toArray()).toString();
*/
        Test test = new Test(msg.getTest().getTestName(),
                msg.getTest().getExpectedResult(),
                msg.getTest().getParams(),
                true);

        ArrayList<Test> testResultsList = new ArrayList<>();
        testResultsList.add(test);

        return testResultsList;
    }

}
