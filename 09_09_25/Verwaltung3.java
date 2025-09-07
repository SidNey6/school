// VERSION 3 - Array in seiner Länge anpassen
/*Das Array soll immer nur so groß sein, wie
es tatsächlich Kurs-Objekte enthält*/

public class Verwaltung3 {
    private Kurs[] kurse;

    public Verwaltung3() {
        kurse = new Kurs[0];
    }

    public void kursHinzufuegen(Kurs pKurs) {
       Kurs[] tempKurs = new Kurs[kurse.length+1]; // erstellt einen temporären Array an Kursen, welcher 1 größer ist als das original
       tempKurs[0] = pKurs; // setzt das neue Objekt ganz an den Anfang
       for (int i = 1; i < tempKurs.length; i++) {
           tempKurs[i] = kurse[i-1]; // die Objekte aus Kurs werden in tempKurs übertragen
       }
       kurse = tempKurs;
    }

    public void kursEntfernen(Kurs pKurs) { // funktioniert nicht. Ich verstehe aber nicht warum.
        boolean found = false; // zu entfernendes Kurs gefunden?
        Kurs[] tempKurs = new Kurs[kurse.length-1]; // erstellt einen temporären Array an Kursen, welcher 1 kleiner ist als das original
        for (int i = 0; i < kurse.length; i++) {
            if (kurse[i] == pKurs) { // wenn zu entfernende Kurs gefunden ist
                found = true;
            }
            if (kurse[i] != pKurs && !found) { // wenn es nicht der zu findende Kurs ist und er noch nicht gefunden wurde
                tempKurs[i] = kurse[i];
            }
            if (kurse[i] != pKurs && found) { // wenn es nicht der zu entfernende Kurs ist und er schon gefunden wurde
                tempKurs[i-1] = kurse[i];
            }
        }
        kurse = tempKurs;
    }

    public Kurs sucheKurs(String pBeschreibung) {
        Kurs ergKurs = null;
        /*
            - Suche in dem Array kurse nach einem
            Kurs mit der Beschreibung pBeschreibung
            und gib den Kurs - falls vorhanden - zurück.
            - Falls kein Kurs gefunden wurde, gib null zurück.
        */
        return ergKurs;
    }

    public int gibAnzahlKurse() {
        int erg = 0;
        /*
            - Zählt, wie viele nicht-null-Einträge
            in dem Array kurse enthalten sind.
        */
        return erg;
    }

    public void kurseAusgeben() {
        for(Kurs aktKurs : kurse) {
            if(aktKurs != null) {
                System.out.println(aktKurs);
            }
            else {
                System.out.println("null");
            }
        }
    }
}