package c26_04_14;
import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server {

    private List<Benutzer> benutzer;
    private List<Miau> meows;

    public ChatCatzServer(int pPort) {
        super(pPort);
        benutzer = new List<Benutzer>();
        meows = new List<Miau>();
    }

    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage) {
        if(pMessage.indexOf(" ") < 0) {
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR Unbekannt");
            return;
        }
        String freund = pMessage.substring(pMessage.indexOf(" ") + 1).trim();
        Benutzer freundBenutzer = null;
        for(benutzer.toFirst(); benutzer.hasAccess() && freundBenutzer == null; benutzer.next()){
            if(benutzer.getContent().gibName().equals(freund)){
                freundBenutzer = benutzer.getContent();
            }
        }
        if(freundBenutzer == null){
            String msg = "-ERR Unbekannt";
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        }
        else {
            freundBenutzer.fuegeFreundeHinzu(pBenutzer);
            String msg = "+OK";
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        }
    }

    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage) {
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "+OK");
        int neueMiauID = 1;
        if(!meows.isEmpty()) {
            meows.toLast();
            neueMiauID = meows.getContent().gibMiauID() + 1;
        }
        String text = pMessage.substring(pMessage.indexOf(" ") + 1);
        Miau neueMiau = new Miau(text, neueMiauID);
        meows.append(neueMiau);

        List<Benutzer> freunde = pBenutzer.gibFreunde();
        for(freunde.toFirst(); freunde.hasAccess(); freunde.next()) {
            send(freunde.getContent().gibAktIP(), freunde.getContent().gibAktPort(), neueMiau.gibMiauID() + "|" + neueMiau.gibText() + "|" + pBenutzer.gibName());
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), neueMiau.gibMiauID() + "|" + neueMiau.gibText() + "|" + pBenutzer.gibName());
    }

    private void bearbeitePURR(Benutzer pBenutzer, String pMessage) {
        int gesuchteMiauID = Integer.parseInt(pMessage.substring(pMessage.indexOf(" ") + 1));
        for(meows.toFirst(); meows.hasAccess(); meows.next()) {
            if(meows.getContent().gibMiauID() == gesuchteMiauID) {
                meows.getContent().fuegeSchnurrerHinzu(pBenutzer);
                return;
            }
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR unbekannt");
    }

    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage) {
        int gesuchteMiauID = Integer.parseInt(pMessage.substring(pMessage.indexOf(" ") + 1));
        for(meows.toFirst(); meows.hasAccess(); meows.next()) {
            if(meows.getContent().gibMiauID() == gesuchteMiauID) {
                send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "+OK " + meows.getContent().liefereAnzahlSchurrer());
                return;
            }
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR unbekannt");
    }

    private void bearbeiteUSERS(Benutzer pBenutzer) {

    }

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
        String msg = "";
        if(!isConnectedTo(pClientIP, pClientPort)) {
            msg = "-ERR Nicht eingeloggt";
            send(pClientIP, pClientPort, msg);
        }
        else if(pMessage == null || pMessage.equals("")) {
            msg = "-ERR Keine Nachricht empfangen";
            send(pClientIP, pClientPort, msg);
        }
        else {
            Benutzer aktBenutzer = null;
            for(benutzer.toFirst(); benutzer.hasAccess() && aktBenutzer == null; benutzer.next()) {
                if(benutzer.getContent().gibAktIP().equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                    aktBenutzer = benutzer.getContent();
                    break;
                }
            }
            String command = pMessage.substring(0, pMessage.indexOf(" "));
            if(aktBenutzer.istEingeloggt()) {
                switch (command) {
                    case "ADDFRIEND":
                        bearbeiteADDFRIEND(aktBenutzer, pMessage);
                        break;
                    case "MEOW":
                        bearbeiteMEOW(aktBenutzer, pMessage);
                        break;
                    case "PURR":
                        bearbeitePURR(aktBenutzer, pMessage);
                        break;
                    case "PURRSOF":
                        bearbeitePURRSOF(aktBenutzer, pMessage);
                        break;
                    case "USERS":
                        bearbeiteUSERS(aktBenutzer);
                        break;
                    default:
                        msg = "-ERR Unbekannter Befehl";
                        send(pClientIP, pClientPort, msg);
                }
            }
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
        for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
            if(benutzer.getContent().gibAktIP() != null
                    && benutzer.getContent().gibAktIP().equals(pClientIP)
                    && benutzer.getContent().gibAktPort() == pClientPort) {
                benutzer.getContent().ausloggen();
                return;
            }
        }
    }
}