package DTOs;

import database.DAO_AnimaleDomestico;
import java.time.LocalDate;

public class AnimaleDomesticoDTO {

    private int chipcode;
    private String nome;
    private String tipo;
    private String razza;
    private String colore;
    private LocalDate datadinascita;


    //getters e setters

    public int getChipcode() {
        return chipcode;
    }
    public void setChipcode(int chipcode) {
        this.chipcode = chipcode;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRazza() {return razza;}
    public void setRazza(String razza) {this.razza = razza;}

    public String getColore() {return colore;}
    public void setColore(String colore) {this.colore = colore;}

    public LocalDate getDataN() {return datadinascita;}
    public void setDataN(LocalDate dataN) {this.datadinascita = dataN;}


    //costruttori

    public AnimaleDomesticoDTO(DAO_AnimaleDomestico P){
        this.chipcode = P.getChipcode();
        this.nome = P.getNome();
        this.tipo = P.getTipo();
        this.razza = P.getRazza();
        this.colore = P.getColore();
        this.datadinascita = P.getDatadinascita();
    }

    public AnimaleDomesticoDTO(int chipcode, String nome, String tipo, String razza, String colore, LocalDate datadinascita) {
        this.chipcode= chipcode;
        this.nome = nome;
        this.tipo = tipo;
        this.razza = razza;
        this.colore = colore;
        this.datadinascita = datadinascita;
    }

}
