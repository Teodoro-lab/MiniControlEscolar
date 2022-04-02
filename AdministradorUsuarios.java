import models.Usuario;
import exceptions.UsuarioInexistenteException;

public class AdministradorUsuarios {
    private Usuario[] usuarios;

    public AdministradorUsuarios(String[][] usuarios){
        this.usuarios = crearArrayUsuarios(usuarios);
    }

    public boolean autenticarUsuario(String usuario, String contrasena) throws UsuarioInexistenteException{
        Usuario currentUsuario;
        Usuario usuarioToValidate = new Usuario(usuario, contrasena);

        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.equals(usuarioToValidate)){
                return true;
            }
        }
        return false;
    }

    public void registrarLoginIncorrecto(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario)){
                currentUsuario.disminuirOportunidadesLogin();
                return;
            }
        }
    }

    public boolean esUsuarioBloqueado(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario)){
                return currentUsuario.isBlocked();
            }
        }
        return false;
    }

    public boolean existe(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario)){
                return currentUsuario.isBlocked();
            }
        }
        return false;
    }

    public void convertirUsuarioAInfo(){
        System.out.println("holi");
    }

    private Usuario[] crearArrayUsuarios(String[][] usuariosInfo){
        Usuario[] usuarios = new Usuario[usuariosInfo.length];

        String[] usuarioInfo;
        String usuarioName, usuarioPassword;
        for (int i = 0; i < usuarios.length; i++){
            usuarioInfo = usuariosInfo[i];
            usuarioName = usuarioInfo[0];
            usuarioPassword = usuarioInfo[1];

            usuarios[i] = new Usuario(usuarioName, usuarioPassword);
        }

        return usuarios;
    }
}