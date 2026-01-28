package c26_01_21;

import java.util.Date;

public class Hauptprogramm {
    private Hauptprogramm(){}

    public static void main(String[] args){
        Verwaltung v = new Verwaltung();
        fülleBaum(v);
        v.ausgeben();
    }

    public static void fülleBaum(Verwaltung pV) {
        if(pV == null) return;
        pV.hinzufügen(new Geräusch("Flugzeugmotor", 120, null, "Weber"));
        pV.hinzufügen(new Geräusch("Motorsäge", 100, null, "Weber"));
        pV.hinzufügen(new Geräusch("Motorrad", 90, null, "Weber"));
        pV.hinzufügen(new Geräusch("Lautes Reden", 80, null, "Weber"));
        pV.hinzufügen(new Geräusch("Flüstern", 40, null, "Weber"));
        pV.hinzufügen(new Geräusch("Atmen", 10, null, "Weber"));
        pV.hinzufügen(new Geräusch("Implementationsphase im Informatikunterricht", 35, new Date(2026, 01, 21, 09, 16), "Weber"));
        pV.hinzufügen(new Geräusch("Spülmaschine", 45, new Date(2026, 01, 25, 09, 16), "Cherno"));
        pV.hinzufügen(new Geräusch("Kino", 81, new Date(2026, 01, 21, 09, 16), "Paul"));
        pV.hinzufügen(new Geräusch("Obiraum", 67, new Date(2026, 01, 21, 09, 16), "Paul"));
        pV.hinzufügen(new Geräusch("Flur vor den Klassenzimmern", 63, new Date(2026, 01, 21, 09, 16), "Paul"));
        pV.hinzufügen(new Geräusch("schneidende Schere", 43, new Date(2026, 01, 21, 09, 16), "Simon"));
        pV.hinzufügen(new Geräusch("mechanische Tastatur", 61, new Date(2026, 01, 21, 09, 16), "Simon"));
    }
}