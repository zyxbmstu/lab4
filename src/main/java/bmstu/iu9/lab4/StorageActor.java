package bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StorageActor extends AbstractActor {

    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create()
                .match(StorageMessage.class)
    }

}
