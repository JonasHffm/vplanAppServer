package vplan.command.commands;


import vplan.command.FLSCommand;
import vplan.utils.Data;

public class CMD_console implements FLSCommand {

    public void execute(String cmd, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("clear")) {
                for(int i = 0; i < 50; i++) {
                    System.out.println(" ");
                }
            }else {
                System.out.println(Data.prefix + "Usage: ");
                System.out.println(Data.prefix + " -> console clear");
            }
        }else {
            System.out.println(Data.prefix + "Usage: ");
            System.out.println(Data.prefix + " -> console clear");
        }

    }
}
