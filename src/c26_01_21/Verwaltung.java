package c26_01_21;

import _shared.BinarySearchTree;

public class Verwaltung {

    private BinarySearchTree<Geräusch> baum;
    private int anzahlElemente;

    public Verwaltung(){
        baum = new BinarySearchTree<Geräusch>();
        anzahlElemente = 0;
    }

    public Geräusch suche(Geräusch pGeräusch){
        return baum.search(pGeräusch);
    }

    public void hinzufügen(Geräusch pGeräusch){
        baum.insert(pGeräusch);
        anzahlElemente++;
    }

    public void entfernen(Geräusch pGeräusch){
        baum.remove(pGeräusch);
        anzahlElemente--;
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