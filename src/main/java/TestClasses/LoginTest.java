//package TestClasses;
//
//import com.systemgen.librarysystem.librarysystem2.Authenticate;
//import DataComponents.AdminRegisterComponent;
//import DataComponents.UserRegisterComponent;
//import java.io.Console;
//import java.io.IOError;
//import java.util.*;
//import java.util.function.Predicate;
//
///**
// *
// * @author deiv
// */
//public class LoginTest implements Authenticate {
//    
//    //verifica os dados sao vazios ou nulos
//    Predicate<String> isNull = Objects::isNull;
//    Predicate<String> isEmpty = String::isEmpty;
//    
//    //encadeia os predicados
//    Predicate<String> p = isNull.or(isEmpty);
//    
//    //construtor padrao
//    public LoginTest() {}
//    
//    //metodo de login
//    public void secureLogin() throws IOError, IllegalFormatException {
//        //instancia de console --- a aplicacao deve ser inicializaca pelo terminal
//        //ou o console retornara null;
//        Console console = System.console();
//        
//        //verifica se o console existe
//        if(console == null) {
//            System.err.println("Error: Console does not exist");
//            System.exit(1);
//        }
//        
//        //variaveis de armazenamento
//        String email = console.readLine("%s", "E-Mail: ");
//        char[] password = console.readPassword("%s", "Password:");
//        
//        //testa se as variaveis email/password contem dados inseridos
//        if(p.test(email) || Objects.isNull(password)) {
//            
//            //guarda o objeto (ou null caso nao exista)
//            //realizar o teste null nesta variavel;
//            authenticate(email, password);
//            
//            //limpa todos os caracteres na variavel de senha por ' '
//            java.util.Arrays.fill(password, ' ');
//            
//            //retorna o objeto
//            return registeredUser;
//            
//        } else {
//            System.err.println("Error: Give valid information. Try again");
//            secureLogin();
//        }
//        
//        //retorno sem eficiencia
//        return null;
//    }
//    
//    public static void main(String[] args) {
//        LoginTest login = new LoginTest();
//        
//        switch (login.secureLogin()) {
//            case UserRegisterComponent user -> {
//                user.fullName();
//                user.uid();
//            }
//            case AdminRegisterComponent admin -> {
//                admin.fullName();
//                admin.uid();
//            }
//            default -> {
//            }
//        }
//    }
//}
