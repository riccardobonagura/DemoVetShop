package entity;

import database.DAO_Visita;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class Visita {

    private String tipoVisita;
    private double costo;
    private int idPrenotazione;
    private String descrizione;
    private ArrayList<Farmaco> farmaci; //Lista di Faramci prescritti dal Veterinario


    public int scriviVisita () throws SQLIntegrityConstraintViolationException{
        DAO_Visita linker = new DAO_Visita();
        linker.setCosto(this.costo);
        linker.setDescrizione(this.descrizione);
        linker.setIdPrenotazione(this.idPrenotazione);
        linker.setTipoVisita(this.tipoVisita);
        linker.setFarmaci(this.farmaci);
        linker.scriviFarmaci();
        try {
            return linker.scriviVisita();
        } catch (SQLIntegrityConstraintViolationException e) { throw e;}
    }

    //getters e setters

    public int getIdPrenotazione(){ return this.idPrenotazione; }

    //costruttori

    public Visita(int idPrenotazione, String visita, double costo, String descrizione, ArrayList<Farmaco> farmaci) {
        this.tipoVisita = visita;
        this.idPrenotazione = idPrenotazione;
        this.descrizione = descrizione;
        this.costo = costo;
        //da cambiare!
        this.farmaci = new ArrayList<>();
    }
}


