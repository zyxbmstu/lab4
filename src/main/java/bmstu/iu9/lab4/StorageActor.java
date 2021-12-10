package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageActor extends AbstractActor {

    private HashMap<Integer, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create()
                .match(StorageMessage.class,)
    }

    private void storeMessage(TestMessage msg) {
        if (!storage.containsKey())
    }

}
