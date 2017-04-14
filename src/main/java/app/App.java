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
            while (numStrings > 0) {
                messages[messages.length - numStrings] = scanner.nextLine();
                scanner.nextLine(); // Discard leftover characters

                numStrings--;
            }

            System.out.println("==========");
            for (String message : messages) {
                System.out.println("Original message: " + message);
                System.out.println("Hashed Message: " + sha1.digestMessage(message));
            }
        }
    }
}
