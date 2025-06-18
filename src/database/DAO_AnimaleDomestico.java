package database;

import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class DAO_AnimaleDomestico {

    private int chipcode;
    private String nome;
    private String tipo;
    private String razza;
    private String colore;
    private LocalDate datadinascita;


    public ArrayList<DAO_AnimaleDomestico> caricaAnimaliDomestici(String email)  {
        ArrayList<DAO_AnimaleDomestico> lista_temp = new ArrayList<>();

        String trovaAnimali = "SELECT animali.* FROM progettoambulatorio.animali JOIN clienti ON Cliente_idCliente = idCliente WHERE email = \'"+email+"\';";
        System.out.println(trovaAnimali);
        try{
            ResultSet ret = DBConnectionManager.selectQuery(trovaAnimali);
            while (ret.next()) {
                DAO_AnimaleDomestico temp = new DAO_AnimaleDomestico();
                temp.setChipcode(ret.getInt(1));
                temp.setNome(ret.getObject(2).toString());
                temp.setTipo(ret.getObject(3).toString());
                temp.setRazza(ret.getObject(4).toString());
                temp.setColore(ret.getObject(5).toString());
                temp.setDatadinascita(ret.getObject(6, LocalDate.class));
                lista_temp.add(temp);
            }
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
        return lista_temp;
    }


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

    public String getRazza() {
        return razza;
    }
    public void setRazza(String razza) {
        this.razza = razza;
    }

    public String getColore() {
        return colore;
    }
    public void setColore(String colore) {
        this.colore = colore;
    }

    public LocalDate getDatadinascita() {
        return datadinascita;
    }
    public void setDatadinascita(LocalDate datadinascita) {
        this.datadinascita = datadinascita;
    }


    //costruttori

    public DAO_AnimaleDomestico(int chipcode, String nome, String tipo, String razza, String colore, LocalDate datadinascita) {
        this.chipcode= chipcode;
        this.nome = nome;
        this.tipo = tipo;
        this.razza = razza;
        this.colore = colore;
        this.datadinascita = datadinascita;
    }

    public DAO_AnimaleDomestico() {    }
}

