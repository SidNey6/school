/*
 * Stoppuhr, um die Laufzeit eines Algorithmus zu messen.
 * Anwendungsbeispiel:
 * void main()
 * {
 *     Stoppuhr uhr = new Stoppuhr();
 *     uhr.start();
 *     algorithmus();
 *     uhr.stopp();
 *     Console.println("Die Zeit beträgt " + uhr.gestoppteZeit() + " Sekunden.");
 * }
 * Christian Pothmann, 2.6.2013
 */
public class Stoppuhr
{ 
    // Attribute
    private long startZeit, stoppZeit;

    // Konstruktor
    public Stoppuhr()
    {
        startZeit = 0;
        stoppZeit   = 0;
    }

    /*
     * Die Stoppuhr läuft los
     */
    public void start()
    {
        startZeit = System.currentTimeMillis();
    }

    /*
     * Die Stoppuhr stoppt
     */
    public void stopp()
    {
        stoppZeit = System.currentTimeMillis();
    }

    /*
     * Gibt die gestoppte Zeit in Sekunden zurück
     */
    public double gestoppteZeit()
    {
        if (stoppZeit < startZeit) return 0.0;
        return Math.round((stoppZeit - startZeit) / 10.0) / 100.0;
    }
}
