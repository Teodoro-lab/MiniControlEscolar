public class ManagerArchivo{
    public boolean validarEstructuraArchivo(){

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

        while ((st = br.readLine()) != null){
            System.out.println(st);
        }
    }

    public void updateLine(int line, String info){

    }
}