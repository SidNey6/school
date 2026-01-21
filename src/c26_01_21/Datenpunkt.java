package c26_01_21;

import _shared.ComparableContent;

public class Datenpunkt implements ComparableContent<Datenpunkt> {

    private String name;
    private int lautstaerke;

    public Datenpunkt(String pName, int pLautstaerke){
        name = pName;
        lautstaerke = pLautstaerke;
    }

    public String getName(){
        return name;
    }

    public int getLautstaerke(){
        return lautstaerke;
    }


    public boolean isLess(Datenpunkt pContent){
        return this.lautstaerke < pContent.getLautstaerke();
    }
    public boolean isEqual(Datenpunkt pContent){ // wenn lautstärke gleich; lexiographisch weiter sortieren!
        return this.lautstaerke == pContent.getLautstaerke();
    }
    public boolean isGreater(Datenpunkt pContent){
        return this.lautstaerke > pContent.getLautstaerke();
    }

}