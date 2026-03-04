package com.exceptions.study;

import java.io.IOException;

import main.java.com.exceptions.study.BankService;
import main.java.com.exceptions.study.FileService;
import main.java.com.exceptions.study.InsufficientBalanceException;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        try {
            fileService.saveFile("Myfile.txt", "content");
        } catch (IOException exception) {
            System.out.println("Failed to save file" + exception.getMessage());
        }

        BankService bankService = new BankService();
        try {
            bankService.withdraw(1000, 1500);
        } catch (InsufficientBalanceException insufficientBalanceException) {
            System.out.println("Transaction failed:" + e.getMessage());
        }
    }
}