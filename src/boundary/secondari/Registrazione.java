package boundary.secondari;

import control.ControllerRegistraUtente;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;

public class Registrazione extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JLabel lblNewLabel_2;
    private JTextField txtCognome;
    private JLabel lblNewLabel_3;
    private JTextField txtEmail;
    private JLabel lblNewLabel_4;
    private JTextField txtUser;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JPasswordField pswField;
    private final ButtonGroup btnGroup = new ButtonGroup();


    /**
     * Create the frame.
     */
    public Registrazione() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 561, 436);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtNome = new JTextField();
        txtNome.setBounds(164, 61, 162, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblNewLabel = new JLabel("Inserisci i dati per la registrazione");
        lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        lblNewLabel.setBounds(104, 10, 375, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(42, 64, 45, 13);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Cognome");
        lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(42, 105, 71, 17);
        contentPane.add(lblNewLabel_2);

        txtCognome = new JTextField();
        txtCognome.setColumns(10);
        txtCognome.setBounds(164, 102, 162, 20);
        contentPane.add(txtCognome);

        lblNewLabel_3 = new JLabel("Email");
        lblNewLabel_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(42, 152, 45, 13);
        contentPane.add(lblNewLabel_3);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(164, 149, 162, 20);
        contentPane.add(txtEmail);

        lblNewLabel_4 = new JLabel("username");
        lblNewLabel_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(42, 202, 71, 13);
        contentPane.add(lblNewLabel_4);

        txtUser = new JTextField();
        txtUser.setColumns(10);
        txtUser.setBounds(164, 199, 162, 20);
        contentPane.add(txtUser);

        lblNewLabel_5 = new JLabel("password");
        lblNewLabel_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_5.setBounds(42, 254, 71, 17);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("Seleziona ruolo:");
        lblNewLabel_6.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_6.setBounds(42, 321, 114, 13);
        contentPane.add(lblNewLabel_6);

        JRadioButton rdbtnCliente = new JRadioButton("Cliente");
        btnGroup.add(rdbtnCliente);
        rdbtnCliente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        rdbtnCliente.setBounds(180, 317, 71, 21);
        contentPane.add(rdbtnCliente);

        JRadioButton rdbtnAmministratore = new JRadioButton("Amministratore");
        btnGroup.add(rdbtnAmministratore);
        rdbtnAmministratore.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        rdbtnAmministratore.setBounds(267, 317, 127, 21);
        contentPane.add(rdbtnAmministratore);

        JRadioButton rdbtnVeterinario = new JRadioButton("Veterinario");
        btnGroup.add(rdbtnVeterinario);
        rdbtnVeterinario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        rdbtnVeterinario.setBounds(418, 317, 111, 21);
        contentPane.add(rdbtnVeterinario);

        JButton btnConferma = new JButton("Conferma");
        btnConferma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nome = txtNome.getText().trim();;
                String cognome = txtCognome.getText().trim();;
                String email = txtEmail.getText().trim();;
                String user = txtUser.getText().trim();;
                String psw = new String(pswField.getPassword());
                String ruolo = new String();

                //controllo del ruolo in base al radioButton selezionato
                if(rdbtnCliente.isSelected()) {
                    ruolo = "Cliente";
                } else if(rdbtnAmministratore.isSelected()) {
                    ruolo = "Amministratore";
                } else if(rdbtnVeterinario.isSelected()) {
                    ruolo = "Veterinario";
                } else {
                    JOptionPane.showMessageDialog(null, "Devi selezionare un ruolo");
                }

                if(validazioneDati(nome, cognome, user, email, psw)){
                    String s = ControllerRegistraUtente.verificaRuoloUtente_scriviUtente(nome, cognome, user, email, psw, ruolo );
                    JOptionPane.showMessageDialog(null, s);
                }

            }
        });
        btnConferma.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnConferma.setBounds(267, 369, 97, 21);
        contentPane.add(btnConferma);

        JButton btnCancellaDati = new JButton("Cancella Dati");
        btnCancellaDati.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Svuota le JTextField
                txtNome.setText("");
                txtCognome.setText("");
                txtEmail.setText("");
                txtUser.setText("");
                pswField.setText("");
                btnGroup.clearSelection();
            }
        });
        btnCancellaDati.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnCancellaDati.setBounds(395, 369, 134, 21);
        contentPane.add(btnCancellaDati);

        pswField = new JPasswordField();
        pswField.setBounds(164, 255, 162, 19);
        contentPane.add(pswField);
    }

    public boolean validazioneDati(String nome, String cognome, String user, String email, String psw) {
        boolean validazione = false;
        // Validazione dei dati
        if (nome.length() <= 1 || nome.length() >= 45 || !nome.matches("[a-zA-ZàèéìòùÀÈÉÌÒÙ\\s]+")) {
            JOptionPane.showMessageDialog(null, "Il nome deve contenere solo lettere e avere una lunghezza tra 2 e 44 caratteri.");
        }else if (cognome.length() <= 1 || cognome.length() >= 45 || !cognome.matches("[a-zA-ZàèéìòùÀÈÉÌÒÙ\\s]+")) {
            JOptionPane.showMessageDialog(null, "Il cognome deve contenere solo lettere e avere una lunghezza tra 2 e 44 caratteri.");
        }else if (user.length() <= 1 || user.length() >= 45 || user.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Lo username non deve contenere numeri e deve avere una lunghezza tra 2 e 44 caratteri.");
        }   else if (!email.contains("@") || email.indexOf("@") == 0 || email.indexOf("@") == email.length() - 1) {
            JOptionPane.showMessageDialog(null, "L'indirizzo email deve contenere una chiocciola '@' e non può iniziare o terminare con essa.");
        }else if (!psw.matches(".*\\d.*") || !psw.matches(".*[A-Z].*")|| !psw.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(null, "La password deve contenere almeno un numero, una lettera maiuscola e una lettera minuscola.");
        }else {
            // Se tutto è valido
            validazione = true;
        }
        return validazione;
    }
}
