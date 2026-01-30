package c26_01_21;

import _shared.ComparableContent;
import java.util.Date;

public class GeräuschL extends Geräusch {

    private String typ;

    public GeräuschL(String pName, int pLautstärke, Date pDatum, String pUrheber) {
        super(pName, pLautstärke, pDatum, pUrheber);
        typ = "L";
    }

    public String gibTyp() {
        return typ;
    }

    public boolean isLess(Geräusch pContent){
        return this.gibLautstärke() < pContent.gibLautstärke();
    }
    public boolean isEqual(Geräusch pContent){
        return this.gibLautstärke() == pContent.gibLautstärke();
    }
    public boolean isGreater(Geräusch pContent){
        return this.gibLautstärke() > pContent.gibLautstärke();
    }
}