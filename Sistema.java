import models.Usuario;

public class Sistema{
    private AdministradorUsuarios admnistradorUsuarios; 
    private ManagerArchivo managerArchivo = new ManagerArchivo();
    
    private final String rutaArchivoUsuarios = "./";
    private final String nombreArchivoUsuarios = "usuarios.txt"

    public Sistema() {
        boolean esArchivoCorrecto = verificarArchivoUsuario;
        if (esArchivoCorrecto){
            cargarUsuarios();
            mostrarInterfazUsuario();
        } else {
            //Cambiar nombre para que sea correcto
            alertaArchivoUsuariosNoExiste();
        }

    }

    public boolean verificarArchivoUsuario(){
        existeArchivo = managerArchivo.verificarExistenciaArchivo(
            rutaArchivoUsuarios, nombreArchivoUsuarios
        );
        
        estructuraCorrecta = managerArchivo.validarEstructuraArchivo(
            rutaArchivoUsuarios, nombreArchivoUsuarios
        );
        
        return (existeArchivo && estructuraCorrecta);
    }
    
    public void ingresarUsuarioContra(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario");
        usuario = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        contrasena = sc.nextLine();

        boolean usuarioValido = verificarUsuarioContra(usuario, contrasena);

    }
    
    public boolean verificarUsuarioContra(String usuario, String contra){
        boolean existeUsuario = administradorUsuarios.existe(usuario);
          
        boolean usuarioBloqueado;
        if (existeUsuario){
            usuarioBloqueado = administradorUsuarios.esUsuarioBloqueado(usuario);
        } else{
            alertaUsuarioNoExiste();
            return false;
        }

        boolean autenticado;
        if (!usuarioBloqueado){
            autenticado = administradorUsuarios.autenticarUsuario(usuario, contrasena);
        } else{
            alertaUsuarioContraIncorrectos();
            return false;
        }

        return autenticado;
    }
    
    public void mostrarMenu(){
        System.out.println( 
                "-------------Menú-------------" +
                "0. Terminar programa" +
                "1. Capturar calificaciones" +
                "2. Generar archivo .csv" +
                "3. Generar archivo .pdf" +
                "Opcion. "
        );
    }
    
    private void mostrarInterfazUsuario(){

    }

    private void cargarUsuarios(){
        String separator = ",";
        String[][] usuariosInfo = managerArchivo.leerArchivoUsuarios(
            rutaArchivoUsuarios, 
            nombreArchivoUsuarios,
            User.getDeclaredFields().length,
            separator
            )

        administradorUsuarios = new AdministradorUsuarios(usuariosInfo);
    }
    
    private void alertaArchivoUsuariosNoExiste(){
        System.out.println("ArchivoUsuariosNoExiste");
    }
    
    private void alertaUsuarioContraIncorrectos(){
        System.out.println("UsuarioContraIncorrectos");
    }
    
    private void alertaUsuarioBloqueado(){
        System.out.println("UsuarioBloqueado");
    }

    public void alertaUsuarioNoExiste(){
        System.out.println("UsuarioNoExiste");
    }

}