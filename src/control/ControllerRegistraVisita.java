package control;

import DTOs.FarmacoDTO;
import DTOs.PrenotazioneDTO;
import entity.Agenda;
import entity.CatalogoFarmaci;
import entity.Visita;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;


public class ControllerRegistraVisita {

   public static ArrayList<PrenotazioneDTO> caricaListaPrenotazioni() {
        //creo ArrayList vuoto
        ArrayList<PrenotazioneDTO> lista_prenotazioni = new ArrayList<>();

        //vado a prendere la lista di DTO chiamando il metodo di Entity
        lista_prenotazioni= Agenda.getInstance().caricaPrenotazioniGiornaliere();
        //restituisco la lista alla GUI
        return lista_prenotazioni;
    }
    //nota: c'era un errore, chiamava la funzione generica: caricaPrenotazioni.
    // Modificato e testato con successo.

    public static ArrayList<FarmacoDTO> caricaListaFarmaci() {
        //creo ArrayList vuoto
        ArrayList<FarmacoDTO> lista_farmaci = new ArrayList<>();

        //vado a prendere la lista di DTO chiamando il metodo di Entity
        //come adattare qui?
        lista_farmaci = CatalogoFarmaci.getInstance().caricaListaFarmaci();

        //restituisco la lista alla GUI
        return lista_farmaci;
   }

    public static String scriviVisita(int id_pren, String tipologia, double costo, String descrizione, ArrayList farmaci) {

            Visita v = new Visita(id_pren, tipologia, costo, descrizione, farmaci);
            try {
                v.scriviVisita();
            } catch (SQLIntegrityConstraintViolationException e) {
                return "Questa prenotazione ha già una visita registrata, riprovare";
            }
            return "Visita registrata con successo come il SUCCESSO del grande NAPOLI con ID: " + v.getIdPrenotazione();
        }
}

