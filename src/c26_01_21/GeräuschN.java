package c26_01_21;

import _shared.ComparableContent;
import java.time.LocalDate;

public class GeräuschN extends Geräusch {

    private String typ;

    public GeräuschN(String pName, int pLautstärke, LocalDate pDatum, String pUrheber) {
        super(pName, pLautstärke, pDatum, pUrheber);
        typ = "N";
    }
    
    public String gibTyp() {
        return typ;
    }

    public boolean isLess(Geräusch pContent){
        return this.gibName().compareToIgnoreCase(pContent.gibName()) < 0;
    }
    
    public boolean isEqual(Geräusch pContent){
        return this.gibName().equalsIgnoreCase(pContent.gibName());
    }

    public boolean isGreater(Geräusch pContent){
        return this.gibName().compareToIgnoreCase(pContent.gibName()) > 0;
    }
    
}