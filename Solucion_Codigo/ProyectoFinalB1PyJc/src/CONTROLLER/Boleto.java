package CONTROLLER;

import java.text.DecimalFormat;

    public class Boleto extends Venta{

    public int dia;
    public int limFil;
    public int limCol;
    public double precioXboleto;
    public int nBoletos;
    public int pelicula;
    public String facturaAsientos[];
    public String nombrePelicula;
    public String nombreHora;
    public String sala; 
    public String nombreDia; 
    public int contador; 
    public String datosRegistroPelicula[][];
    public Boleto(int dia, int limFil, int limCol) {
        this.dia = dia;
        this.limFil = limFil;
        this.limCol = limCol;
    }

    public String imprimirSegunPeli_Hora(int pelicula, int hora, Sala[][] listaSalas) {//se usa para no sobrecargar la clase main, devuelve un dinujo de la sala segun la hora y pelicula
        switch (pelicula) {
            case 1:
                switch (hora) {
                    case 1:
                        return dibujarAsientos(listaSalas[0][0].matrizAsientos);
                    case 2:
                        return dibujarAsientos(listaSalas[0][1].matrizAsientos);
                    case 3:
                        return dibujarAsientos(listaSalas[0][2].matrizAsientos);
                    case 4:
                        return dibujarAsientos(listaSalas[0][3].matrizAsientos);
                }
                break;
            case 2:
                switch (hora) {
                    case 1:
                        return dibujarAsientos(listaSalas[1][0].matrizAsientos);
                    case 2:
                        return dibujarAsientos(listaSalas[1][1].matrizAsientos);
                    case 3:
                        return dibujarAsientos(listaSalas[1][2].matrizAsientos);
                    case 4:
                        return dibujarAsientos(listaSalas[1][3].matrizAsientos);
                }
                break;
            case 3:
                switch (hora) {
                    case 1:
                        return dibujarAsientos(listaSalas[2][0].matrizAsientos);
                    case 2:
                        return dibujarAsientos(listaSalas[2][1].matrizAsientos);
                    case 3:
                        return dibujarAsientos(listaSalas[2][2].matrizAsientos);
                    case 4:
                        return dibujarAsientos(listaSalas[2][3].matrizAsientos);
                }
                break;
            case 4:
                switch (hora) {
                    case 1:
                        return dibujarAsientos(listaSalas[3][0].matrizAsientos);
                    case 2:
                        return dibujarAsientos(listaSalas[3][1].matrizAsientos);
                    case 3:
                        return dibujarAsientos(listaSalas[3][2].matrizAsientos);
                    case 4:
                        return dibujarAsientos(listaSalas[3][3].matrizAsientos);
                }
                break;
        }
        return "";
    }

    
    public boolean validarSegunPeli_Hora(int pelicula, int hora, int fil, int col, String facturaAsientos[],int i,Sala[][] listaSalas) { //se usa para no sobrecargar la clase main, valida segun la hora y pelicula
        boolean asientoLibre=true;
        
        
        switch (pelicula) {
            case 1:
                switch (hora) {
                    case 1:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[0][0].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 2:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[0][1].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 3:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[0][2].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 4:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[0][3].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                }
                break;
            case 2:
                switch (hora) {
                    case 1:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[1][0].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 2:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[1][1].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 3:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[1][2].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 4:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[1][3].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                }
                break;
            case 3:
                switch (hora) {
                    case 1:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[2][0].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 2:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[2][1].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 3:
                        asientoLibre =validar_Asignar_Asiento(listaSalas[2][2].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 4:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[2][3].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                }
                break;
            case 4:
                switch (hora) {
                    case 1:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[3][0].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 2:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[3][1].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 3:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[3][2].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                    case 4:
                        asientoLibre = validar_Asignar_Asiento(listaSalas[3][3].matrizAsientos, fil, col, facturaAsientos, i);
                        break;
                }
                break;
        }
        return asientoLibre;
    }
     
    public String dibujarAsientos(String asientos[][]) { //devuelve un dibujo de la sala enviada
        String dibujoAsientos = "";
        for (int i = 0; i < this.limFil; i++) {
            for (int j = 0; j < this.limCol; j++) {
                dibujoAsientos += (asientos[i][j] + "\t");
            }
            dibujoAsientos += "\n";
        }
        return dibujoAsientos;
    }

    public boolean validar_Asignar_Asiento(String asientos[][], int fil, int col, String facturaAsientos[], int i) {// devuelve si un asiento esta libre o no
        if (asientos[fil - 1][col - 1] == "X") {
            return true;
        } else {
            asientos[fil - 1][col - 1] = "X";
            facturaAsientos[i] = fil + "-" + col;
        }
        return false;
    }

    public String[] asignarDatosHora(String[][] mat, int hora, double precioXboleto) {
        String datosHora[] = new String[2]; // segun la hora escogida asigna y devuelvediferentes datos a un array
        String nombreHora;
        switch (hora) {
            case 1:
                datosHora[0] = mat[0][3];
                nombreHora = mat[0][3];
                datosHora[1] = ""+(precioXboleto - (precioXboleto * 0.25) + "");
                precioXboleto = precioXboleto - (precioXboleto * 0.25); //se reduce el precio por boleto por el descuento de la tarde
                break;
            case 2:
                datosHora[0] = mat[1][3];
                nombreHora = mat[1][3];
                datosHora[1] = precioXboleto + "";
                break;
            case 3:
                datosHora[0] = mat[2][3];
                nombreHora = mat[2][3];
                datosHora[1] = ""+(precioXboleto + (precioXboleto * 0.25)) + "";
                precioXboleto = precioXboleto + (precioXboleto * 0.25);  //se aumenta el precio por boleto por la tarifa adicional de hora pico
                break;
            case 4:
                datosHora[0] = mat[3][3];
                nombreHora = mat[3][3];
                datosHora[1] = precioXboleto + "";
                break;

        }
        return datosHora;
    }

    public String[] asignarDatosPelicula(String[][] mat, int pelicula) { // segun la pelicula y el dia escogidos asigna y devuelve diferentes datos a un array
        String datosPelicula[] = new String[3];
        switch (pelicula) {
            case 1:
                if ((dia == 2) || (dia == 4)) {
                    datosPelicula[0] = mat[0][2];
                } else {
                    datosPelicula[0] = mat[0][1];       
                }
                datosPelicula[1] = mat[0][0];
                datosPelicula[2] = "A";
                break;
            case 2:
                if ((dia == 2) || (dia == 4)) {
                    datosPelicula[0] = mat[1][2];
                } else {
                    datosPelicula[0] = mat[1][1];
                }
                datosPelicula[1] = mat[1][0];
                datosPelicula[2] = "B";
                break;
            case 3:
                if ((dia == 2) || (dia == 4)) {
                    datosPelicula[0] = mat[2][2];
                } else {
                    datosPelicula[0] = mat[2][1];
                }
                datosPelicula[1] = mat[2][0];
                datosPelicula[2] = "C";
                break;
            case 4:
                if ((dia == 2) || (dia == 4)) {
                    datosPelicula[0] = mat[3][2];
                } else {
                    datosPelicula[0] = mat[3][1];
                }
                datosPelicula[1] = mat[3][0];
                datosPelicula[2] = "D";
                break;
        }
        return datosPelicula;
    }
    
<<<<<<< HEAD:Solucion_Codigo/ProyectoFinalB1PyJc/src/CONTROLLER/Boleto.java

=======
>>>>>>> 400869c998f325abb8d416d41e49c083979b5ca7:Solucion_Codigo/ProyectoFinalB1PyJc/src/CONTROLLER/VentasBoletos.java
    @Override
    public double calcularTotal(){
        double iva = ((nBoletos* precioXboleto)*0.12);
        double total = ((nBoletos * precioXboleto)+ iva);
        return total;
    }
    @Override
    public String construirFacctura() {
    String boletos = "";
        DecimalFormat df = new DecimalFormat("#.##"); //es una clase que permite reducir valores decimales
        double iva = ((nBoletos * precioXboleto) * 0.12), totalPagarPelicula = ((nBoletos * precioXboleto) + iva);
        String ivaDecim = df.format(iva);
        String totalPagarPeliculaDecim = df.format(totalPagarPelicula);  //se guardan los datos en variables con los decimales reducidos
        boletos += "==================== FACTURA ====================" + "\n"
                + "Numero de Boletos: " + nBoletos + "\n"
                + "Precio por Boleto: $" + precioXboleto + "\n"
                + "Pelicula: " + nombrePelicula + "\n"
                + "Hora: " + nombreHora + "\n"
                + "Total: $" + (nBoletos * precioXboleto) + "\n"
                + "IVA: " + ivaDecim + "\n"
                + "Total a Pagar: $" + totalPagarPeliculaDecim + "\n"
                + "=================================================" + "\n\n\n\n\n";
        for (int i = 0; i < nBoletos; i++) {
            boletos += "==================== BOLETO " + (i + 1) + " ====================" + "\n"
                    + "Pelicula: " + nombrePelicula + "\n"
                    + "Hora: " + nombreHora + "\n"
                    + "Sala: " + sala + "\n"
                    + "Asiento: " + facturaAsientos[i] + "\n"
                    + "==================================================" + "\n\n\n";
        }
        datosRegistroPelicula[contador][0] = nombreHora;
        datosRegistroPelicula[contador][1] = nombrePelicula;
        datosRegistroPelicula[contador][2] = (String.valueOf(nBoletos) + " boletos");
        datosRegistroPelicula[contador][3] = totalPagarPeliculaDecim;
        return boletos;
    }
}
