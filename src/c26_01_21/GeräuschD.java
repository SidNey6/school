package c26_01_21;

import _shared.ComparableContent;
import java.util.Date;
import java.util.LocalDate;

public class GeräuschD extends Geräusch {

    private String typ;

    public GeräuschD(String pName, int pLautstärke, Date pDatum, String pUrheber) {
        super(pName, pLautstärke, pDatum, pUrheber);
        this.typ = "D";
    }
    
    public String gibTyp() {
        return typ;
    }

    public boolean isLess(Geräusch pContent){
        return this.gibDatum().toLocalDate().compareTo(pContent.gibDatum().toLocalDate()) < 0;
    }
    public boolean isEqual(Geräusch pContent){
        return this.gibDatum().toLocalDate().compareTo(pContent.gibDatum().toLocalDate()) == 0;
    }
    public boolean isGreater(Geräusch pContent){
        return this.gibDatum().toLocalDate().compareTo(pContent.gibDatum().toLocalDate()) > 0;
    }
}