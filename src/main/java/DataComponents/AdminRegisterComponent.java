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
public record AdminRegisterComponent(String fullName, String address, Long phone, String email, String password, Long uid, Roles role) 
        implements Authenticate {
    
    public static Long adminSeed = 10L;        //max = 30 - 3 admins
}
