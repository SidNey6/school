package c26_05_05;
import _shared.List;

public class Miau {
    private String text;
    private int miauID;
    private List<Benutzer> schnurrer = new List<Benutzer>();
   
    public Miau(String pText, int pMiauID){
        text = pText;
        miauID = pMiauID;
    }
 
    public String gibText(){
        return text;
    }
    public int gibMiauID(){
        return miauID;
    }
    public List<Benutzer> gibSchnurrer(){
        return schnurrer;
    }  
    public int liefereAnzahlSchnurrer() {
        int anzahlSchnurrer = 0;
        for (schnurrer.toFirst() ; schnurrer.hasAccess() ; schnurrer.next()) {
            anzahlSchnurrer =+ 1;
        }
        return anzahlSchnurrer;
    }
    public void fuegeSchnurrerHinzu(Benutzer pBenutzer){
        if (pBenutzer != null) {
            for (schnurrer.toFirst() ; schnurrer.hasAccess() ; schnurrer.next()) {
                if (schnurrer.getContent() == pBenutzer) {
                    return;
                }
            }
            schnurrer.append(pBenutzer);
        }
    }
}