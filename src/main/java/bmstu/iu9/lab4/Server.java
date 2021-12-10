package bmstu.iu9.lab4;

import akka.actor.ActorSystem;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");
    }

}
