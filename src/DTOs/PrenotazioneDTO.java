package DTOs;

import database.DAO_Prenotazione;
import java.time.LocalDateTime;

public class PrenotazioneDTO {

    private int id;
    private LocalDateTime dataOra;
    private int chipcode;


    //getters e setters

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int getChipcode(){
        return this.chipcode;
    }
    public void setChipcode(int id){
        this.chipcode=chipcode;
    }

    public LocalDateTime getDataOra(){
        return this.dataOra;
    }
    public void setDataOra(LocalDateTime dataOra){
        this.dataOra=dataOra;
    }

    //costruttori

    public PrenotazioneDTO(int id,LocalDateTime dataOra,int chipcode){
        super();
        this.dataOra=dataOra;
        this.id=id;
        this.chipcode=chipcode;
    }

    //costruttore che prende in ingresso un DAO, utilizzato
    // nel flusso di ritorno di RegistraVisita, per visualizzare a schermo le prenotazioni giornaliere.
    public PrenotazioneDTO(DAO_Prenotazione P){
        this.id = P.getId();
        this.dataOra = P.getDataOra();
        this.chipcode = P.getChipcode();
    }


}
