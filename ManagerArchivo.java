import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ManagerArchivo{
    
    public boolean validarEstructuraArchivo(String path, String name, int elementos, String separador) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path + name));
        String st;

        while ((st = br.readLine()) != null){
            String[] tokens = st.split(separador);
            if (tokens.length != elementos){
                return false;
            }
        }

        return true;
    }

    public boolean verificarExistenciaArchivo(String path, String name){
       File file = new File(path + name); 
       if (file.exists()){
           return true;
       }
       return false;
    }

    public String[][] leerArchivoUsuarios(String path, String name, int elementos, String separador) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path + name));
        String st;

        long fileLength = countFileLines(path, name);
        String[][] result = new String[(int)fileLength][];

        int currentLine = 0;
        while ((st = br.readLine()) != null){
            String[] userInfo = st.split(separador);
            result[currentLine] = userInfo;
        }

        return result;
    }

    public void updateLine(int line, String info){

    }

    private long countFileLines(String path, String nombre){
        Path filePath = Paths.get(path + nombre);
        long lines = 0;
        try {
            lines = Files.lines(filePath).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}