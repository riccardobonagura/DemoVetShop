package database;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class DAO_Amministratore {

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;


    public int ScriviAmministratore () throws SQLIntegrityConstraintViolationException {
        int ret;
        String InsertQuery = "INSERT INTO AMMINISTRATORI(nome,cognome,email,username,password) VALUES ( \'"+this.nome+"\',"+"\'"+ this.cognome +"\',"+"\'"+this.email+"\',"+"\'"+this.username+"\',"+"\'"+this.password+"')";
        System.out.println(InsertQuery);
        try{
            ret = DBConnectionManager.updateQueryReturnGeneratedKey(InsertQuery);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Chiave duplicata
            throw e;
        }
        catch(ClassNotFoundException |  SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    // getters e setters per nome
    public String getNome() {
        return cognome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per cognome
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e Setter per username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter e Setter per email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter per password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}