package control;

import entity.Cliente;
import entity.Veterinario;
import entity.Amministratore;
import java.sql.SQLIntegrityConstraintViolationException;


public class ControllerRegistraUtente {

    public static String verificaRuoloUtente_scriviUtente(String name, String surname, String username, String email, String password, String ruolo){
       int res = 0;


       String[] dominio = email.split("@");
       try {
           if (ruolo.equals("Cliente")) {
               Cliente c = new Cliente(name, surname, username, email, password);
               res = c.linkDAO();
           } else if (ruolo.equals("Veterinario") && dominio[1].equals("veterinario.ambulatorio.it")) {
               Veterinario v = new Veterinario(name, surname, username, email, password);
               res = v.linkDAO();
           } else if (ruolo.equals("Amministratore") && dominio[1].equals("amministratore.ambulatorio.it")) {
               Amministratore a = new Amministratore(name, surname, username, email, password);
               res = a.linkDAO();
           } else {
               return "errore, dominio e ruolo non coincidono, riprovare";
           }
           return ruolo + " registrato correttamente con id: " + String.valueOf(res);

       } catch (SQLIntegrityConstraintViolationException e) {
           return "email già registrata, riprovare";
       }
    }
}