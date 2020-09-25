package vplan.server;

import vplan.utils.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class ClientAcception extends Thread {

    private ServerSocket server;
    private boolean running = false;

    public ClientAcception(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        this.running = true;
        while(this.running) {
            try {
                Client client = new Client(server.accept());
                Data.clientList.add(client.getClient().getInetAddress().toString());
            } catch (SocketException e) {
                e.printStackTrace();
                this.running = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ClientAcception is terminating...");
    }

    public void requestTermination() {
        System.out.println("ClientAcception shutdown requested.");
        this.running = false;
    }
}
