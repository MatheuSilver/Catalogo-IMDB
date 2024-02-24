package utils;

import java.util.Scanner;

public class ScannerSingleton {
    // Padrão Singleton

    private final Scanner scanner = new Scanner(System.in);
    private static final ScannerSingleton SINGLETON = new ScannerSingleton();

    private ScannerSingleton() {}

    public static ScannerSingleton instance() {
        return SINGLETON;
    }

    public void clean_scanner(){ this.scanner.nextLine(); }

    public Scanner getScanner() {
        return scanner;
    }
}
