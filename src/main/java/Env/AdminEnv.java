package Env;

import java.util.Scanner;

/**
 * Contem a interface e chamadas de metodo especificas para contas de admins
 * 
 * @author deiv
 * @param <AdminRegisterComponent> instancia de administrador
 */
public class AdminEnv <AdminRegisterComponent> {
    
    //contem a conta de admin logada
    private final AdminRegisterComponent ADMIN;
    
    Scanner adminScanner;
    
    //construtor
    public AdminEnv(AdminRegisterComponent admin) {
        this.ADMIN = admin;
        main(adminScanner = new Scanner(System.in));
    }
    
    //interface de admin
    public static void main(Scanner... args) {
        System.out.println("""
                           Hello Admin!
                           """);
    }
}
