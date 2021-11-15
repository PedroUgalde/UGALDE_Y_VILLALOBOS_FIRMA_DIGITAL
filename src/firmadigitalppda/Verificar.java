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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controladores.Controlador;

@SuppressWarnings("serial")
public class Verificar extends JFrame implements ActionListener {
    public static final String SUBIR_ARCHIVO = "Explorar archivo";
    public static final String SUBIR_FIRMA = "Explorar firma";
    public static final String SUBIR_CLAVE_PUBLICA = "Explorar clave";
    public static final String COMPROBAR = "Comprobar";
    private JTextField txtSubirArchivo;
    private JTextField txtSubirFirma;
    private JTextField txtSubirClavePublica;
    private FirmaDigitalPPDA inicio;
    private String rutaArchivo;
    private String rutaFirma;
    private String rutaClavePublica;

    public Verificar(FirmaDigitalPPDA inicio) {
        this.inicio = inicio;
        rutaArchivo = "";
        rutaFirma = "";
        rutaClavePublica = "";

        setTitle("Comprobar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 299);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        JButton butSubirArchivo = new JButton("Explorar archivo");
        butSubirArchivo.setActionCommand(SUBIR_ARCHIVO);
        butSubirArchivo.addActionListener((ActionListener) this);
        butSubirArchivo.setBounds(10, 56, 190, 23);
        contentPane.add(butSubirArchivo);

        JButton butSubFirma = new JButton("Explorar firma");
        butSubFirma.setActionCommand(SUBIR_FIRMA);
        butSubFirma.addActionListener((ActionListener) this);
        butSubFirma.setBounds(10, 102, 190, 23);
        contentPane.add(butSubFirma);

        JButton butSubClavePublica = new JButton("Explorar clave");
        butSubClavePublica.setActionCommand(SUBIR_CLAVE_PUBLICA);
        butSubClavePublica.addActionListener((ActionListener) this);
        butSubClavePublica.setBounds(10, 156, 190, 23);
        contentPane.add(butSubClavePublica);

        JButton butComprobar = new JButton("Comprobar");
        butComprobar.setActionCommand(COMPROBAR);
        butComprobar.addActionListener((ActionListener) this);
        butComprobar.setBounds(10, 238, 190, 23);
        contentPane.add(butComprobar);

        JButton butMenuPrincipal = new JButton("←");
        butMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.setVisible(true);
                dispose();
            }
        });
        butMenuPrincipal.setBounds(10, 10, 190, 23);
        contentPane.add(butMenuPrincipal);

        txtSubirArchivo = new JTextField();
        txtSubirArchivo.setEditable(false);
        txtSubirArchivo.setBounds(294, 57, 190, 23);
        contentPane.add(txtSubirArchivo);
        txtSubirArchivo.setColumns(10);

        txtSubirFirma = new JTextField();
        txtSubirFirma.setEditable(false);
        txtSubirFirma.setColumns(10);
        txtSubirFirma.setBounds(294, 103, 190, 23);
        contentPane.add(txtSubirFirma);

        txtSubirClavePublica = new JTextField();
        txtSubirClavePublica.setEditable(false);
        txtSubirClavePublica.setColumns(10);
        txtSubirClavePublica.setBounds(294, 157, 190, 23);
        contentPane.add(txtSubirClavePublica);
    }

    public void subirArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Explorador de archivos");

        int seleccion = fc.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            rutaArchivo = fichero.getAbsolutePath();
            txtSubirArchivo.setText(fichero.getName());
        }
    }

    public void subirFirma() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Explorador de archivos");
        int seleccion = fc.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            rutaFirma = fichero.getAbsolutePath();
            txtSubirFirma.setText(fichero.getName());
        }
    }

    public void subirClavePublica() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Explorador de archivos");
        int seleccion = fc.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            rutaClavePublica = fichero.getAbsolutePath();
            txtSubirClavePublica.setText(fichero.getName());
        }
    }

    public void comprobar() {
        if (rutaArchivo.equals("") || rutaFirma.equals("") || rutaClavePublica.equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Controlador controlador = inicio.getControlador();
            try {
                boolean respuesta = controlador.validarFirma(rutaArchivo, rutaFirma, rutaClavePublica);
                if (respuesta) {
                    JOptionPane.showMessageDialog(this, "Autenticidad confirmada.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(this, "Autenticidad no confirmada. Documento comprometido.", "Resultado", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Firma incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void limpiar() {
        rutaArchivo = "";
        rutaFirma = "";
        rutaClavePublica = "";
        txtSubirArchivo.setText("");
        txtSubirFirma.setText("");
        txtSubirClavePublica.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if (comando.equals(SUBIR_ARCHIVO)) {
            subirArchivo();
        } else if (comando.equals(SUBIR_FIRMA)) {
            subirFirma();
        } else if (comando.equals(SUBIR_CLAVE_PUBLICA)) {

            subirClavePublica();
        } else if (comando.equals(COMPROBAR)) {
            comprobar();
        }
    }
}