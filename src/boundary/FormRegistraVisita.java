package boundary;

import boundary.secondari.RegistraVisita;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormRegistraVisita extends JFrame {
    //classe che si occupa della corretta visualizzazione della GUI
    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormRegistraVisita frame = new FormRegistraVisita();
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
    public FormRegistraVisita() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/images/HomeRegistraVisita.png"));
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

        JButton btnVisita = new JButton("Registra Visita");
        btnVisita.setBackground(new Color(5, 51, 102));
        btnVisita.setBounds(250, 256, 157, 52);
        btnVisita.setForeground(Color.WHITE);
        btnVisita.setFocusPainted(false);
        contentPane.add(btnVisita);
        btnVisita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistraVisita frame = new RegistraVisita();
                frame.setVisible(true);
            }
        });
        btnVisita.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
    }
}
