package c26_01_21;

import _shared.BinarySearchTree;
import _shared.List;
import java.time.LocalDate;

public class Verwaltung {

    private String typ;
    private BinarySearchTree<Geräusch> baum;

    public Verwaltung(String pTyp) {
        this.typ = pTyp;
        baum = new BinarySearchTree<Geräusch>();
    }

    public Geräusch suche(Geräusch pGeräusch){
        return baum.search(pGeräusch);
    }

    public void setzeTyp(String pTyp) {
        this.typ = pTyp;
        BinarySearchTree<Geräusch> neuerBaum = new BinarySearchTree<Geräusch>();

        while(baum.getContent() != null) {
            neuerBaum.insert(transformiereGeräusch(baum.getContent(), pTyp));
            baum.remove(baum.getContent());
        }

        baum = neuerBaum;
    }


    public Geräusch transformiereGeräusch(Geräusch pGeräusch, String pTyp){
        Geräusch neuesGeräusch;
        if(pTyp.equals("N")) {
            neuesGeräusch = new GeräuschN(pGeräusch.gibName(), pGeräusch.gibLautstärke(), pGeräusch.gibDatum(), pGeräusch.gibUrheber());
        } else if(pTyp.equals("L")) {
            neuesGeräusch = new GeräuschL(pGeräusch.gibName(), pGeräusch.gibLautstärke(), pGeräusch.gibDatum(), pGeräusch.gibUrheber());
        } else if(pTyp.equals("D")) {
            neuesGeräusch = new GeräuschD(pGeräusch.gibName(), pGeräusch.gibLautstärke(), pGeräusch.gibDatum(), pGeräusch.gibUrheber());
        } else {
            return null;
        }
        return neuesGeräusch;
        
    }

    public void hinzufügen(String pName, int pLautstärke, LocalDate pDatum, String pUrheber){
        Geräusch neuesGeräusch;
        if(this.typ.equals("N")) {
            neuesGeräusch = new GeräuschN(pName, pLautstärke, pDatum, pUrheber);
        } else if(this.typ.equals("L")) {
            neuesGeräusch = new GeräuschL(pName, pLautstärke, pDatum, pUrheber);
        } else if(this.typ.equals("D")) {
            neuesGeräusch = new GeräuschD(pName, pLautstärke, pDatum, pUrheber);
        } else {
            return;
        }
        baum.insert(neuesGeräusch);
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
            System.out.println(pBaum.getContent().gibName() + " | " + pBaum.getContent().gibLautstärke() + " dB" + " | " + pBaum.getContent().gibDatum());
            ausgebenRekursiv(pBaum.getRightTree());
        }
    }
}