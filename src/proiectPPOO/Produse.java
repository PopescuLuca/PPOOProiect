package proiectPPOO;

import java.io.Serializable;

public class Produse implements Serializable {
    private int id;
    private String denumire;
    private float pret;
    private int cantitate;

    public Produse(int id, String denumire, float pret, int cantitate) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produse{");
        sb.append("id=").append(id);
        sb.append(", denumire='").append(denumire).append('\'');
        sb.append(", pret=").append(pret);
        sb.append(", cantitate=").append(cantitate);
        sb.append('}');
        return sb.toString();
    }
}
