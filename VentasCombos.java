package view;

import java.util.ArrayList;

public class VentasCombos {
    public VentasBoletos ventas;//accedo a la clase ventasBoletos
    public double precioxCombo;
    public double totalCombo;

    public String verificarDescuento(String nombrePelicula, String nombreHora, String[][] matrizPeliculas, String[][] matrizCombos, int dia) {
        //get de la clase boletos(nombrePelicula, nombreHora y dia)
        if ((nombrePelicula.equals(matrizPeliculas[0][0])) || (nombrePelicula.equals(matrizPeliculas[1][0]))) {
            String retorna = "*********************************************************"
                    + "\nLAS FUNCIONES " + matrizPeliculas[0][0] + " Y " + matrizPeliculas[1][0] + " TIENE UN DESCUENTO PARA EL " + matrizCombos[0][0]
                    + "\n*********************************************************";

            precioxCombo = Integer.valueOf(matrizCombos[0][3]);
            return retorna;
        }
        if (nombrePelicula.equals(matrizPeliculas[2][0])) {
            String retorna = "*********************************************************"
                    + "\nLAS FUNCIONES " + matrizPeliculas[1][0] + " TIENE UN DESCUENTO PARA EL " + matrizCombos[1][0]
                    + "\n*********************************************************";

            precioxCombo = Integer.valueOf(matrizCombos[1][3]);
            return retorna;
        }
        if (nombreHora.equals(matrizPeliculas[3][3])) {
            String retorna = "*********************************************************"
                    + "\nPOR SER LA ULTIMA FUNCION 22h00 SE APLICA DESCUENTO EN EL COMBO"
                    + "\n*********************************************************";
            precioxCombo = Integer.valueOf(matrizCombos[2][3]);
            return retorna;
        }
        if (dia == 7) {//get de la clase boletos
            String retorna ="*********************************************************"
                    + "COMO ES EL DIA "+dia+"EL COMBO ESTA A MITAD DE PRECIO"
                    +"\n*********************************************************";
            precioxCombo = (precioxCombo / 2);
            return retorna;
        }
        return "NO SE HA APLICADO NINGUN DESCUENTO";
    }

    public void calcularTotalCombo() {
        totalCombo += precioxCombo;
    }
}
