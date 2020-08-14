package vplan.main;


import vplan.server.Server;
import vplan.utils.Log;

import java.util.Date;

public class FLSVertretungsplan {

    public static Server server;
    public static Date date;
    public static String dateToSend;

    public static Initializer instance = new Initializer();

    public static void main(String[] args) {


        Log.prLogo();

        instance.init();

        System.out.println();
        System.out.println();

        server = new Server(1111);
        server.boot();

        date = new Date();
        dateToSend = date.getYear() + "" + date.getMonth() + "" + date.getDay();


    }

}
