
import model.account.Account;
import model.Bank;
import model.card.Card;
import model.card.CreditCard;
import model.card.DebitCard;
import model.Client;
import model.Employee;
import exceptions.AccountError;
import exceptions.CardError;
import exceptions.TransactionError;

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
    private final static Service service = Service.getInstance();
    private final static LogCSV logCSV = LogCSV.getInstance();


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
                    int bankIndex = chooseAAA(scanner, banks.stream().map(Bank::toString).toList());
                    Bank chosenBank = banks.get(bankIndex);
                    employeeNeededData();
                    Employee emp = service.makeEmployee(scanner.nextLine(), scanner.nextLine(),
                            LocalDate.parse(scanner.nextLine()), scanner.nextLine(), scanner.nextLine(),
                            Integer.parseInt(scanner.nextLine()),bankIndex);
                    logCSV.log("hired a new employee");
                    service.hireEmployee(chosenBank, emp);
                }
                case 2 -> { // fire someone
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    Employee toBeFired = service.getListEmployees(chosenBank).get(chooseAAA(scanner,
                            service.getListEmployees(chosenBank).stream().map(Employee::toString).toList()));
                    logCSV.log("hired an employee");
                    service.fireEmployee(chosenBank, toBeFired);
                }
                case 3 -> { //add new account
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    int accountType = chooseAAA(scanner, Arrays.asList("Checking", "Savings"));
                    if (accountType == 1) {
                        service.addAccount(client, service.makeSavingsAccount(client));
                    } else
                        service.addAccount(client, service.makeCheckingAccount(client));
                    logCSV.log("added an account");
                    System.out.println("done");
                }
                case 4 -> {// Add Card
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    Account account = service.getAccounts(client).get(chooseAAA(scanner,
                            service.getAccounts(client).stream().map(Object::toString).toList()));
                    int cardType = chooseAAA(scanner, Arrays.asList("Debit", "Credit"));
                    if (cardType == 1) {
                        service.addCard(account, service.makeCreditCard(account));
                    } else
                        service.addCard(account, service.makeDebitCard(account));
                    logCSV.log("added a card");

                    System.out.println("done");

                }
                case 5 -> { // Check balance
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
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
                    logCSV.log("someone viewed their balence");

                }
                case 7 -> { // make transaction
                    System.out.println("I need two cards.");
                    System.out.println("Sender:");
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBankSender = banks.get(chooseAAA(scanner,
                            banks.stream().map(Bank::toString).toList()));
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
                            banks.stream().map(Bank::toString).toList()));
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
                        logCSV.log("a transaction has been made");


                    } catch (CardError e) {

                        System.out.println(e.getMessage());

                    } catch (AccountError e) {

                        System.out.println(e.getMessage());

                    } catch (TransactionError e) {

                        System.out.println(e.getMessage());
                    }

                }
                case 9 -> { //add new bank
                    bankNeededData();
                    banks.add(service.makeBank(scanner.nextLine()));
                    logCSV.log("added a new bank");

                }
                case 10 -> { //exit
                    System.out.println("Ok...");
                    quit = true;
                }
                case 11 -> {// print employees form bank
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    service.getListEmployees(chosenBank).forEach(System.out::println);


                }
                case 12 -> {// add new client
                    if (banks.isEmpty()) {
                        break;
                    }
                    int bankIndex = chooseAAA(scanner, banks.stream().map(Bank::toString).toList());
                    Bank chosenBank = banks.get(bankIndex);
                    clientNeededData();
                    Client client = service.makeClient(scanner.nextLine(), scanner.nextLine(),
                            LocalDate.parse(scanner.nextLine()), scanner.nextLine(), bankIndex);
                    if (service.addClient(chosenBank, client) == 1) {
                        System.out.println("AlreadyExists Error");
                    }
                    logCSV.log("added a new client");

                }
                case 13 -> { // print clients form model.bank
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    service.getListClients(chosenBank).forEach(System.out::println);

                }
                case 14 -> { // print all accounts from model.client
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    service.getAccounts(client).forEach(System.out::println);


                }
                case 15 -> { // list all cards from model.account
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    Account account = service.getAccounts(client).get(chooseAAA(scanner,
                            service.getAccounts(client).stream().map(Object::toString).toList()));
                    service.getCards(account).forEach(System.out::println);
                }
                case 16 ->{ // update email
                    if (banks.isEmpty()) {
                        break;
                    }
                    Bank chosenBank = banks.get(chooseAAA(scanner, banks.stream().map(Bank::toString).toList()));
                    if (service.getListClients(chosenBank).isEmpty()) {
                        break;
                    }
                    Client client = service.getListClients(chosenBank).get(chooseAAA(scanner,
                            service.getListClients(chosenBank).stream().map(Object::toString).toList()));
                    System.out.println("give Email");
                    service.updateClientEmail(client,scanner.nextLine());
                    logCSV.log("an email has been updated");

                }
                default -> {
                    System.out.println("Invalid command");
                }

            }
        }
        scanner.close();
    }
}
