import java.io.IOException;
import java.util.Scanner;

import exceptions.UsuarioInexistenteException;
import models.Usuario;

public class Sistema{
    private AdministradorUsuarios administradorUsuarios; 
    private ManagerArchivo managerArchivo = new ManagerArchivo();
    
    private final String rutaArchivoUsuarios = "./";
    private final String nombreArchivoUsuarios = "usuarios.txt";

    public Sistema() {
        boolean esArchivoCorrecto;
        try {
            esArchivoCorrecto = verificarArchivoUsuario();
            if (esArchivoCorrecto) {
                cargarUsuarios();
                mostrarInterfazUsuario();
            } else {
                // Cambiar nombre para que sea correcto
                alertaArchivoUsuariosNoExiste();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarArchivoUsuario() throws IOException{
        boolean existeArchivo = managerArchivo.verificarExistenciaArchivo(
            rutaArchivoUsuarios, nombreArchivoUsuarios
        );
        
        //WARNING: está hardcodeado -> arreglar if possible
        boolean estructuraCorrecta = managerArchivo.validarEstructuraArchivo(
            rutaArchivoUsuarios, nombreArchivoUsuarios, 4, ","
        );
        
        return (existeArchivo && estructuraCorrecta);
    }
    
    public void ingresarUsuarioContra() throws UsuarioInexistenteException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario");
        String usuario = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        String contrasena = sc.nextLine();

        boolean usuarioValido = verificarUsuarioContra(usuario, contrasena);

    }
    
    public boolean verificarUsuarioContra(String usuario, String contra) throws UsuarioInexistenteException{
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
            autenticado = administradorUsuarios.autenticarUsuario(usuario, contra);
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
        String[][] usuariosInfo;
        try {
            usuariosInfo = managerArchivo.leerArchivoUsuarios(
                rutaArchivoUsuarios, 
                nombreArchivoUsuarios,
                4,
                separator
            );
            administradorUsuarios = new AdministradorUsuarios(usuariosInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
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