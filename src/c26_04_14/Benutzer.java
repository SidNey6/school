import _shared.List;

public class Benutzer {

    private String name;
    private String passwort;
    private String aktIP;
    private int aktPort;

    private List<Miau> miaus;
    private List<Benutzer> freunde;    

    public Benutzer(String pName, String pPass) {
        name = pName;
        passwort = pPass;
        aktIP = null;
        aktPort = -1;
    }

    public String gibName() {
        return name;
    }

    public String gibAktIP() {
        return aktIP;
    }

    public int gibAktPort() {
        return aktPort;
    }

    public void einloggen(String pAktIP, int pAktPort, String pPass) {
        if(pPass.equals(passwort)) {
            aktIP = pAktIP;
            aktPort = pAktPort;
        } else {
            System.out.println("einloggen nicht erfolgreich")
        }
    }
    public void ausloggen() {
        aktIP = null;
        aktPort = -1;
    }

    public boolean istEingeloggt() {
        if(aktIP != null) {
            if(aktPort > 0)
        }
    }
    public List<Miau> gibMiaus() {
        return miaus;
    }

    public void fuegeFreundeHinzu(Benutzer, pBenutzer) {
        freunde.append(pBenutzer);
    }

    public boolean beinhaltet(List<Benutzer> pListe, Benutzer pBenutzer) {}

}