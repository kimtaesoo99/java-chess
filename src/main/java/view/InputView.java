package view;

import java.util.Scanner;

public class InputView {

    private static final InputValidation inputValidation = new InputValidation();
    private static final Scanner scanner = new Scanner(System.in);

    public static String readFirstCommand() {
        try {
            String command = scanner.nextLine();
            inputValidation.validateFirstCommand(command);
            return command;
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readFirstCommand();
        }
    }

    public static String readCommand() {
        try {
            String command = scanner.nextLine();
            inputValidation.validateCommand(command);
            return command;
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }
}
