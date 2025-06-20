package boundary.secondari;


import DTOs.AnimaleDomesticoDTO;
import control.ControllerPrenotazione;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RichiediPrenotazione extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEmail;
    private LocalDateTime datas;
    private int chipCode;


    /**
     * Create the frame.
     */
    public RichiediPrenotazione() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 585, 344);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Effettua una prenotazione");
        lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        lblNewLabel.setBounds(135, 23, 278, 36);
        contentPane.add(lblNewLabel);

        txtEmail = new JTextField();
        txtEmail.setBounds(125, 92, 189, 19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Email");
        lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(37, 91, 51, 19);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Seleziona l'animale per cui effettuare la prenotazione");
        lblNewLabel_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(37, 155, 351, 19);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Seleziona la data");
        lblNewLabel_1_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        lblNewLabel_1_1_1.setBounds(37, 215, 128, 19);
        contentPane.add(lblNewLabel_1_1_1);

        //bottone per la scelta dell'animale
        JButton btnAnimale = new JButton("Seleziona Animale");
        btnAnimale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //mi creo un array su cui vado a chiamare il metodo del controller
                ArrayList<AnimaleDomesticoDTO> lista_animali = new ArrayList<>();

                //controlli sulla corretta immissione della mail
                String email = txtEmail.getText();
                if (!email.contains("@") || email.indexOf("@") == 0 || email.indexOf("@") == email.length() - 1) {
                    JOptionPane.showMessageDialog(RichiediPrenotazione.this, "L'indirizzo email deve contenere una chiocciola '@' e non può iniziare o terminare con essa.");
                }

                //caricamento della lista degli animali dal database
                lista_animali = ControllerPrenotazione.caricaAnimaliDomestici(email);

                if(lista_animali.isEmpty()){
                    JOptionPane.showMessageDialog(RichiediPrenotazione.this, "Indirizzo email non esistente o nessun animale ancora registrato");
                } else{
                    txtEmail.setEditable(false);

                    SelezionaAnimale dialog = new SelezionaAnimale(lista_animali);
                    //per centrarlo sul form principale
                    dialog.setLocationRelativeTo(RichiediPrenotazione.this);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                    chipCode = dialog.getChipcode();
                }
            }
        });
        btnAnimale.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnAnimale.setBounds(397, 152, 164, 25);
        contentPane.add(btnAnimale);

        //bottone per la scelta dello slot orario
        JButton btnSelezionaData = new JButton("Seleziona Data");
        btnSelezionaData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //creazione di un array su cui chiamare il metodo del controller
                ArrayList<LocalDateTime> dateNonDisponibili = new ArrayList<>();
                dateNonDisponibili = ControllerPrenotazione.caricaDateNonDisponibili();

                SelezionaData dialog = new SelezionaData(dateNonDisponibili);
                //per centrarlo sul form principale
                dialog.setLocationRelativeTo(RichiediPrenotazione.this);
                dialog.setModal(true);
                dialog.setVisible(true);
                datas = dialog.getData();
            }
        });
        btnSelezionaData.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnSelezionaData.setBounds(204, 212, 142, 25);
        contentPane.add(btnSelezionaData);

        JButton btnRiepilogo = new JButton("Avanti");
        btnRiepilogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Mostriamo il riepilogo e la conferma
                String messaggio = "Riepilogo dati inseriti:\n" +
                        "ChipCode animale: " + chipCode + "\n" +
                        "Data selezionata: " + datas + "\n" +
                        "Confermi l'inserimento?";

                int scelta = JOptionPane.showConfirmDialog(
                        RichiediPrenotazione.this,
                        messaggio,
                        "Conferma Inserimento",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (scelta == JOptionPane.YES_OPTION) {
                    String s = ControllerPrenotazione.scriviPrenotazione(chipCode, datas);
                    JOptionPane.showMessageDialog(null, s);
                    dispose();
                }

            }
        });
        btnRiepilogo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnRiepilogo.setBounds(325, 272, 113, 25);
        contentPane.add(btnRiepilogo);

        JButton btnCancella = new JButton("Cancella");
        btnCancella.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtEmail.setText("");
                txtEmail.setEditable(true);
            }
        });
        btnCancella.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnCancella.setBounds(448, 272, 113, 25);
        contentPane.add(btnCancella);
    }
}
