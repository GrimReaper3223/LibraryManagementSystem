
package com.systemgen.librarysystem.librarysystem2;

import DataComponents.AdminRegisterComponent;
import DataComponents.Box;
import DataComponents.UserRegisterComponent;
import Env.AdminEnv;
import Env.UserEnv;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author deiv
 */
public class ScannerLogin implements Authenticate, DataValidationAndFormatting {
    
    //declara uma variavel de referencia para scanner.
    //a variavel e estatica para que o objeto scanner seja inutilizado no 
    //bloco catch da classe main, para evitar o StackOverFlowException
    static Scanner loginScanner;
    
    //construtor
    public ScannerLogin() {
        
    }
    
    //metodo de login
    public void newLogin() throws InputMismatchException, IllegalArgumentException {
        //cria uma instancia de Scanner
        loginScanner = new Scanner(System.in);
        
        //email
        System.out.print("\nE-mail: ");
        String email = loginScanner.nextLine();
        validateEmail(email);
        
        //senha
        System.out.print("Password: ");
        String password = loginScanner.nextLine();
        if(!pass_predicate.test(password)) {
            throw new IllegalArgumentException("\nError: Password cannot be empty/null or outside the size standard\n");
        }
        
        //o metodo authenticateHelper retorna uma instancia de box contendo o usuario dentro da caixa.
        //o metodo authenticateHelper chama o metodo authenticate na expressao de retorno
        Box<?> objectBox = authenticateHelper(email, password);
        
        //verifica qual instancia de usuario foi retornada e faz login no sistema
        switch(objectBox.getObject()) {
            case UserRegisterComponent user -> {
                System.out.println("\n***Logged as user! Redirecting...***\n");
                UserEnv userEnvironment = new UserEnv(user);
                break;
            }
            
            case AdminRegisterComponent admin -> {
                System.out.println("\n***Logged as superuser! Redirecting...***\n");
                AdminEnv adminEnvironment = new AdminEnv(admin);
                break;
            }
            //caso haja algum comportamento anormal, encerrar imediatamente a execucao do sistema
            default -> System.exit(0);
        }
    }
}
