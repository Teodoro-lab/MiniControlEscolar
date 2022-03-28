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
            if (currentUsuario.equals(usuarioToValidate)){}
                return true;
            }
        }

        return false;
    }

    public void registrarLoginIncorrecto(String usuario){
        public boolean existe(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario))){
                currentUsuario.disminuirOportunidadesLogin();
                return;
            }
        }
    }
    }

    public boolean esUsuarioBloqueado(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario))){
                return currentUsuario.isBlocked();
            }
        }
    }

    public boolean existe(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario))){
                return currentUsuario.isBlocked();
            }
        }
    }

    public convertirUsuarioAInfo(){
        
    }

    private Usuario[] crearArrayUsuarios(String[][] usuariosInfo){
        usuarios = new Usuario[usuariosInfo.length];

        for (int i = 0; i < usuarios.length; i++){
            usuarioInfo = usuariosInfo[i];
            usuarioName = usuarioInfo[0];
            usuarioPassword = usuarioInfo[1];

            usuarios[i] = new Usuario(usuarioName, usuarioPassword);
        }
    }

}