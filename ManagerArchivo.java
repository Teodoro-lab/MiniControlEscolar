import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ManagerArchivo{
    
    public boolean validarEstructuraArchivo(String path, String name, int elementos, String separador) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path + name));
        String st;

        while ((st = br.readLine()) != null){
            String[] tokens = st.split(separador);
            if (tokens.length != elementos){
                br.close();
                return false;
            }
        }

        br.close();
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
            currentLine++;
        }

        br.close();
        return result;
    }

    public void updateLine(String name, String path, int line, String info) throws FileNotFoundException{

        String filePath = path + name;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer buffer = new StringBuffer();

        int index = 0;
        String oldLine = "";
        while (sc.hasNextLine()) {

            String nextLine = sc.nextLine();
            buffer.append(nextLine + System.lineSeparator());

            if(index == line) {
                oldLine = nextLine;
            }
            index++;
        }

        String fileContents = buffer.toString();
        System.out.println(fileContents);
        sc.close();

        fileContents = fileContents.replace(oldLine, info);
        
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

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