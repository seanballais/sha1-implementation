package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        SHA1 sha1 = new SHA1();

        System.out.println("What would you like to input?");
        System.out.println("[a] A string");
        System.out.println("[b] A set of strings");

        char opt = '\0';
        Scanner scanner = new Scanner(System.in);
        while (opt != 'a' && opt != 'b') {
            System.out.print("Input: ");
            opt = Character.toLowerCase(scanner.next(".").charAt(0));
            scanner.nextLine(); // Discard leftover characters

            if (opt != 'a' && opt != 'b') {
                System.out.println("Enter only 'a' or 'b'.");
            }
        }

        if (opt == 'a') {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();

            System.out.println("==========");
            System.out.println("Original message: " + message);
            System.out.println("Hashed Message: " + sha1.digestMessage(message));
        } else if (opt == 'b') {
            boolean isAGo = false;
            int numStrings = 0;
            while (!isAGo) {
                System.out.print("Number of strings: ");
                try {
                    numStrings = scanner.nextInt();
                    isAGo = true;
                } catch (InputMismatchException imex) {
                    isAGo = false;
                }
                scanner.nextLine(); // Discard leftover characters
            }

            String[] messages = new String[numStrings];
            int messageIndex = 0;
            while (numStrings > 0) {
                messageIndex = messages.length - numStrings;
                System.out.print("Message #" + (messageIndex + 1) + ": ");
                messages[messageIndex] = scanner.nextLine();

                numStrings--;
            }

            System.out.println("==========");
            int nMessage = 1;
            for (String message : messages) {
                System.out.println("Original message #" + nMessage++ + ": " + message);
                System.out.println("Hashed Message: " + sha1.digestMessage(message));
                System.out.println("");
            }
        }
    }
}
