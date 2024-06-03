package CONTROLLER;
import java.io.Serializable;
public class Cliente {

    public String nombreCliente;
    public String nombrePelicula;
    public String nombreHora;
    public int nBoletos;
    public double totalPelis;
    public String orden;
    public double totalCombos;
    public String nombreDia;

    public Cliente(String nombreCliente, String nombrePelicula, String nombreHora, int nBoletos,
            double totalPelis, String orden, double totalCombos, String nombreDia) {
        this.nombreCliente = nombreCliente;
        this.nombrePelicula = nombrePelicula;
        this.nombreHora = nombreHora;
        this.nBoletos = nBoletos;
        this.totalPelis = totalPelis;
        this.orden = orden;
        this.totalCombos = totalCombos;
        this.nombreDia = nombreDia;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombreCliente=" + nombreCliente + ", nombrePelicula=" + nombrePelicula
                + ", nombreHora=" + nombreHora + ", nBoletos=" + nBoletos + ", totalPelis=" + totalPelis
                + ", orden=" + orden + ", totalCombos=" + totalCombos + "}\n";
    } 
}
