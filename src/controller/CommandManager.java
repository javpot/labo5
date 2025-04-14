package controller;// controller.CommandManager.java (Singleton)
import java.util.Stack;

public class CommandManager {
    private static CommandManager instance;
    private Stack<Command> commandHistory;

    private CommandManager() {
        commandHistory = new Stack<>();
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        if (!(command instanceof SaveCommand)) {
            commandHistory.push(command);
        }
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("Aucune commande à annuler.");
        }
    }
}
