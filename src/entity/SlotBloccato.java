package entity;

import database.DAO_SlotBloccato;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

public class SlotBloccato {

    private LocalDateTime dataOra;

//funzione DAO che scrive nel database i nuovi slot appena bloccati
    public int scriviSlotBloccato() throws SQLIntegrityConstraintViolationException {
        DAO_SlotBloccato linker = new DAO_SlotBloccato();
        linker.setDataOra(this.getDataOra());
        try {
            return linker.scriviSlotBloccati();
        } catch (SQLIntegrityConstraintViolationException e) { throw e;}
    }

    //getters e setters

    public LocalDateTime getDataOra(){return dataOra;}
    public void setDataOra(LocalDateTime dataOra){this.dataOra=dataOra;}

    //costruttori

    public SlotBloccato(LocalDateTime dataOra){
        this.dataOra=dataOra;
    }
}
