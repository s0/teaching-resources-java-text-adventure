package adventure_game.ui;

import java.util.Scanner;

public class CommandLineUserInterface implements UserInterface {
    
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public String getStringFromUser(String prompt) {
        if (prompt != null)
            System.out.print(prompt + " ");
        return scanner.nextLine();
    }
    
    @Override
    public void sendTextToUser(String message) {
        System.out.println(message);
    }
    
}
