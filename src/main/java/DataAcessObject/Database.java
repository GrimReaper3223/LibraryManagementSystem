
package DataAcessObject;

/**
 *
 * @author deiv
 */
public class Database {
    
    //Estruturas de dados estaticas (nao e necessario integrar isso a uma instancia) para registro de objetos no sistema.
    //A estrutura de dado 'user' pode armazenar 1000 objetos de registro de usuarios
    //A estrutura de dado 'book' pode armazenar 2000 objetos de registro de livros
    static UserRegister[] user = new UserRegister[1000];
    static BookRegister[] book = new BookRegister[2000];
};
