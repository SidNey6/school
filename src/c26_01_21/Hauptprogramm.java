package c26_01_21;

public class Hauptprogramm {
    private Hauptprogramm(){}

    public static void main(String[] args){
        Verwaltung v = new Verwaltung();
        fülleBaum();
        v.ausgeben();
    }

    public static void fülleBaum(Verwaltung pV) {
        if(pV == null) return;
        pV.hinzufügen(new Geräusch("Flugzeugmotor", 120));
        //TODO: DEIN CODE HIER
    }
}