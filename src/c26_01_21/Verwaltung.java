package c26_01_21;

import _shared.BinarySearchTree;
import _shared.List;

public class Verwaltung {

    private BinarySearchTree<Geräusch> baum;

    public Verwaltung(){
        baum = new BinarySearchTree<Geräusch>();
    }

    public Geräusch suche(Geräusch pGeräusch){
        return baum.search(pGeräusch);
    }

    public void hinzufügen(Geräusch pGeräusch){
        baum.insert(pGeräusch);
    }

    public void entfernen(Geräusch pGeräusch){
        baum.remove(pGeräusch);
    }

    public List<Geräusch> gibAlleSchädlichenGeräusche(){
       return gibAlleGeräuscheAb(85);
    }

    public List<Geräusch> gibAlleGeräuscheAb(int pDezibel) {
        return gibAlleGeräuscheAbRekursiv(baum, pDezibel);
    }

    public List<Geräusch> gibAlleGeräuscheAbRekursiv(BinarySearchTree<Geräusch> pBaum, int pDezibel) {
        List<Geräusch> ergebnis = new List<Geräusch>();

        if(!pBaum.isEmpty()) {
            ergebnis.concat(gibAlleGeräuscheAbRekursiv(pBaum.getLeftTree(), pDezibel));
            if(pBaum.getContent().gibLautstärke() >= pDezibel) {
                ergebnis.append(pBaum.getContent());
            }
            ergebnis.concat(gibAlleGeräuscheAbRekursiv(pBaum.getRightTree(), pDezibel));
            return ergebnis;
        }
        return ergebnis;
    }

    public void ausgeben() {
        if(baum.isEmpty()){
            System.out.println("Der Baum ist leer");
        } else {
            ausgebenRekursiv(baum);
        }
    }

    private void ausgebenRekursiv(BinarySearchTree<Geräusch> pBaum) { // In-order Traversierung
        if (!pBaum.isEmpty()) {
            ausgebenRekursiv(pBaum.getLeftTree());
            System.out.println(pBaum.getContent().gibName() + " | " + pBaum.getContent().gibLautstärke() + " dB");
            ausgebenRekursiv(pBaum.getRightTree());
        }
    }
}