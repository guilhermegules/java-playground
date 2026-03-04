package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    static String process(Payment payment) {
        return switch (payment) {
            case CreditCard c -> "Processing credit card";
            case PayPal p -> "Processing PayPal";
            case BankTransfer b -> "Processing bank transfer";
        };
    }
}