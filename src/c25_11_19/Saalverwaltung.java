import _shared.List;

public class Saalverwaltung {
    private List<Saal> saele;
    private List<Person> personen;
    
    public Saalverwaltung() {
        saele = new List<Saal>();
        personen = new List<Person>();
    }
    public Saal erstelleSaal(int pNummer, int pReihen, int pAnzahlSitze) {
        Saal neuerSaal = new Saal(pNummer, pReihen, pAnzahlSitze);
        saele.append(neuerSaal);
        return neuerSaal;
    }
    public void entferneSaal(int pNummer) {
        if(saele.isEmpty()) return;
        for(saele.toFirst(); saele.hasAccess(); saele.next()) {
            if(saele.getContent().gibNummer() == pNummer) {
                saele.remove();
            }
        }
    }
    
    public void saeleAusgeben() {
        if(!saele.isEmpty()) {
            for(saele.toFirst(); saele.hasAccess(); saele.next()) {
                Saal aktSaal = saele.getContent();
                System.out.println("Saal " + aktSaal.gibNummer() + ":");
                aktSaal.ausgeben();
                System.out.println();
            }
        }
    }
    public List<Saal> gibSaele() {
        return saele;
    }
    public List<Person> gibPersonen() {
        return personen;
    }
}
