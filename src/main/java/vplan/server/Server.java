package vplan.server;

import vplan.utils.Data;
import vplan.utils.Vertretung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Server {

    private ServerSocket server;
    private int port;
    static final String newLine = "\r\n";

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

    public void sendVertretungsstunden(Socket _client) throws IOException {

        System.out.println("Sende alle Vertretungsstunden zu Client: " + _client.getInetAddress().toString());

        OutputStream out = new BufferedOutputStream(_client.getOutputStream());
        PrintStream pout = new PrintStream(out, true, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(new InputStreamReader(_client.getInputStream()));


        // read first line of request
        String request = in.readLine();
        String response = "";

        // we ignore the rest
        while (true) {
            String ignore = in.readLine();
            if (ignore == null || ignore.length() == 0) break;
        }

        if (!request.startsWith("GET ") ||
                !(request.endsWith(" HTTP/1.0") || request.endsWith(" HTTP/1.1"))) {
            // bad request
            pout.print("HTTP/1.0 400 Bad Request" + newLine + newLine);
        } else {


/*        for(String stundeTosend : Data.packedToSend) {

            response += stundeTosend + "\n";

        }*/
            response += "3;2020-02-10;1082;1./2.|08:00:00|09:30:00|;Martina|Ecker-Link|ECKL;Deutsch|DEUT;301;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-10;11/6 DV;1./2.|08:00:00|09:30:00|;Rainer|Füll|FÜLL;Praktische Informatik|INFO;U10;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1082;3./4.|09:45:00|11:15:00|;Eugen|Berger|EBER;Mathematik|MATH;203  SB GH;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-10;1081;3./4.|09:45:00|11:15:00|;Harald|Hofmann|HOFM;Mathematik|MATH;A211 SB;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-10;1081;5./6.|11:30:00|13:00:00|;Martina|Krämer|KRÄM;NAWI|NAWI;106 Che SB;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-11;12;3./4.|09:45:00|11:15:00|;Rainer|Füll|FÜLL;Mathematik LK|MALK;U10;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1081;1./2.|08:00:00|09:30:00|;Kira|Berendes-Schirmer|BERE;Englisch|ENGL;304;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Kira|Berendes-Schirmer|BERE;Englisch LK|ENLK;304;||;|;A317;Unterrichtsgang;null\n" +
                    "3;2020-02-10;1082;3./4.|09:45:00|11:15:00|;Doris|Müller|MUEL;Rechnungswesen|REWE;A212;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-11;1135;1./2.|08:00:00|09:30:00|;Annelie|Heil|HEIL;BBUN|BBUN;202;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-11;1135;3./4.|09:45:00|11:15:00|;Annelie|Heil|HEIL;BBUN|BBUN;202;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1082;5./6.|11:30:00|13:00:00|;Eugen|Berger|EBER;Mathematik|MATH;401 SB;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-11;1036;5./6.|11:30:00|13:00:00|;Annelie|Heil|HEIL;BBUN|BBUN;202;||;|;null;In die Betriebe;Klasse frei\n" +
                    "1;2020-02-12;11/6 DV;1./2.|08:00:00|09:30:00|;Rainer|Füll|FÜLL;Praktische Informatik|INFO;U10;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1081;3./4.|09:45:00|11:15:00|;Tina|Laudenbach|LAUD;BBUN|BBUN;403;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-10;13;1./2.|08:00:00|09:30:00|;Sandra|Schubert|SCHU;Geschichte|GESC;303   SB;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-11;12;7./8.|13:30:00|15:00:00|;Tina|Laudenbach|LAUD;Powi|POWI;A111;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-10;1121;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;||;|;null;Pausenhalle, Lehrbuch „Lieferungsverzug“ herausarbeiten;Klasse frei\n" +
                    "3;2020-02-12;1081;5./6.|11:30:00|13:00:00|;Martina|Herz|HERZ;BBUN|BBUN;A114 DV;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1081;5./6.|11:30:00|13:00:00|;Martina|Herz|HERZ;BBUN|BBUN;A114 DV;||;|;null;null;Fehlend: 1081\n" +
                    "2;2020-02-11;1138;1./2.|08:00:00|09:30:00|;Marianne|Löffler|LÖFF;BBUN|BBUN;406;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-10;1124;3./4.|09:45:00|11:15:00|;Annelie|Heil|HEIL;BBUN|BBUN;401 SB;Katja|Hell-Berlin|HELL;|;null;null;null\n" +
                    "3;2020-02-12;1082;3./4.|09:45:00|11:15:00|;Martina|Krämer|KRÄM;NAWI|NAWI;106 Che SB;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-11;12;7./8.|13:30:00|15:00:00|;Conchi|Medina|MEDI;Spanisch|SPAN;A206 SB;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-10;1041;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1081;5./6.|11:30:00|13:00:00|;Doris|Müller|MUEL;Rechnungswesen|REWE;A212;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-12;1261;1./2.|08:00:00|09:30:00|;Jürgen|Morbe|MORB;BBUN|BBUN;A305  SB;||;|;null;verschoben auf Mi, 12.02. 3. Std.;null\n" +
                    "3;2020-02-10;1181;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;Deutsch|DEUT;301;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1082;7./8.|13:30:00|15:00:00|;Doris|Müller|MUEL;BBUN|BBUN;A212;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-12;1051;1./2.|08:00:00|09:30:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1082;1./2.|08:00:00|09:30:00|;Brigitte|Schuchert|SCHT;Sport|SPOR;AH2;||;|;null;null;Klasse frei\n" +
                    "1;2020-02-12;11/13 M;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;Geschichte|GESC;303   SB;||;|;null;Pausenhalle, Arbeitsauftrag: Lehrbuch: S. 114 - 119 lesen; S. 120 M2 Nr. 1-3 bearbeiten;Klasse frei\n" +
                    "2;2020-02-11;1135;5./6.|11:30:00|13:00:00|;Gudrun|Sell-Menke|SELL;BBUN|BBUN;401 SB;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1081;1./2.|08:00:00|09:30:00|;Michael|Seng|SENG;Sport|SPOR;KH;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1082;1./2.|08:00:00|09:30:00|;Melanie|Wicht|WICH;Englisch|ENGL;A110  SB;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-11;1081;7./8.|13:30:00|15:00:00|;Petra|Schmidt 1|SPE;Religion|RELI;A109;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-12;1261;3./4.|09:45:00|11:15:00|;Jochen|Schalon|SCAL;BBUN|BBUN;A305  SB;Jürgen|Morbe|MORB;BBUN|BBUN;null;anstatt Mi, 12.02. 1. Std.;null\n" +
                    "1;2020-02-11;11/1 W Bili;5./6.|11:30:00|13:00:00|;Marianne|Löffler|LÖFF;Rechnungswesen|REWE;A310;Sylvia|Baier|BAIE;|;null;null;null\n" +
                    "1;2020-02-12;11/2 W;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;Deutsch|DEUT;301;||;|;null;Pausenhalle;Klasse frei\n" +
                    "1;2020-02-12;11/2 W;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;Deutsch|DEUT;301;Marcel|Metz|METZ;Mathematik|MATH;A212;anstatt Mi, 12.02. 5.-6. Std.;null\n" +
                    "2;2020-02-11;1152;5./6.|11:30:00|13:00:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;Philipp|Kratz|KRAT;|;null;null;Arbeitsauftrag Projektgruppe\n" +
                    "2;2020-02-12;1041;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;In die Betriebe;Klasse frei\n" +
                    "2;2020-02-12;1041;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-11;1151;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;Patrick|Pohl|POHL;|;null;null;Arbeitsauftrag Projektgruppe\n" +
                    "2;2020-02-11;1151;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;Alexander|Emde|EMDE;|;null;null;Arbeitsauftrag Schubert\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Hardt|Hardt|Hardt;Englisch LK|ENLK;001;||;|;null;null;Klausur wie angekÃ¼ndigt\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Hardt|Hardt|Hardt;Englisch LK|ENLK;001;||;|;null;null;Klausur wie angekuendigt\n" +
                    "2;2020-02-11;1042;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;In die Betriebe;Klasse frei\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Hardt|Hardt|Hardt;Englisch LK|ENLK;001;||;|;null;null;Klausur wie angekÃ¼ndigt\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Hardt|Hardt|Hardt;Englisch LK|ENLK;001;||;|;null;null;Klausur wie angekuendigt\n" +
                    "2;2020-02-11;1151;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;Patrick|Pohl|POHL;|;null;null;Arbeitsauftrag Projektgruppe\n" +
                    "2;2020-02-11;1151;3./4.|09:45:00|11:15:00|;Sandra|Schubert|SCHU;BBUN|BBUN;303   SB;Alexander|Emde|EMDE;|;null;null;Arbeitsauftrag Schubert\n" +
                    "2;2020-02-12;1123;5./6.|11:30:00|13:00:00|;Kristina|Grünberg|GRÜN;BBUN|BBUN;405 Verk;||;|;401 SB;null;null\n" +
                    "2;2020-02-11;1042;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;Andrea|Volbracht|VOLA;|;U09;null;null\n" +
                    "2;2020-02-12;1231;3./4.|09:45:00|11:15:00|;Patrick|Schmidt|SPA;BBUN|BBUN;405 Verk;||;|;403;null;null\n" +
                    "2;2020-02-11;1036;1./2.|08:00:00|09:30:00|;Patrick|Schmidt|SPA;BBUN|BBUN;405 Verk;||;|;U08;null;null\n" +
                    "1;2020-02-12;11/2 W;5./6.|11:30:00|13:00:00|;Marcel|Metz|METZ;Mathematik|MATH;A212;||;|;null;verschoben auf Mi, 12.02. 3.-4. Std.;null\n" +
                    "2;2020-02-11;1231;3./4.|09:45:00|11:15:00|;Patrick|Schmidt|SPA;BBUN|BBUN;405 Verk;||;|;U10;null;null\n" +
                    "2;2020-02-12;1041;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;In die Betriebe;Klasse frei\n" +
                    "2;2020-02-12;1041;5./6.|11:30:00|13:00:00|;Patrick|Pohl|POHL;BBUN|BBUN;301;||;|;null;null;Klasse frei\n" +
                    "2;2020-02-11;1221;5./6.|11:30:00|13:00:00|;Patrick|Schmidt|SPA;BBUN|BBUN;405 Verk;||;|;U10;null;null\n" +
                    "1;2020-02-12;11/2 W;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;Deutsch|DEUT;301;||;|;null;Pausenhalle;Klasse frei\n" +
                    "1;2020-02-12;11/2 W;3./4.|09:45:00|11:15:00|;Patrick|Pohl|POHL;Deutsch|DEUT;301;Marcel|Metz|METZ;Mathematik|MATH;A212;anstatt Mi, 12.02. 5.-6. Std.;null\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Michael|Hardt\t|HARD;Englisch LK|ENLK;1;||;|;null;null;Klausur wie angekÃ¼ndigt\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Michael|Hardt\t|HARD;Englisch LK|ENLK;1;||;|;null;null;Klausur wie angekuendigt\n" +
                    "1;2020-02-12;SPORT12;7./8.|13:30:00|15:00:00|;Jürgen|Morbe|MORB;Sport|SP12;NH1;||;|;null;null;Unterricht findet statt\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Michael|Hardt\t|HARD;Englisch LK|ENLK;1;||;|;null;null;Klausur wie angekÃ¼ndigt\n" +
                    "1;2020-02-12;13;3./4.|09:45:00|11:15:00|;Michael|Hardt\t|HARD;Englisch LK|ENLK;1;||;|;null;null;Klausur wie angekuendigt\n" +
                    "2;2020-02-12;1231;5./6.|11:30:00|13:00:00|;Swantje|Feuerhake|FEUE;Englisch|ENGL;A313;||;|;null;in die Betriebe;Klasse frei\n" +
                    "3;2020-02-12;1081;3./4.|09:45:00|11:15:00|;Martina|Herz|HERZ;BBUN|BBUN;A105 DV SB;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1081;3./4.|09:45:00|11:15:00|;Martina|Herz|HERZ;BBUN|BBUN;A105 DV SB;||;|;null;null;Fehlend: 1081\n" +
                    "3;2020-02-12;1081;5./6.|11:30:00|13:00:00|;Martina|Herz|HERZ;BBUN|BBUN;A114 DV;||;|;null;null;Klasse frei\n" +
                    "3;2020-02-12;1081;5./6.|11:30:00|13:00:00|;Martina|Herz|HERZ;BBUN|BBUN;A114 DV;||;|;null;null;Fehlend: 1081\n";

            System.out.println(response.length());

            pout.print(
                    "HTTP/1.0 200 OK" + newLine +
                            "Content-Type: text/plain" + newLine +
                            "Date: " + new Date() + newLine +
                            "Content-length: " + response.length() + newLine + newLine +
                            response
            );

        }

        pout.close();


    }

    public void sendNull(Socket _client) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(_client.getOutputStream());

            writer.write("\"HTTP/1.0 200 OK\" + newLine + newLine");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ServerSocket getServer() {
        return server;
    }
}
