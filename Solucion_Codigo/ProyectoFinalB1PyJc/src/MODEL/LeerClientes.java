package MODEL;

import CONTROLLER.Cliente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LeerClientes {

    public ArrayList<Cliente> leerClientes(ArrayList<Cliente> listaClientes) {
        ArrayList<Cliente> listaDeserializada = new ArrayList<>();
        try {
            ObjectInputStream lectorSer = new ObjectInputStream(new FileInputStream("textito1.dat"));
            for (Cliente ente : listaClientes) {
                listaDeserializada.add(ente);
            }
            //listaDeserializada = (ArrayList<Cliente>) lectorSer.readObject();

        } catch (IOException e) {
            System.out.println("error al escribir objeto");
        } /*catch (ClassNotFoundException e) {
            System.out.println("error leer");
        }*/
        return listaDeserializada;

    }
}
