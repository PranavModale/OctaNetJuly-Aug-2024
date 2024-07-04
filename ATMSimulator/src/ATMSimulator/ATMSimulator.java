package ATMSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMSimulator {
    private static double balance = 1000.00;
    private static String pin = "1234";
    private static List<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        if (!authenticate(scanner)) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        do {
            System.out.println("\nWelcome to the ATM Machine");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    changePIN(scanner);
                    break;
                case 5:
                    displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static boolean authenticate(Scanner scanner) {
        System.out.print("Enter your PIN: ");
        String inputPin = scanner.next();
        return inputPin.equals(pin);
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
        transactionHistory.add("Checked balance: $" + String.format("%.2f", balance));
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.printf("You have successfully deposited $%.2f. Your new balance is: $%.2f%n", amount, balance);
            transactionHistory.add("Deposited: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("You have successfully withdrawn $%.2f. Your new balance is: $%.2f%n", amount, balance);
            transactionHistory.add("Withdrew: $" + String.format("%.2f", amount));
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Please try again.");
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private static void changePIN(Scanner scanner) {
        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.next();
        if (currentPin.equals(pin)) {
            System.out.print("Enter your new PIN: ");
            String newPin = scanner.next();
            pin = newPin;
            System.out.println("PIN successfully changed.");
            transactionHistory.add("Changed PIN");
        } else {
            System.out.println("Incorrect current PIN. Please try again.");
        }
    }

    private static void displayTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}