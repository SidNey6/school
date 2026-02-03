package c26_01_21;

import _shared.ComparableContent;
import java.time.LocalDate;

public class GeräuschD extends Geräusch {

    private String typ;

    public GeräuschD(String pName, int pLautstärke, LocalDate pDatum, String pUrheber) {
        super(pName, pLautstärke, pDatum, pUrheber);
        this.typ = "D";
    }
    
    public String gibTyp() {
        return typ;
    }

    public boolean isLess(Geräusch pContent){
        return this.gibDatum().isAfter(pContent.gibDatum()) ;
    }
    public boolean isEqual(Geräusch pContent){
        return this.gibDatum().isEqual(pContent.gibDatum());
    }
    public boolean isGreater(Geräusch pContent){
        return this.gibDatum().isBefore(pContent.gibDatum());
    }
}