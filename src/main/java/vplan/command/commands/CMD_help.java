package vplan.command.commands;

import vplan.command.FLSCommand;
import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;
import vplan.utils.Log;

public class CMD_help implements FLSCommand {

    public void execute(String cmd, String[] args) {
        Log.prln(" ");
        Log.prln(Data.prefix + "Es existieren folgende Befehle: ");
        for(String command : FLSVertretungsplan.instance.getHandler().getRegisteredCommands().keySet()) {
            Log.prln(Data.prefix + " " + command);
        }
        Log.prln(" ");
    }

}
