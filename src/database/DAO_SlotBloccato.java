package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAO_SlotBloccato {

    private LocalDateTime dataOra;


    //FUNZIONA E STAMPA ID CORRETTAMENTE
    public int scriviSlotBloccati() throws SQLIntegrityConstraintViolationException {
        int ret;
        String InsertQuery = "INSERT INTO `progettoambulatorio`.`blocchi` (`slot`) VALUES ( \'" + this.dataOra + "\')";

        System.out.println(InsertQuery);
        try {
            ret = DBConnectionManager.updateQueryReturnGeneratedKey(InsertQuery);
        } catch (SQLIntegrityConstraintViolationException e) {
            //Data già bloccata
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }


    public ArrayList<DAO_SlotBloccato> caricaSlotBloccati() {
        ArrayList<DAO_SlotBloccato> lista_temp = new ArrayList<>();
        String trovaPrenotazioniOdierne = "SELECT * \n" +
                "FROM blocchi WHERE DATE(slot)>CURDATE();" ;
        System.out.println(trovaPrenotazioniOdierne);
        try{
            ResultSet ret = DBConnectionManager.selectQuery(trovaPrenotazioniOdierne);
            while (ret.next()) {
                DAO_SlotBloccato temp = new DAO_SlotBloccato();
                temp.setDataOra(ret.getObject("slot", LocalDateTime.class));
                lista_temp.add(temp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista_temp;
    }

    //getters e setters

    public LocalDateTime getDataOra() {
        return dataOra;
    }
    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

}

