import Account.CheckingAccount;
import Account.SavingsAccount;
import Bank.Bank;
import Card.CreditCard;
import Card.DebitCard;
import Client.Client;
import Exceptions.AccountError;
import Exceptions.AlreadyExists;
import Exceptions.CardError;
import Exceptions.TransactionError;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Bank aia = new Bank("Cogealac");
        Client lefter = new Client("lefter", "adlsk", LocalDate.of(2002, 4, 16), "sdfsdf@dff.com");
        Client adela = new Client("adela", "sadasd", LocalDate.of(2002, 6, 25), "sdfsdf@dff.com");
        try {
            aia.addClient(lefter);
            CheckingAccount leftAcc = new CheckingAccount(lefter);
            lefter.addAccount(leftAcc);
            DebitCard lefterCard = new DebitCard(leftAcc);

            aia.addClient(adela);
            SavingsAccount adelAcc = new SavingsAccount(adela);
            adela.addAccount(adelAcc);
            CreditCard adelaCard = new CreditCard(adelAcc);

            adelaCard.makeTransaction(lefterCard, 1000,"");

            System.out.println(adelaCard.getMoneySpent());
            System.out.println(lefterCard.getBalance());
            System.out.println(adelaCard.getTransactions());

        } catch (AlreadyExists e) {
            System.out.println(e.getMessage());
        }
        catch (CardError e){
            System.out.println(e.getMessage());
        }
        catch (AccountError e){
            System.out.println(e.getMessage());
        }
        catch (TransactionError e){
            System.out.println(e.getMessage());
        }


    }

}
