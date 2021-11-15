/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigitalppda;

/**
 *
 * @author aza06
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import controladores.Controlador;

@SuppressWarnings("serial")
public class Generar extends JFrame implements ActionListener {

    public static final String GENERAR_CLAVES = "Generar";
    public static final String EXPORTAR_CLAVE_PUBLICA = "Exportar";
    private JPasswordField passFieldContrasena;
    private JPasswordField passFieldIngreseNuevamenteContrasena;
    private FirmaDigitalPPDA inicio;

    public Generar(FirmaDigitalPPDA inicio) {
        this.inicio = inicio;
        setTitle("Generar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 522, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        JLabel labContrasena = new JLabel("Contraseña:");
        labContrasena.setBounds(30, 72, 261, 20);
        contentPane.add(labContrasena);
        JLabel labConfirmarContrasena = new JLabel("Confirmar contraseña:");
        labConfirmarContrasena.setBounds(30, 113, 261, 20);
        contentPane.add(labConfirmarContrasena);
        JButton butGenerarClaves = new JButton("Generar");
        butGenerarClaves.setBounds(30, 161, 162, 20);
        contentPane.add(butGenerarClaves);
        butGenerarClaves.setActionCommand(GENERAR_CLAVES);
        butGenerarClaves.addActionListener((ActionListener) this);
        passFieldContrasena = new JPasswordField();
        passFieldContrasena.setBounds(301, 72, 153, 20);
        contentPane.add(passFieldContrasena);
        passFieldIngreseNuevamenteContrasena = new JPasswordField();
        passFieldIngreseNuevamenteContrasena.setBounds(301, 113, 153, 20);
        contentPane.add(passFieldIngreseNuevamenteContrasena);
        JButton butExportarClavePublica = new JButton("Exportar");
        butExportarClavePublica.setBounds(30, 209, 162, 20);
        contentPane.add(butExportarClavePublica);
        butExportarClavePublica.setActionCommand(EXPORTAR_CLAVE_PUBLICA);
        butExportarClavePublica.addActionListener((ActionListener) this);
        JButton butIrAFirma = new JButton("←");
        butIrAFirma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                inicio.setVisible(true);
                dispose();
            }
        });
        butIrAFirma.setBounds(30, 10, 162, 20);
        contentPane.add(butIrAFirma);
    }

    public void generarClaves() {
        Controlador controlador = inicio.getControlador();
        String pass1 = String.valueOf(passFieldContrasena.getPassword());
        String pass2 = String.valueOf(passFieldIngreseNuevamenteContrasena.getPassword());

        if (pass1.equals("") || pass2.equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (pass1.equals(pass2) == false) {
                JOptionPane.showMessageDialog(this, "Verifica que las contraseñas coincidan", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                controlador.generarClave(pass1);
                JOptionPane.showMessageDialog(this, "¡Operación exitosa!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                passFieldContrasena.setText("");
                passFieldIngreseNuevamenteContrasena.setText("");
            }
        }
    }

    public void exportarClavePublica() {
        Controlador controlador = inicio.getControlador();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ingresa la contraseña:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Aceptar", "Cancelar"};
        int option = JOptionPane.showOptionDialog(null, panel, "Exportación", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if (option == 0) {
            String password = new String(pass.getPassword());
            JOptionPane.showMessageDialog(this, "Elige la ubicación del archivo.");
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Explorador de archivos");

            int seleccion = fc.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fc.getSelectedFile();
                String rutaClavePublica = fichero.getAbsolutePath();
                try {
                    controlador.exportarClavePublica(rutaClavePublica, password);
                    JOptionPane.showMessageDialog(this, "¡Operación exitosa!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error. Intenta de nuevo.", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals(GENERAR_CLAVES)) {
            generarClaves();
        } else if (comando.equals(EXPORTAR_CLAVE_PUBLICA)) {
            exportarClavePublica();
        }
    }
}

