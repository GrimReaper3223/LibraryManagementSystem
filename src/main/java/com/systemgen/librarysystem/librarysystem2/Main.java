package com.systemgen.librarysystem.librarysystem2;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Contem a interface inicial do aplicativo
 *
 * @author deiv
 */
public class Main {

    static Scanner scanner;
    
    public static void main(String... args) {

        try {
            scanner = new Scanner(System.in);
            
            System.out.print("""
                               Library System Manager - v2.0
                               
                               Select an option:
                               
                               1 - Login
                               2 - Register
                               3 - Exit
                               
                               >> """);
            
            int option = scanner.nextInt();
            
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
                    main();
            }
            
        } catch(InputMismatchException | IOException e) {
            System.err.println(e.getMessage());
            main();
            
            
        }catch(NoSuchElementException nsee) {
            System.err.println(nsee.getMessage());
            main();
        }
    }
};
