package DataComponents;


/**
 * Empacota o objeto de usuario/admin autenticado
 * 
 * @author deiv
 * @param <T> o objeto registrado
 */
public class Box <T>  {
    
    //contem a instancia do usuario/admin autenticado
    T objectBox;
    
    //construtor que recebe a instancia autenticada do usuario
    public Box(T t) {
        this.objectBox = t;
    }
    
    //metodo para obter a instancia de um usuario
    public T getObject() {
        return objectBox;
    }
}
