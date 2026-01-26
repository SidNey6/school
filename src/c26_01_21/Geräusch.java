package c26_01_21;

import _shared.ComparableContent;

//Ein Objekt vom Typ Geräusch kann mit einem Objekt vom Typ Geräusch verglichen werden
public class Geräusch implements ComparableContent<Geräusch> {

    private String name;
    private int lautstärke;
    //TODO: Weitere Attribute hinzufügen (siehe Excel-Tabelle)

    public Geräusch(String pName, int pLautstärke){
        name = pName;
        lautstärke = pLautstärke;
    }

    public String gibName(){
        return name;
    }

    public int gibLautstärke(){
        return lautstärke;
    }


    public boolean isLess(Geräusch pContent){
        return lautstärke < pContent.gibLautstärke();
    }
    public boolean isEqual(Geräusch pContent){
        // TODO: wenn lautstärke gleich; lexiographisch weiter vergleichen
        return lautstärke == pContent.gibLautstärke();
    }
    public boolean isGreater(Geräusch pContent){
        return lautstärke > pContent.gibLautstärke();
    }
    public String toString() {
        String erg = "";
        //TODO: DEIN CODE HIER
        return erg;
    }
}
