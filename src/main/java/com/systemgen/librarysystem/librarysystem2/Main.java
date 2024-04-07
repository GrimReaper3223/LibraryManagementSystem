package com.systemgen.librarysystem.librarysystem2;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Contem a interface inicial do aplicativo
 *
 * @author deiv
 */
public class Main {
    
    public static void main(String... args) {
        Scanner scanner;
        int option;
        
        try {
            do {
                scanner = new Scanner(System.in);
                System.out.print("""
                                   Library System Manager - v2.2.0

                                   Select an option:

                                   1 - Login
                                   2 - Register
                                   3 - Exit

                                   >> """);

                option = Integer.parseInt(scanner.nextLine());

                switch(option) {
                    case 1:
                        Login loginInstance = new Login();
                        loginInstance.secureLogin();
                        main();
                        break;

                    case 2: 
                        Register registerInstance = new Register(false);    //default - false
                        registerInstance.newRegister();
                        main();
                        break;

                    case 3: 
                        System.out.println("\nExiting...\n");
                        scanner.close();
                        break;

                    default: 
                        System.err.println("\nInvalid option\n");
                }
                
            } while(option < 1 || option > 3);
            
            //inutiliza a instancia do scanner atual com o valor null, independente da excessao lancada
        } catch(InputMismatchException ime) {
            ScannerRegister regScanner = null;
            ScannerLogin loginScanner = null;
            main();
            
        } catch(NoSuchElementException | IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
            main();
        }
    }
};
