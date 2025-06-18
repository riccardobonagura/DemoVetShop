package DTOs;

import database.DAO_SlotBloccato;
import java.time.LocalDateTime;

public class SlotBloccatoDTO {

    private LocalDateTime dataOra;


    //getters and setters

    public void setDataOra(LocalDateTime dataOra){this.dataOra=dataOra;}
    public LocalDateTime getDataOra(){return this.dataOra;}

    //costruttore con DAO

    public SlotBloccatoDTO(DAO_SlotBloccato S){
        this.dataOra = S.getDataOra();
    }

    //costruttore con argomenti

    public SlotBloccatoDTO(LocalDateTime dataOra){this.dataOra=dataOra;}

}
