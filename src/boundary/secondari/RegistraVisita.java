package boundary.secondari;

import DTOs.FarmacoDTO;
import DTOs.PrenotazioneDTO;
import control.ControllerRegistraVisita;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RegistraVisita extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTipo;
    private JTextField txtCosto;
    private int idP = -1;
    ArrayList<Integer> idF = new ArrayList<>();
    ArrayList<String> nomeF = new ArrayList<>();
    /**
     * Create the frame.
     */
    public RegistraVisita() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 574, 434);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Inserisci i dati per la registrazione");
        lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        lblNewLabel.setBounds(94, 10, 375, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tipo Visita");
        lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(32, 111, 97, 17);
        contentPane.add(lblNewLabel_1);

        txtTipo = new JTextField();
        txtTipo.setColumns(10);
        txtTipo.setBounds(154, 108, 162, 20);
        contentPane.add(txtTipo);

        JLabel lblNewLabel_2 = new JLabel("Descrizione");
        lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(32, 153, 97, 17);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Costo");
        lblNewLabel_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(32, 268, 45, 13);
        contentPane.add(lblNewLabel_3);

        txtCosto = new JTextField();
        txtCosto.setColumns(10);
        txtCosto.setBounds(154, 265, 162, 20);
        contentPane.add(txtCosto);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(154, 153, 162, 81);
        contentPane.add(scrollPane);

        JTextArea txtDescrizione = new JTextArea();
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        scrollPane.setViewportView(txtDescrizione);

        JButton btnRiepilogo = new JButton("Riepilogo Dati");
        btnRiepilogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String TipoV = txtTipo.getText();
                String Descrizione = txtDescrizione.getText();
                String Costo = txtCosto.getText();
                String messaggio = "";

                //Se i dati inseriti sono validi Mostriamo il riepilogo e la conferma
                if (ValidazioneDati(idP, TipoV, Descrizione, Costo)) {
                    if (idF.size()>0) {
                        messaggio = "Riepilogo dati inseriti:\n" +
                                "Id Prenotazione di riferimento: " + idP + "\n" +
                                "Tipo Visita: " + TipoV + "\n" +
                                "Descrizione della visita: " + Descrizione + "\n" +
                                "Costo della visita: " + Costo + "\n" +
                                "Farmaci Selezionati: " + nomeF + "\n\n" +
                                "Confermi l'inserimento?";
                    }else {
                        messaggio = "Riepilogo dati inseriti:\n" +
                                "Id Prenotazione di riferimento: " + idP + "\n" +
                                "Tipo Visita: " + TipoV + "\n" +
                                "Descrizione della visita: " + Descrizione + "\n" +
                                "Costo della visita: " + Costo + "\n" +
                                "Nessun Farmaco Selezionato"+ "\n\n" +
                                "Confermi l'inserimento?";
                    }

                    int scelta = JOptionPane.showConfirmDialog(
                            RegistraVisita.this,
                            messaggio,
                            "Conferma Inserimento",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    if (scelta == JOptionPane.YES_OPTION) {

                        String s = ControllerRegistraVisita.scriviVisita(idP, TipoV, Double.parseDouble(Costo), Descrizione, idF);
                        JOptionPane.showMessageDialog(null, s);
                    }
                }
            }
        });
        btnRiepilogo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnRiepilogo.setBounds(235, 366, 134, 21);
        btnRiepilogo.setFocusPainted(false);
        contentPane.add(btnRiepilogo);

        JButton btnCancellaDati = new JButton("Cancella Dati");
        btnCancellaDati.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Svuota le JTextField
                txtTipo.setText("");
                txtCosto.setText("");
                txtDescrizione.setText("");
            }
        });
        btnCancellaDati.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnCancellaDati.setBounds(402, 366, 134, 21);
        btnCancellaDati.setFocusPainted(false);
        contentPane.add(btnCancellaDati);

        JButton btnPrenotazione = new JButton("Seleziona Prenotazione");
        btnPrenotazione.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //creo ArrayList vuoto
                ArrayList<PrenotazioneDTO> elencoPren = new ArrayList<>();

                //ArrayList che ci restituisce le prenotazioni memorizzate nel DB
                elencoPren = ControllerRegistraVisita.caricaListaPrenotazioni();

                //Istanzio e mostro il dialog
                SelezionaPrenotazione dialog = new SelezionaPrenotazione(elencoPren);
                //per centrarlo sul form principale
                dialog.setLocationRelativeTo(RegistraVisita.this);
                dialog.setModal(true);
                dialog.setVisible(true);

                //ci prendiamo l'id selezionato
                idP = dialog.getIdPrenotazioneSelezionata();
            }
        });

        btnPrenotazione.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnPrenotazione.setBounds(330, 62, 190, 21);
        contentPane.add(btnPrenotazione);

        JLabel lblNewLabel_1_1 = new JLabel("Seleziona la prenotaziona di riferimento");
        lblNewLabel_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(34, 61, 282, 21);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Seleziona farmaco prescritto");
        lblNewLabel_1_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblNewLabel_1_1_1.setBounds(32, 310, 216, 21);
        contentPane.add(lblNewLabel_1_1_1);

        JButton btnSelezionaFarmaco = new JButton("Seleziona Farmaco");
        btnSelezionaFarmaco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //creo ArrayList vuoto
                ArrayList<FarmacoDTO> elencoFarm = new ArrayList<>();

                //ArrayList che ci restituisce le prenotazioni memorizzate nel DB
                elencoFarm = ControllerRegistraVisita.caricaListaFarmaci();

                //Istanzio e mostro il dialog
                SelezionaFarmaco dialog = new SelezionaFarmaco(elencoFarm);
                //per centrarlo sul form principale
                dialog.setLocationRelativeTo(RegistraVisita.this);
                dialog.setModal(true);
                dialog.setVisible(true);

                //ci prendiamo l'id selezionato
                idF = dialog.getIdFarmaciSelezionati();
                nomeF = dialog.getNomeFarmaciSelezionati();
            }
        });

        btnSelezionaFarmaco.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnSelezionaFarmaco.setBounds(293, 311, 190, 21);
        contentPane.add(btnSelezionaFarmaco);

    }

    public boolean ValidazioneDati(int idP, String TipoVisita, String Descrizione, String Costo) {
        boolean validi = false;

        //Controllo presenza prenotazione
        if (idP == -1){
            JOptionPane.showMessageDialog(null, "Errore: Devi inserire la prenotazione di riferimento");
        } else
            // Controllo tipoVisita
            if (TipoVisita == null || TipoVisita.length() < 2 || TipoVisita.length() > 200 || !TipoVisita.matches("^[a-zA-Z0-9 ]+$")){
                JOptionPane.showMessageDialog(null, "Errore: 'Tipo visita' deve avere tra 2 e 200 caratteri.");
            } else
                //Controllo Descrizione
                if (Descrizione == null || Descrizione.length() < 2 || Descrizione.length() > 500 || !Descrizione.matches("^[a-zA-Z0-9 ]+$")){
                    JOptionPane.showMessageDialog(null, "Errore: 'Descrizione' deve avere tra 2 e 500 caratteri.");
                } else
                    //Controllo Costo
                    if (Costo == null || !Costo.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null,"Errore: 'Costo' deve contenere solo cifre positive (senza simboli).");
                    } else {
                        int valoreCosto = Integer.parseInt(Costo);
                        if (valoreCosto <= 0) {
                            JOptionPane.showMessageDialog(null,"Errore: 'Costo' deve essere maggiore di zero.");
                        }
                        validi = true;
                    }

        return validi;
    }

}

