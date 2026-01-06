import _shared.List;

public class Hauptprogramm {
    private Hauptprogramm(){}
    
    public static void main(String[] args) {
        Saalverwaltung v = new Saalverwaltung();
        Saal saal = v.erstelleSaal(1, 10, 10);
        List<Person> personen1 = new List<Person>();
        List<Person> personen2 = new List<Person>();
        List<Person> personen3 = new List<Person>();
        List<Person> personen4 = new List<Person>();
        List<Person> personen5 = new List<Person>();

        personen1.append(new Person(1, "A", "A"));
        personen1.append(new Person(2, "B", "B"));
        personen1.append(new Person(3, "C", "C"));
        personen1.append(new Person(4, "D", "D"));
        personen1.append(new Person(5, "E", "E"));
        personen1.append(new Person(6, "F", "F"));
        
        personen2.append(new Person(7, "G", "G"));
        personen2.append(new Person(8, "H", "H"));
        personen2.append(new Person(9, "I", "I"));
        personen2.append(new Person(10, "J", "J"));
        personen2.append(new Person(11, "K", "K"));
        
        personen3.append(new Person(12, "L", "L"));
        personen3.append(new Person(13, "M", "M"));
        personen3.append(new Person(14, "N", "N"));
        personen3.append(new Person(15, "O", "O"));
        personen3.append(new Person(16, "P", "P"));
        
        personen4.append(new Person(17, "Q", "Q"));
        personen4.append(new Person(18, "Q", "Q"));
        
        personen5.append(new Person(19, "R", "R"));
        personen5.append(new Person(20, "S", "S"));
        personen5.append(new Person(21, "T", "T"));
        
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        saal.reserviere(new Person(0, "X","X"));
        
        saal.reserviere(personen1);
        saal.reserviere(personen2);
        saal.reserviere(personen3);
        saal.reserviere(personen4);
        saal.reserviere(personen5);
        
        v.saeleAusgeben();
    }
}