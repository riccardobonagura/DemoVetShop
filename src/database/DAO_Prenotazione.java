package database;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAO_Prenotazione {

    private int id;
    private LocalDateTime dataOra;
    private int chipcode;

    //inserimento di una nuova prenotazione
    public int scriviPrenotazione()  {
        int ret;
        String InsertQuery =
                "INSERT INTO PRENOTAZIONI (`data`, `Animale_chipCode`) " +
                        "VALUES ( \'"+this.dataOra+"\',"+this.chipcode+");";
        System.out.println(InsertQuery);
        try {
            ret= DBConnectionManager.updateQueryReturnGeneratedKey(InsertQuery);
        }   catch (MysqlDataTruncation e) {
            System.err.println("Errore nel formato della data: " + e.getMessage());
            return -1;
        }  catch (SQLException e) {
            System.err.println("Errore SQL generico: " + e.getMessage());
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
        return ret;
    }


    //funzione che accede al database e restituisce le prenotazioni per il giorno corrente che NON SONO ancora
    //associate ad alcuna visita. UTILIZZO: registra visita
    public ArrayList<DAO_Prenotazione> caricaPrenotazioniGiornaliere() {

        ArrayList<DAO_Prenotazione> lista_temp = new ArrayList<>();
        String trovaPrenotazioniOdierne = "SELECT prenotazioni.* \n" +
                "FROM prenotazioni\n" +
                "LEFT JOIN visite ON prenotazioni.idPrenotazione = visite.Prenotazione_idPrenotazione\n" +
                "WHERE (visite.Prenotazione_idPrenotazione IS NULL) AND DATE(prenotazioni.`data`) = CURDATE();";

        System.out.println(trovaPrenotazioniOdierne);

        try{
            ResultSet ret = DBConnectionManager.selectQuery(trovaPrenotazioniOdierne);
            while (ret.next()) {
                DAO_Prenotazione temp = new DAO_Prenotazione();
                temp.setId(ret.getInt(1));
                temp.setDataOra(ret.getObject("data", LocalDateTime.class));
                temp.setChipcode(ret.getInt(3));
                lista_temp.add(temp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista_temp;
    }

    //funzione che accede al database e restituisce tutte le prenotazioni che NON SONO ancora
    //associate ad alcuna visita. UTILIZZO: richiedi prenotazione
    public ArrayList<DAO_Prenotazione> caricaPrenotazioni() {

        ArrayList<DAO_Prenotazione> lista_temp = new ArrayList<>();

        String trovaPrenotazioni = "SELECT prenotazioni.* \n" +
                "FROM prenotazioni\n" +
                "LEFT JOIN visite ON prenotazioni.idPrenotazione = visite.Prenotazione_idPrenotazione\n" +
                "WHERE (visite.Prenotazione_idPrenotazione IS NULL) AND DATE(prenotazioni.`data`)>CURDATE();";
        System.out.println(trovaPrenotazioni);
        try{
            ResultSet ret = DBConnectionManager.selectQuery(trovaPrenotazioni);
            while (ret.next()) {
                DAO_Prenotazione temp = new DAO_Prenotazione();
                temp.setId(ret.getInt(1));
                temp.setDataOra(ret.getObject("data", LocalDateTime.class));
                temp.setChipcode(ret.getInt(3));
                lista_temp.add(temp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista_temp;
    }

    //getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDataOra() { return dataOra; }
    public void setDataOra(LocalDateTime dataOra) { this.dataOra = dataOra; }

    public int getChipcode() { return chipcode; }
    public void setChipcode(int chipcode) { this.chipcode = chipcode; }

}
