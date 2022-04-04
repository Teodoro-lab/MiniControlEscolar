package models;

import utils.EncriptadorAES;

public class Usuario{
    private static int numOportunidadesPorDefecto = 3;

    private String username;
    private String password;
    private int numOportunidadesLogin = numOportunidadesPorDefecto;

    public Usuario(String username, String password, String oportunidadesLogin) {
        this.username = username;
        this.password = password;
        //setPassword(password);

        try{
            this.numOportunidadesLogin = Integer.parseInt(oportunidadesLogin);
        } catch (NumberFormatException e){
            this.numOportunidadesLogin = 0;
        }
    }

    public int disminuirOportunidadesLogin(){
        return --numOportunidadesLogin;
    }

    public boolean isBlocked(){
        if(numOportunidadesLogin <= 0 ){
            return true;     
        } 
        return false;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return username + "," + password + "," + numOportunidadesLogin;
    }

    
}