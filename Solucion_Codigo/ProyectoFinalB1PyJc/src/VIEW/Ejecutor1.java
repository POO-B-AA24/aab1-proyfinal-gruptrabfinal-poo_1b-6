package VIEW;

import CONTROLLER.*;
import MODEL.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejecutor1 {
    
    public static void main(String[] args) {

        Scanner put = new Scanner(System.in);

        String nombrePelicula = "", sala, nombreHora = "", orden, nombreDia = "", nombreCliente, mensaje = "";
        int comprar, nBoletos = 0, pelicula, hora, fil, col, combo, limFil = 5, limCol = 5, maxClientes = 400, anadirCliente, dia = 0, contador = 0;
        boolean asientoLibre = true, otroCliente = true;
        double precioXboleto, precioxCombo, totalCombo, totalPelis;
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String asientosA16[][] = new String[limFil][limCol]; 
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
        

        LeerDatos leerdatos = new LeerDatos();
        leerdatos.importarDatosPeliculas(matPelis);
        leerdatos.importarDatosSnacks(matCombos);
        String datosRegistroCombos[][] = new String[maxClientes][4];
        String datosRegistroPelicula[][] = new String[maxClientes][5];
        
        
        Sala[][] listaSalas = new Sala[4][4];
        listaSalas[0][0] = new Sala("A", matPelis[3][0], matPelis[0][0], asientosA16);
        listaSalas[0][1] = new Sala("A", matPelis[3][1], matPelis[0][0], asientosA18);
        listaSalas[0][2] = new Sala("A", matPelis[3][2], matPelis[0][0], asientosA20);
        listaSalas[0][3] = new Sala("A", matPelis[3][3], matPelis[0][0], asientosA22);
        
        listaSalas[1][0] = new Sala("B", matPelis[3][0], matPelis[1][0], asientosB16);
        listaSalas[1][1] = new Sala("B", matPelis[3][1], matPelis[1][0], asientosB18);
        listaSalas[1][2] = new Sala("B", matPelis[3][2], matPelis[1][0], asientosB20);
        listaSalas[1][3] = new Sala("B", matPelis[3][3], matPelis[1][0], asientosB22);
        
        listaSalas[2][0] = new Sala("C", matPelis[3][0], matPelis[2][0], asientosC16);
        listaSalas[2][1] = new Sala("C", matPelis[3][1], matPelis[2][0], asientosC18);
        listaSalas[2][2] = new Sala("C", matPelis[3][2], matPelis[2][0], asientosC20);
        listaSalas[2][3] = new Sala("C", matPelis[3][3], matPelis[2][0], asientosC22);
        
        listaSalas[3][0] = new Sala("D", matPelis[3][0], matPelis[3][0], asientosD16);
        listaSalas[3][1] = new Sala("D", matPelis[3][1], matPelis[3][0], asientosD18);
        listaSalas[3][2] = new Sala("D", matPelis[3][2], matPelis[3][0], asientosD20);
        listaSalas[3][3] = new Sala("D", matPelis[3][3], matPelis[3][0], asientosD22);

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
        
        
        Boleto ventasboletos = new Boleto(dia, limFil, limCol);
        
        for (int i = 0; i < listaSalas.length; i++) {
            for (int j = 0; j < listaSalas[0].length; j++) {
                listaSalas[i][j].crearSalas(listaSalas[i][j].matrizAsientos);
            }
        }
        
        
        do {
            orden = "";
            precioXboleto = 0;
            precioxCombo = 0;
            totalCombo = 0;
            totalPelis = 0;
            put.nextLine();
            System.out.println("Ingrese su nombre");    //se guarda el nombre del cliente
            nombreCliente = put.nextLine();
            datosRegistroPelicula[contador][4]=nombreCliente;
            datosRegistroCombos[contador][3]=nombreCliente;
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
                        asientoLibre = ventasboletos.validarSegunPeli_Hora(pelicula, hora, fil, col, facturaAsientos, i, listaSalas);
                        
                        System.out.println(ventasboletos.imprimirSegunPeli_Hora(pelicula, hora,listaSalas));
                        
                        if (asientoLibre) {
                            System.out.println("\n" + "!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!");
                            System.out.println("ESE ASIENTO YA ESTA OCUPADO, PRUEBA CON OTRO");
                            System.out.println("!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!" + "\n");
                        }
                    }
                }
                ventasboletos.datosRegistroPelicula = datosRegistroPelicula;
                ventasboletos.nBoletos = nBoletos;
                ventasboletos.precioXboleto = precioXboleto;
                ventasboletos.nombrePelicula = nombrePelicula;
                ventasboletos.nombreHora = nombreHora;
                ventasboletos.facturaAsientos= facturaAsientos;
                ventasboletos.sala= sala;
                ventasboletos.contador = contador;
                totalPelis = ventasboletos.calcularTotal();
                System.out.println(ventasboletos.construirFacctura());
            }
            System.out.println("****************************");
            System.out.println("DESEA COMPRAR SNACKS?");
            System.out.println("[1] Si");
            System.out.println("[2] No");
            comprar = put.nextInt();
            System.out.println("****************************");
            if (comprar == 1) {
                totalCombo = 0;
                Combo ventasCombos = new Combo(ventasboletos, precioxCombo, totalCombo);
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
                ventasCombos.datosRegistroCombos = datosRegistroCombos;
                ventasCombos.totalCombo=totalCombo;
                ventasCombos.orden = orden;
                ventasCombos.contador = contador;
                

                System.out.println(ventasCombos.construirFacctura());
                totalCombo = ventasCombos.calcularTotal();
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

        System.out.println("-----------CLIENTES HOY: " + contador + "-----------");

        
        EscribirClientes serialOUT = new EscribirClientes();
        serialOUT.escribirClientes(listaClientes);
        LeerClientes serialIN = new LeerClientes();
        System.out.println(serialIN.leerClientes(listaClientes));
        RegistrarVentas registrarVentas = new RegistrarVentas();
        registrarVentas.exportarRegistroPeliculas(datosRegistroPelicula, contador, nombreDia);
        registrarVentas.exportarRegistroSnacks(contador, orden, nombreDia, datosRegistroCombos);
    }
    
   

}

