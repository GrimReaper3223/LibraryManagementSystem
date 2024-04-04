package DataComponents;

/**
 * contem um metodo abstrato para geracao de seed
 * 
 * @author deiv
 * @param <T> qualquer subtipo de Number
 */
@FunctionalInterface
public interface GenerateSystemIdentifier<T extends Number> {
    
    //recebe uma seed para a geracao de um identificador do sistema
    //TODO: verificar a viabilidade de um Supplier
    T id(T seed);
}
