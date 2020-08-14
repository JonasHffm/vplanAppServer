package vplan.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

   //public static final String host = "45.85.217.201";
   //public static final String database = "fls";
   //public static final String user = "phpmyadmin";
   //public static final String password = "REPLQ1zri7JSfHGj";

    public static final String host = "localhost";
    public static final String database = "fls";
    public static final String user = "root";
    public static final String password = "";

    public static final String error = "✖✖✖✖";
    public static final String prefix = "FLSVPLAN >> ";

    public static ArrayList<Vertretung> _vertretungsList = new ArrayList<Vertretung>();
    public static ArrayList<Vertretung> _vstundenToAdd = new ArrayList<Vertretung>();

    public static HashMap<String, Boolean> clientUpdateList = new HashMap<String, Boolean>();

    public static ArrayList<String> clientList = new ArrayList<String>();

    public static boolean acceptClients = true;

    public static int downloadCounter = 0;



}
