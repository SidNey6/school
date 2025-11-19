
import java.util.Random;

public class Saal {
    private int nummer;
    private Sitz[][] sitze;
    
    public Saal(int pNummer, int pReihen, int pPlaetze) {
        nummer = pNummer;
        sitze = new Sitz[pReihen][pPlaetze];
        for(int i = 0; i < pReihen; i++) {
            for(int j = 0; j < pPlaetze; j++) {
                sitze[i][j] = new Sitz(i, j);
            }
        }
    }
    
    public List<Sitz> gibFreieSitze() {
        List<Sitz> erg = new List<Sitz>();
        for(Sitz[] aktReihe: sitze) {
            for(Sitz aktSitz: aktReihe) {
                if(aktSitz.gibPerson() == null) {
                    erg.append(aktSitz);
                }
            }
        }
        return erg;
    }
    public int gibNummer() {
        return nummer;
    }
    public void reserviere(Person pPerson) {
        Random r = new Random();
        if(sitze == null) return;
        if(sitze.length < 1) return;
        if(sitze[0].length < 1) return;
        
        int x = r.nextInt(sitze.length);
        int y = r.nextInt(sitze[0].length);
        
        while(sitze[x][y].gibPerson() != null) {
            x = r.nextInt(sitze.length);
            y = r.nextInt(sitze[0].length);
        }
        sitze[x][y].setzePerson(pPerson);
        pPerson.sitzHinzufuegen(sitze[x][y]);
    }
    public void reserviere(List<Person> pPersonen) {
        int anzahl = gibAnzahlPersonen(pPersonen);
        List<Sitz> freieSitze = gibFreieSitze();
        for(Sitz[] aktReihe: sitze) {
            int ersterPlatz = findeErstenPlatz(aktReihe, anzahl, anzahl, 0);
            if(ersterPlatz > -1) {
                pPersonen.toFirst();
                while(!pPersonen.isEmpty()) {
                    Person aktPerson = pPersonen.getContent();
                    aktPerson.sitzHinzufuegen(aktReihe[ersterPlatz]);
                    aktReihe[ersterPlatz].setzePerson(aktPerson);
                    pPersonen.remove();
                    ersterPlatz++;
                }
                return;
            }
        }
    }
    
    private int findeErstenPlatz(Sitz[] pReihe, int pAnzahl, int pAktAnzahl, int pAktPlatz) {
        if(pAktAnzahl == 0) return pAktPlatz-pAnzahl-1;
        if(pAktAnzahl >= pReihe.length) return -1;
        if(pAktPlatz >= pReihe.length) return -1;
        if(pAktAnzahl == 1 && pReihe[pAktPlatz].gibPerson() == null) return pAktPlatz-pAnzahl+1;
        if(pReihe[pAktPlatz].gibPerson() != null) return findeErstenPlatz(pReihe, pAnzahl, pAnzahl, pAktPlatz+1);
        else return findeErstenPlatz(pReihe, pAnzahl, pAktAnzahl-1, pAktPlatz+1);
    }
    private int gibAnzahlPersonen(List<Person> pPersonen) {
        int erg = 0;
        if(!pPersonen.isEmpty()) {
            for(pPersonen.toFirst(); pPersonen.hasAccess(); pPersonen.next()) {
                erg++;
            }
        }
        return erg;
    }
    
    public String toString() {
        String erg = "";
        for(Sitz[] aktReihe : sitze) {
            for(Sitz aktPlatz : aktReihe) {
                Person aktPerson = aktPlatz.gibPerson();
                erg += aktPerson == null ? "[-]" : "["+aktPerson.gibId()+"]";
                erg += "\t";
            }
            erg+="\n";
        }
        return erg;
    }
    public void ausgeben() {
        System.out.println(this);
    }
    
}
