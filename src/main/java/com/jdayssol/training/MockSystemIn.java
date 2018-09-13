package com.jdayssol.training;

import java.util.Scanner;

public class MockSystemIn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(scanner.next());
        scanner.close();
    }
}