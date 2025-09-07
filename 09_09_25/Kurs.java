public class Kurs {
    private String beschreibung;

    public Kurs(String pBeschreibung) {
        beschreibung = pBeschreibung;
    }

    public void setzeBeschreibung(String pBeschreibung) {
        beschreibung = pBeschreibung;
    }

    public String gibBeschreibung() {
        return beschreibung;
    }

    public String toString() {
        return beschreibung;
    }
}