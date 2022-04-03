import models.Usuario;
import exceptions.UsuarioInexistenteException;

public class AdministradorUsuarios {
    private Usuario[] usuarios;

    public AdministradorUsuarios(String[][] usuarios){
        this.usuarios = crearArrayUsuarios(usuarios);
    }

    public boolean autenticarUsuario(String usuario, String contrasena) throws UsuarioInexistenteException{
        Usuario currentUsuario;
        Usuario usuarioToValidate = new Usuario(usuario, contrasena, "", "0");
        usuarioToValidate.setPassword(contrasena);

        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.equals(usuarioToValidate)){
                return true;
            }
        }
        return false;
    }

    public void registrarLoginIncorrecto(String usuarioNombre){
        Usuario usuario = getUsuario(encontrarUsuario(usuarioNombre));

        int oportunidadesRestantes = usuario.disminuirOportunidadesLogin();

        if(oportunidadesRestantes <= 0){
            usuario.setFechaBloqueado();
        }
    }

    public boolean esUsuarioBloqueado(String usuario){
        int usuarioIndex = encontrarUsuario(usuario);
        Usuario currentUsuario = getUsuario(usuarioIndex); 
        return currentUsuario.isBlocked();
    }

    public boolean existe(String usuario){
        Usuario currentUsuario;
        for(int i = 0; i < usuarios.length; i++){
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario)){
                return true;
            }
        }
        return false;
    }


    private Usuario[] crearArrayUsuarios(String[][] usuariosInfo){
        Usuario[] usuarios = new Usuario[usuariosInfo.length];

        String[] usuarioInfo;
        String usuarioName, usuarioPassword, usuarioFechaBloqueado,usuarioOportunidadesLogin;
        for (int i = 0; i < usuarios.length; i++){
            usuarioInfo = usuariosInfo[i];

            usuarioName = usuarioInfo[0];
            usuarioPassword = usuarioInfo[1];
            usuarioFechaBloqueado = usuarioInfo[2];
            usuarioOportunidadesLogin = usuarioInfo[3];

            usuarios[i] = new Usuario(usuarioName, usuarioPassword, usuarioFechaBloqueado, usuarioOportunidadesLogin);
        }

        return usuarios;
    }

    public int encontrarUsuario(String usuario){
        Usuario currentUsuario = null;

        for (int i = 0; i < usuarios.length; i++) {
            currentUsuario = usuarios[i];
            if (currentUsuario.getUsername().equals(usuario)) {
                return i;
            }
        }

        return -1;
    }

    public Usuario getUsuario(int index){
        return usuarios[index];
    }
}