import exceptions.UsuarioInexistenteException;

class App {
    
    public static void main(String[] args) {
        Sistema app = new Sistema();

        while (true){
            try {
                app.ingresarUsuarioContra();
            } catch (UsuarioInexistenteException e) {
                e.printStackTrace();
            }
        }
        
    }
}