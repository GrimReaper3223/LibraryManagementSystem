package com.systemgen.librarysystem.librarysystem2;

import DataComponents.AdminRegisterComponent;
import DataComponents.Box;
import DataComponents.UserRegisterComponent;
import Env.AdminEnv;
import Env.UserEnv;
import java.io.Console;

/**
 * Classe de login. Esta classe nao pode ser subclassificada
 *
 * @author deiv
 */
public class Login implements Authenticate, DataValidationAndFormatting {

    public Login() {
        
    }
    
    //metodo de login
    public void secureLogin() {
        //obtem a instancia de um console
        //retorna null se nao existir um console
        Console console = System.console();
        
        if(console == null) {
            System.err.println("\n!!!Error: Console does not exist!!!\n");
            System.out.println("Redirecting to \"ScannerLogin\" registry instance...");
            
            ScannerLogin loginScanner = new ScannerLogin();
            loginScanner.newLogin();
            return;
        }
        
        //recebe e valida o email para login
        String email = console.readLine("%n%s", "E-mail: ");
        validateEmail(email);
        
        //recebe e valida a senha para login
        char[] password = console.readPassword("%s", "Password:");
        formatpassword(password);
        
        //caso email/senha sejam validos, autenticar email e senha.
        //se o usuario existir, uma instancia de box com o tipo da conta deve ser retornado
        Box<?> objectBox = authenticate(email, password);

        //apos isso, e efetuado uma limpeza na array de senhas para garantir a seguranca da senha
        java.util.Arrays.fill(password, ' ');
        
        //a objectBox desempacota o objeto recebido, chamando o metodo getObject()
        //e assim e possivel analisar qual o padrao do objeto para redirecionar a instancia correta do ambiente
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
            //caso exista alguma anomalia, o sistema encerra a execucao com o codigo 0
            default ->  System.exit(0);
        }
    }
}
