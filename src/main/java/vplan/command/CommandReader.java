package vplan.command;

import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;
import vplan.utils.Log;
import java.io.IOException;

public class CommandReader extends Thread {

    private CommandHandler commandHandler;
    public CommandReader(CommandHandler commandHandler) {
       this.commandHandler = commandHandler;
    }

    @Override
    public void run() {

        String cmdLine = "";
        do {
            ConsoleReader reader = null;
            try {
                reader = new ConsoleReader();
                reader.setPrompt("FLSVPLAN@root >> ");
                for(FLSCommand command : this.commandHandler.getRegisteredCommands().values()) {
                    reader.addCompleter(new ArgumentCompleter(new StringsCompleter(command.getCommand()), new StringsCompleter(command.getArguments())));
                }
                /*reader.addCompleter(new ArgumentCompleter(new StringsCompleter("vertretung"), new StringsCompleter(new String[]{"list", "pack", "update"})));
                reader.addCompleter(new ArgumentCompleter(new StringsCompleter("console"), new StringsCompleter("clear")));
                reader.addCompleter(new ArgumentCompleter(new StringsCompleter("client"), new StringsCompleter("list"), new StringsCompleter("clear")));
                reader.addCompleter(new StringsCompleter("help"));
                 */
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                cmdLine = reader.readLine();
                String command = cmdLine.split(" ")[0];
                String[] args = new String[cmdLine.split(" ").length-1];
                for(int i = 1; i < cmdLine.split(" ").length; i++) {
                    args[i-1] = cmdLine.split(" ")[i];
                }

                if(this.commandHandler.getRegisteredCommands().containsKey(command)) {
                    this.commandHandler.getRegisteredCommands().get(command).execute(command, args);
                }else {
                    Log.prln("✖✖✖✖ Dieser Befehl existiert nicht! ✖ 2 ✖ ✖✖✖✖");
                }

            } catch (IOException e) {
                Log.prln("✖✖✖✖ Command Reader Error ✖ 1 ✖ ✖✖✖✖");
            }
        }while (!cmdLine.startsWith("exit"));

        System.exit(0);
    }
}
