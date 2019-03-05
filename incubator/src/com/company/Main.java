package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();

        String request;

        Scanner input = new Scanner(System.in);
        System.out.println("Input a request");

        while (!(request = input.next()).equals("exit")) {
            System.out.println(dispatcher.handle(request));
        }
    }
}
