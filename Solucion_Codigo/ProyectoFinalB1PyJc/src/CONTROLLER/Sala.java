package CONTROLLER;
public class Sala {
    public String nombreSala;
    public String hora;
    public String pelicula;
    public String[][] matrizAsientos;

    public Sala(String nombreSala, String hora, String pelicula, String [][]matrizAsientos) {
        this.nombreSala = nombreSala;
        this.hora = hora;
        this.pelicula = pelicula;
        this.matrizAsientos = matrizAsientos;
    }
    
    public void crearSalas(String asientos[][]) {   //se llenan las salas de el signo "-" para representar que estan vacias
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                asientos[i][j] = "-";
            }
        }
    }

    
}
    
