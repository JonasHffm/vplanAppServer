package vplan.command;

import java.util.HashMap;

public class CommandHandler {

    private HashMap<String, FLSCommand> registeredCommands = new HashMap<String, FLSCommand>();

    public void registerCommand(String cmd, FLSCommand flscommandclass) {
        registeredCommands.put(cmd, flscommandclass);
    }

    public HashMap<String, FLSCommand> getRegisteredCommands() {
        return registeredCommands;
    }
}
