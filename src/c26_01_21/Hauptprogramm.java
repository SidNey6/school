package c26_01_21;

import java.time.LocalDate;
import _shared.List;

public class Hauptprogramm {
    private Hauptprogramm(){}

    public static void main(String[] args){
        Verwaltung v = new Verwaltung("L");
        fülleBaum(v);
        v.ausgeben();
        System.out.println("-------------------------------------------------");
        v.setzeTyp("D");
        v.ausgeben();
    }

    public static void fülleBaum(Verwaltung pV) {
        if(pV == null) return;
        pV.hinzufügen("Flugzeugmotor", 120, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Motorsäge", 100, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Motorrad", 90, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Lautes Reden", 80, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Flüstern", 40, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Atmen", 10, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Implementationsphase im Informatikunterricht", 35, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Spülmaschine", 45, LocalDate.of(2026, 01, 25), "Cherno");
        pV.hinzufügen("Kino", 81, LocalDate.of(2026, 01, 21), "Paul");
        pV.hinzufügen("Obiraum", 67, LocalDate.of(2026, 01, 21), "Paul");
        pV.hinzufügen("Flur vor den Klassenzimmern", 63, LocalDate.of(2026, 01, 21), "Paul");
        pV.hinzufügen("schneidende Schere", 43, LocalDate.of(2026, 01, 21), "Simon");
        pV.hinzufügen("mechanische Tastatur", 61, LocalDate.of(2026, 01, 21), "Simon");
    }
}