package c26_01_21;

import _shared.BinarySearchTree;

public class Verwaltung {

    private BinarySearchTree<Geräusch> baum;

    public Verwaltung(){
        baum= new BinarySearchTree<Geräusch>();
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

    public void ausgeben() {
        //TODO: DEIN CODE HIER
    }
}