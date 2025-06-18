package entity;

import DTOs.AnimaleDomesticoDTO;
import database.DAO_AnimaleDomestico;
import database.DAO_Cliente;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class Cliente extends Utente {

    private ArrayList<AnimaleDomestico> animaliDomestici;

    //costruttore con argomenti
    public Cliente(String name, String surname, String username, String email, String password) {
        super(name, surname, username, email, password);
    }

    //funzione di collegamento con il DAO
    public int linkDAO () throws SQLIntegrityConstraintViolationException {
        DAO_Cliente linker = new DAO_Cliente();
        linker.setNome(this.getNome());
        linker.setCognome(this.getCognome());
        linker.setUsername(this.getUsername());
        linker.setPassword(this.getPassword());
        linker.setEmail(this.getEmail());
        try {
            return linker.scriviCliente();
        } catch (SQLIntegrityConstraintViolationException e) { throw e;}
    }

    public static ArrayList<AnimaleDomesticoDTO> caricaAnimaliDomestici(String email) {
        //inizializzo lista dto
        ArrayList<AnimaleDomesticoDTO> lista_dto = new ArrayList<>();

        //creo un DAO_AnimaleDomestico per caricarmi la lista
        DAO_AnimaleDomestico a = new DAO_AnimaleDomestico();
        //mi carico la lista delle prenotazioni
        ArrayList<DAO_AnimaleDomestico> lista_db_animali = a.caricaAnimaliDomestici(email);

        //Andiamo ad estrarre gli oggetti DAO_AnimaleDomestico dall'arrayList
        //e li salviamo nell'ArrayList di AnimaliDomesticiDTO
        for (DAO_AnimaleDomestico animali: lista_db_animali) {
            AnimaleDomesticoDTO temp = new AnimaleDomesticoDTO(animali);
            lista_dto.add(temp);
        }
        return lista_dto;
    }
}

