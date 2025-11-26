public class Verwaltung {
    
    LKW lkw;
    Gabelstapler[] alleGabelstapler;

    int kosten;

    public Verwaltung(){
        lkw = new LKW();
        alleGabelstapler = new Gabelstapler[5];
    }

    public Paket entladen(){
        Paket zuEntladendesPaket = lkw.gibOberstesPaket();
        lkw.entferneOberstesPaket();
        return zuEntladendesPaket;
    }

    public void schickeGabelstaplerWeg(Gabelstapler pGabelstapler) {
        pGabelstapler.bringPaketeWeg();
    }

    public void beladePaket(){
        Paket aktPaket = entladen();
        if(aktPaket == null) {
            return;
        }

        for(int i = 0; i < alleGabelstapler.length; i++) {
            Gabelstapler aktGabelstapler = alleGabelstapler[i];
            if(aktGabelstapler.gibAktGewicht() < aktGabelstapler.gibMaxGewicht() - aktPaket.gibGewicht()) {
                aktGabelstapler.belade(aktPaket);
                return;
            }
        }

        for(int i = 0; i < alleGabelstapler.length; i++) {
            alleGabelstapler[i].bringPaketeWeg();
            kosten = kosten + 25;
        }
        lkw.legePaketZurück(aktPaket);
        
        beladePaket();
    }

    public void beladePakete(){
        while(lkw.gibOberstesPaket() != null) {
            beladePaket();
        }
    }
}