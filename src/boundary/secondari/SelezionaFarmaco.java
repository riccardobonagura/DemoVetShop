package boundary.secondari;

import DTOs.FarmacoDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SelezionaFarmaco extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    ArrayList<Integer> idFarmaciSelezionati = new ArrayList<>();
    ArrayList<String> nomeFarmaciSelezionati = new ArrayList<>();
    private JTable tabella;

    /**
     * Create the dialog.
     */
    public SelezionaFarmaco(ArrayList<FarmacoDTO> elencoFarm) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            //Colonne della tabella
            String[] colonne = {"ID Farmaco", "Nome", "Produttore"};

            //Modello dati della tabella con 0 righe iniziali
            DefaultTableModel modelloTabella = new DefaultTableModel(colonne, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; //Nessuna cella è modificabile
                }
            };

            //Popolamento del modello con i dati
            for (FarmacoDTO farmaco : elencoFarm) {
                int idFarmaco = farmaco.getIdFarmaco();
                String nome = farmaco.getNome();
                String produttore = farmaco.getProduttore();

                Object[] rigaTab = {idFarmaco, nome, produttore};
                //Aggiungo la riga alla tabella
                modelloTabella.addRow(rigaTab);
            }

            //Creazione della JTable
            tabella = new JTable(modelloTabella);
            //per impostare una sola riga selezionabile
            tabella.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

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
                        int[] righeSelezionate = tabella.getSelectedRows();
                        if (righeSelezionate.length >= 0) {
                            for (int i= 0; i<righeSelezionate.length; i++) {
                                idFarmaciSelezionati.add((int) tabella.getValueAt(i, 0));
                                nomeFarmaciSelezionati.add((String) tabella.getValueAt(i, 1));
                            }
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(SelezionaFarmaco.this,
                                    "Seleziona una riga prima di premere OK, altrimenti premi Cancel", "Attenzione",
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
    public ArrayList<Integer> getIdFarmaciSelezionati() {
        return idFarmaciSelezionati;
    }

    public ArrayList<String> getNomeFarmaciSelezionati() {
        return nomeFarmaciSelezionati;
    }

}
