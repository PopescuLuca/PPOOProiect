package proiectPPOO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum enumLivratori{Tudor,Andrei;
    public static enumLivratori getRandomNume() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }}
public class Comenzi implements Serializable {
    private int id;
    private String numeLivrator;
    private int nrLivratori;
    private List<Produse> produseList = new ArrayList<>();

    public Comenzi(int id, List<Produse> produseList) {
        this.id = id;
        this.nrLivratori = (int) (Math.random() * 2);
        if (this.nrLivratori == 1) {
            this.numeLivrator = String.valueOf(enumLivratori.getRandomNume());
        }
        else{
            this.numeLivrator="Tudor si Andrei";
        }
        this.produseList = produseList;
    }
    public Comenzi(int id, List<Produse> produseList,int nrLivratori) {
        this.id = id;
        this.nrLivratori = nrLivratori;
        if (this.nrLivratori == 1) {
            this.numeLivrator = String.valueOf(enumLivratori.getRandomNume());
        }
        else{
            this.numeLivrator="Tudor si Andrei";
        }
        this.produseList = produseList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeLivrator() {
        return numeLivrator;
    }

    public void setNumeLivrator(String numeLivrator) {
        this.numeLivrator = numeLivrator;
    }

    public int getNrLivratori() {
        return nrLivratori;
    }

    public void setNrLivratori(int nrLivratori) {
        this.nrLivratori = nrLivratori;
        if (this.nrLivratori == 1) {
            this.numeLivrator = String.valueOf(enumLivratori.getRandomNume());
        }
        else{
            this.numeLivrator="Tudor si Andrei";
        }
    }

    public List<Produse> getProduseList() {
        return produseList;
    }

    public void setProduseList(List<Produse> produseList) {
        this.produseList = produseList;
    }


    public float sumaComanda(int idComanda){
        float suma=0;
        if(this.id==idComanda){
            for(Produse produse:produseList) {
                suma += produse.getPret()*produse.getCantitate();
            }
        }
        return suma;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comenzi{");
        sb.append("id=").append(id);
        sb.append(", numeLivrator='").append(numeLivrator).append('\'');
        sb.append(", produseList=").append(produseList);
        sb.append('}');
        return sb.toString();
    }
}
