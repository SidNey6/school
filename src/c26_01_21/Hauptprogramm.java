package c26_01_21;

import java.time.LocalDate;
import _shared.List;

public class Hauptprogramm {
    private Hauptprogramm(){}

    public static void main(String[] args){
        Verwaltung v = new Verwaltung();
        fülleBaum(v);
        v.ausgeben();
        System.out.println("-------------------------------------------------");
        List<Geräusch> liste = v.gibAlleGeräuscheAb(32);
        for(liste.toFirst(); liste.hasAccess(); liste.next()) {
            System.out.println(liste.getContent().gibName() + " | " + liste.getContent().gibLautstärke() );
        }
    }

    public static void fülleBaum(Verwaltung pV) {
        if(pV == null) return;
        pV.hinzufügen(new GeräuschD("Flugzeugmotor", 120, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Motorsäge", 100, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Motorrad", 90, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Lautes Reden", 80, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Flüstern", 40, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Atmen", 10, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Implementationsphase im Informatikunterricht", 35, new Date(2026, 01, 21, 9, 16), "Weber"));
        pV.hinzufügen(new GeräuschD("Spülmaschine", 45, new Date(2026, 01, 25, 9, 16), "Cherno"));
        pV.hinzufügen(new GeräuschD("Kino", 81, new Date(2026, 01, 21, 9, 16), "Paul"));
        pV.hinzufügen(new GeräuschD("Obiraum", 67, new Date(2026, 01, 21, 9, 16), "Paul"));
        pV.hinzufügen(new GeräuschD("Flur vor den Klassenzimmern", 63, new Date(2026, 01, 21, 9, 16), "Paul"));
        pV.hinzufügen(new GeräuschD("schneidende Schere", 43, new Date(2026, 01, 21, 9, 16), "Simon"));
        pV.hinzufügen(new GeräuschD("mechanische Tastatur", 61, new Date(2026, 01, 21, 9, 16), "Simon"));
    }
}