package c26_05_08;

import _shared.List;
import _shared.Server;

public class QOTDServer extends Server {
    int anzahlZitate;
    List<String> zitate;

    public QOTDServer() {
        super(1717);
        zitate = new List<String>();
        zitate.append("Vergiss nicht, man braucht nur wenig um ein glückliches Leben zu führen. - Mark Aurel");
        zitate.append("Die besten Dinge im Leben sind nicht die, die man für Geld bekommt. - Albert Einstein");
        zitate.append("Keinen Tag soll man verpassen. - Johann Wolfgang von Goethe");
        zitate.append("Heutzutage kennen die Leute von allem den Preis und nicht den Wert. - Oscar Wilde");
        zitate.append("Wahre Freundschaft ist eine sehr langsam wachsende Pflanze. - George Washington");
        zitate.append("Liebe verschenkt, Egoismus leiht. - Friedrich von Schiller");
        zitate.append("Wenn ich liebe, werde ich um das reicher was ich liebe. - Friedrich von Schiller");
        zitate.append("Erfahrungen vererben sich nicht, jeder muss sie alleine machen. - Kurt Tucholsky");
        zitate.append("Niemand ist unentbehrlich. - Franklin D. Roosevelt");
        zitate.append("Ignorieren ist noch keine Toleranz. - Theodor Fontane");
        zitate.append("Auch aus Steinen, die dir in den Weg gelegt werden, kannst du etwas Schönes bauen. - Erich Kästner");
        zitate.append("Irrtümer haben ihren Wert; jedoch nur hier und da. Nicht jeder, der nach Indien fährt, entdeckt Amerika. - Erich Kästner");
        zitate.append("Träume dir dein Leben schön und mach aus diesen Träumen eine Realität. - Marie Curie");
        zitate.append("Gib niemals auf, für das zu kämpfen, was du tun willst. Mit etwas, wo Leidenschaft und Inspiration ist, kann man nicht falsch liegen. - Ella Fitzgerald");
        zitate.append("Warte nicht darauf, dass die Menschen Dich anlächeln... Zeige ihnen wie es geht! - Astrid Lindgren");
        zitate.append("Verweile nicht in der Vergangenheit, träume nicht von der Zukunft. Konzentriere dich auf den gegenwärtigen Moment. - Buddha");
        zitate.append("Die Welt, obgleich sie wunderlich, ist gut genug für dich und mich. - Wilhelm Busch");
        zitate.append("Ein gutes Wort, ein frohes Lachen kann dich und andere glücklich machen - Heinrich Heine");
        zitate.append("Dummheiten können reizend sein, Dummheit nicht. - Alberto Moravia");
        zitate.append("Ich spreche gerne von nichts, das ist das Einzige, wovon ich wirklich etwas verstehe. - Oscar Wilde");
    
        anzahlZitate = 0;
        for(zitate.toFirst(); zitate.hasAccess(); zitate.next()) {
            anzahlZitate++;
        }
    }

    public void processMessage(String pIp, int pPort, String pMessage) {
    }
    
    public void processClosingConnection(String pIp, int pPort) {
    }

    public void processNewConnection(String pIp, int pPort) {
        send(pIp, pPort, waehleZitat());
        closeConnection(pIp, pPort);
    }

    private String waehleZitat() {
        int randint = (int) (Math.random() * anzahlZitate);
        zitate.toFirst();
        
        for (int i = 0; i < randint; i++) {
            zitate.next();
        }
        
        return zitate.getContent();
    }

    public static void main(String[] args) {
        QOTDServer server = new QOTDServer();
        if (!server.isOpen()) {
            System.err.println("Server konnte auf Port 1717 nicht gestartet werden.");
            System.exit(1);
            return;
        }

        System.out.println("QOTD-Server laeuft auf Port 1717.");
    }
}