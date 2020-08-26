package vplan.server;

import vplan.utils.Data;
import vplan.utils.Vertretung;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Server {

    private ServerSocket server;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void boot() {
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Der Server wurde gestartet!");
            System.out.println("Starte Client Acception");
            System.out.println("Suche nach Clients...");
            System.out.println();

            ClientAcception clientAcception = new ClientAcception(this.server);
            clientAcception.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendVertretungsstunden(Socket _client) {

        System.out.println("Sende alle Vertretungsstunden zu Client: " + _client.getInetAddress().toString());


        PrintWriter writer = null;
        try {
            writer = new PrintWriter(_client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newLine = "\r\n";

        String data = "HTTP/1.0 200 OK" + newLine +
                "Content-Type: text/plain" + newLine +
                "Date: " + new Date() + newLine +
                "Content-length: ";

        String response = "";


        for(String stundeTosend : Data.packedToSend) {

//            writer.write(stundeTosend + " \n");
//            writer.flush();
            response += stundeTosend + " \n";


        }

        data += response.length() + newLine + newLine + response;
        writer.write(data);
        writer.flush();

    }

    public void sendNull(Socket _client) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(_client.getOutputStream());
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ServerSocket getServer() {
        return server;
    }
}
