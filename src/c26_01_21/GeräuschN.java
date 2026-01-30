package c26_01_21;

import _shared.ComparableContent;
import java.util.Date;

public class GeräuschN extends Geräusch {

    private String typ;

    public GeräuschN(String pName, int pLautstärke, Date pDatum, String pUrheber) {
        super(pName, pLautstärke, pDatum, pUrheber);
        typ = "N";
    }
    
    public String gibTyp() {
        return typ;
    }

    public boolean isLess(Geräusch pContent){
        return this.gibName().compareTo(pContent.gibName()) < 0;
    }
    public boolean isEqual(Geräusch pContent){
        return this.gibName().equals(pContent.gibName());
    }
    public boolean isGreater(Geräusch pContent){
        return this.gibName().compareTo(pContent.gibName()) > 0;
    }
}