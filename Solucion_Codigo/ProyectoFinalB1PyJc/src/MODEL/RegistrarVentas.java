package MODEL;

import java.util.Formatter;

public class RegistrarVentas {
    public void exportarRegistroSnacks(int contador, String orden, String nombreDia, String datosRegistroCombos[][]) {
        try {
            Formatter escritura = new Formatter("registroSnacks.csv");
            escritura.format("%s;%s;%s;%s; \n", "NOMBRE", "PEDIDO", "TOTAL", "DIA");
            for (int i = 0; i < contador; i++) {
                int aleat = (int) (Math.random() * 10 + 0);
                escritura.format("%s;%s;%s;%s; \n", datosRegistroCombos[i][3], datosRegistroCombos[i][0], ("$" + datosRegistroCombos[i][1]), nombreDia);
            }
            escritura.close();

        } catch (Exception e) {
        }
    }
    public void exportarRegistroPeliculas(String datosRegistroPelicula[][], int contador, String nombreDia) {
        try {
            Formatter escritura = new Formatter("registroPelis.csv");
            escritura.format("%s;%s;%s;%s;%s;%s; \n", "NOMBRE", "HORA", "PELICULA", "NUM BOL", "TOTAL", "DIA");
            for (int i = 0; i < contador; i++) {
                escritura.format("%s;%s;%s;%s;%s;%s\n", datosRegistroPelicula[i][4], datosRegistroPelicula[i][0], datosRegistroPelicula[i][1], datosRegistroPelicula[i][2], ("$" + datosRegistroPelicula[i][3]), nombreDia);

            }
            escritura.close();

        } catch (Exception e) {
        }
    }
}
