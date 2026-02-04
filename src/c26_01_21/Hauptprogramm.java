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
        pV.hinzufügen("Flugzeugmotor", 120, LocalDate.MIN, "Weber");
        pV.hinzufügen("Motorsäge", 100, LocalDate.MIN, "Weber");
        pV.hinzufügen("Motorrad", 90, LocalDate.MIN, "Weber");
        pV.hinzufügen("Lautes Reden", 80, LocalDate.MIN, "Weber");
        pV.hinzufügen("Flüstern", 40, LocalDate.MIN, "Weber");
        pV.hinzufügen("Atmen", 10, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Implementationsphase im Informatikunterricht", 35, LocalDate.of(2026, 01, 21), "Weber");
        pV.hinzufügen("Spülmaschine", 45, LocalDate.of(2026, 01, 25), "Cherno");
        pV.hinzufügen("Kino", 81, LocalDate.of(2026, 01, 25), "Paul");
        pV.hinzufügen("Obiraum", 67, LocalDate.of(2026, 01, 26), "Paul");
        pV.hinzufügen("Flur vor den Klassenzimmern", 63, LocalDate.of(2026, 01, 26), "Paul");
        pV.hinzufügen("Schneidende Schere", 43, LocalDate.of(2026, 01, 21), "Simon");
        pV.hinzufügen("Mechanische Tastatur", 61, LocalDate.of(2026, 01, 26), "Simon");
        pV.hinzufügen("Reisebus", 72, LocalDate.of(2026, 01, 27), "Paul");
        pV.hinzufügen("Chemie Unterricht", 51, LocalDate.of(2026, 01, 27), "Olivia");
        pV.hinzufügen("Religionsunterricht", 54, LocalDate.of(2026, 01, 26), "Vera");
        pV.hinzufügen("Gruppenphase", 62, LocalDate.of(2026, 01, 26), "Vera");
        pV.hinzufügen("Kaffeemaschine", 71, LocalDate.of(2026, 01, 28), "Fabian");
        pV.hinzufügen("Bus", 71, LocalDate.of(2026, 01, 28), "Olivia");
    }
}