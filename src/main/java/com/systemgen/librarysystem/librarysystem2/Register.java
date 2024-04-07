
package com.systemgen.librarysystem.librarysystem2;

import DataComponents.*;
import java.io.Console;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Classe responsavel por registrar um usuario/admin
 * 
 * @author deiv
 */
public class Register implements DataValidationAndFormatting {
    
    //esses valores serao inicializados na linha 46 ou 57
    protected Random random;
    protected Supplier<Long> randomSupplier;
    
    //variaveis de armazenamento final
    String fullName, address, email;
    Long phone;
    
    
    //variaveis de representacao para verificacao dos dados inseridos
    String name, surname, stringPhone;
    
    //essa variavel nao pode ser subclassificada
    private String secureVisiblePass;
    
    
    //se for true, entao um admin sera cadastrado. Se nao, um user sera cadastrado
    protected final boolean isSystemRegister;
    
    
    //construtor padrao
    public Register(boolean isSystemRegister) {
        this.isSystemRegister = isSystemRegister;
    }
    
    
    //gera um id para um usuario
    protected final GenerateSystemIdentifier<Long> generateID = new GenerateSystemIdentifier<>() {
        @Override
        public Long id(Long seed) {
            random = new Random(seed);
            randomSupplier = random::nextLong;
            UserRegisterComponent.userSeed += 10;
            return Math.abs(randomSupplier.get());
        }
    };
    
    //gera um id para um admin
    protected final GenerateSystemIdentifier<Long> generateAdminID = new GenerateSystemIdentifier<>() {
        @Override
        public Long id(Long seed) {
            random = new Random(seed);
            randomSupplier = random::nextLong;
            AdminRegisterComponent.adminSeed += 10;
            return Math.abs(randomSupplier.get());
        }
    };
    
    
    //metodo de registro
    //as declaracoes de lancamento de excessao referem-se ao objeto Scanner da instancia regScan de ScannerRegister
    //caso o metodo de registro por scanner falhar, este metodo declara que lanca a excessao ao metodo main
    public void newRegister() throws InputMismatchException, IllegalArgumentException {
        
        //cria uma instancia de console
        Console console = System.console();
        
        //se a instancia nao existir, redirecionar o registro para uma instancia 
        //que use um objeto Scanner ao inves de um console
        if(console == null) {
            System.err.println("\n!!!Error: Console does not exist!!!\n");
            System.out.println("Redirecting to \"ScannerRegister\" registry instance...\n");
            
            ScannerRegister regScan = new ScannerRegister(isSystemRegister);
            regScan.newRegister();
            return;
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
                    DataStorage.user.add(new UserRegisterComponent(fullName, address, phone, email, String.valueOf(charPassword), generateID.id(UserRegisterComponent.userSeed), Roles.USER));
                    Arrays.fill(charPassword, ' ');
                    System.out.println("\n***User " + fullName + " registered successfully!***\n");
                    break;
                    
                } else {
                    DataStorage.admin.add(new AdminRegisterComponent(fullName, address, phone, email, String.valueOf(charPassword), generateAdminID.id(AdminRegisterComponent.adminSeed), Roles.ADMIN));
                    Arrays.fill(charPassword, ' ');
                    System.out.println("\n***Admin " + fullName + " registered successfully!***\n");
                    break;
                }

            case "n", "no": 
                System.out.println("\nRestarting register process...\n");
                Arrays.fill(charPassword, ' ');
                newRegister();

            default: 
                System.err.println("\nInvalid Option\n");
        }
    }
}
