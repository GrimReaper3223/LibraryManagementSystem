
package com.systemgen.librarysystem.librarysystem2;

import DataComponents.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author deiv
 */
public class Register {
    
    Scanner scanner = Main.scanner;
    Random random;
    
    private String fullName, address, email, password;
    private Long phone;
    
    //se for true, entao um admin sera cadastrado. Se nao, um user sera cadastrado
    private final boolean isSystemRegister;   
    
    //construtor padrao
    public Register(boolean isSystemRegister) {
        this.isSystemRegister = isSystemRegister;
    }
    
    //gera um id para um usuario
    private final GenerateSystemIdentifier<Long> generateID = seed -> {
        random = new Random(seed);
        UserRegisterComponent.userSeed += 10;
        return Math.abs(random.nextLong());
    };
    
    //gera um id para um admin
    private final GenerateSystemIdentifier<Long> generateAdminID = intSeed -> {
        random = new Random(intSeed);
        AdminRegisterComponent.adminSeed += 10;
        return Math.abs(random.nextLong());
    };
    
    //metodo de registro
    public void newRegister() throws InputMismatchException, IOException {
        
        System.out.print("\nName: ");
        fullName = scanner.next();
        scanner.nextLine();
        
        System.out.print("Address: ");
        address = scanner.nextLine();
        
        System.out.print("Phone: ");
        phone = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Email: ");
        email = scanner.next();
        scanner.nextLine();
        
        System.out.print("Password: ");
        password = scanner.next();
        scanner.nextLine();
        
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.println("Data is correct?: \n");
        System.out.format("Name: %s%nAddress: %s%nPhone: %s%nEmail: %s%nPassword: %s%n", fullName, address, phone, email, password);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print(">> ");
        
        String choice = scanner.next();
        scanner.nextLine();
        
        switch(choice.toLowerCase()) {
            case "y", "yes": 
                if(!isSystemRegister) {
                    DataStorage.user.add(new UserRegisterComponent(fullName, address, phone, email, password, generateID.id(UserRegisterComponent.userSeed)));
                    System.out.println("\n***User " + fullName + " registered successfully!***\n");
                    break;
                    
                } else {
                    DataStorage.admin.add(new AdminRegisterComponent(fullName, address, phone, email, password, generateAdminID.id(AdminRegisterComponent.adminSeed)));
                    System.out.println("\n***Admin " + fullName + " registered successfully!***\n");
                    break;
                }

            case "n", "no": 
                System.out.println("\nRestart register process...\n");
                newRegister();
                break;

            default: 
                System.err.println("\nInvalid Option\n");
                Main.main();
        }
    }
}
