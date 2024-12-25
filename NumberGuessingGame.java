package java_Console_Projects;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            System.out.print("\nEnter the range for the random number (e.g., 1 100): ");
            int lowerBound = 1, upperBound = 100;
            try {
                String[] bounds = scanner.nextLine().split(" ");
                lowerBound = Integer.parseInt(bounds[0]);
                upperBound = Integer.parseInt(bounds[1]);
                if (lowerBound >= upperBound) {
                    System.out.println("Invalid range. Lower bound must be less than upper bound.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two integers separated by a space.");
                continue;
            }

            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int maxAttempts = (int) Math.ceil(Math.log(upperBound - lowerBound + 1) / Math.log(2)) + 1; // Logarithmic attempts
            int attemptsLeft = maxAttempts;

            System.out.printf("\nI have selected a number between %d and %d. You have %d attempts to guess it!%n",
                    lowerBound, upperBound, maxAttempts);

            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.printf("Attempts remaining: %d. Enter your guess: ", attemptsLeft);
                try {
                    int playerGuess = Integer.parseInt(scanner.nextLine());

                    if (playerGuess < lowerBound || playerGuess > upperBound) {
                        System.out.printf("Your guess is out of range! Please guess between %d and %d.%n", lowerBound, upperBound);
                        continue;
                    }

                    if (playerGuess < numberToGuess) {
                        System.out.println("Too low!");
                    } else if (playerGuess > numberToGuess) {
                        System.out.println("Too high!");
                    } else {
                        System.out.println("Congratulations! You guessed the number!");
                        guessedCorrectly = true;
                        break;
                    }
                    attemptsLeft--;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }

            if (!guessedCorrectly) {
                System.out.printf("You've run out of attempts! The correct number was %d.%n", numberToGuess);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thank you for playing! Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
