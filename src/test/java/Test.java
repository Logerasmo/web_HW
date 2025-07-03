import ru.netology.Handler;
import ru.netology.Server;
import ru.netology.Request;

import java.io.BufferedOutputStream;

public class Test {
    public void serverTest(){
        final var server = new Server();
        server.addHandler("GET", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
            }
        });
        server.addHandler("POST", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
            }
        });

        server.listen(9999);
    }
}
