package com.systemgen.librarysystem.librarysystem2;

import DataComponents.AdminRegisterComponent;
import DataComponents.Box;
import DataComponents.UserRegisterComponent;
import Env.AdminEnv;
import Env.UserEnv;
import java.io.Console;

/**
 *
 * @author deiv
 */
public class Login implements Authenticate, DataValidationAndFormatting {

    //metodo de login
    public void secureLogin() {
        //obtem a instancia de um console
        //retorna null se nao existir um console
        Console console = System.console();
        
        if(console == null) {
            System.err.println("\n!!!Error: Console does not exist!!!\n");
            System.exit(1);
        }
        
        String email = console.readLine("%n%s", "E-mail: ");
        validateEmail(email);
        char[] password = console.readPassword("%s", "Password:");
        
        if(email != null && password != null) {
            Box<?> objectBox = authenticate(email, password);
            
            java.util.Arrays.fill(password, ' ');
            
            switch (objectBox.getObject()) {
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
                default ->  System.exit(0);
            }
        }
    }
}
