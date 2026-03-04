package com.example;

import main.java.com.example.BankTransfer;
import main.java.com.example.CreditCard;
import main.java.com.example.PayPal;
import main.java.com.example.Payment;

public class Main {
    public static void main(String[] args) {
        String result = "MONDAY";

        switch(day) {
            case "MONDAY":
                result = "Start of week";
                break;
            case "FRIDAY":
                result = "Almost weekend";
                break;
            default:
                result = "Regular day";
        }

        String result2 = switch(day) {
            case "MONDAY" -> "Start of week";
            case "FRIDAY" -> "Almost weekend";
            default -> "Regular day";
        };

        String type = switch(day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Weekday";
        };

        int discount = switch(customerType) {
            case "VIP" -> {
                System.out.println("VIP Customer");
                yield 20;
            }
            case "REGULAR" -> 10;
            default -> 0;
        };
    }
}