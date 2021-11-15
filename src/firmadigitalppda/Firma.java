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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controladores.Controlador;

@SuppressWarnings("serial")
public class Firma extends JFrame implements ActionListener{
    
    public static final String SUBIR_ARCHIVO = "Explorar";
    public static final String FIRMAR_ARCHIVO = "Firmar";
    private JPasswordField passFieldContrasena;
    private JTextField txtNombreDocumento;
    private FirmaDigitalPPDA inicio;
    private String rutaArchivo;

    public Firma(FirmaDigitalPPDA inicio) {

        this.inicio = inicio;
        rutaArchivo = "";

        setTitle("Firmar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 522, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        JButton butSubirDocumento = new JButton("Explorar");
        butSubirDocumento.setActionCommand(SUBIR_ARCHIVO);
        butSubirDocumento.addActionListener((ActionListener) this);
        butSubirDocumento.setBounds(30, 72, 162, 20);
        contentPane.add(butSubirDocumento);

        passFieldContrasena = new JPasswordField();
        passFieldContrasena.setBounds(301, 113, 153, 20);
        contentPane.add(passFieldContrasena);

        JLabel labIngreseContrasena = new JLabel("Contraseña:");
        labIngreseContrasena.setBounds(30, 113, 261, 20);
        contentPane.add(labIngreseContrasena);

        JButton butFirmarDocumento = new JButton("Firmar");
        butFirmarDocumento.setActionCommand(FIRMAR_ARCHIVO);
        butFirmarDocumento.addActionListener((ActionListener) this);
        butFirmarDocumento.setBounds(30, 161, 162, 20);
        contentPane.add(butFirmarDocumento);

        txtNombreDocumento = new JTextField();
        txtNombreDocumento.setEditable(false);
        txtNombreDocumento.setBounds(301, 72, 153, 20);
        contentPane.add(txtNombreDocumento);
        txtNombreDocumento.setColumns(10);

        JButton butMenuPrincipal = new JButton("←");
        butMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.setVisible(true);
                dispose();
            }
        });
        butMenuPrincipal.setBounds(30, 10, 162, 20);
        contentPane.add(butMenuPrincipal);
    }

    public void subirArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Explorador de archivos");

        int seleccion = fc.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            rutaArchivo = fichero.getAbsolutePath();
            txtNombreDocumento.setText(fichero.getName());

        }
    }

    public void firmarArchivo() {
        String password = new String(passFieldContrasena.getPassword());
        if (rutaArchivo.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Controlador controlador = inicio.getControlador();

            JOptionPane.showMessageDialog(this, "Por favor, elige una ubicación.");
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Guardar");
            int seleccion = fc.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fc.getSelectedFile();
                String rutaFirma = fichero.getAbsolutePath();
                try {
                    controlador.firmarArchivo(rutaArchivo, rutaFirma, password);
                    JOptionPane.showMessageDialog(this, "¡Operación exitosa!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    rutaArchivo = "";
                    txtNombreDocumento.setText("");
                    passFieldContrasena.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error. Intenta de nuevo.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals(SUBIR_ARCHIVO)) {
            subirArchivo();
        } else if (comando.equals(FIRMAR_ARCHIVO)) {
            firmarArchivo();
        }
    }
    
}
