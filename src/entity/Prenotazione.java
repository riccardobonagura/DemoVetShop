package entity;

import database.DAO_Prenotazione;
import java.time.LocalDateTime;


public class Prenotazione {

    private int idPrenotazione;
    private LocalDateTime dataOra;
    private int chipcode;

    //funzione che sfrutta la classe DAO per salvare nel database una nuova prenotazione
    //che arriva dalla GUI
    public int scriviPrenotazione() {
        DAO_Prenotazione linker = new DAO_Prenotazione();
        linker.setDataOra(this.dataOra);
        linker.setChipcode(this.chipcode);
        //Settiamo l'idPrenotazione che abbiamo fatto generare al DB
        this.idPrenotazione = linker.scriviPrenotazione();
        return idPrenotazione;

    }

    //getters e setters
    public int getChipcode() {
        return chipcode;
    }
    public void setChipcode(int chipcode) {
        this.chipcode = chipcode;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }
    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }
    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    //costruttori con argomenti
    public Prenotazione(int id, LocalDateTime dataOra, int chip) {
        this.idPrenotazione = id;
        this.dataOra = dataOra;
        this.chipcode = chip;
    }

    public Prenotazione(int chipcode, LocalDateTime dataOra) {
        this.dataOra = dataOra;
        this.chipcode = chipcode;
    }
}
