package DataComponents;

import com.systemgen.librarysystem.librarysystem2.Authenticate;

/**
 * Registra um usuario no sistema
 * O tipo de registro e um record.
 * 
 * Este record contem todas as informacoes de um usuario
 * 
 * @author deiv
 */
public record UserRegisterComponent(String fullName, String address, long phone, String email, String password, Long uid) 
        implements Authenticate {
    
    public static Long userSeed = 100L;        //max = 1100L - 100 usuarios
}
