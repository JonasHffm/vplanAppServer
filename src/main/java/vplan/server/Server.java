package vplan.server;

import vplan.utils.Data;
import vplan.utils.Vertretung;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
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
        for(String stundeTosend : Data.packedToSend) {
            try {

                GregorianCalendar now = new GregorianCalendar();
                DateFormat df = //DateFormat.getDateInstance(DateFormat.SHORT);   // 14.04.12
                df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM); // 14.04.12 21:34:07 MESZ

                PrintWriter writer = new PrintWriter(_client.getOutputStream());
                writer.write(stundeTosend + " \n");
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
