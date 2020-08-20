package vplan.main;


import vplan.command.CommandHandler;
import vplan.command.CommandReader;
import vplan.command.commands.*;
import vplan.sql.SQL;
import vplan.sql.VplanSQLMethods;
import vplan.sql.VplanUpdater;
import vplan.utils.Data;

public class Initializer {

    private CommandHandler handler;
    private CommandReader command_reader;
    private VplanUpdater updater;
    private SQL mysql;
    private VplanSQLMethods vplanSQLMethods;

    public CommandHandler getHandler() {
        return handler;
    }

    public void setHandler(CommandHandler handler) {
        this.handler = handler;
    }

    public CommandReader getCommand_reader() {
        return command_reader;
    }

    public void setCommand_reader(CommandReader command_reader) {
        this.command_reader = command_reader;
    }

    public VplanUpdater getUpdater() {
        return updater;
    }

    public void setUpdater(VplanUpdater updater) {
        this.updater = updater;
    }

    public SQL getMysql() {
        return mysql;
    }

    public void setMysql(SQL mysql) {
        this.mysql = mysql;
    }

    public VplanSQLMethods getVplanSQLMethods() {
        return vplanSQLMethods;
    }

    public void setVplanSQLMethods(VplanSQLMethods vplanSQLMethods) {
        this.vplanSQLMethods = vplanSQLMethods;
    }

    public void init() {

        vplanSQLMethods = new VplanSQLMethods();

        mysql = new SQL(Data.host, Data.database, Data.user, Data.password);
        mysql.connect();

        updater = new VplanUpdater(3);
        updater.update();

        this.handler = new CommandHandler();
        this.command_reader = new CommandReader(this.handler);
        this.command_reader.start();

        handler.registerCommand(new CMD_vertretung("vertretung", new String[]{"list", "update", "pack"}));
        handler.registerCommand(new CMD_help("help", new String[]{}));
        handler.registerCommand(new CMD_client("client", new String[]{"list", "clearlist"}));
        handler.registerCommand(new CMD_console("console", new String[]{"clear"}));
        handler.registerCommand(new CMD_exit("exit", new String[]{}));
    }
}
