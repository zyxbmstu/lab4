package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.iu9.lab4.message.StorageMessage;
import bmstu.iu9.lab4.message.Test;
import bmstu.iu9.lab4.message.TestMessage;

import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> sender().tell(new StorageMessage(msg.getPackageId(),
                        executeTest(msg)))
    }

    private ArrayList<Test> executeTest(StorageMessage msg)

}
