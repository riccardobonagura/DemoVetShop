package boundary;


import boundary.secondari.Registrazione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormRegistrazioneUtente extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormRegistrazioneUtente frame = new FormRegistrazioneUtente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FormRegistrazioneUtente() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/images/RegistrazioneUtente.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        //Pannello centrale per i testi
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setBorder(null);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(null);

        // Testo descrittivo
        JTextArea subText = new JTextArea( "            Gestisci comodamente le visite del tuo animale\n" +
                "                               registrandoti sul nostro sito.\n" +
                "               Il benessere del tuo amico a quattro zampe \n" +
                "                                       è a portata di mano!"
        );
        subText.setBounds(100, 227, 586, 116);
        subText.setForeground(Color.WHITE);
        subText.setEnabled(false);
        subText.setBackground(Color.WHITE);
        centerPanel.add(subText);
        subText.setWrapStyleWord(true);
        subText.setLineWrap(true);
        subText.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        subText.setOpaque(false);
        subText.setFocusable(false);
        subText.setHighlighter(null);
        subText.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Testo principale
        JTextArea mainText = new JTextArea("Prenota la tua visita in pochi click!");
        mainText.setBounds(137, 185, 513, 32);
        mainText.setForeground(Color.WHITE);
        centerPanel.add(mainText);
        mainText.setEditable(false);
        mainText.setWrapStyleWord(true);
        mainText.setLineWrap(true);
        mainText.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
        mainText.setOpaque(false);
        mainText.setFocusable(false);
        mainText.setHighlighter(null);
        mainText.setBorder(null);

        JButton btnRegistrazione = new JButton("Registrazione");
        btnRegistrazione.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registrazione frame = new Registrazione();
                frame.setVisible(true);
                dispose();
            }
        });
        btnRegistrazione.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        btnRegistrazione.setBounds(188, 380, 152, 46);
        btnRegistrazione.setBackground(new Color(5, 51, 102));
        btnRegistrazione.setForeground(Color.WHITE);
        btnRegistrazione.setFocusPainted(false);
        centerPanel.add(btnRegistrazione);

        JButton btnAccedi = new JButton("Accedi");
        btnAccedi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(FormRegistrazioneUtente.this, "Spiacenti, questa funzionalità ancora non è stata implementata :(");
            }
        });
        btnAccedi.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        btnAccedi.setBounds(445, 380, 152, 46);
        btnAccedi.setFocusPainted(false);
        btnAccedi.setBackground(new Color(5, 51, 102));
        btnAccedi.setForeground(Color.WHITE);
        centerPanel.add(btnAccedi);
    }
}
