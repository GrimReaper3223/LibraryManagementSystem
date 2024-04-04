package DataComponents;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de armazenamento de dados
 *
 * @author deiv
 */
public class DataStorage {

    //limite maximo de objetos registrados
    static final int MAX_USERS_LIMIT = 100;     //100 usuarios;
    static final int MAX_ADMINS_LIMIT = 3;     //100 usuarios;
    static final int MAX_BOOKS_LIMIT = 500;     //500 objetos de livros;

    //armazena objetos registrados
    public static List<UserRegisterComponent> user = new ArrayList<>(MAX_USERS_LIMIT);
    public static List<BookRegisterComponent> book = new ArrayList<>(MAX_BOOKS_LIMIT);
    
    public static List<AdminRegisterComponent> admin = new ArrayList<>(MAX_ADMINS_LIMIT);
};
