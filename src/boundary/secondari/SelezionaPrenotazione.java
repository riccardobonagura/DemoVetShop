package boundary.secondari;

import DTOs.PrenotazioneDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SelezionaPrenotazione extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private int idPrenotazioneSelezionata = -1;
    private JTable tabella;


    /**
     * Create the dialog.
     */
    public SelezionaPrenotazione(ArrayList<PrenotazioneDTO> elencoPren) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            //Colonne della tabella
            String[] colonne = {"ID Prenotazione", "Giorno", "Chip Code"};

            //Modello dati della tabella con 0 righe iniziali
            DefaultTableModel modelloTabella = new DefaultTableModel(colonne, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; //Nessuna cella è modificabile
                }
            };

            //Popolamento del modello con i dati
            for (PrenotazioneDTO pren : elencoPren) {
                int idPrenotazione = pren.getId();
                LocalDateTime Giorno = pren.getDataOra();
                int chipCode = pren.getChipcode();

                Object[] rigaTab = {idPrenotazione, Giorno, chipCode};
                modelloTabella.addRow(rigaTab); // Aggiungo la riga alla tabella
            }

            //Creazione della JTable
            tabella = new JTable(modelloTabella);
            //per impostare una sola riga selezionabile
            tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(tabella);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selectedRow = tabella.getSelectedRow();
                        if (selectedRow >= 0) {
                            //salviamo l'ID
                            idPrenotazioneSelezionata = (int) tabella.getValueAt(selectedRow, 0);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(SelezionaPrenotazione.this,
                                    "Seleziona una riga prima di premere OK.", "Attenzione",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                okButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dispose();
                    }
                });
                cancelButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
    public int getIdPrenotazioneSelezionata() {
        return idPrenotazioneSelezionata;
    }

}
