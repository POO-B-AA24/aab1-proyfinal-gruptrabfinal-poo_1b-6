package MODEL;

import CONTROLLER.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class EscribirClientes {

    public void escribirClientes(ArrayList<Cliente> listaClientes) {
        try {
            ObjectOutputStream escritorSer = new ObjectOutputStream(new FileOutputStream("clientesSeri.dat"));
            for (Cliente cli : listaClientes) {
                escritorSer.writeObject(cli);
            }
            escritorSer.close();

        } catch (IOException e) {
            
        }
    }
}
