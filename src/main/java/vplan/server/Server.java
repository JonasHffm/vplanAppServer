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

        for(Vertretung _vertretung : Data._vertretungsList) {

             String schultyp = _vertretung.getSchultyp();
             String datum = _vertretung.getDatum();
             String klasse = _vertretung.getKlasse();
             String stunde = _vertretung.getStunde();
             String teacher = _vertretung.getTeacher();
             String fach = _vertretung.getFach();
             String raum = _vertretung.getRaum();
             String vteacher = _vertretung.getVteacher();
             String vfach = _vertretung.getVfach();
             String vraum = _vertretung.getVraum();
             String merkmal = _vertretung.getMerkmal();
             String info = _vertretung.getInfo();

             String _vstundeToSend = schultyp + ";" + datum + ";" + klasse + ";" + stunde + ";" + teacher + ";" + fach + ";" + raum + ";" + vteacher + ";" + vfach + ";" + vraum + ";" + merkmal + ";" + info;

            try {

                GregorianCalendar now = new GregorianCalendar();
                DateFormat df = //DateFormat.getDateInstance(DateFormat.SHORT);   // 14.04.12
                df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM); // 14.04.12 21:34:07 MESZ
                System.out.println();

                PrintWriter writer = new PrintWriter(_client.getOutputStream());
                System.out.println(df.format(now.getTime()) + " | Sende ->  \"" + _vstundeToSend + "\"      -> CLIENT: " + _client.getInetAddress());
                writer.write(_vstundeToSend + " \n");
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
