package MODEL;

import CONTROLLER.Cliente;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LeerClientes {

    public ArrayList<Object> leerClientes(ArrayList<Cliente> listaClientes) {
        ArrayList<Object> listaDeserializada = new ArrayList<>();
        try {
            ObjectInputStream lectorSer = new ObjectInputStream(new FileInputStream("clientesSeri.dat"));
            
            
            for (int i = 0; i < listaClientes.size(); i++) {
                listaDeserializada.add(lectorSer.readObject());
            }

        } catch (IOException e) {
            return listaDeserializada;
            
        } catch (ClassNotFoundException e) {
            return listaDeserializada;
        }
        return listaDeserializada;

    }
}
