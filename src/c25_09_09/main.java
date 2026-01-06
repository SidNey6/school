public class main {
    public static void main(String[] args) {
        System.out.println("Verwaltung:");
        Verwaltung3 v = new Verwaltung3();

        v.kursHinzufuegen(new Kurs("Kurs 1"));
        v.kursHinzufuegen(new Kurs("Kurs 2"));

        Kurs kurs3 = new Kurs("Kurs 3");
        v.kursHinzufuegen(kurs3);

        v.kursHinzufuegen(new Kurs("Kurs 4"));
        v.kursHinzufuegen(new Kurs("Kurs 5"));
        v.kursHinzufuegen(new Kurs("Kurs 6"));
        v.kursHinzufuegen(new Kurs("Kurs 7"));
        v.kursHinzufuegen(new Kurs("Kurs 8"));
        v.kursHinzufuegen(new Kurs("Kurs 9"));
        v.kursHinzufuegen(new Kurs("Kurs 10"));
        v.kursHinzufuegen(new Kurs("Kurs 11"));
        v.kursHinzufuegen(new Kurs("Kurs 12"));
        v.kursHinzufuegen(new Kurs("Kurs 13"));
        v.kursHinzufuegen(new Kurs("Kurs 14"));

        v.kursEntfernen(kurs3);
        v.kursHinzufuegen(new Kurs("Kurs 15"));

        v.kurseAusgeben();
    }
}