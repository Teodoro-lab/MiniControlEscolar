package models;

import utils.EncriptadorAES;

public class Usuario{
    private static int numOportunidadesPorDefecto = 3;

    private String username;
    private String password;
    private int numOportunidadesLogin = numOportunidadesPorDefecto;

    public Usuario(String username, String password){
        this.username = username;
        setPassword(password);
    }

    public void disminuirOportunidadesLogin(){
        numOportunidadesLogin--;
    }

    public void restaurarOportunidadesLogin(){
        numOportunidadesLogin = numOportunidadesPorDefecto;
    }

    public boolean isBlocked(){
        return (0 >= numOportunidadesLogin);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = EncriptadorAES.encrypt(password, EncriptadorAES.secret);
    }

}