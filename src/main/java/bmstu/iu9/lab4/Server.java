package bmstu.iu9.lab4;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        Server app = new Server(system);
    }

}
