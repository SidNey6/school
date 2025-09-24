public class Sachen {
 
    public int findeAlleUngeloestenFaelle(){
        int counter;
        for (faelle.toFirst();faelle.hasAccess(); faelle.next()) {
            if(!current.getContent().getIstAbgeschlossen()){
                counter++;
            }
        }
        return counter;
    }
     
   public int wasMacheIch() {
        List<Fall> tmpListe = new List<Fall>();
        for(detektive.toFirst(); detektive.hasAccess(); detektive.next()){
            Detektiv aktDetektiv = detektive.getContent();
            Fall[] aktFaelle = aktDetektiv.gibFaelle();
            for(Fall aktFall : aktFaelle) {
                boolean gefunden = false;
                for(tmpListe.toFirst(); tmpListe.hasAccess() && !gefunden; tmpListe.next()){
                    if(tmpListe.getContent() == aktFall) {
                        gefunden = true;
                    }
                }
                if(!gefunden && !aktFall.getIstAbgeschlossen()) {
                    tmpListe.append(aktFall);
                }
               
            }
        }
        int anzahl = 0;
        for(tmpListe.toFirst(); tmpListe.hasAccess(); tmpListe.next()){
            anzahl++;
        }
        return anzahl;
    }
}