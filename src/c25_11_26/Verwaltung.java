// Zum Ausführen:
// cd /workspaces/school/26_11_25 && javac *.java
// java -cp . Verwaltung

import _shared.Stoppuhr;

public class Verwaltung {
    
    LKW lkw;
    Gabelstapler[] alleGabelstapler;

    int kosten;
    private java.util.List<AbgefahreneInfo> abgefahreneHistorie;

    public Verwaltung(){
        lkw = new LKW();
        alleGabelstapler = new Gabelstapler[5];
        for (int i = 0; i < alleGabelstapler.length; i++) {
            alleGabelstapler[i] = new Gabelstapler();
        }
        abgefahreneHistorie = new java.util.ArrayList<AbgefahreneInfo>();
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
            if(aktGabelstapler != null && aktGabelstapler.gibAktGewicht() + aktPaket.gibGewicht() <= aktGabelstapler.gibMaxGewicht()) {
                aktGabelstapler.belade(aktPaket);
                return;
            }
        }

        for(int i = 0; i < alleGabelstapler.length; i++) {
            Gabelstapler g = alleGabelstapler[i];
            // Erfasse Zustand vor dem Entleeren
            int vorherGewicht = g.gibAktGewicht();
            java.util.List<Paket> paketeVorher = g.entleereUndGibPakete();
            // Neu: nimm in die Historie auf, wenn der Gabelstapler vor dem Abfahren Pakete hatte
            if (paketeVorher != null && !paketeVorher.isEmpty()) {
                abgefahreneHistorie.add(new AbgefahreneInfo(i, vorherGewicht, paketeVorher));
            }
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

    public void druckeGabelstaplerStatus() {
        System.out.println("Gabelstapler-Status:");
        for (int i = 0; i < alleGabelstapler.length; i++) {
            Gabelstapler g = alleGabelstapler[i];
            if (g != null) {
                String voll = g.istVoll() ? "voll" : "nicht voll";
                System.out.println("Gabelstapler " + i + ": " + voll + ", aktGewicht=" + g.gibAktGewicht());
                java.util.List<Paket> pakete = g.gibPakete();
                if (pakete == null || pakete.isEmpty()) {
                    System.out.println("  keine Pakete");
                } else {
                    for (int j = 0; j < pakete.size(); j++) {
                        Paket p = pakete.get(j);
                        System.out.println("  Paket " + j + ": Gewicht=" + p.gibGewicht());
                    }
                }
            } else {
                System.out.println("Gabelstapler " + i + ": null");
            }
        }
        // Ausgabe der Historie: abgefahrene, zuvor volle Gabelstapler
        System.out.println("\nAbgefahrene (voll) Gabelstapler-Historie:");
        if (abgefahreneHistorie.isEmpty()) {
            System.out.println("  keine abgefahrenen (vollen) Gabelstapler");
        } else {
            for (AbgefahreneInfo info : abgefahreneHistorie) {
                System.out.println("Gabelstapler " + info.index + " (vorher aktGewicht=" + info.aktGewicht + ")");
                if (info.pakete == null || info.pakete.isEmpty()) {
                    System.out.println("  keine Pakete");
                } else {
                    for (int k = 0; k < info.pakete.size(); k++) {
                        System.out.println("  Paket " + k + ": Gewicht=" + info.pakete.get(k).gibGewicht());
                    }
                }
            }
        }
        System.out.println("Kosten insgesamt: " + kosten + "\n");
    }

    private static class AbgefahreneInfo {
        int index;
        int aktGewicht;
        java.util.List<Paket> pakete;

        AbgefahreneInfo(int index, int aktGewicht, java.util.List<Paket> pakete) {
            this.index = index;
            this.aktGewicht = aktGewicht;
            this.pakete = pakete == null ? new java.util.ArrayList<Paket>() : new java.util.ArrayList<Paket>(pakete);
        }
    }

    public static void main(String[] args) {
        Stoppuhr stoppuhr = new Stoppuhr();
        Verwaltung verwaltung = new Verwaltung();
        verwaltung.lkw.beladeLkw(20);
        verwaltung.beladePakete();
        verwaltung.druckeGabelstaplerStatus();
        stoppuhr.stopp();
        System.out.println("Dauer: " + stoppuhr.gestoppteZeit() + " ms");
    }
}