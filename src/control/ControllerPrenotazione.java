package control;

import DTOs.AnimaleDomesticoDTO;
import entity.Agenda;
import entity.Cliente;
import entity.Prenotazione;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControllerPrenotazione {

    //funzione che ritorna alla GUI la lista degli animali posseduti dal cliente che interroga il sistema
    //non avendo implementato una funzione di accesso, il cliente viene identificato tramite la sua mail

    public static ArrayList<AnimaleDomesticoDTO> caricaAnimaliDomestici(String email) {
        //creazione di un ArrayList vuoto
        ArrayList<AnimaleDomesticoDTO> lista_animali = new ArrayList<>();

        //recupero della lista tramite il metodo statico della classe cliente: caricaAnimaliDomestici(String)
        lista_animali= Cliente.caricaAnimaliDomestici(email);

        //passaggio della lista alla GUI
        return lista_animali;
    }

    //funzione che ritorna alla GUI la lista degli slot orari che non è possibile prenotare
    public static ArrayList<LocalDateTime> caricaDateNonDisponibili(){
        //creo ArrayList vuoto
        ArrayList<LocalDateTime> dateNonDisponibili = new ArrayList<>();

        //recupero della lista che unisce prenotazioni e slot bloccati
        dateNonDisponibili = Agenda.getInstance().caricaDateNonDisponibili();

        //passaggio della lista alla GUI
        return dateNonDisponibili;
    }

    public static String scriviPrenotazione(int chipcode, LocalDateTime dataOra ) {
        int res = 0;

        Prenotazione p = new Prenotazione(chipcode, dataOra);

        //utilizzando il metodo: UpdateQueryReturnGeneratedKey
        res = p.scriviPrenotazione();

        if (res == -1) {
            return "Errore nel formato della richiesta.";
        } else return "Prenotazione registrata con successo con id: " + res;
    }
}
