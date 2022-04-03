package models;

import utils.EncriptadorAES;
import java.util.Date;
import java.util.Calendar;

public class Usuario{
    private static int numOportunidadesPorDefecto = 3;
    private static int tiempoBloqueadoPorDefecto = 600000;

    private String username;
    private String password;
    private Date fechaBloqueado;
    private int numOportunidadesLogin = numOportunidadesPorDefecto;


    public Usuario(String username, String password, String fechaBloqueado, String oportunidadesLogin) {
        this.username = username;
        this.password = password;
        //setPassword(password);

        try{
            this.numOportunidadesLogin = Integer.parseInt(oportunidadesLogin);
        } catch (NumberFormatException e){
            this.numOportunidadesLogin = 0;
        }

        if(fechaBloqueado.equals("") || fechaBloqueado.equals("null"))
            this.fechaBloqueado = null;
        else
            this.fechaBloqueado = new Date(fechaBloqueado);
    }

    public int disminuirOportunidadesLogin(){
        return --numOportunidadesLogin;
    }

    public void restaurarOportunidadesLogin(){
        numOportunidadesLogin = numOportunidadesPorDefecto;
    }

    public boolean isBlocked(){
        if(numOportunidadesLogin <= 0 && getTiempoBloqueado() >= tiempoBloqueadoPorDefecto){
            restaurarOportunidadesLogin();
            return false;     
        } else if(numOportunidadesLogin > 0){
            return false;
        }
        return true;
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

    public void setFechaBloqueado() {
        this.fechaBloqueado = Calendar.getInstance().getTime();
    }

    public long getTiempoBloqueado(){
        Date now = Calendar.getInstance().getTime();
        try{
            return now.getTime() - fechaBloqueado.getTime();
        } catch (NullPointerException e){
            return 0;
        }
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
        return username + "," + password + "," + fechaBloqueado + "," + numOportunidadesLogin;
    }

    
}