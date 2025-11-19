
public class Person {
    private int id;
    private String vorname;
    private String nachname;
    private List<Sitz> sitze;
    
    public Person(int pId, String pVorname, String pNachname) {
        id = pId;
        vorname = pVorname;
        nachname = pNachname;
        sitze = new List<Sitz>();
    }
    
    public int gibId() {
        return id;
    }
    
    public void bezahle(Sitz pSitz) {
        if(pSitz.gibPerson() == this && pSitz.gibBezahlt() == false) {
            pSitz.setzeBezahlt(true);    
        }
    }
    
    public void storniere(Sitz pSitz) {
        if(pSitz.gibPerson() == this) {
            pSitz.annulliere();
        }
    }
    
    public void sitzHinzufuegen(Sitz pSitz) {
        sitze.append(pSitz);
    }
    
    private boolean istRegistrierterSitz(Sitz pSitz) {
        if(!sitze.isEmpty()) {
            for(sitze.toFirst(); sitze.hasAccess(); sitze.next()) {
                if(sitze.getContent() == pSitz) return true;
            }
        }
        return false;
    }
    
    public List<Sitz> gibSitze() {
        return sitze;
    }
}
