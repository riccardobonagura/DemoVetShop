package boundary;

import boundary.secondari.SelezionaSlot;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormSelezionaSlot extends JFrame {
    //classe che si occupa della corretta visualizzazione della GUI
    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormSelezionaSlot frame = new FormSelezionaSlot();
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
    public FormSelezionaSlot() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/images/HomeSelezionaSlot.png"));
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
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Pannello centrale per i testi
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, 0, 0);
        centerPanel.setOpaque(false);
        centerPanel.setBorder(null);
        contentPane.add(centerPanel);
        centerPanel.setLayout(null);

        JButton btnSelSlot = new JButton("Seleziona Slot");
        btnSelSlot.setBackground(new Color(5, 51, 102));
        btnSelSlot.setBounds(267, 272, 157, 52);
        btnSelSlot.setForeground(Color.WHITE);
        btnSelSlot.setFocusPainted(false);
        contentPane.add(btnSelSlot);
        btnSelSlot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelezionaSlot dialog = new SelezionaSlot();
                //per centrarlo sul form principale
                dialog.setLocationRelativeTo(FormSelezionaSlot.this);
                dialog.setModal(true);
                dialog.setVisible(true);
                dispose();
            }
        });
        btnSelSlot.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));

        JTextArea txtSlot = new JTextArea();
        txtSlot.setWrapStyleWord(true);
        txtSlot.setForeground(new Color(5, 51, 102));
        txtSlot.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
        txtSlot.setLineWrap(true);
        txtSlot.setText("Seleziona gli slot orari per cui non è possibile                   effettuare una prenotazione");
        txtSlot.setBounds(61, 197, 565, 68);
        txtSlot.setOpaque(false);
        txtSlot.setBorder(null);
        txtSlot.setHighlighter(null);
        txtSlot.setEditable(false);
        txtSlot.setFocusable(false);
        txtSlot.setBackground(new Color(0, 0, 0, 0));
        contentPane.add(txtSlot);
    }
}
