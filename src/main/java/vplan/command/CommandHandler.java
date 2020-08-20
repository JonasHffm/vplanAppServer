package vplan.command;

import java.util.HashMap;

public class CommandHandler {

    private HashMap<String, FLSCommand> registeredCommands = new HashMap<String, FLSCommand>();

    public void registerCommand(FLSCommand flscommandclass) {
        registeredCommands.put(flscommandclass.getCommand(), flscommandclass);
    }

    public HashMap<String, FLSCommand> getRegisteredCommands() {
        return registeredCommands;
    }
}
