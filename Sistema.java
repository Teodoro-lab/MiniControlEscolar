import java.io.IOException;
import java.util.Scanner;

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
    
    public boolean ingresarUsuarioContra(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario");
        String usuario = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        String contrasena = sc.nextLine();

        boolean usuarioValido = verificarUsuarioContra(usuario, contrasena);
        return usuarioValido;
    }
    
    public boolean verificarUsuarioContra(String usuario, String contra) {
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
            administradorUsuarios.registrarLoginIncorrecto(usuario, managerArchivo, nombreArchivoUsuarios, 
                    rutaArchivoUsuarios);
            alertaUsuarioContraIncorrectos();
        }

        return autenticado;
    }
    
    public void mostrarMenu(){
        String menu = ( 
                "-------------Menú-------------\n" +
                "0. Terminar programa\n" +
                "1. Capturar calificaciones\n" +
                "2. Generar archivo .csv\n" +
                "3. Generar archivo .pdf\n" +
                "Opcion: "
        );
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(menu);
            int response = sc.nextInt();
            sc.nextLine();
            switch (response) {
                case 0:
                    return;
                case 1:
                    System.out.println("Capturando calificaciones");
                    break;
                case 2:
                    System.out.println("Generando .csv");
                    break;
                case 3:
                    System.out.println("Generando .pdf");
                    break;
            }
        }
    }
    
    public void mostrarInterfazUsuario(){
        
        boolean usuarioValido;
        do{
            usuarioValido = ingresarUsuarioContra();
        } while (!usuarioValido);

        mostrarMenu();

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