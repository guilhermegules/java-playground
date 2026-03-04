package main.java.com.example;

public sealed interface Payment permits CreditCard, PayPal, BankTransfer {
    
}
