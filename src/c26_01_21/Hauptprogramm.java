package c26_01_21;

public class Hauptprogramm {
    private Hauptprogramm(){}

    public static void main(String[] args){
        Verwaltung v = new Verwaltung();
        fülleBaum(v);
        v.ausgeben();
    }

    public static void fülleBaum(Verwaltung pV) {
        if(pV == null) return;
        pV.hinzufügen(new Geräusch("Flugzeugmotor", 120, false, "Weber"));
        pV.hinzufügen(new Geräusch("Motorsäge", 100, false, "Weber"));
        pV.hinzufügen(new Geräusch("Motorrad", 90, false, "Weber"));
        pV.hinzufügen(new Geräusch("Lautes Reden", 80, false, "Weber"));
        pV.hinzufügen(new Geräusch("Flüstern", 40, false, "Weber"));
        pV.hinzufügen(new Geräusch("Atmen", 10, false, "Weber"));
        pV.hinzufügen(new Geräusch("Implementationsphase im Informatikunterricht", 35, true, "Weber"));
        pV.hinzufügen(new Geräusch("Spülmaschine", 45, true, "Cherno"));
        pV.hinzufügen(new Geräusch("Kino", 81, true, "Paul"));
        pV.hinzufügen(new Geräusch("Obiraum", 67, true, "Paul"));
        pV.hinzufügen(new Geräusch("Flur vor den Klassenzimmern", 63, true, "Paul"));
        pV.hinzufügen(new Geräusch("schneidende Schere", 43, true, "Simon"));
        pV.hinzufügen(new Geräusch("mechanische Tastatur", 61, true, "Simon"));
    }
}