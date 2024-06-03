package MODEL;
import CONTROLLER.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
public class Serializacion {
    public void serializarCliente(ArrayList<Cliente> cliente, String nombreArchivo) {
        try (FileOutputStream archivoOut = new FileOutputStream(nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(archivoOut)) {
            out.writeObject(cliente);
            System.out.println("Objeto Cliente serializado en " + nombreArchivo);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    // Método para deserializar un objeto Cliente
    public  Cliente deserializarCliente(String nombreArchivo) {
        Cliente cliente = null;
        try (FileInputStream archivoIn = new FileInputStream(nombreArchivo);
             ObjectInputStream in = new ObjectInputStream(archivoIn)) {
            cliente = (Cliente) in.readObject();
            System.out.println("Objeto Cliente deserializado de " + nombreArchivo);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Clase Cliente no encontrada");
            c.printStackTrace();
        }
        return cliente;
    }
}