/*
run:
Dia:
[1]LUNES
[2]MARTES  (boletos a mitad de precio)
[3]MIERCOLES
[4]JUEVES  (boletos a mitad de precio)
[5]VIERNES
[6]SABADO
[7]DOMINGO  (snacks a mitad de precio)
3
Ingrese su nombre
Juan
****************************
DESEA COMPRAR BOLETOS?
[1] Si
[2] No
1
****************************
CUANTOS BOLETOS QUIERE?
2
****************************
ELIJA SU PELICULA
1 SPIDERMAN[$8] (estreno)
2 LEGO[$4]
3 BARBIE[$4]
4 MATRIX[$2] (ultima semana)
2
****************************
ELIJA LA HORA
[1] 16h00 (descuento -25%)
[2] 18h00
[3] 20h00 (tarifa adicional +25%)
[4] 22h00
3
****************************
----------------------
ELIJA EL ASIENTO 1
-ELIJA SU FILA  -  [1-5]
1
-ELIJA SU COLUMNA  -  [1-5]
1
----------------------
X	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	

----------------------
ELIJA EL ASIENTO 2
-ELIJA SU FILA  -  [1-5]
1
-ELIJA SU COLUMNA  -  [1-5]
2
----------------------
X	X	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	

==================== FACTURA ====================
Numero de Boletos: 2
Precio por Boleto: $5.0
Pelicula: LEGO
Hora: 20h00
Total: $10.0
IVA: 1,2
Total a Pagar: $11,2
=================================================




==================== BOLETO 1 ====================
Pelicula: LEGO
Hora: 20h00
Sala: B
Asiento: 1-1
==================================================


==================== BOLETO 2 ====================
Pelicula: LEGO
Hora: 20h00
Sala: B
Asiento: 1-2
==================================================



****************************
DESEA COMPRAR SNACKS?
[1] Si
[2] No
1
****************************
******************************************
ESCOJA SU OPCION DE COMBO
[1]COMBO Bebida_con_Canguil [$3]
[2]COMBO Bebida_con_Canguil_y_un_Raspado [$7]
[3]COMBO Nachos_con_burrito_y_un_Raspado [$9]
******************************************
2
 
******************************************
DESEA COMPRAR OTRO COMBO?
[1] Si
[2] No
1
******************************************
ESCOJA SU OPCION DE COMBO
[1]COMBO Bebida_con_Canguil [$3]
[2]COMBO Bebida_con_Canguil_y_un_Raspado [$7]
[3]COMBO Nachos_con_burrito_y_un_Raspado [$9]
******************************************
1
 *********************************************************
LAS FUNCIONES SPIDERMAN Y LEGO TIENE UN DESCUENTO PARA EL [1]COMBO
*********************************************************

******************************************
DESEA COMPRAR OTRO COMBO?
[1] Si
[2] No
2
==================== FACTURA ====================
Orden:[2]COMBO [1]COMBO 
IVA: 0,96
TOTAL: $8
TOTAL A PAGAR: $8,96
=================================================





DESEA INGRESAR OTRO CLIENTE?
[1] Si
[2] No
1
Ingrese su nombre
Jose
****************************
DESEA COMPRAR BOLETOS?
[1] Si
[2] No
1
****************************
CUANTOS BOLETOS QUIERE?
1
****************************
ELIJA SU PELICULA
1 SPIDERMAN[$8] (estreno)
2 LEGO[$4]
3 BARBIE[$4]
4 MATRIX[$2] (ultima semana)
2
****************************
ELIJA LA HORA
[1] 16h00 (descuento -25%)
[2] 18h00
[3] 20h00 (tarifa adicional +25%)
[4] 22h00
3
****************************
----------------------
ELIJA EL ASIENTO 1
-ELIJA SU FILA  -  [1-5]
1
-ELIJA SU COLUMNA  -  [1-5]
1
----------------------
X	X	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	
-	-	-	-	-	


!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!
ESE ASIENTO YA ESTA OCUPADO, PRUEBA CON OTRO
!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!

----------------------
ELIJA EL ASIENTO 1
-ELIJA SU FILA  -  [1-5]
3
-ELIJA SU COLUMNA  -  [1-5]
3
----------------------
X	X	-	-	-	
-	-	-	-	-	
-	-	X	-	-	
-	-	-	-	-	
-	-	-	-	-	

==================== FACTURA ====================
Numero de Boletos: 1
Precio por Boleto: $5.0
Pelicula: LEGO
Hora: 20h00
Total: $5.0
IVA: 0,6
Total a Pagar: $5,6
=================================================




==================== BOLETO 1 ====================
Pelicula: LEGO
Hora: 20h00
Sala: B
Asiento: 3-3
==================================================



****************************
DESEA COMPRAR SNACKS?
[1] Si
[2] No
1
****************************
******************************************
ESCOJA SU OPCION DE COMBO
[1]COMBO Bebida_con_Canguil [$3]
[2]COMBO Bebida_con_Canguil_y_un_Raspado [$7]
[3]COMBO Nachos_con_burrito_y_un_Raspado [$9]
******************************************
3
 
******************************************
DESEA COMPRAR OTRO COMBO?
[1] Si
[2] No
2
==================== FACTURA ====================
Orden:[3]COMBO 
IVA: 1,08
TOTAL: $9
TOTAL A PAGAR: $10,08
=================================================





DESEA INGRESAR OTRO CLIENTE?
[1] Si
[2] No
2
-----------CLIENTES HOY: 2-----------
[Cliente{nombreCliente=Juan, nombrePelicula=LEGO, nombreHora=20h00, nBoletos=2, totalPelis=11.2, orden=[2]COMBO [1]COMBO , totalCombos=8.96}
, Cliente{nombreCliente=Jose, nombrePelicula=LEGO, nombreHora=20h00, nBoletos=1, totalPelis=5.6, orden=[3]COMBO , totalCombos=10.08}
]
BUILD SUCCESSFUL (total time: 1 minute 1 second)
*/