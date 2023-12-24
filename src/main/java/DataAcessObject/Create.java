
package DataAcessObject;

import static DataAcessObject.Database.BOOK;
import static DataAcessObject.Database.USER;

/**
 *
 * @author deiv
 */
public class Create {
    
    //cria e envia o objeto do usuario para a array USER
    public static void pushUserRegisterToArray(String name, String email, String address, int phone) {
       
        if(UserRegister.verifyIfHasSpaceToRegisterUser() && USER[UserRegister.getQuantityOfUserInstances()] != null) {
            System.err.println(String.format("Erro: Nao ha espaco livre para armazenar novos usuarios. O armazenamento atigiu seu limite: %d elementos (%d indices);", Database.USER.length, Database.USER.length - 1 ));
            return;
        } 
       
        if(USER[UserRegister.getQuantityOfUserInstances()] == null) {
            USER[UserRegister.getQuantityOfUserInstances()] = new UserRegister(name, email, address, phone);
        }
    };
    
    //cria e envia o objeto do livro para a array BOOK
    public static void pushBookRegisterToArray(String title, String author, String genre, int publishYear, int length) {
        
        for(int i = 0; i < BOOK.length; i++) {
            if(BOOK[i] == null) {
                BOOK[i] = new BookRegister(title, author, genre, publishYear, length);
                break;
            } else if(BOOK[i] != null && i == 1999) {
                System.err.print("Erro: Nao ha espaco livre para armazenar novos livros. O armazenamento atigiu seu limite (" +BOOK[i]+");");
                break;
            }
        }
    };
};