package vplan.command;



import com.iclojure.jline.console.ConsoleReader;
import com.iclojure.jline.console.completer.Completer;
import com.iclojure.jline.console.completer.StringsCompleter;
import vplan.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandReader extends Thread{

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
                StringsCompleter completer = new StringsCompleter("vertretung", "list", "update");
                reader.addCompleter(completer);
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
