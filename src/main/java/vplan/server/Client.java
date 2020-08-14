package vplan.server;


import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.net.Socket;

public class Client{

    private Socket client;

    public Client(Socket client) {
        this.client = client;
        System.out.println("\nClient wurde registriert!");

        //VPLAN ALREADY SENT CHECK


        if (!Data.clientUpdateList.containsKey(this.client.getInetAddress().toString())) {
            Data.clientUpdateList.put(this.client.getInetAddress().toString(), false);
            FLSVertretungsplan.server.sendNull(this.client);
        }

        if (Data.clientUpdateList.get(this.client.getInetAddress().toString()) == false) {
            FLSVertretungsplan.server.sendVertretungsstunden(this.client);
            Data.clientUpdateList.put(this.client.getInetAddress().toString(), true);
        }

    }


    public Socket getClient() {
        return client;
    }
}
