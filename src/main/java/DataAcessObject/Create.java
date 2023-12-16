
package DataAcessObject;

import static DataAcessObject.Database.book;
import static DataAcessObject.Database.user;

/**
 *
 * @author deiv
 */
public class Create {
    
    public Create() {};
    
    //cria e envia o objeto do usuario para a array user
    public void pushUserRegisterToArray(String name, String email, String address, int phone) {
        for(int i = 0; i < user.length; i++) {
            if(user[i] == null) {
                user[i] = new UserRegister(name, email, address, phone);
                break;
            } else if(user[i] != null && i == 999) {
                System.err.print("Erro: Nao ha espaco livre para armazenar novos usuarios. O armazenamento atigiu seu limite (" +user[i]+");");
                break;
            }
        }
    };
    
    //cria e envia o objeto do livro para a array book
    public void pushBookRegisterToArray(String title, String author, String genre, int publishYear, int length) {
        for(int i = 0; i < book.length; i++) {
            if(book[i] == null) {
                book[i] = new BookRegister(title, author, genre, publishYear, length);
                break;
            } else if(book[i] != null && i == 1999) {
                System.err.print("Erro: Nao ha espaco livre para armazenar novos livros. O armazenamento atigiu seu limite (" +book[i]+");");
                break;
            }
        }
    };
};