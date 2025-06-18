package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Farmaco {
    private int idFarmaco;
    private String nome;
    private String produttore;


    public ArrayList<DAO_Farmaco> caricaFarmaci() {

        ArrayList<DAO_Farmaco> lista_temp = new ArrayList<>();

        String trovaFarmaci = "SELECT * \n" + "FROM FARMACI;" ;
        System.out.println(trovaFarmaci);

        try{
            ResultSet ret = DBConnectionManager.selectQuery(trovaFarmaci);
            while (ret.next()) {
                DAO_Farmaco temp = new DAO_Farmaco();
                temp.setId(ret.getInt(1));
                temp.setNome(ret.getObject(2).toString());
                temp.setProduttore(ret.getObject(3).toString());
                lista_temp.add(temp);

            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista_temp;
    }

    //getters e setters

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

    public int getId() {
        return idFarmaco;
    }
    public void setId(int id) {
        this.idFarmaco=id;
    }

    //costruttore

    public DAO_Farmaco() {    }
}
