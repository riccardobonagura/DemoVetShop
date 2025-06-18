package entity;

import database.DAO_Farmaco;

public class Farmaco {

    private int idFarmaco;
    private String nome;
    private String produttore;

    //costruttore da DAO
    public Farmaco (DAO_Farmaco F) {
        this.idFarmaco=F.getId();
        this.nome = F.getNome();
        this.produttore=F.getProduttore();
    }

    public Farmaco () {
    }

    //getters e setters
    public int getIdFarmaco(){
        return idFarmaco;
    }
    public void setIdFarmaco(int idFarmaco) {
        this.idFarmaco=idFarmaco;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProduttore() {
        return produttore;
    }
    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

}
