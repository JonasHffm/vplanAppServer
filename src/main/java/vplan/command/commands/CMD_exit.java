package vplan.command.commands;

import vplan.command.FLSCommand;
import vplan.utils.Data;

public class CMD_exit extends CMD_vertretung {

    public CMD_exit(String exit, String[] strings) {
        super(exit, strings);
    }

    @Override
    public void execute(String command, String[] arguments) {
        System.out.println(Data.prefix + "Der FLSVplanServer wird heruntergefahren!");
        System.exit(0);
    }
}
