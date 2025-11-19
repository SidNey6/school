
public class Sitz {
    private boolean bezahlt;
    private int reihe;
    private int platz;
    private Person person;
    
    public Sitz(int pReihe, int pPlatz) {
        reihe = pReihe;
        platz = pPlatz;
        bezahlt = false;
        person = null;
    }
    
    public void setzePerson(Person pPerson) {
        person = pPerson;
    }
    public void annulliere() {
        person = null;
        bezahlt = false;
    }
    
    public boolean gibBezahlt() {
        return bezahlt;
    }
    
    public void setzeBezahlt(boolean pBezahlt) {
        bezahlt = pBezahlt;
    }
    
    public int gibReihe() {
        return reihe;
    }
    
    public int gibPlatz() {
        return platz;
    }
    
    public Person gibPerson() {
        return person;
    }

    
}
