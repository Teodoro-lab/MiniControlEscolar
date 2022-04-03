import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exceptions.UsuarioInexistenteException;
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
                //mostrarInterfazUsuario();
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
        int infoIndex = administradorUsuarios.encontrarUsuario(usuario);
        String infoUsuario = administradorUsuarios.getUsuario(infoIndex).toString();

        try {
            managerArchivo.updateLine(nombreArchivoUsuarios, rutaArchivoUsuarios, administradorUsuarios.encontrarUsuario(usuario), infoUsuario);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (usuarioValido){
            mostrarInterfazUsuario();
        }

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
            int userIndex = administradorUsuarios.encontrarUsuario(usuario);
            long restTime = administradorUsuarios.getUsuario(userIndex).getTiempoBloqueado();
            restTime = 10 - restTime/60000;
            alertaUsuarioBloqueado(restTime);
            return false;
        }

        if(!autenticado){
            administradorUsuarios.registrarLoginIncorrecto(usuario);
            alertaUsuarioContraIncorrectos();
        }

        return autenticado;
    }
    
    public void mostrarMenu(){
        System.out.println( 
                "-------------Menú-------------\n" +
                "0. Terminar programa\n" +
                "1. Capturar calificaciones\n" +
                "2. Generar archivo .csv\n" +
                "3. Generar archivo .pdf\n" +
                "Opcion: "
        );
        

    }
    
    private void mostrarInterfazUsuario(){
        while(true){
            mostrarMenu();
            Scanner sc = new Scanner(System.in);
            int response = sc.nextInt();
            sc.close();
            switch (response) {
                case 0:
                    return;
                case 1:
                    System.out.println("Capturando calificaciones");
                case 2:
                    System.out.println("Generando .csv");
                case 3:
                    System.out.println("Generando .pdf");
            }
        }
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
    
    private void alertaUsuarioBloqueado(long minutosRestantes){
        System.out.println("UsuarioBloqueado: " + minutosRestantes + " minutos restantes"); 
    }

    public void alertaUsuarioNoExiste(){
        System.out.println("UsuarioNoExiste");
    }

}