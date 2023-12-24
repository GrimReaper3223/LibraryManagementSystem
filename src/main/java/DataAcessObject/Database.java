
package DataAcessObject;

/**
 *
 * @author deiv
 */
public class Database {
    
    //Estruturas de dados estaticas (nao e necessario uma instancia da classe para alterar os registros)
    //A estrutura de dado 'USER' pode armazenar 1000 objetos de registro de usuarios
    //A estrutura de dado 'BOOK' pode armazenar 2000 objetos de registro de livros
    static final UserRegister[] USER = new UserRegister[1000];
    static final BookRegister[] BOOK = new BookRegister[2000];
};
