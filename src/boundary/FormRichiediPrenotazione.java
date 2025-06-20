package boundary;



import boundary.secondari.RichiediPrenotazione;
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

public class FormRichiediPrenotazione extends JFrame {

    //classe che si occupa della corretta visualizzazione della GUI
    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormRichiediPrenotazione frame = new FormRichiediPrenotazione();
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
    public FormRichiediPrenotazione() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    //immagine
                    backgroundImage = ImageIO.read(getClass().getResource("/images/HomeRichiediPrenotazione.png"));
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

        JButton btnPrenota = new JButton("");
        btnPrenota.setBounds(123, 331, 199, 65);
        contentPane.add(btnPrenota);
        btnPrenota.setOpaque(false);
        btnPrenota.setContentAreaFilled(false);
        btnPrenota.setBorderPainted(false);
        btnPrenota.setFocusPainted(false);
        btnPrenota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RichiediPrenotazione frame = new RichiediPrenotazione();
                frame.setVisible(true);
            }
        });
        btnPrenota.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
    }
}
