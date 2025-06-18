package DTOs;

import database.DAO_Farmaco;


public class FarmacoDTO {

    private String nome;
    private String produttore;
    private int idFarmaco;


    //getters e setters

    public int getIdFarmaco() {return idFarmaco;}
    public void setIdFarmaco(int idFarmaco) {this.idFarmaco=idFarmaco;}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {this.nome=nome;}

    public String getProduttore() {
        return produttore;
    }
    public void setProduttore(String produttore) {this.produttore=produttore;}


    //costruttori

    public FarmacoDTO(DAO_Farmaco P){
        this.nome = P.getNome();
        this.produttore = P.getProduttore();
        this.idFarmaco = P.getId();
    }

    public FarmacoDTO(String nome, String produttore, int idFarmaco){
        this.nome = nome;
        this.produttore = produttore;
        this.idFarmaco = idFarmaco;
    }
}
