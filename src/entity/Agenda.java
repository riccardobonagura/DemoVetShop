package entity;

import DTOs.PrenotazioneDTO;
import DTOs.SlotBloccatoDTO;
import database.DAO_Prenotazione;
import database.DAO_SlotBloccato;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Agenda {

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


    public ArrayList<PrenotazioneDTO> caricaPrenotazioni() {
        ArrayList<PrenotazioneDTO> lista_dto = new ArrayList<>();
        DAO_Prenotazione p = new DAO_Prenotazione();
        ArrayList<DAO_Prenotazione> lista_db_prenotazioni = p.caricaPrenotazioni();
        for (DAO_Prenotazione pren : lista_db_prenotazioni) {
            lista_dto.add(new PrenotazioneDTO(pren));
        }
        return lista_dto;
    }

    public ArrayList<SlotBloccatoDTO> caricaSlotBloccati() {
        ArrayList<SlotBloccatoDTO> lista_dto = new ArrayList<>();
        DAO_SlotBloccato dao = new DAO_SlotBloccato();
        ArrayList<DAO_SlotBloccato> lista_db_slot = dao.caricaSlotBloccati();
        for (DAO_SlotBloccato slot : lista_db_slot) {
            lista_dto.add(new SlotBloccatoDTO(slot));
        }
        return lista_dto;
    }

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
