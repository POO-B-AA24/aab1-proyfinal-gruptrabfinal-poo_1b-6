package CONTROLLER;

import java.text.DecimalFormat;

public class Combo extends Venta{
    public double precioxCombo;
    public double totalCombo;
    public String orden;
    public int contador;
    public String[][] datosRegistroCombos;

    public Combo(Boleto ventas, double precioxCombo, double totalCombo) {
        this.precioxCombo = precioxCombo;
        this.totalCombo = totalCombo;
    }

    public String[] asignarDatosCombos(String matC[][], int combo, String nombrePelicula, String nombreHora,
            String[][] matrizPeliculas, String[][] matrizCombos, int dia, String orden, String mensaje) { 
        String datosCombo[] = new String[3];// segun el combo, la hora, la pelicula y el dia escogidos asigna y devuelve diferentes datos a un array
        mensaje = " ";
        switch (combo) {
            case 1:
                orden += (matC[0][0] + " ");
                datosCombo[0] = matrizCombos[0][2];
                precioxCombo = Integer.valueOf(matC[0][2]);
                if ((nombrePelicula.equals(matrizPeliculas[0][0])) || (nombrePelicula.equals(matrizPeliculas[1][0]))) {
                    mensaje += "*********************************************************"
                            + "\nLAS FUNCIONES " + matrizPeliculas[0][0] + " Y " + matrizPeliculas[1][0] + " TIENE UN DESCUENTO PARA EL " + matrizCombos[0][0]
                            + "\n*********************************************************\n";
                    datosCombo[0] = matrizCombos[0][3];
                    precioxCombo = Integer.valueOf(matrizCombos[0][3]);
                } else {
                    mensaje = " ";
                }
                break;
            case 2:
                orden += (matC[1][0] + " ");
                datosCombo[0] = matrizCombos[1][2];
                precioxCombo = Integer.valueOf(matC[1][2]);
                if (nombrePelicula.equals(matrizPeliculas[2][0])) {
                    mensaje += "*********************************************************"
                            + "\nLAS FUNCIONES " + matrizPeliculas[1][0] + " TIENE UN DESCUENTO PARA EL " + matrizCombos[1][0]
                            + "\n*********************************************************\n";
                    datosCombo[0] = matrizCombos[1][3];
                    precioxCombo = Integer.valueOf(matrizCombos[1][3]);
                } else {
                    mensaje = " ";
                }
                break;
            case 3:
                orden += (matC[2][0] + " ");
                datosCombo[0] = matrizCombos[2][2];
                precioxCombo = Integer.valueOf(matC[2][2]);
                if (nombreHora.equals(matrizPeliculas[3][3])) {
                    mensaje += "*********************************************************"
                            + "\nPOR SER LA ULTIMA FUNCION 22h00 SE APLICA DESCUENTO EN EL COMBO"
                            + "\n*********************************************************\n";
                    datosCombo[0] = matrizCombos[2][3];
                    precioxCombo = Integer.valueOf(matrizCombos[2][3]);
                } else {
                    mensaje = " ";
                }
                break;
        }
        if (dia == 7) {//get de la clase boletos
            mensaje += "*********************************************************"
                    + "\nCOMO ES EL DIA " + dia + " EL COMBO ESTA A MITAD DE PRECIO"
                    + "\n*********************************************************\n";
            precioxCombo = (precioxCombo / 2);
            datosCombo[0] = precioxCombo + "";
        }
        datosCombo[2] = mensaje;
        datosCombo[1] = orden;
        return datosCombo;
    }
    
   
    @Override
    public String construirFacctura() {
        String facturaSn = "";
        DecimalFormat df = new DecimalFormat("#.##"); //es una clase que permite reducir valores decimales
        
        double iva = (totalCombo *0.12);
        double total = calcularTotal();
        
        
        String totalDecim = df.format(totalCombo);
        String ivaDecim = df.format(iva);
        String totalPagarSnacksDecim = df.format(total);  //se guardan los datos en variables con los decimales reducidos
        
        
        facturaSn += "==================== FACTURA ====================" + "\n"
                + "Orden:" + orden + "\n"
                + "IVA: " + ivaDecim + "\n"
                + "TOTAL: $" + totalDecim + "\n"
                + "TOTAL A PAGAR: $" + totalPagarSnacksDecim + "\n"
                + "=================================================" + "\n\n\n\n\n";
        datosRegistroCombos[contador][0] = orden;
        datosRegistroCombos[contador][1] = totalPagarSnacksDecim;
        return facturaSn;
    }
    
    @Override
    public double calcularTotal(){
        double iva = (this.totalCombo * 0.12);
        double total = (this.totalCombo + iva);
        
        return total;
    }

   

}
