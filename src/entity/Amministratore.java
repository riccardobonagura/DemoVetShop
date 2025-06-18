package entity;

import database.DAO_Amministratore;
import java.sql.SQLIntegrityConstraintViolationException;

public class Amministratore extends Utente {

    //Costruttore con argomenti
    public Amministratore(String name, String surname, String username, String email, String password) {
        super(name, surname, username, email, password);
    }

    public int linkDAO () throws SQLIntegrityConstraintViolationException {
        DAO_Amministratore linker = new DAO_Amministratore();
        linker.setNome(this.getNome());
        linker.setCognome(this.getCognome());
        linker.setUsername(this.getUsername());
        linker.setPassword(this.getPassword());
        linker.setEmail(this.getEmail());
        try {
            return linker.ScriviAmministratore();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        }
    }
}
