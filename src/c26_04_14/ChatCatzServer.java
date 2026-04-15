import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server {

    private List<Benutzer> benutzer;

    public ChatCatzServer(int pPort) {
        benutzer = new List<Benutzer>();
        super(pPort);

        run(); // soll das so?
    }

    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURR(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteUSERS(Benutzer pBenutzer) {}

    public void processNewConnection(String pClientIP, int pClientPort) {
        for(benutzer.toFirst(), benutzer.hasAccess(), benutzer.next()) {
            if(benutzer.getContent().gibAktIP.equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                send("+ERR bereits eingeloggt")
                return;
            }
        }
        send("+OK")
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String[] tNachricht = pMessage.split(" ");
        Benutzer tBenutzer;

        for(benutzer.toFirst(), benutzer.hasAccess(), benutzer.next()) {
            if(benutzer.getContent().gibAktIP.equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                tBenutzer = benutzer.getContent();
                break;
            }
        }

        switch (tNachricht[0]) {
            case "LOGIN":
                for(benutzer.toFirst(), benutzer.hasAccess(), benutzer.next()) {
                    if(benutzer.getContent().gibName().equals(tNachricht[1])) {
                    tBenutzer = benutzer.getContent();
                    break;
                    }
                }
                
                tBenutzer.einloggen(tBenutzer.gibAktIP(), tBenutzer.gibAktPort(), tNachricht[2]);
                
                if(tBenutzer.istEingeloggt()) {
                    send("+OK");
                } else {
                    send("+ERR Passwort falsch");
                }

            case "ADDFRIEND":
                for(benutzer.toFirst(), benutzer.hasAccess(), benutzer.next()) {
                    if(benutzer.getContent().gibName().equals(tNachricht[1])) {
                    tBenutzer = benutzer.getContent();
                    break;
                    }
                }

                bearbeiteADDFRIEND(tBenutzer, )


            case "MEOW":

            case  "PURR":

            case "PURRSOF":

            case "USERS":
        }

        

    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
    
    }
}