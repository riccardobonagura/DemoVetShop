package control;

import DTOs.AnimaleDomesticoDTO;
import entity.Agenda;
import entity.Cliente;
import entity.Prenotazione;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControllerPrenotazione {

    public static ArrayList<AnimaleDomesticoDTO> caricaAnimaliDomestici(String email) {
        //creo ArrayList vuoto
        ArrayList<AnimaleDomesticoDTO> lista_animali = new ArrayList<>();

        //vado a prendere la lista di DTO chiamando il metodo di Entity
        lista_animali= Cliente.caricaAnimaliDomestici(email);

        //restituisco la lista alla GUI
        return lista_animali;
    }

    public static ArrayList<LocalDateTime> caricaDateNonDisponibili(){
        //creo ArrayList vuoto
        ArrayList<LocalDateTime> dateNonDisponibili = new ArrayList<>();

        //vado a prendere la lista di DTO chiamando il metodo di Entity
        //come adattare qui?
        dateNonDisponibili = Agenda.getInstance().caricaDateNonDisponibili();

        //restituisco la lista alla GUI
        return dateNonDisponibili;
    }

    public static String scriviPrenotazione(int chipcode, LocalDateTime dataOra ) {
        int res = 0;

        Prenotazione p = new Prenotazione(chipcode, dataOra);

        res = p.scriviPrenotazione();

        if (res == -1) {
            return "Errore nel formato della richiesta.";
        } else return "Prenotazione registrata con successo con id: " + res;
    }
}
