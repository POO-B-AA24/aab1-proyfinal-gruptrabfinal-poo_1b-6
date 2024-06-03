package MODEL;

import java.io.File;
import java.util.Scanner;

public class LeerDatos {
    public static void importarDatosPeliculas(String[][] matPelis) {
        try {
            Scanner leer = new Scanner(new File("pelicula.csv"));
            int h = 0;
            while (leer.hasNext()) {
                String datos[] = leer.nextLine().split(";");
                matPelis[h][0] = datos[0];
                matPelis[h][1] = datos[1];
                matPelis[h][2] = datos[2];
                matPelis[h][3] = datos[3];
                h++;
            }
        } catch (Exception e) {
        }
    }
    public static void importarDatosSnacks(String[][] matCombos) {
        try {
            Scanner leer = new Scanner(new File("combos.csv"));
            int i = 0;
            while (leer.hasNext()) {
                String datos[] = leer.nextLine().split(";");
                matCombos[i][0] = datos[0];
                matCombos[i][1] = datos[1];
                matCombos[i][2] = datos[2];
                matCombos[i][3] = datos[3];
                i++;
            }
        } catch (Exception e) {
        }
    }
}
