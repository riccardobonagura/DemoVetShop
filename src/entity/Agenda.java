package entity;

import DTOs.PrenotazioneDTO;
import DTOs.SlotBloccatoDTO;
import database.DAO_Prenotazione;
import database.DAO_SlotBloccato;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Agenda {

    //in questa classe, durante la richiesta di una nuova prenotazione, vengono gestiti
    // contemporaneamente prenotazioni già effettuate e slot bloccati
    private static Agenda instance; // Singleton
    private ArrayList<Prenotazione> prenotazioni;
    private ArrayList<SlotBloccato> bloccati;

    private Agenda() {
        this.prenotazioni = new ArrayList<>();
        this.bloccati = new ArrayList<>();
    }

    public static synchronized Agenda getInstance() {
        if (instance == null) {
            instance = new Agenda();
        }
        return instance;
    }

    // prelievo delle prenotazioni già effettuate dal database
    public ArrayList<PrenotazioneDTO> caricaPrenotazioni() {
        //creazione dell'arraylist di ritorno
        ArrayList<PrenotazioneDTO> lista_dto = new ArrayList<>();

        //creazione di un oggetto DAO responsabile dell'accesso in memoria
        DAO_Prenotazione p = new DAO_Prenotazione();

        //vettore DAO di ritorno dal database
        ArrayList<DAO_Prenotazione> lista_db_prenotazioni = p.caricaPrenotazioni();

        //popolamento del DTO
        for (DAO_Prenotazione pren : lista_db_prenotazioni) {
            lista_dto.add(new PrenotazioneDTO(pren));
        }
        return lista_dto;
    }


    // prelievo degli slot già bloccati presenti nel database
    public ArrayList<SlotBloccatoDTO> caricaSlotBloccati() {

        //creazione dell'arraylist di ritorno
        ArrayList<SlotBloccatoDTO> lista_dto = new ArrayList<>();

        //creazione di un oggetto DAO responsabile dell'accesso in memoria
        DAO_SlotBloccato dao = new DAO_SlotBloccato();

        //vettore DAO di ritorno dal database
        ArrayList<DAO_SlotBloccato> lista_db_slot = dao.caricaSlotBloccati();

        //popolamento del DTO
        for (DAO_SlotBloccato slot : lista_db_slot) {
            lista_dto.add(new SlotBloccatoDTO(slot));
        }

        return lista_dto;
    }

    //creazione di un unico vettore da restituire alla GUI che contenga tutte le date
    //in cui non è possibile effettuare una prenotazione, ossia indistintamente quelle
    //associate a prenotazioni già effettuate e quelle associate a slot bloccati
    public ArrayList<LocalDateTime> caricaDateNonDisponibili() {
        ArrayList<SlotBloccatoDTO> slotBloccati = caricaSlotBloccati();
        ArrayList<PrenotazioneDTO> prenotazioni = caricaPrenotazioni();
        ArrayList<LocalDateTime> dateNonDisponibili = new ArrayList<>();

        for (SlotBloccatoDTO s : slotBloccati) {
            dateNonDisponibili.add(s.getDataOra());
        }
        for (PrenotazioneDTO p : prenotazioni) {
            dateNonDisponibili.add(p.getDataOra());
        }

        return dateNonDisponibili;
    }

    //scrittura sul database dei nuovi slot appena bloccati dall'amministratore
    public String scriviSlotBloccati(ArrayList<LocalDateTime> slotBloccati) {
        ArrayList<SlotBloccato> passaggio = new ArrayList<>();
        for (LocalDateTime f : slotBloccati) {
            SlotBloccato s = new SlotBloccato(f);
            passaggio.add(s);
        }

        this.setBloccati(passaggio);

        try {
            for (SlotBloccato s : this.bloccati) {
                System.out.println(s.scriviSlotBloccato());
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return "Si è verificato un problema durante l'inserimento. Riprovare.";
        }
        return "Inserimento degli slot bloccati eseguito con successo.";
    }

    public ArrayList<PrenotazioneDTO> caricaPrenotazioniGiornaliere() {
        ArrayList<PrenotazioneDTO> lista_dto = new ArrayList<>();
        DAO_Prenotazione p = new DAO_Prenotazione();
        ArrayList<DAO_Prenotazione> lista_db_prenotazioni = p.caricaPrenotazioniGiornaliere();
        for (DAO_Prenotazione pren : lista_db_prenotazioni) {
            lista_dto.add(new PrenotazioneDTO(pren));
        }
        return lista_dto;
    }

    //getters and setters
    public ArrayList<Prenotazione> getPrenotazioni() {return prenotazioni;}
    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {this.prenotazioni = prenotazioni;}

    public ArrayList<SlotBloccato> getBloccati() {return bloccati;}
    public void setBloccati(ArrayList<SlotBloccato> bloccati) {this.bloccati = bloccati;}


}
