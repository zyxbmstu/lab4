package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.iu9.lab4.message.StorageMessage;
import bmstu.iu9.lab4.message.Test;
import bmstu.iu9.lab4.message.TestMessage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {

    private final String LANGUAGE = "js";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> sender().tell(new StorageMessage(msg.getPackageId(),
                        executeTest(msg)), self())).build();
    }

    private ArrayList<Test> executeTest(TestMessage msg) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(LANGUAGE);
        engine.eval(msg.getScript());
        In
    }

}
