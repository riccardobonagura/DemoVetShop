package boundary.secondari;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import control.ControllerSlotBloccati;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JCheckBox;

public class SelezionaSlot extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private final JPanel pannelloCombo;
    private final List<DatePicker> datePickers = new ArrayList<>();
    private final List<TimePicker> timePickers = new ArrayList<>();
    ArrayList<LocalDateTime> slotSalvati = new ArrayList<>();
    private JCheckBox ultimoCheckbox;
    /**
     * Create the dialog.
     */
    public SelezionaSlot() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            pannelloCombo = new JPanel();
            pannelloCombo.setLayout(new BoxLayout(pannelloCombo, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(pannelloCombo);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            getContentPane().add(scrollPane, BorderLayout.CENTER);

            //Aggiungiamo una prima combinazione per la selzione di default
            aggiungiComboDefault();

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton okButton = new JButton("Aggiungi nuovo slot");
            okButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Recupera l'ultimo DatePicker, TimePicker
                    if (!datePickers.isEmpty() && !timePickers.isEmpty()) {
                        DatePicker ultimoDP = datePickers.get(datePickers.size() - 1);
                        TimePicker ultimoTP = timePickers.get(timePickers.size() - 1);

                        LocalDate data = ultimoDP.getDate();
                        LocalTime ora = ultimoTP.getTime();

                        //controlliamo che abbia inserito tutto
                        if (data == null) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "Selezionare una data");
                        } else if (!(ultimoCheckbox != null && ultimoCheckbox.isSelected()) && (ora == null)) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "Inserire un orario o spuntare l'opzione per l'intera giornata");
                        } else if (!(ultimoCheckbox != null && ultimoCheckbox.isSelected())) {
                            if (LocalDateTime.of(data, ora).isBefore(LocalDateTime.now())) {
                                JOptionPane.showMessageDialog(SelezionaSlot.this, "La data e l'ora selezionate non possono essere nel passato!");
                            }  else if (slotSalvati.contains(LocalDateTime.of(data, ora))) {
                                JOptionPane.showMessageDialog(SelezionaSlot.this, "Errore! Data o Slot Orario già selezionato");
                            } else {
                                slotSalvati.add(LocalDateTime.of(data, ora));
                                aggiungiComboDefault();
                            }
                        } else {
                            if (data.isBefore(LocalDate.now()) && (ultimoCheckbox != null && ultimoCheckbox.isSelected())) {
                                JOptionPane.showMessageDialog(SelezionaSlot.this, "La data selezionata non può essere nel passato!");
                            } else {

                                for (int i = 8; i < 18; i++) {
                                    if (slotSalvati.contains(LocalDateTime.of(data, LocalTime.of(i, 0)))) {
                                        JOptionPane.showMessageDialog(SelezionaSlot.this, "Errore! Data o Slot Orario già selezionato");
                                        return;
                                    } else {
                                        slotSalvati.add(LocalDateTime.of(data, LocalTime.of(i, 0)));
                                    }
                                }
                                aggiungiComboDefault();
                            }
                        }
                    }
                }
            });

            okButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
            okButton.setActionCommand("OK");
            okButton.setFocusPainted(false);
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
            JButton cancelButton = new JButton("Salva");
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    ArrayList<LocalDateTime> risultati = new ArrayList<>();
                    //effettuiamo tutti i controlli nel caso seleziona una sola data
                    LocalDate d = datePickers.get(0).getDate();
                    LocalTime o = timePickers.get(0).getTime();

                    if(datePickers.size()==1 && !(ultimoCheckbox != null && ultimoCheckbox.isSelected())){
                        if (d == null) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "Selezionare una data");
                        } else if (!(ultimoCheckbox != null && ultimoCheckbox.isSelected()) && (o == null)) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "Inserire un orario o spuntare l'opzione per l'intera giornata");
                        } else if (LocalDateTime.of(d, o).isBefore(LocalDateTime.now())) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "La data e l'ora selezionate non possono essere nel passato!");
                        } else if (!(ultimoCheckbox != null && ultimoCheckbox.isSelected()) && o == null) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "Inserire un orario o spuntare l'opzione per l'intera giornata");
                        } else {
                            risultati.add(LocalDateTime.of(d, o));
                            String s = ControllerSlotBloccati.scriviSlot(risultati);
                            JOptionPane.showMessageDialog(SelezionaSlot.this, s);
                            dispose();
                        }

                    }else if(datePickers.size()==1 && (ultimoCheckbox != null && ultimoCheckbox.isSelected())) {
                        if (d.isBefore(LocalDate.now())) {
                            JOptionPane.showMessageDialog(SelezionaSlot.this, "La data selezionata non può essere nel passato!");
                        } else {
                            risultati = salvaDate();
                            String s = ControllerSlotBloccati.scriviSlot(risultati);
                            JOptionPane.showMessageDialog(SelezionaSlot.this, s);
                            dispose();
                        }
                    } else {
                        risultati = salvaDate();
                        String s = ControllerSlotBloccati.scriviSlot(risultati);
                        JOptionPane.showMessageDialog(SelezionaSlot.this, s);
                        dispose();
                    }

                }
            });
            cancelButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
            cancelButton.setActionCommand("Cancel");
            cancelButton.setFocusPainted(false);
            buttonPane.add(cancelButton);
        }
    }

    private void aggiungiComboDefault() {
        DatePickerSettings dateSettings = new DatePickerSettings();

        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.use24HourClockFormat();
        //Mostriamo solamente gli orari di apertura dell'ambulatorio
        timeSettings.generatePotentialMenuTimes(
                TimePickerSettings.TimeIncrement.OneHour, //Ogni visita dura un'ora
                LocalTime.of(8, 0),    //Ora di apertura
                LocalTime.of(18, 0)   //Ora di chiusura
        );

        DatePicker dp = new DatePicker(dateSettings);
        //La domenica non è selezionabile perchè già giorno di chiusura
        dateSettings.setVetoPolicy(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY);


        TimePicker tp = new TimePicker(timeSettings);


        JPanel riga = new JPanel();
        riga.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Data:");
        label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        riga.add(label);
        riga.add(dp);
        JLabel label_1 = new JLabel("Ora:");
        label_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        riga.add(label_1);
        riga.add(tp);

        pannelloCombo.add(riga);
        pannelloCombo.revalidate();
        pannelloCombo.repaint();

        datePickers.add(dp);
        timePickers.add(tp);

        JCheckBox chckbxGiorno = new JCheckBox("Tutto il giorno");
        chckbxGiorno.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));

