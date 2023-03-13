package ui.command;
import java.io.FileNotFoundException;

import ui.Console;

public class Save extends Command {
    public Save(Console console) {
        super(console);
    }

    @Override
    public String description() {
        return "Save to file";
    }

    @Override
    public void execute() throws FileNotFoundException {
        super.getConsole().save();
    }
}