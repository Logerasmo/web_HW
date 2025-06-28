package ru.netology;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class UsersHandlerRunnable implements Runnable{
    private final BufferedReader in;
    private final BufferedOutputStream out;
    private final Server server;

    public UsersHandlerRunnable(Socket userSocket, Server server){
        try {
            this.server = server;
            in = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            out = new BufferedOutputStream(userSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        try{
            final var requestLine = in.readLine();
            final var parts = requestLine.split(" ");
            if (parts.length == 3) {
                final var path = parts[1];
                server.handlers.get(parts[0] + parts[1]).handle(new Request(parts[0], parts[1]), out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
