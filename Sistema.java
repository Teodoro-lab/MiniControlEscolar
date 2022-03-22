public class Sistema{
    private final AdministradorUsuarios admnistradorUsuarios = new AdministradorUsuarios();|
    private final ManagerArchivo managerArchivo = new ManagerArchivo();

    public Sistema() {
        existeArchivo = managerArchivo.verificarExistenciaArchivo("./", "usuarios.txt");

        if (existeArchivo){
            estructuraCorrecta = managerArchivo.validarEstructuraArchivo();
        }

        if (estructuraCorrecta){
            cargarUsuarios();
            mostrarInterfazUsuario();
        } else {
            alertaArchivoUsuariosNoExiste();
        }

    }


    public boolean verificarArchivoUsuario(){

    }
    
    public void ingresarUsuarioContra(){
        
    }
    
    public boolean verificarUsuarioContra(String usuario, String contra){
        
    }
    
    public void mostrarMenu(){
        
    }
    
    private void mostrarInterfazUsuario(){

    }

    private void cargarUsuarios(){

    }
    
    private void alertaArchivoUsuariosNoExiste(){

    }
    
    private void alertaUsuarioContraIncorrectos(){

    }
    
    private void alertaUsuarioBloqueado(){

    }

    public void alertaUsuarioNoExiste(){
        
    }

}