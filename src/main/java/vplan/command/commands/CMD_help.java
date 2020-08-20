package vplan.command.commands;

import vplan.command.FLSCommand;
import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;
import vplan.utils.Log;

public class CMD_help extends FLSCommand {

    public CMD_help(String command, String[] arguments) {
        super(command, arguments);
    }

    @Override
    public void execute(String command, String[] arguments) {
        Log.prln(" ");
        Log.prln(Data.prefix + "Es existieren folgende Befehle: ");
        for(String commands : FLSVertretungsplan.instance.getHandler().getRegisteredCommands().keySet()) {
            Log.prln(Data.prefix + " " + commands);
        }
        Log.prln(" ");
    }



    /*public CMD_help(String command) {
        super();
    }

    public void execute(String cmd, String[] args) {

    }

    @Override
    public String[] getArguments() {
        return new String[]{};
    }

    @Override
    public String getCommand() {
        return super();
    }
     */
}
