package vplan.command;

public abstract class FLSCommand {

    private final String command;
    private final String[] arguments;

    public FLSCommand(String command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract void execute(String command, String[] arguments);

    public String getCommand() {
        return command;
    }
    public String[] getArguments() {
        return arguments;
    }
}
