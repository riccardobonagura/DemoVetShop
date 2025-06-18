package entity;

import database.DAO_Veterinario;
import java.sql.SQLIntegrityConstraintViolationException;

public class Veterinario extends Utente {

    public Veterinario(String name, String surname, String username, String email, String password) {
        super(name, surname, username, email, password);
    }

    public int linkDAO () throws SQLIntegrityConstraintViolationException {
        DAO_Veterinario linker = new DAO_Veterinario();
        linker.setNome(this.getNome());
        linker.setCognome(this.getCognome());
        linker.setUsername(this.getUsername());
        linker.setPassword(this.getPassword());
        linker.setEmail(this.getEmail());
        try {
            return linker.scriviVeterinario();
        } catch (SQLIntegrityConstraintViolationException e) { throw e;}
    }
}
