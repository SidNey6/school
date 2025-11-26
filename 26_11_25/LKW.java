public class LKW {
    private Stack<Paket> inhalt;

    public LKW(){
        inhalt = new Stack<Paket>();
    }

    public void beladeLkw(int pAnzahlPakete) {
        for(int i = 0; i < pAnzahlPakete; i++) {
            inhalt.push(new  Paket((int) (Math.random()*100)));
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