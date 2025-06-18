package database;

import entity.Farmaco;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class DAO_Visita {
    private String tipoVisita;
    private double costo;
    private int idPrenotazione;
    private String descrizione;
    private ArrayList<Farmaco> farmaci;


    public int scriviVisita() throws SQLIntegrityConstraintViolationException {
        int ret;
        String InsertQuery = "INSERT INTO VISITE (tipo, costo, Prenotazione_idPrenotazione, descrizione) VALUES ( \'"+this.tipoVisita+"\',\'"+this.costo+"\', \'"+this.idPrenotazione+"\', \'"+this.descrizione+"\');";
        System.out.println(InsertQuery);
        try{
            ret = DBConnectionManager.updateQuery(InsertQuery);
        } catch (SQLIntegrityConstraintViolationException e) {

            System.out.println("errore, i vincoli sul database");
            throw e;
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public int scriviFarmaci() throws SQLIntegrityConstraintViolationException{
        int ret = 0;
        int lenght = this.farmaci.size();
        if (lenght != 0) {
            for (int i = 0; i < lenght; i++) {
                int idFarmaco = this.farmaci.get(i).getIdFarmaco();
                String InsertQuery = "INSERT INTO VISITA_HAS_FARMACO (Visita, Farmaco) VALUES (\'" + this.idPrenotazione + "\',\'" + idFarmaco + "\');";
                System.out.println(InsertQuery);
                try {
                    ret = DBConnectionManager.updateQuery(InsertQuery);
                } catch (SQLIntegrityConstraintViolationException e) {
                    // Chiave duplicata

                    throw e;

                } catch (ClassNotFoundException | SQLException e) {

                    e.printStackTrace();
                    ret = -1;
                }
            }
        }
        else {
            System.out.println("nessun farmaco selezionato");
            ret=0;
        }
        return ret;
    }




    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public ArrayList<Farmaco> getFarmaci() {
        return farmaci;
    }

    public void setFarmaci(ArrayList<Farmaco> farmaci) {
        this.farmaci = farmaci;
    }

}
