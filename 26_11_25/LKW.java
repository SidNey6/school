public class LKW {
    private Stack<Paket> inhalt;

    public LKW(){
        inhalt = new Stack<Paket>();
    }

    public void beladeLkw(int pAnzahlPakete) {
        for(int i = 0; i < pAnzahlPakete; i++) {
            int gewicht = (int) (Math.random() * 100);
            Paket p = new Paket(gewicht);
            inhalt.push(p);
            System.out.println("Generiertes Paket " + i + ": Gewicht=" + gewicht);
        }
    }

    public void legePaketZurück(Paket pPaket) {
        inhalt.push(pPaket);
    }

    public Paket gibOberstesPaket() {
        return inhalt.top();
    }

    public void entferneOberstesPaket() {
        inhalt.pop();
    }
}