import _shared.List;

public class Hauptprogramm(){

    Verwaltun v = new Verwaltung;
    List<List<String>> ergebnisse = v.gibAllePfade("A", "D");
    for(ergebnisse.toFirst(); ergebnisse.hasAccess(); ergebnisse.next()) {
        List<String> aktListe = ergebnisse.getContent();
        for(aktListe.toFirst(); aktListe.hasAccess(); aktListe.next()) {
            System.out.println(aktListe.getContent());
        }
    }
}