//Aggiungiamo un listener per gestire la selezione del checkbox
        chckbxGiorno.addItemListener(e -> {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

            //Se "Tutto il giorno" è selezionato, disabilita il TimePicker
            if (isSelected) {
                tp.setEnabled(false);
                tp.setTime(null); //Resetta l'orario

            } else {
                tp.setEnabled(true);
            }
        });

        //Salviamo il riferimento all'ultimo checkbox
        ultimoCheckbox = chckbxGiorno;

        riga.add(chckbxGiorno);
        pannelloCombo.add(riga);
        pannelloCombo.revalidate();
        pannelloCombo.repaint();
    }
 public ArrayList<LocalDateTime> salvaDate() {
    ArrayList<LocalDateTime> risultati = new ArrayList<>();
     //Salviamo gli slot in un ArrayList da passare al Controller
     for (int i = 0; i < datePickers.size(); i++) {
         LocalDate data = datePickers.get(i).getDate();
         LocalTime ora = timePickers.get(i).getTime();
         if (data != null) {
             if (ultimoCheckbox != null && ultimoCheckbox.isSelected()) {
                 for (int h = 8; h < 18; h++) {
                     risultati.add(LocalDateTime.of(data, LocalTime.of(h, 0)));
                 }
             } else if (ora != null) {
                 risultati.add(LocalDateTime.of(data, ora));
             }
         }
     }
     return risultati;
 }
}