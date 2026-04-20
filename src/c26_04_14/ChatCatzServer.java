package c26_04_14;
import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server {

    private List<Benutzer> benutzer;

    public ChatCatzServer(int pPort) {
        super(pPort);
        List<Benutzer> benutzer = new List<Benutzer>();
    }

    /*

    WICHTIG:
    ÜBERPRÜFE IPS/PORTS GENUTZT BEI SEND();

     */

    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURR(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteUSERS(Benutzer pBenutzer) {}

    public void processNewConnection(String pClientIP, int pClientPort) {
        for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
            if(benutzer.getContent().gibAktIP().equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                send(pClientIP, pClientPort, "+ERR bereits eingeloggt");
                return;
            }
        }
        send(pClientIP, pClientPort, "+OK");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String[] tNachricht = pMessage.split(" ");
        Benutzer tBenutzer = null;

        for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
            if(benutzer.getContent().gibAktIP().equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                tBenutzer = benutzer.getContent();
                break;
            }
        }

        switch (tNachricht[0]) {
            case "LOGIN":
                for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
                    if(benutzer.getContent().gibName().equals(tNachricht[1])) {
                        tBenutzer = benutzer.getContent();
                        break;
                    }
                }

                if(tBenutzer == null) {
                    // User unknown, create new User
                }

                if(tBenutzer.istEingeloggt()) {
                    send(tBenutzer.gibAktIP(), tBenutzer.gibAktPort(), "+ERR bereits eingeloggt");
                    return;
                }

                tBenutzer.einloggen(tBenutzer.gibAktIP(), tBenutzer.gibAktPort(), tNachricht[2]);
                
                if(tBenutzer.istEingeloggt()) {
                    send(tBenutzer.gibAktIP(), tBenutzer.gibAktPort(), "+OK");
                } else {
                    send(tBenutzer.gibAktIP(), tBenutzer.gibAktPort(), "+ERR Passwort falsch");
                }

            case "ADDFRIEND":
                for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
                    if(benutzer.getContent().gibName().equals(tNachricht[1])) {
                    tBenutzer = benutzer.getContent();
                    break;
                    }
                }

                bearbeiteADDFRIEND(tBenutzer, null); // muss noch gemacht werden


            case "MEOW":

            case  "PURR":

            case "PURRSOF":

            case "USERS":
        }

        

    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
    
    }
}