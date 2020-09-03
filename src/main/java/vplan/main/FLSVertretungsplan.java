package vplan.main;


import vplan.server.Server;
import vplan.utils.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FLSVertretungsplan {

    public static Server server;
    public static String dateToSend;

    public static Initializer instance = new Initializer();

    public static void main(String[] args) throws IOException {


        Log.prLogo();


        instance.init();

        System.out.println();
        System.out.println();

        server = new Server(1112);
        server.boot();




    }

}
