package VIEW;

import CONTROLLER.*;
import MODEL.*;
import MODEL.Serializacion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Ejecutor {

    public static void main(String[] args) {

        Scanner put = new Scanner(System.in);
        
        String nombrePelicula = "", sala, nombreHora = "", orden, nombreDia = "", nombreCliente, mensaje = "";
        int comprar, nBoletos = 0, pelicula, hora, fil, col, combo, limFil = 5, limCol = 5, maxClientes = 400, anadirCliente, dia = 0, contador = 0;
        boolean asientoLibre = true, otroCliente = true;
        double precioXboleto, precioxCombo, totalCombo, totalPelis;
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String asientosA16[][] = new String[limFil][limCol]; //SE INICIALIZAN LAS MATRICES DE LAS SALAS
        String asientosA18[][] = new String[limFil][limCol];
        String asientosA20[][] = new String[limFil][limCol];
        String asientosA22[][] = new String[limFil][limCol];

        String asientosB16[][] = new String[limFil][limCol];
        String asientosB18[][] = new String[limFil][limCol];
        String asientosB20[][] = new String[limFil][limCol];
        String asientosB22[][] = new String[limFil][limCol];

        String asientosC16[][] = new String[limFil][limCol];
        String asientosC18[][] = new String[limFil][limCol];
        String asientosC20[][] = new String[limFil][limCol];
        String asientosC22[][] = new String[limFil][limCol];

        String asientosD16[][] = new String[limFil][limCol];
        String asientosD18[][] = new String[limFil][limCol];
        String asientosD20[][] = new String[limFil][limCol];
        String asientosD22[][] = new String[limFil][limCol];

        String[][] matPelis = new String[4][4];
        String[][] matCombos = new String[3][4];
        String nombresRandom[] = {"Pedro", "Carlos", "Juan", "Emilia", "Daniel", "Sebastian", "Manuel", "Maria", "Paula", "Jean"};

        LeerDatos leerdatos = new LeerDatos();
        leerdatos.importarDatosPeliculas(matPelis);
        leerdatos.importarDatosSnacks(matCombos);

        String datosRegistroCombos[][] = new String[maxClientes][3];
        String datosRegistroPelicula[][] = new String[maxClientes][4];

        while (true) {
            System.out.println("Dia:" + "\n[1]LUNES" + "\n[2]MARTES  (boletos a mitad de precio)" + "\n[3]MIERCOLES" + "\n[4]JUEVES  (boletos a mitad de precio)"
                    + "\n[5]VIERNES" + "\n[6]SABADO" + "\n[7]DOMINGO  (snacks a mitad de precio)");
            dia = put.nextInt();

            if (dia >= 1 && dia <= 7) {
                break;
            } else {
                System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!" + "\nNO EXISTE ESA OPCION" + "\n!-!-!-!-!-!-!-!-!-!-!-!-!" + "\n");
            }
        }
        switch (dia) {  //se asigna el dia
            case 1:
                nombreDia = "LUNES";
                break;
            case 2:
                nombreDia = "MARTES";
                break;
            case 3:
                nombreDia = "MIERCOLES";
                break;
            case 4:
                nombreDia = "JUEVES";
                break;
            case 5:
                nombreDia = "VIERNES";
                break;
            case 6:
                nombreDia = "SABADO";
                break;
            case 7:
                nombreDia = "DOMINGO";
                break;
        }
        VentasBoletos ventasboletos = new VentasBoletos(dia, limFil, limCol);
        ventasboletos.crearSalas(asientosA16); //SE INVOCAN LOS METODOS PARA LLENAR LAS MATRICES DE LAS SALAS
        ventasboletos.crearSalas(asientosA18);
        ventasboletos.crearSalas(asientosA20);
        ventasboletos.crearSalas(asientosA22);

        ventasboletos.crearSalas(asientosB16);
        ventasboletos.crearSalas(asientosB18);
        ventasboletos.crearSalas(asientosB20);
        ventasboletos.crearSalas(asientosB22);

        ventasboletos.crearSalas(asientosC16);
        ventasboletos.crearSalas(asientosC18);
        ventasboletos.crearSalas(asientosC20);
        ventasboletos.crearSalas(asientosC22);

        ventasboletos.crearSalas(asientosD16);
        ventasboletos.crearSalas(asientosD18);
        ventasboletos.crearSalas(asientosD20);
        ventasboletos.crearSalas(asientosD22);
        do {
            orden = "";
            precioXboleto = 0;
            precioxCombo = 0;
            totalCombo = 0;
            totalPelis = 0;
            put.nextLine();
            System.out.println("Ingrese su nombre");    //se guarda el nombre del cliente
            nombreCliente = put.nextLine();
            System.out.println("****************************");
            System.out.println("DESEA COMPRAR BOLETOS?");
            System.out.println("[1] Si");
            System.out.println("[2] No");
            comprar = put.nextInt(); //si no se compran, se pasa directamente a snacks
            System.out.println("****************************");
            if (comprar == 1) {
                System.out.println("CUANTOS BOLETOS QUIERE?");
                nBoletos = put.nextInt();
                System.out.println("****************************");
                String facturaAsientos[] = new String[nBoletos];  //se crea en base al numero de boletos comprados
                System.out.println("ELIJA SU PELICULA");
                System.out.println("1 " + matPelis[0][0] + "[$" + matPelis[0][1] + "]" + " (estreno)");
                System.out.println("2 " + matPelis[1][0] + "[$" + matPelis[1][1] + "]");
                System.out.println("3 " + matPelis[2][0] + "[$" + matPelis[2][1] + "]");
                System.out.println("4 " + matPelis[3][0] + "[$" + matPelis[3][1] + "]" + " (ultima semana)");
                pelicula = put.nextInt();
                while (true) {      //valida que sea una respuesta valida
                    if (pelicula >= 1 && pelicula <= 4) {
                        break;
                    } else {
                        System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!" + "\nNO EXISTE ESA OPCION" + "\n!-!-!-!-!-!-!-!-!-!-!-!-!" + "\n");//vuelve a preguntar si no existe la respuesta
                        pelicula = put.nextInt();
                    }
                }
                String datosPelicula[] = ventasboletos.asignarDatosPelicula(matPelis, pelicula); //se guardan los valores en un array segun la pelicula escogida
                precioXboleto = Double.parseDouble(datosPelicula[0]);
                nombrePelicula = datosPelicula[1];
                sala = datosPelicula[2];
                System.out.println("****************************");
                System.out.println("ELIJA LA HORA");
                System.out.println("[1] " + matPelis[0][3] + " (descuento -25%)");
                System.out.println("[2] " + matPelis[1][3]);
                System.out.println("[3] " + matPelis[2][3] + " (tarifa adicional +25%)");
                System.out.println("[4] " + matPelis[3][3]);
                hora = put.nextInt();
                while (true) {      //valida que sea una respuesta valida
                    if (hora >= 1 && hora <= 4) {
                        break;
                    } else {
                        System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!" + "\nNO EXISTE ESA OPCION" + "\n!-!-!-!-!-!-!-!-!-!-!-!-!" + "\n");//vuelve a preguntar si no existe la respuesta
                        hora = put.nextInt();
                    }
                }
                String datosHora[] = ventasboletos.asignarDatosHora(matPelis, hora, precioXboleto); //se guardan los valores en un array segun la hora escogida
                nombreHora = datosHora[0];
                precioXboleto = Double.parseDouble(datosHora[1]);
                System.out.println("****************************");
                for (int i = 0; i < nBoletos; i++) {
                    asientoLibre = true;
                    while (asientoLibre) {
                        System.out.println("----------------------");
                        System.out.println("ELIJA EL ASIENTO " + (i + 1));
                        System.out.println("-ELIJA SU FILA  -  [1-5]");
                        while (true) {
                            fil = put.nextInt();
                            if (fil >= 1 && fil <= 5) {
                                break;
                            } else {
                                System.out.println("OPCION NO VALIDA");
                            }
                        }
                        System.out.println("-ELIJA SU COLUMNA  -  [1-5]");
                        while (true) {
                            col = put.nextInt();
                            if (col >= 1 && col <= 5) {
                                break;
                            } else {
                                System.out.println("OPCION NO VALIDA");
                            }
                        }
                        System.out.println("----------------------");
                        asientoLibre = ventasboletos.validarSegunPeli_Hora(pelicula, hora, fil, col, facturaAsientos, i, // se valida la disponibilidad del asiento segun su sala
                                asientosA16, asientosA18, asientosA20, asientosA22,
                                asientosB16, asientosB18, asientosB20, asientosB22,
                                asientosC16, asientosC18, asientosC20, asientosC22,
                                asientosD16, asientosD18, asientosD20, asientosD22);
                        System.out.println(ventasboletos.imprimirSegunPeli_Hora(pelicula, hora, //se imprime la matriz de la sala seleccionada
                                asientosA16, asientosA18, asientosA20, asientosA22,
                                asientosB16, asientosB18, asientosB20, asientosB22,
                                asientosC16, asientosC18, asientosC20, asientosC22,
                                asientosD16, asientosD18, asientosD20, asientosD22));
                        if (asientoLibre) {
                            System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!");
                            System.out.println("ESE ASIENTO YA ESTA OCUPADO, PRUEBA CON OTRO");
                            System.out.println("!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!" + "\n");
                        }
                    }
                }
                totalPelis = ventasboletos.calcularTotalBoletos(precioXboleto, nBoletos);
                System.out.println(ventasboletos.facturaPelicula(facturaAsientos, nBoletos, precioXboleto, nombrePelicula, nombreHora, sala,
                        nombreDia, contador, datosRegistroPelicula));
            }
            System.out.println("****************************");
            System.out.println("DESEA COMPRAR SNACKS?");
            System.out.println("[1] Si");
            System.out.println("[2] No");
            comprar = put.nextInt();
            System.out.println("****************************");
            VentasCombos ventasCombos = new VentasCombos( precioxCombo, totalCombo);
            if (comprar == 1) {
                totalCombo = 0;
                //VentasCombos ventasCombos = new VentasCombos( precioxCombo, totalCombo);
                do {
                    System.out.println("******************************************");
                    System.out.println("ESCOJA SU OPCION DE COMBO");
                    System.out.println(matCombos[0][0] + " " + matCombos[0][1] + " [$" + matCombos[0][2] + "]");
                    System.out.println(matCombos[1][0] + " " + matCombos[1][1] + " [$" + matCombos[1][2] + "]");
                    System.out.println(matCombos[2][0] + " " + matCombos[2][1] + " [$" + matCombos[2][2] + "]");
                    System.out.println("******************************************");
                    combo = put.nextInt();
                    while (true) {      //valida que sea una respuesta valida
                        if (combo >= 1 && combo <= 3) {
                            break;
                        } else {
                            System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!" + "\nNO EXISTE ESA OPCION" + "\n!-!-!-!-!-!-!-!-!-!-!-!-!\n");
                            combo = put.nextInt();
                        }
                    }
                    String datosCombo[] = ventasCombos.asignarDatosCombos(matCombos, combo, nombrePelicula, nombreHora,
                            matPelis, matCombos, dia, orden, mensaje);//se guardan los valores en un array segun el combo escogido
                    precioxCombo = Double.parseDouble(datosCombo[0]);
                    orden = datosCombo[1];
                    mensaje = datosCombo[2];
                    System.out.println(mensaje);
                    System.out.println("******************************************");
                    totalCombo += precioxCombo;
                    System.out.println("DESEA COMPRAR OTRO COMBO?");
                    System.out.println("[1] Si");
                    System.out.println("[2] No");
                    comprar = put.nextInt();
                } while (comprar == 1);
                System.out.println(ventasCombos.facturaSnack(totalCombo, orden, datosRegistroCombos, contador));
                totalCombo = ventasCombos.calcularTotalCombos(totalCombo);
            }
            listaClientes.add(new Cliente(nombreCliente, nombrePelicula, nombreHora, nBoletos, totalPelis, orden, totalCombo, nombreDia));
            contador++;
            System.out.println("DESEA INGRESAR OTRO CLIENTE?");
            System.out.println("[1] Si");
            System.out.println("[2] No");
            anadirCliente = put.nextInt();
            if (anadirCliente == 2) {
                otroCliente = false;
            }
            if (contador >= maxClientes) {
                System.out.println("AFORO MAXIMO ALCANZADO");
                otroCliente = false;
            }
        } while (otroCliente); // SI SE ESCRIBE 2 O SE REGISTRAN MAS DE 400 CLIENTES, SE CIERRA EL CICLO
        String nombres[] = new String[contador];
        for (int i = 0; i < contador; i++) {//SE LLENA EL ARREGLO DE NOMBRES 
            int aleat = (int) (Math.random() * 10 + 0);
            nombres[i] = nombresRandom[aleat];
        }
        System.out.println("-----------CLIENTES HOY: " + contador + "-----------");
        exportarRegistroPeliculas(datosRegistroPelicula, contador, nombreDia, nombres);
        exportarRegistroSnacks(contador, orden, nombreDia, datosRegistroCombos, nombres);
        System.out.println(listaClientes);
        //////////////////////Serializacion
        
        String nombreArchivo = "Proyecto.ser";//nombre por el cual yo voy a guardar el objeto
        //serializarObjeto2(nombreArchivo, listaClientes);//aqui convierto el archivo en un .dat
        //Combos combo = deserializarObjeto(nombreArchivo, Combos.class);
        Serializacion serializar = new Serializacion();
        serializar.serializarCliente(listaClientes, nombreArchivo);
        //serializar.deserializarCliente(nombreArchivo);
    }
    
    public static <E> void serializarObjeto2(String nombreArchivo, E objeto) {
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(objeto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }public static void exportarRegistroSnacks(int clienteNuevo, String orden, String nombreDia, String datosRegistroCombos[][], String nombres[]) {
        try {
            Formatter escritura = new Formatter("registroSnacks.csv");
            escritura.format("%s;%s;%s;%s; \n", "NOMBRE", "PEDIDO", "TOTAL", "DIA");
            for (int i = 0; i < clienteNuevo; i++) {
                int aleat = (int) (Math.random() * 10 + 0);
                escritura.format("%s;%s;%s;%s; \n", nombres[i], datosRegistroCombos[i][0], ("$" + datosRegistroCombos[i][1]), nombreDia);
            }
            escritura.close();
        } catch (Exception e) {
        }
    }

    public static void exportarRegistroPeliculas(String datosRegistroPelicula[][], int clienteNuevo, String nombreDia, String nombres[]) {
        try {
            Formatter escritura = new Formatter("registroPelis.csv");
            escritura.format("%s;%s;%s;%s;%s;%s; \n", "NOMBRE", "HORA", "PELICULA", "NUM BOL", "TOTAL", "DIA");
            for (int i = 0; i < clienteNuevo; i++) {
                escritura.format("%s;%s;%s;%s;%s;%s\n", nombres[i], datosRegistroPelicula[i][0], datosRegistroPelicula[i][1], datosRegistroPelicula[i][2], ("$" + datosRegistroPelicula[i][3]), nombreDia);
            }
            escritura.close();
        } catch (Exception e) {
        }
    }

}
