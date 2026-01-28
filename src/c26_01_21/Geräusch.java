package c26_01_21;

import _shared.ComparableContent;

//Ein Objekt vom Typ Geräusch kann mit einem Objekt vom Typ Geräusch verglichen werden
public class Geräusch implements ComparableContent<Geräusch> {

    private String name;
    private int lautstärke;
    private boolean eigeneMessung;
    private Date datum;
    private String Urheber;

    public Geräusch(String pName, int pLautstärke, Date pDatum, String pUrheber) {
        this.name = pName;
        if(pDatum != null) {
            this.eigeneMessung = True;
        } else {
            this.eigeneMessung = False;
        }
        this.datum = pDatum;
        this.Urheber = pUrheber;
    }

    public String gibName(){
        return this.name;
    }

    public int gibLautstärke(){
        return this.lautstärke;
    }

    public Date gibDatum(){
        return this.datum;
    }

    public String gibUrheber(){
        return this.Urheber;
    }

    public boolean isEigeneMessung(){
        return this.eigeneMessung;
    }


    public boolean isLess(Geräusch pContent){
        return this.lautstärke < pContent.gibLautstärke();
    }
    public boolean isEqual(Geräusch pContent){
        return this.lautstärke == pContent.gibLautstärke();
    }
    public boolean isGreater(Geräusch pContent){
        if (lautstärke == pContent.gibLautstärke()) {
            return this.name.compareTo(pContent.gibName()) > 0; // keine Ahnung wie compareTo funktioniert; aber wenn objekt lexikographisches größer ist als pContent, dann kommt da was positives raus
        }
        return this.lautstärke > pContent.gibLautstärke();
    }
}
