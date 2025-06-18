package entity;

import DTOs.FarmacoDTO;
import database.DAO_Farmaco;
import java.util.ArrayList;

public class CatalogoFarmaci {

    private static CatalogoFarmaci instance; // unica istanza
    private ArrayList<Farmaco> elencoFarmaci; // non statico

    private CatalogoFarmaci() {
        elencoFarmaci = new ArrayList<>();
    }

    public static synchronized CatalogoFarmaci getInstance() {
        if (instance == null) {
            instance = new CatalogoFarmaci();
        }
        return instance;
    }

    public void caricaFarmaciDaDB() {
        elencoFarmaci = new ArrayList<>();
        DAO_Farmaco dao = new DAO_Farmaco();
        ArrayList<DAO_Farmaco> lista_db_farmaci = dao.caricaFarmaci();

        for (DAO_Farmaco d : lista_db_farmaci) {
            Farmaco f = new Farmaco();
            f.setIdFarmaco(d.getId());
            f.setNome(d.getNome());
            f.setProduttore(d.getProduttore());
            elencoFarmaci.add(f);
        }
    }

    public ArrayList<FarmacoDTO> caricaListaFarmaci() {
        if (elencoFarmaci == null || elencoFarmaci.isEmpty()) {
            caricaFarmaciDaDB();
        }
        return convertiinDTO(elencoFarmaci);
    }

    private ArrayList<FarmacoDTO> convertiinDTO(ArrayList<Farmaco> listaF) {
        ArrayList<FarmacoDTO> f = new ArrayList<>();
        for (Farmaco far : listaF) {
            f.add(new FarmacoDTO(far.getNome(), far.getProduttore(), far.getIdFarmaco()));
        }
        return f;
    }
}
