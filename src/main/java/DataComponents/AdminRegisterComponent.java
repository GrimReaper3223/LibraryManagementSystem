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
public record AdminRegisterComponent(String fullName, String address, long phone, String email, String password, Long uid) 
        implements Authenticate {
    
    public static Long adminSeed = 10L;        //max = 30 - 3 admins
}
