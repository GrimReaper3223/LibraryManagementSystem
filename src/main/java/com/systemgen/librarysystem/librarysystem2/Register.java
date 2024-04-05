
package com.systemgen.librarysystem.librarysystem2;

import DataComponents.*;
import java.io.Console;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * Classe responsavel por registrar um usuario/admin
 * 
 * TODO: refatorar essa classe para que ela registre via instancia de console
 * NOTE: ao fazer a refatoracao, apagar alguns caracteres da senha ao perguntar o 
 *       usuario se esta tudo certo
 * 
 * @author deiv
 */
public class Register implements DataValidationAndFormatting {
    
    Random random;
    
    //variaveis de armazenamento final
    private String fullName, address, email;
    private Long phone;
    
    
    //variaveis de representacao para verificacao dos dados inseridos
    private String name, surname, stringPhone, secureVisiblePass;
    
    
    //se for true, entao um admin sera cadastrado. Se nao, um user sera cadastrado
    private final boolean isSystemRegister;
    
    
    //construtor padrao
    public Register(boolean isSystemRegister) {
        this.isSystemRegister = isSystemRegister;
    }
    
    
    //gera um id para um usuario
    private final GenerateSystemIdentifier<Long> generateID = new GenerateSystemIdentifier<>() {
        @Override
        public Long id(Long seed) {
            random = new Random(seed);
            UserRegisterComponent.userSeed += 10;
            return Math.abs(random.nextLong());
        }
    };
    
    //gera um id para um admin
    private final GenerateSystemIdentifier<Long> generateAdminID = new GenerateSystemIdentifier<>() {
        @Override
        public Long id(Long seed) {
            random = new Random(seed);
            AdminRegisterComponent.adminSeed += 10;
            return Math.abs(random.nextLong());
        }
    };
    
    
    //metodo de registro
    public void newRegister() throws InputMismatchException, IOException {
        
        //cria uma instancia de console
        Console console = System.console();
        if(console == null) {
            System.err.println("\n!!!Error: Console does not exist!!!\n");
            System.exit(1);
        }
        
        //verifica o nome
        name = console.readLine("%n%s", "Name: ");
        surname = console.readLine("%s", "Surname: ");
        fullName = formatAndValidateName(name, surname);
        
        if(def_predicate.test(address = console.readLine("%s", "Address: "))) {
            address = null;
        }
        
        //verifica o numero de telefone
        phone = Long.valueOf(console.readLine("%s", "Phone: "));
        stringPhone = formatAndValidatePhoneNumber(phone);
        
        //valida o email
        email = console.readLine("%s", "E-mail: ");
        validateEmail(email);
        
        //atribui a senha para o campo password e em seguida formata a senha para visualizacao simples
        char[] charPassword = console.readPassword("%s", "Password (min. 6 - max. 32):");
        secureVisiblePass = formatpassword(charPassword);
        
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.println("is Data Correct?(Y/n): \n");
        System.out.format("Name: %s%nAddress: %s%nPhone: %s%nEmail: %s%nPassword: %s%n", fullName, address, stringPhone, email, secureVisiblePass);
        System.out.println("---------------------------------------------------------------------------------");
        
        String choice = console.readLine("%s", ">> ");
        
        switch(choice.toLowerCase()) {
            case "y", "yes": 
                if(!isSystemRegister) {
                    DataStorage.user.add(new UserRegisterComponent(fullName, address, phone, email, String.valueOf(charPassword), generateID.id(UserRegisterComponent.userSeed)));
                    System.out.println("\n***User " + fullName + " registered successfully!***\n");
                    break;
                    
                } else {
                    DataStorage.admin.add(new AdminRegisterComponent(fullName, address, phone, email, String.valueOf(charPassword), generateAdminID.id(AdminRegisterComponent.adminSeed)));
                    System.out.println("\n***Admin " + fullName + " registered successfully!***\n");
                    break;
                }

            case "n", "no": 
                System.out.println("\nRestarting register process...");
                newRegister();
                break;

            default: 
                System.err.println("\nInvalid Option\n");
                Main.main();
        }
    }
}
