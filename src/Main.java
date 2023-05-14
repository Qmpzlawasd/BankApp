import Account.Account;
import Account.CheckingAccount;
import Account.SavingsAccount;
import Bank.Bank;
import Card.Card;
import Card.CreditCard;
import Card.DebitCard;
import Client.Client;
import Employee.Employee;
import Exceptions.AccountError;
import Exceptions.CardError;
import Exceptions.TransactionError;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static List<Bank> banks = new ArrayList<>();

    private static void bankNeededData() {
        System.out.println("I Need:\n" +
                "Location: string");
    }

    private static void employeeNeededData() {
        System.out.println("""
                I Need:
                firstName: string
                lastName: string
                birthday: Date
                email: string
                department: string
                salary: int""");
    }

    private static void clientNeededData() {
        System.out.println("""
                I Need:
                firstName: string
                lastName: string
                birthday: Date
                email: string""");
    }

    private static int chooseAAA(Scanner scanner, List<String> list) {
        System.out.println("Choose");
        int index = 0;
        for (String as : list) {
            System.out.println((index++) + "." + as);
        }
        return Integer.parseInt(scanner.nextLine());

    }

    public static void main(String[] args) throws IOException {
        Service service = Service.getInstance();
        Path path = Paths.get("/home/stefan/IdeaProjects/BankApp/src/.in");
        Scanner scanner = new Scanner(path);
//        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println(Service.meniu);
            System.out.print(">");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> { // hire someone
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    employeeNeededData();
                    Employee emp = new Employee(scanner.nextLine(), scanner.nextLine(),
                            LocalDate.parse(scanner.nextLine()), scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()));
                    service.hireEmployee(chosenBank, emp);
                }
                case 2 -> { // fire someone
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    Employee toBeFired = service.getListEmployees(chosenBank).get(chooseAAA(scanner,
                            service.getListEmployees(chosenBank).stream().map(employee -> employee.toString()).toList()));
                    service.fireEmployee(chosenBank, toBeFired);
                }
                case 3 -> { //add account
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    int accountType = chooseAAA(scanner, Arrays.asList("Checking", "Savings"));
                    if (accountType == 1) {
                        service.addAccount(client, new SavingsAccount(client));
                    } else
                        service.addAccount(client, new CheckingAccount(client));
                    System.out.println("done");
                }
                case 4 -> {// Add Card
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    Account account = service.getAccounts(client).get(chooseAAA(scanner,
                            service.getAccounts(client).stream().map(Object::toString).toList()));
                    int cardType = chooseAAA(scanner, Arrays.asList("Debit", "Credit"));
                    if (cardType == 1) {
                        service.addCard(account, new CreditCard(account));
                    } else
                        service.addCard(account, new DebitCard(account));
                    System.out.println("done");

                }
                case 5 -> { // Check balance
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    Account account = service.getAccounts(client).get(chooseAAA(scanner,
                            service.getAccounts(client).stream().map(Object::toString).toList()));
                    Card card = service.getCards(account).get(chooseAAA(scanner,
                            service.getCards(account).stream().map(Object::toString).toList()));
                    if (card instanceof DebitCard d) {
                        System.out.println(d.getBalance());
                    } else if (card instanceof CreditCard c)
                        System.out.println(c.getMoneySpent());
                }
                case 7 -> { // make transaction
                    System.out.println("I need two cards.");
                    System.out.println("Sender:");
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBankSender = banks.get(chooseAAA(scanner,
                            banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBankSender).isEmpty()) {
                        break;
                    }
                    Client sender = service.getListClients(chosenBankSender).get(chooseAAA(scanner,
                            service.getListClients(chosenBankSender).stream().map(Object::toString).toList()));
                    Account accountSender = service.getAccounts(sender).get(chooseAAA(scanner,
                            service.getAccounts(sender).stream().map(Object::toString).toList()));
                    Card cardSender = service.getCards(accountSender).get(chooseAAA(scanner,
                            service.getCards(accountSender).stream().map(Object::toString).toList()));
                    System.out.println("Receiver:");
                    Bank chosenBankReceiver = banks.get(chooseAAA(scanner,
                            banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBankReceiver).isEmpty()) {
                        break;
                    }
                    Client receiver = service.getListClients(chosenBankReceiver).get(chooseAAA(scanner,
                            service.getListClients(chosenBankReceiver).stream().map(Object::toString).toList()));
                    Account accountReceiver = receiver.getAccounts().get(chooseAAA(scanner,
                            service.getAccounts(receiver).stream().map(Object::toString).toList()));
                    Card cardReceiver = accountReceiver.getCards().get(chooseAAA(scanner,
                            service.getCards(accountReceiver).stream().map(Object::toString).toList()));
                    try {
                        System.out.println("Enter the amount and a description:");
                        service.makeTransaction(cardSender,cardReceiver, Double.parseDouble(scanner.nextLine()),
                                scanner.nextLine().trim());

                    } catch (CardError e) {

                        System.out.println(e.getMessage());

                    } catch (AccountError e) {

                        System.out.println(e.getMessage());

                    } catch (TransactionError e) {

                        System.out.println(e.getMessage());
                    }
                }
                case 9 -> { //add bank
                    bankNeededData();
                    banks.add(new Bank(scanner.nextLine()));
                }
                case 10 -> { //exit
                    System.out.println("Ok...");
                    quit = true;
                }
                case 11 -> {// print employees form bank
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    service.getListEmployees(chosenBank).forEach(System.out::println);

                }
                case 12 -> {// add client
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    clientNeededData();
                    Client client = new Client(scanner.nextLine(), scanner.nextLine(),
                            LocalDate.parse(scanner.nextLine()), scanner.nextLine());
                    if (service.addClient(chosenBank, client) == 1) {
                        System.out.println("AlreadyExists Error");
                    }

                }
                case 13 -> { // print clients form bank
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    service.getListClients(chosenBank).forEach(System.out::println);

                }
                case 14 -> { // print all accounts from client
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    service.getAccounts(client).forEach(System.out::println);


                }
                case 15 -> { // list all cards from account
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(bank -> bank.toString()).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    Account account = service.getAccounts(client).get(chooseAAA(scanner,
                            service.getAccounts(client).stream().map(Object::toString).toList()));
                    service.getCards(account).forEach(System.out::println);
                }
                default -> {
                    System.out.println("Invalid command");
                }

            }
        }
        scanner.close();
    }
}
