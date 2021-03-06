package bmstu.iu9.lab4.message;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class PackageActor extends AbstractActor{

    private ActorSelection executeActor = getContext().actorSelection("/user/executeActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PackageMessage.class, msg -> {
                    for (Test test : msg.getTests()) {
                        executeActor.tell(new TestMessage(msg.getPackageId(),
                                msg.getScript(),
                                msg.getFunctionName(),
                                test), self());
                    }
                }).build();
    }

}
