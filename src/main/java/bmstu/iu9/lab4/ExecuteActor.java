package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import bmstu.iu9.lab4.message.StorageMessage;
import bmstu.iu9.lab4.message.Test;
import bmstu.iu9.lab4.message.TestMessage;

import javax.script.*;
import java.util.ArrayList;
import java.util.List;

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
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(LANGUAGE);

        System.out.println(msg.getScript());
        System.out.println(msg.getScript().getClass());

        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println(factory.getEngineName());
            System.out.println(factory.getEngineVersion());
            System.out.println(factory.getLanguageName());
            System.out.println(factory.getLanguageVersion());
            System.out.println(factory.getExtensions());
            System.out.println(factory.getMimeTypes());
            System.out.println(factory.getNames());
        }
        
        engine.eval(msg.getScript());
        Invocable invocable = (Invocable) engine;
        String testResult = invocable.invokeFunction(msg.getFunctionName(), msg.getTest().getParams().toArray()).toString();

        Test test = new Test(msg.getTest().getTestName(),
                msg.getTest().getExpectedResult(),
                msg.getTest().getParams(),
                true);

        ArrayList<Test> testResultsList = new ArrayList<>();
        testResultsList.add(test);

        return testResultsList;
    }

}
