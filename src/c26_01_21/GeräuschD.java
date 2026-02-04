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
        if(this.gibDatum().isEqual(pContent.gibDatum())) {
            return this.gibName().compareToIgnoreCase(pContent.gibName()) < 0;;
        }
        return  this.gibDatum().isBefore(pContent.gibDatum());;
    }
    public boolean isEqual(Geräusch pContent){
        if(this.gibDatum().isEqual(pContent.gibDatum())) {
            return this.gibName().equalsIgnoreCase(pContent.gibName());
        }
        return false;
    }
    public boolean isGreater(Geräusch pContent){
        if(this.gibDatum().isEqual(pContent.gibDatum())) {
            return this.gibName().compareToIgnoreCase(pContent.gibName()) > 0;
        }
        return this.gibDatum().isAfter(pContent.gibDatum());
    }
}