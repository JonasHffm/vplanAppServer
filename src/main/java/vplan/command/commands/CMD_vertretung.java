package vplan.command.commands;

import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;
import vplan.utils.Vertretung;

public class CMD_vertretung extends CMD_console {

    public CMD_vertretung(String command, String[] arguments) {
        super(command, arguments);
    }

    public void execute(String cmd, String[] args) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("update")) {
                System.out.println(" ");
                System.out.println("  -> Update Vertretungsliste...");
                FLSVertretungsplan.instance.getUpdater().update();
                System.out.println("  ");
                System.out.println("  ");
            }else if(args[0].equalsIgnoreCase("list")) {
                System.out.println(" ");
                System.out.println("---------------- VERTRETUNGSSTUNDEN ----------------");
                System.out.println(" ");

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
                    System.out.println(" >> " + _vstundeToSend);

                }
                System.out.println(" ");
                System.out.println("-----------------------------------------------");
                System.out.println(" -> Es wurden " + Data._vertretungsList.size() + " Stunden auf dem Server geladen!");
                System.out.println("-----------------------------------------------");
            }else if(args[0].equalsIgnoreCase("pack")) {
                FLSVertretungsplan.instance.getVplanSQLMethods().packVertretungsstunden();
                System.out.println("---------------- VERTRETUNGSSTUNDEN | PACKED ----------------");
                System.out.println();
                for(String packed : Data.packedToSend) {
                    System.out.println(packed);
                }
                System.out.println();
            }else {
                System.out.println(Data.prefix + "Usage: ");
                System.out.println(Data.prefix + " -> vertretung update");
                System.out.println(Data.prefix + " -> vertretung list");
                System.out.println(Data.prefix + " -> vertretung pack");
            }
        }else {
            System.out.println(Data.prefix + "Usage: ");
            System.out.println(Data.prefix + " -> vertretung update");
            System.out.println(Data.prefix + " -> vertretung list");
            System.out.println(Data.prefix + " -> vertretung pack");
        }
    }
}
