package entity;

public abstract class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;

    // Getter e Setter per cognome
    public String getNome() {
        return nome;
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


    //costruttori

    public Utente (String name, String surname, String username, String email, String password) {
        this.nome = name;
        this.cognome = surname;
        this.username = username;
        this.email=email;
        this.password=password;

    }
}


