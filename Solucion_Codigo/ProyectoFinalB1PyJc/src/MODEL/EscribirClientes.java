package MODEL;

import VIEW.*;
import CONTROLLER.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class EscribirClientes {

    public void escribirClientes(ArrayList<Cliente> listaClientes) {
        try {
            ObjectOutputStream escritorSer = new ObjectOutputStream(new FileOutputStream("textito1.dat"));
            for (Cliente cli : listaClientes) {
                escritorSer.writeObject(cli);
            }
            escritorSer.close();

        } catch (IOException e) {
            System.out.println("error al escribir objeto");
        }
    }
}
