import _shared.List;

public class Benutzer {

    private String name;
    private String passwort;
    private String aktIP;
    private int aktPort;

    private List<Miau> miaus;
    private List<Benutzer> freunde;    

    public Benutzer(String pName, String pPass) {}

    public String gibName() {
        return name;
    }

    public String gibAktIP() {
        return aktIP;
    }

    public int gibAktPort() {
        return aktPort;
    }

    public void einloggen(String pAktIP, int pAktPort, String pPass) {}
    public void ausloggen() {}
    public boolean istEingeloggt() {}
    public List<Miau> gibMiaus() {
        return miaus;
    }

    public void fuegeFreundeHinzu(Benutzer, pBenutzer) {
        freunde.append(pBenutzer);
    }

    public boolean beinhaltet(List<Benutzer> pListe, Benutzer pBenutzer) {}

}