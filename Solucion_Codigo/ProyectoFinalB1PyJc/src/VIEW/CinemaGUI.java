
package VIEW;
import CONTROLLER.Boleto;
import CONTROLLER.Cliente;
import CONTROLLER.Sala;
import CONTROLLER.Combo;
import MODEL.LeerDatos; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CinemaGUI extends javax.swing.JFrame {
    private JComboBox<String> dayComboBox;
    private JButton buyTicketsButton, buySnacksButton, nextClientButton;
    private JTextArea outputArea;
    private JTextField nameField, ticketCountField;
    private JLabel nameLabel, ticketLabel;
    private ArrayList<Cliente> listaClientes;
    private int contador = 0;
    private final int maxClientes = 400;

    // 
    private String[][] matPelis;
    private String[][] matCombos;
    private String[][] asientosA16, asientosA18, asientosA20, asientosA22;
    private String[][] asientosB16, asientosB18, asientosB20, asientosB22;
    private String[][] asientosC16, asientosC18, asientosC20, asientosC22;
    private String[][] asientosD16, asientosD18, asientosD20, asientosD22;
    private String[][] datosRegistroCombos;
    private String[][] datosRegistroPelicula;
    private Boleto ventaBoleto;
    private Combo ventasCombos;
    

    public CinemaGUI() {
        Window vent = new Window();
        vent.setVisible(false);
        setTitle("SISTEMA CINEMAS LOJA");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listaClientes = new ArrayList<>();

        // Inicialización de matrices
        int limFil = 5, limCol = 5;
        asientosA16 = new String[limFil][limCol];
        asientosA18 = new String[limFil][limCol];
        asientosA20 = new String[limFil][limCol];
        asientosA22 = new String[limFil][limCol];
        asientosB16 = new String[limFil][limCol];
        asientosB18 = new String[limFil][limCol];
        asientosB20 = new String[limFil][limCol];
        asientosB22 = new String[limFil][limCol];
        asientosC16 = new String[limFil][limCol];
        asientosC18 = new String[limFil][limCol];
        asientosC20 = new String[limFil][limCol];
        asientosC22 = new String[limFil][limCol];
        asientosD16 = new String[limFil][limCol];
        asientosD18 = new String[limFil][limCol];
        asientosD20 = new String[limFil][limCol];
        asientosD22 = new String[limFil][limCol];

        matPelis = new String[4][4];
        matCombos = new String[3][4];
        
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

        datosRegistroCombos = new String[maxClientes][4];
        datosRegistroPelicula = new String[maxClientes][5];

        // Configuración de paneles y layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 15, 10);
        
        // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/images/fondo_Negro.png"))); // NOI18N
       
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/VIEW/images/fondo_Negro.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        setContentPane(backgroundLabel);
        setLayout(new FlowLayout());

        // Fila 1: Selección del día
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Seleccione el día:"), gbc);

        gbc.gridx = 1;
        dayComboBox = new JComboBox<>(new String[]{"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"});
        mainPanel.add(dayComboBox, gbc);

        // Fila 2: Ingreso del nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        nameLabel = new JLabel("Nombre del cliente:");
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        // Fila 3: Ingreso de número de boletos
        gbc.gridx = 0;
        gbc.gridy = 2;
        ticketLabel = new JLabel("Número de boletos:");
        mainPanel.add(ticketLabel, gbc);

        gbc.gridx = 1;
        ticketCountField = new JTextField(5);
        mainPanel.add(ticketCountField, gbc);

        // Fila 4: Botones para comprar boletos y snacks
        gbc.gridx = 0;
        gbc.gridy = 3;
        buyTicketsButton = new JButton("Comprar Boletos");
        mainPanel.add(buyTicketsButton, gbc);

        gbc.gridx = 1;
        buySnacksButton = new JButton("Comprar Snacks");
        mainPanel.add(buySnacksButton, gbc);

        // Fila 5: Botón para el siguiente cliente
        gbc.gridx = 0;
        gbc.gridy = 4;
        nextClientButton = new JButton("Siguiente Cliente");
        mainPanel.add(nextClientButton, gbc);

        // Área de texto para mostrar resultados
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        outputArea = new JTextArea(5, 25);
        
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, gbc);

        add(mainPanel);

        // Asignar acciones a los botones
        buyTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventaBoletos();
            }
        });

        buySnacksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventaCombos();
            }
        });

        nextClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
                //construirFactura(ventaBoleto);
            }
        });
        // Inicializar datos de las películas y snacks
        LeerDatos leerdatos = new LeerDatos();
        leerdatos.importarDatosPeliculas(matPelis);
        leerdatos.importarDatosSnacks(matCombos);

        // Inicializar salas
        ventaBoleto = new Boleto(seleccionarDia(), limFil, limCol);
        inicializarSalas(listaSalas);
        double totalPelis = ventaBoleto.calcularTotal();
        
    }

    private void inicializarSalas(Sala [][] listaSalas) {
        
        for (int i = 0; i < listaSalas.length; i++) {
            for (int j = 0; j < listaSalas[0].length; j++) {
                listaSalas[i][j].crearSalas(listaSalas[i][j].matrizAsientos);
            }
        }
        
    }

    private int seleccionarDia() {
        switch ((String) dayComboBox.getSelectedItem()) {
            case "LUNES":
                return 1;
            case "MARTES":
                return 2;
            case "MIERCOLES":
                return 3;
            case "JUEVES":
                return 4;
            case "VIERNES":
                return 5;
            case "SABADO":
                return 6;
            case "DOMINGO":
                return 7;
            default:
                return 1; // Default to Monday
        }
    }

    private void ventaBoletos() {
        String nombreCliente = nameField.getText();
        int nBoletos;
        try {
            nBoletos = Integer.parseInt(ticketCountField.getText());
        } catch (NumberFormatException e) {
            outputArea.append("Número de boletos inválido.\n");
            return;
        }
///////////
        if (nombreCliente.isEmpty()) {
            outputArea.append("El nombre del cliente no puede estar vacío.\n");
            return;
        }

        // Lógica para comprar boletos
        // Solicitar película
        String pelicula = (String) JOptionPane.showInputDialog(this, "Seleccione la película:", "Película", JOptionPane.QUESTION_MESSAGE, null, new String[]{( matPelis[0][0] + "[$" + matPelis[0][1]+ "]" ), (matPelis[1][0] + "[$" + matPelis[1][1]+ "]"),(matPelis[2][0] + "[$" + matPelis[2][1] + "]") }, "Pelicula1");
        if (pelicula == null) {
            outputArea.append("Compra de boletos cancelada.\n");
            return;
        }
        

        // Solicitar horario
        String horario = (String) JOptionPane.showInputDialog(this, "Seleccione el horario:", "Horario", JOptionPane.QUESTION_MESSAGE, null, new String[]{(matPelis[0][3] + " (descuento -25%)"), matPelis[1][3], (matPelis[2][3] + " (tarifa adicional +25%)"), "22:00"}, matPelis[3][3]);
        if (horario == null) {
            outputArea.append("Compra de boletos cancelada.\n");
            return;
        }
        //String datosHora[] = ventaBoleto.asignarDatosHora(matPelis, horario, precioXboleto);
        // Lógica para manejar asientos, horarios
        // Aquí puedes implementar la lógica específica para reservar asientos en la película y horario seleccionados.

        outputArea.append("Cliente " + nombreCliente + " compró " + nBoletos + " boletos para la película " + pelicula + " a las " + horario + ".\n");

        // Agregar cliente a la lista
        Cliente cliente = new Cliente(nombreCliente, nBoletos);
        listaClientes.add(cliente);

        // Registro de boletos (ejemplo)
        datosRegistroPelicula[contador][0] = nombreCliente;
        datosRegistroPelicula[contador][1] = pelicula;
        datosRegistroPelicula[contador][2] = horario;
        datosRegistroPelicula[contador][3] = Integer.toString(nBoletos);
        datosRegistroPelicula[contador][4] = "Datos adicionales";

        contador++;
    }

    private void ventaCombos() {
        ///////////////
        if (listaClientes.isEmpty()) {
            outputArea.append("No hay clientes para comprar snacks.\n");
            return;
        }

        // Solicitar combo
        String combo = (String) JOptionPane.showInputDialog(this, "Seleccione el combo:", "Combo", JOptionPane.QUESTION_MESSAGE, null, new String[]{(matCombos[0][0] + " " + matCombos[0][1] + " [$" + matCombos[0][2] + "]"), (matCombos[1][0] + " " + matCombos[1][1] + " [$" + matCombos[1][2] + "]"), (matCombos[2][0] + " " + matCombos[2][1] + " [$" + matCombos[2][2] + "]")}, "Combo ");
        if (combo == null) {
            outputArea.append("Compra de snacks cancelada.\n");
            return;
        }

        // Registrar compra de snacks para el último cliente en la lista
        Cliente cliente = listaClientes.get(listaClientes.size() - 1);

        outputArea.append("Cliente " + cliente.nombreCliente+ " compró el combo " + combo + ".\n");

        // Registro de combos (ejemplo)
        datosRegistroCombos[contador - 1][0] = cliente.nombreCliente;
        datosRegistroCombos[contador - 1][1] = combo;
        datosRegistroCombos[contador - 1][2] = "Datos adicionales";
    }

    private void agregarCliente() {
        if (listaClientes.isEmpty()) {
            outputArea.append("No hay más clientes.\n");
            return;
        }
        
        Cliente cliente = listaClientes.remove(0);
        outputArea.append("Atendiendo al siguiente cliente: " + cliente.nombreCliente+ "\n");
    }
   /* private void construirFactura(Boleto ventasBoletos){
       //boletos.construirFacctura();
       outputArea.append("FACTURA\n"+ventasBoletos.construirFacctura());
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CinemaGUI().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
