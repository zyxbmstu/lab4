package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.iu9.lab4.message.StorageMessage;
import bmstu.iu9.lab4.message.Test;
import bmstu.iu9.lab4.message.TestMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageActor extends AbstractActor {

    private HashMap<Integer, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create()
                .match(StorageMessage.class, this::storeMessage)
                .match()
                .build();
    }

    private void storeMessage(StorageMessage msg) {
        if (!storage.containsKey(msg.getPackageId())) {
            storage.put(msg.getPackageId(), msg.getTests());
        } else {
            ArrayList<Test> result = storage.get(msg.getPackageId());
            result.addAll(msg.getTests());
            storage.replace(msg.getPackageId(), result);
        }
        System.out.println("Result: " + msg);
    }

}
