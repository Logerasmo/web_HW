package ru.netology;
import java.util.List;
import java.io.*;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    protected Map<String, Handler> handlers = new ConcurrentHashMap<>();
    protected final static List<String> VALID_PATHS = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");
    private final int PORT = 9999;
    private final int THREADS_COUNT = 64;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(THREADS_COUNT);
    private ServerSocket serverSocket;
    private final Thread connectionListening = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!connectionListening.isInterrupted()) {
                try {
                    threadPool.submit(new UsersHandlerRunnable(serverSocket.accept(), this));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });
    public void launch(){
        if (serverSocket == null){
            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void listen(int sec){
        connectionListening.start();
        try {
            wait(sec * 100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        connectionListening.interrupt();
    }

    public void addHandler(String method, String path, Handler handler){
        handlers.put(method + path, handler);
    }

}
