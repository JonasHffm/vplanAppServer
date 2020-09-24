package vplan.server;

import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.io.IOException;
import java.net.Socket;

public class Client{

    private Socket client;

    public Client(Socket client) throws IOException {
        this.client = client;
        System.out.println("\nClient wurde registriert!");

        //VPLAN ALREADY SENT CHECK
        /* FIXME: You should not check on IP address, as the IP is a shared number 
        * between different clients. E.g. in mobile networks or similar.
        * Corresponding apps can uniquely generate a number on first startup 
        * and sent through http header in order to authorize. This one could be checked.
        * Otherwise, min. source port must be added for checking.
        * And next one: this app is behind a reverse-proxy for security reason.
        * The actual client IP address is 127.0.0.1; NGINX is passing the details.
        * NGINX will provide the real ip in HTTP header:
        *  - X-Real-IP
        *  - X-Forwarded-For
        */

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
