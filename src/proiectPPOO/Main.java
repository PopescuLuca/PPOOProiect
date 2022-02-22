package proiectPPOO;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner input = new Scanner(new File("src/proiectPPOO/fisier.txt"));
        input.useDelimiter("=|,|}|Produse|'");
        if (input.hasNext()) {
            input.next();
        }
        List<Comenzi> comenziListFisier = new ArrayList<>();
        while (input.hasNext()) {
            List<Produse> produseList = new ArrayList<>();
            Comenzi comandaFisier;
            int idComanda = input.nextInt();
            input.next();
            input.next();
            String numeLivrator = input.next();
            input.next();
            input.next();
            input.next();
            while (input.next().equals("{id")) {
                int idProdus = input.nextInt();
                input.next();
                input.next();
                String denumireProdus = input.next();
                input.next();
                input.next();
                float pretProdus = Float.parseFloat(input.next());
                input.next();
                int cantitate = input.nextInt();
                if (!input.next().equals("]")) {
                    input.next();
                }
                Produse produsNou = new Produse(idProdus, denumireProdus, pretProdus, cantitate);
                produseList.add(produsNou);
            }
            comandaFisier = new Comenzi(idComanda, produseList);
            comandaFisier.setNumeLivrator(numeLivrator);
            comenziListFisier.add(comandaFisier);
            //System.out.println(comandaFisier);
        }

        input.close();

        Produse p1 = new Produse(1, "mazare", 5.5f, 5);
        Produse p2 = new Produse(2, "coca cola", 6, 2);
        List<Produse> produseList = new ArrayList<>();
        produseList.add(p1);
        produseList.add(p2);
        Comenzi c1 = new Comenzi(100, produseList);
        //System.out.println(c1);

        Produse p3 = new Produse(3, "fanta", 5, 3);
        Produse p4 = new Produse(4, "apa", 2, 2);
        Produse p5 = new Produse(5, "peste", 8, 1);
        List<Produse> produseList2 = new ArrayList<>();
        produseList2.add(p3);
        produseList2.add(p4);
        produseList2.add(p5);
        Comenzi c2 = new Comenzi(200, produseList2);
        //System.out.println(c2);
        Comenzi[] vectComenzi = {c1, c2};
        Scanner inputConsola = new Scanner(System.in);
        System.out.println("Ce operatii CRUD doriti sa folositi? create read update delete sau niciuna");
        String operatie = inputConsola.next().toLowerCase().trim();
        switch (operatie) {
            case "create":
                System.out.println("Operatia de creare");
                System.out.println("Introduceti id-ul comenzii:");
                try {
                    int idComanda = inputConsola.nextInt();
                    for (Comenzi comanda : comenziListFisier) {
                        if (comanda.getId() == idComanda) {
                            System.out.println("Comanda cu acest id exista deja, probabil doriti sa folositi operatia de update");
                            return;
                        }
                    }
                    System.out.println("Introduceti numarul de produse din comanda:");
                    int nrProduse = inputConsola.nextInt();
                    List<Produse> produseListDinConsola = new ArrayList<>();
                    for (int i = 0; i < nrProduse; i++) {
                        System.out.println("Introduceti id-ul produsului " + (i + 1) + ": ");
                        int idProdus = inputConsola.nextInt();
                        System.out.println("Introduceti denumirea produsului " + (i + 1) + ": ");
                        String denProdus = inputConsola.next().trim();
                        if (denProdus.contains("'") || denProdus.contains("Produse") || denProdus.contains(",") || denProdus.contains("}") || denProdus.contains("=")) {
                            throw new IncorrectFileNameException("Denumirea contine apostrof ' ");
                        }
                        System.out.println("Introduceti pretul produsului " + (i + 1) + ": ");
                        float pretProdus = inputConsola.nextFloat();
                        System.out.println("Introduceti cantitatea produsului " + (i + 1) + ": ");
                        int cantProdus = inputConsola.nextInt();
                        Produse produs = new Produse(idProdus, denProdus, pretProdus, cantProdus);
                        System.out.println(produs);
                        produseListDinConsola.add(produs);
                    }
                    System.out.println("Care este numarul de livratori? 1 sau 2");
                    int nrLivratori = inputConsola.nextInt();
                    if (nrLivratori < 1 || nrLivratori > 2) {
                        throw new ArithmeticException();
                    }
                    Comenzi comanda = new Comenzi(idComanda, produseListDinConsola, nrLivratori);
                    System.out.println(comanda);
                    comenziListFisier.add(comanda);
                } catch (IncorrectFileNameException e) {
                    System.out.println("Denumirea contine caractere invalide: = '',,}} sau Produse ");
                } catch (ArithmeticException e) {
                    System.out.println("Ati ales un numar prea mare sau prea mic de livratori");
                } catch (Exception e) {
                    IncorrectFileNameException i = null;
                    System.out.println("Trebuie sa introduceti doar cifre, fara litere");
                }

                break;
            case "read":
                System.out.println("Operatia de citire");
                for (Comenzi comenzi : comenziListFisier) {
                    System.out.println(comenzi);
                }
                break;
            case "delete":
                System.out.println("Operatia de stergere");
                System.out.println("Introduceti id-ul comenzii pe care vreti sa o stergeti");
                int idComandaSters = inputConsola.nextInt();
                int contor = comenziListFisier.size();
                for (int i = 0; i < comenziListFisier.size(); i++) {
                    if (comenziListFisier.get(i).getId() == idComandaSters) {
                        comenziListFisier.remove(i);
                        contor++;
                    }
                }
                if (comenziListFisier.size() == contor) {
                    System.out.println("Nu exista comanda cu acest id");
                }
                else{
                    System.out.println("fisierul a fost sters");
                }
                break;
            case "update":
                System.out.println("Operatia de update");
                System.out.println("Introduceti id-ul comenzii pe care vreti sa o actualizati ");
                int idComandaDeActualizat = inputConsola.nextInt();
                for (Comenzi comenzi : comenziListFisier) {
                    String contorSchimbat = "";
                    if (comenzi.getId() == idComandaDeActualizat) {
                        while (contorSchimbat.equals("")) {
                            System.out.println("Ce doriti sa schimbati? comanda sau listaProduse");
                            System.out.println("Cand doriti sa incetati scrieti niciuna");
                            String operatieUpdate = inputConsola.next().trim().toLowerCase();
                            if (operatieUpdate.equals("comanda")) {
                                System.out.println("Ce doriti sa modificati in comanda? id, nrLivratori, numeLivrator");
                                String operatieComandaUpdate = inputConsola.next().trim().toLowerCase();
                                if (operatieComandaUpdate.equals("id")) {
                                    System.out.println("Care va fi noul id al comenzii? ");
                                    int idNou = inputConsola.nextInt();
                                    for (int i = 0; i < comenziListFisier.size(); i++) {
                                        if (comenziListFisier.get(i).getId() != idNou) {
                                            comenzi.setId(idNou);
                                        } else {
                                            System.out.println("Exista deja o comanda cu acest id");
                                        }
                                    }
                                } else if (operatieComandaUpdate.equals("nrlivratori")) {
                                    System.out.println("Care va fi noul numar de livratori? 1 sau 2");
                                    int nrLivratoriNou = inputConsola.nextInt();
                                    if (nrLivratoriNou < 1 || nrLivratoriNou > 2) {
                                        throw new ArithmeticException("Numar invalid");
                                    } else {
                                        comenzi.setNrLivratori(nrLivratoriNou);
                                    }
                                } else if (operatieComandaUpdate.equals("numelivrator")) {
                                    System.out.println("Care va fi noul nume al livratorui?");
                                    String numeNou = inputConsola.next().trim();
                                    comenzi.setNumeLivrator(numeNou);
                                }
                            } else if (operatieUpdate.equals("listaproduse")) {
                                System.out.println("Introduceti id-ul produsului pe care vreti sa il modificati");
                                int idProdusDeModificat = inputConsola.nextInt();
                                for (int i = 0; i < comenzi.getProduseList().size(); i++) {
                                    if (comenzi.getProduseList().get(i).getId() == idProdusDeModificat) {
                                        System.out.println("Ce doriti sa modificati in produs? id, denumirea, pretul, cantitatea");
                                        String operatiePeProdus = inputConsola.next();
                                        if (operatiePeProdus.equals("id")) {
                                            System.out.println("In ce id vreti sa schimbati produsul? ");
                                            int idNou = inputConsola.nextInt();
                                            for (Produse produse : comenzi.getProduseList()) {
                                                if (produse.getId() == idNou) {
                                                    System.out.println("Exista deja produsul cu acest id");
                                                } else {
                                                    comenzi.getProduseList().get(i).setId(idNou);
                                                }
                                            }
                                        } else if (operatiePeProdus.equals("denumirea")) {
                                            System.out.println("Care va fi noua denumire a produsului?");
                                            try {
                                                String nouaDenumire = inputConsola.next().trim();
                                                if (nouaDenumire.contains("'") || nouaDenumire.contains("Produse") || nouaDenumire.contains(",") || nouaDenumire.contains("}") || nouaDenumire.contains("=")) {
                                                    throw new IncorrectFileNameException("Denumirea contine apostrof ' ");
                                                } else {
                                                    comenzi.getProduseList().get(i).setDenumire(nouaDenumire);
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Denumirea contine caractere invalide: '',,}} sau Produse ");
                                            }
                                        } else if (operatiePeProdus.equals("pretul")) {
                                            System.out.println("Care va fi noul pret al produsului");
                                            float pretNou = inputConsola.nextFloat();
                                            comenzi.getProduseList().get(i).setPret(pretNou);
                                        } else if (operatiePeProdus.equals("cantitatea")) {
                                            System.out.println("Care va fi cantitatea noua al produsului");
                                            int cantitateNoua = inputConsola.nextInt();
                                            comenzi.getProduseList().get(i).setCantitate(cantitateNoua);
                                        }
                                    }
                                }
                            } else if (operatieUpdate.equals("niciuna")) {
                                contorSchimbat = "niciuna";
                            } else {
                                System.out.println("Nu exista vreo astfel de operatie");
                            }
                        }
                    }
                }
                break;
            case "niciuna":
                System.out.println("Nu ati ales nicio operatie");
                break;
            default:
                System.out.println("Introduceti una din operatiile enumerate");
                break;
        }

        //Introducere in fisiere
        TreeMap<Integer,Float> raportPret=new TreeMap<>();
        for(Comenzi comenzi:comenziListFisier) {
            raportPret.put(comenzi.getId(),comenzi.sumaComanda(comenzi.getId()));
        }
        PrintStream printStreamRaport = new PrintStream(new File("src/proiectPPOO/raport.txt"));
        for(Map.Entry<Integer, Float> raportComanda : raportPret.entrySet()){
            int idComanda=raportComanda.getKey();
            float pretComanda=raportComanda.getValue();
            printStreamRaport.append("Comanda ").append(String.valueOf(idComanda)).append(" are costul total ").append(String.valueOf(pretComanda)).append("\n");
        }

        PrintStream printStreamFisier = new PrintStream(new File("src/proiectPPOO/fisier.txt"));
        for (Comenzi comenzi : comenziListFisier) {
            printStreamFisier.append(comenzi.toString()).append("\n");
        }
    }
}

