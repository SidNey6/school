public class Gabelstapler {
    private Stack<Paket> inhalt = new Stack<Paket>();
    private int maxGewicht;
    private int aktGewicht;

    public Gabelstapler() {
        inhalt = new Stack<Paket>();
        maxGewicht = 800;
        aktGewicht = 0;
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
    }

    public void bringPaketeWeg() {
        inhalt = new Stack<Paket>();
    }
}