package Env;

import java.util.Scanner;

/**
 * Contem a interface de usuario e as chamadas de metodo especificas de um usuario comum
 * 
 * @author deiv
 * @param <UserRegisterComponent> instancia de usuario
 */
public class UserEnv <UserRegisterComponent> {
    
    //contem a instancia do usuario atual logado
    final UserRegisterComponent USER;
    private static Scanner userScanner;
    
    //construtor
    public UserEnv(UserRegisterComponent user) {
        this.USER = user;
        main(userScanner = new Scanner(System.in));
    }
    
    //metodo main
    public static void main(Scanner... args) {
        System.out.println("""
                           Hello user!
                           """);
    }
}
