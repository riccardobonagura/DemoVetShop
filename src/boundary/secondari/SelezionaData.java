package boundary.secondari;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelezionaData extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private LocalDateTime slot;
    DatePicker dp;
    TimePicker tp;

    /**
     * Create the dialog.
     */
    public SelezionaData(ArrayList<LocalDateTime> dateNonDisponibili) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        DatePickerSettings dateSettings = new DatePickerSettings();
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.use24HourClockFormat();
        // Mostriamo solamente gli orari di apertura dell'ambulatorio
        timeSettings.generatePotentialMenuTimes(
                TimePickerSettings.TimeIncrement.OneHour, // Ogni visita dura un'ora
                LocalTime.of(8, 0),    // Ora di apertura
                LocalTime.of(18, 0)    // Ora di chiusura
        );

        dp = new DatePicker(dateSettings);
        // La domenica non è selezionabile perchè già giorno di chiusura
        dateSettings.setVetoPolicy(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY);

        tp = new TimePicker(timeSettings);

        dp.setVisible(true);
        tp.setVisible(true);

        // Aggiorna orari disponibili quando cambia la data
        dp.addDateChangeListener(e -> {
            LocalDate dataSelezionata = dp.getDate();

            // Filtra gli orari da disabilitare per la data selezionata
            ArrayList<LocalTime> orariPrenotati = (ArrayList<LocalTime>) dateNonDisponibili.stream()
                    .filter(dt -> dt.toLocalDate().equals(dataSelezionata))
                    .map(LocalDateTime::toLocalTime)
                    .collect(Collectors.toList());

            timeSettings.setVetoPolicy(time -> !orariPrenotati.contains(time));
            tp.setTime(null);  // resetta l’orario se diventa non valido
        });

        JPanel riga = new JPanel();
        riga.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelData = new JLabel("Data:");
        labelData.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        JLabel labelOra = new JLabel("Ora:");
        labelOra.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));

        riga.add(labelData);
        riga.add(dp);
        riga.add(labelOra);
        riga.add(tp);

        contentPanel.add(riga);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Salviamo gli slot in un ArrayList da passare al Controller
                LocalDate data = dp.getDate();
                LocalTime ora = tp.getTime();


                if (data == null || ora == null) {
                    JOptionPane.showMessageDialog(SelezionaData.this, "Devi selezionare prima una data e un orario per la tua prenotazione");
                } else {
                    LocalDateTime selezionata = LocalDateTime.of(data, ora);

                    if (selezionata.isBefore(LocalDateTime.now())) {
                        JOptionPane.showMessageDialog(SelezionaData.this, "La data e l'ora selezionate non possono essere nel passato!");
                    } else {
                        slot = selezionata;
                        // solo se tutto è ok chiudi la finestra
                        dispose();
                    }
                }
            }
        });
        okButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

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

    public LocalDateTime getData() {
        return slot;
    }
}