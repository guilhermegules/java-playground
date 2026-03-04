package main.java.com.exceptions.study;

public class BankService {
    public void withdraw(double balance, double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal exceed balance.");
        }

        System.out.println("Withdrawal successful");
    }
}
