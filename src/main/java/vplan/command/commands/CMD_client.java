package vplan.command.commands;


import vplan.command.FLSCommand;
import vplan.utils.Data;

public class CMD_client implements FLSCommand {

    public void execute(String cmd, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("list")) {
                System.out.println(" ");
                System.out.println("---------------- CLIENTS ----------------");
                System.out.println(" ");
                if(Data.clientUpdateList.size() == 0) {
                    System.out.println(" >> Es sind keine Clients auf dem Server registriert!");
                }
                for(String client : Data.clientUpdateList.keySet()) {
                    System.out.println("  >> " + client + "     |     " + Data.clientUpdateList.get(client));
                }
                System.out.println("  ");
                System.out.println("  ");
            }
        }else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("list")) {
                if(args[1].equalsIgnoreCase("clear")) {
                    Data.clientUpdateList.clear();
                    System.out.println(" ");
                    System.out.println("Die Client-Update-Liste wurde geleert!");
                    System.out.println(" ");
                }
            }
        }else {
            System.out.println(Data.prefix + "Usage: ");
            System.out.println(Data.prefix + " -> client list");
            System.out.println(Data.prefix + " -> client list clear");
        }

    }
}
