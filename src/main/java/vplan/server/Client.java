package vplan.server;


import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{

    private Socket client;

    public Client(Socket client) throws IOException {
        this.client = client;
        System.out.println("\nClient wurde registriert!");

        //VPLAN ALREADY SENT CHECK


        if (Data.clientUpdateList.containsKey(this.client.getInetAddress().toString())) {
            if(!Data.clientUpdateList.get(this.client.getInetAddress().toString())) {
                Data.clientUpdateList.put(this.client.getInetAddress().toString(), false);
                FLSVertretungsplan.server.sendNull(this.client);
            }else {
                FLSVertretungsplan.server.sendVertretungsstunden(this.client);
                Data.clientUpdateList.put(this.client.getInetAddress().toString(), true);
            }
        } else {
            FLSVertretungsplan.server.sendVertretungsstunden(this.client);
            Data.clientUpdateList.put(this.client.getInetAddress().toString(), true);
        }

    }


    public Socket getClient() {
        return client;
    }

}
