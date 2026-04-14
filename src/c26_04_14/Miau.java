import _shared.List;

public class Miau {
    private String text;
    private int miauID;
    private List<Benutzer> schnurrer;

    public Miau(String pText, int pMiauID) {}
    public String gibText() {
        return text;
    }

    public int gibMiauID() {
        return miauID;
    }

    public List<Benutzer> gibSchnurrer() {
        return schnurrer;
    }

    public int liefereAnzahlSchurrer() {}

    public void fuegeSchnurrerHinzu(Benutzer pBenutzer) {
        schnurrer.append(pBenutzer)
    }

}