package com.systemgen.librarysystem.librarysystem2;

import DataComponents.AdminRegisterComponent;
import DataComponents.DataStorage;
import DataComponents.Roles;
import DataComponents.UserRegisterComponent;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Registra um usuario caso a instancia de System.console estiver indisponivel
 * 
 * @author deiv
 */
public class ScannerRegister extends Register {
    
    //armazena a senha de usuario
    private String password;
    static Scanner regScanner;
    
    public ScannerRegister(boolean isSystemRegister) {
        super(isSystemRegister);
    }
    
    @Override
    public void newRegister() {
        try {
            regScanner = new Scanner(System.in);
            
            //nome
            System.out.print("Name: ");
            name = regScanner.next();
            regScanner.nextLine();

            //sobrenome
            System.out.print("Surname: ");
            surname = regScanner.next();
            fullName = formatAndValidateName(name, surname);
            regScanner.nextLine();

            //endereco
            System.out.print("Address ('null', '0', or '.' if there is none): ");
            address = regScanner.next();
            if(!def_predicate.test(address) 
                    || address.contains("null") 
                    || address.contains("0") 
                    || address.contains(".")) {
                address = null;
            }
            regScanner.nextLine();

            //telefone --- padrao brasileiro (XX) XXXXX-XXXX
            System.out.print("Phone (Only numbers): ");
            phone = regScanner.nextLong();
            stringPhone = formatAndValidatePhoneNumber(phone);
            regScanner.nextLine();
            
            System.out.print("E-mail: ");
            email = regScanner.nextLine();
            validateEmail(email);

            System.out.print("Password: ");
            password = regScanner.nextLine();
            if(!pass_predicate.test(password)) {
                throw new IllegalArgumentException("\nError: Password cannot be empty/null or outside the size standard\n");
            }

            System.out.println("\n---------------------------------------------------------------------------------");
            System.out.println("is Data Correct?(Y/n): \n");
            System.out.format("Name: %s%nAddress: %s%nPhone: %s%nEmail: %s%n", fullName, address, stringPhone, email);
            System.out.println("---------------------------------------------------------------------------------");
            System.out.print(">>");
            
            String choice = regScanner.next();

            switch(choice.toLowerCase()) {
                case "y", "yes": 
                    if(!isSystemRegister) {
                        DataStorage.user.add(new UserRegisterComponent(fullName, address, phone, email, password, generateID.id(UserRegisterComponent.userSeed), Roles.USER));
                        System.out.println("\n***User " + fullName + " registered successfully!***\n");
                        break;

                    } else {
                        DataStorage.admin.add(new AdminRegisterComponent(fullName, address, phone, email, password, generateAdminID.id(AdminRegisterComponent.adminSeed), Roles.ADMIN));
                        System.out.println("\n***Admin " + fullName + " registered successfully!***\n");
                        break;
                    }

                case "n", "no":
                    System.out.println("\nRestarting register process...\n");
                    newRegister();

                default: 
                    System.err.println("\nInvalid Option\n");
            }
            
        } catch(InputMismatchException | IllegalArgumentException e) {
            //lanca a excessao capturada do Scanner para um bloco de tratamento superior
            throw e;
        }
    }
}
