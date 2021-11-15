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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controladores.Controlador;

@SuppressWarnings("serial")

public class FirmaDigitalPPDA extends JFrame{
private Controlador controlador;

    public FirmaDigitalPPDA() {
        controlador = new Controlador();
        setTitle("Menú de selección");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLayout(null);
        contentPane.setBackground(Color.WHITE);

        Generar generadorClaves = new Generar(this);
        Firma firmador = new Firma(this);
        Verificar comprobador = new Verificar(this);

        JButton butGeneradorClaves = new JButton("Generar");
        butGeneradorClaves.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generadorClaves.setVisible(true);
                dispose();
            }
        });
        butGeneradorClaves.setBounds(116, 60, 207, 23);
        contentPane.add(butGeneradorClaves);

        JButton butFirmador = new JButton("Firmar");
        butFirmador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firmador.setVisible(true);
                dispose();
            }
        });
        butFirmador.setBounds(116, 120, 207, 23);
        contentPane.add(butFirmador);

        JButton butComprobador = new JButton("Comprobar");
        butComprobador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprobador.setVisible(true);
                dispose();
            }
        });
        butComprobador.setBounds(116, 180, 207, 23);
        contentPane.add(butComprobador);
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public static void FirmaDigitalPPDA(String[] args) {
        menu window = new menu();
        window.setVisible(true);
    }
    
}
