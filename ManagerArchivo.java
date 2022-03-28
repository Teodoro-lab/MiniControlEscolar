public class ManagerArchivo{
    
    public boolean validarEstructuraArchivo(String path, String name, int elementos, char separador){
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null){
            String[] tokens = st.split(separator);
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

    public String[][] leerArchivoUsuarios(String path, String name, int elementos, char separador){
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        int fileLength = countFileLines(path, name);
        String[][] result = new String[fileLength][];

        int currentLine = 0;
        while ((st = br.readLine()) != null){
            String[] userInfo = st.split(separador);
            result[currentLine] = userInfo;
        }

        return result;
    }

    public void updateLine(int line, String info){

    }

    private int countFileLines(String path, String nombre){
        Path path = Paths.get(path + nombre);
        int lines = 0;
        try {
            lines = Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}