package control;

import entity.Agenda;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class ControllerSlotBloccati {

    public static String scriviSlot(ArrayList<LocalDateTime> slotBloccati) {
        Agenda a;
        a = Agenda.getInstance();


        if (slotBloccati == null || slotBloccati.isEmpty()) {
                System.out.println("Nessuno slot bloccato presente.");

            }
            for (int i = 0; i < slotBloccati.size(); i++) {
                LocalDateTime s = slotBloccati.get(i);
                System.out.println("Slot #" + i + ": " + s);
            }
        return a.scriviSlotBloccati(slotBloccati);
        }
    }



