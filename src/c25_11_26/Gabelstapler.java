import _shared.Stack;

public class Gabelstapler {
    private Stack<Paket> inhalt = new Stack<Paket>();
    private int maxGewicht;
    private int aktGewicht;
    private java.util.List<Paket> paketListe;

    public Gabelstapler() {
        inhalt = new Stack<Paket>();
        maxGewicht = 800;
        aktGewicht = 0;
        paketListe = new java.util.ArrayList<Paket>();
    }

    public int gibMaxGewicht() {
        return maxGewicht;
    }

    public int gibAktGewicht() {
        return aktGewicht;
    }

    public void belade(Paket pPaket) {
        inhalt.push(pPaket);
        aktGewicht = aktGewicht + pPaket.gibGewicht();
        paketListe.add(pPaket);
    }

    public void bringPaketeWeg() {
        inhalt = new Stack<Paket>();
        aktGewicht = 0;
        paketListe = new java.util.ArrayList<Paket>();
    }

    public boolean istVoll() {
        return aktGewicht >= maxGewicht;
    }

    public java.util.List<Paket> gibPakete() {
        return new java.util.ArrayList<Paket>(paketListe);
    }

    /**
     * Entleert den Gabelstapler und liefert die zuvor enthaltenen Pakete zurück.
     * Wird verwendet, damit außerhalb (z.B. in Verwaltung) die Pakete inspiziert
     * werden können, ohne am Stack selbst Änderungen vorzunehmen.
     *
     * @return Kopie der Paketliste vor dem Leeren
     */
    public java.util.List<Paket> entleereUndGibPakete() {
        java.util.List<Paket> copy = new java.util.ArrayList<Paket>(paketListe);
        inhalt = new Stack<Paket>();
        aktGewicht = 0;
        paketListe = new java.util.ArrayList<Paket>();
        return copy;
    }
}