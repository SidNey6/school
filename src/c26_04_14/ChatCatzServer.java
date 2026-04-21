package c26_04_14;
import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server {

    private List<Benutzer> benutzer;
    private List<String[]> meows;

    public ChatCatzServer(int pPort) {
        super(pPort);
        List<Benutzer> benutzer = new List<Benutzer>();
        List<String[]> moews = new List<String[]>();
    }

    /*

    WICHTIG:
    ÜBERPRÜFE IPS/PORTS GENUTZT BEI SEND();

     */

    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage) {
        String freund = pMessage.substring(pMessage.indexOf(" "), pMessage.length());
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
            freundBenutzer.fuegeFreundHinzu(pBenutzer);
            String msg = "+OK";
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        }
    }

    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage) {
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "+OK");
        String[] tString = new String[3];
        tString[0] = (Integer.parseInt(meows.toLast().getContent()[0]) + 1) + "";
        tString[1] = pMessage;
        meows.append(tString);

        List<Benutzer> freunde = pBenutzer.gibFreunde();
        for(freunde.toFirst(); freunde.hasAccess(); freunde.next()) {
            send(freunde.getContent().gibAktIP(), freunde.getContent().gibAktPort(), tString[0] + "|" + tString[1] + "|" + pBenutzer.gibName());
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), tString[0] + "|" + tString[1] + "|" + pBenutzer.gibName());
    }

    private void bearbeitePURR(Benutzer pBenutzer, String pMessage) {
        for(meows.toFirst(); meows.hasAccess(); meows.next()) {
            if(meows.getContent()[0] == Integer.parseInt(pMessage)) {
                meows.getContent()[3] =  (Integer.parseInt(meows.getContent()[3]) + 1) + "";
                return;
            }
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR unbekannt");
    }

    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage) {

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
            send(pClietIP, pClientPort, msg);
        }
        else if(pMessage == null || pMessage.equals("")) {
            msg = "-ERR Keine Nachricht empfangen";
            send(pClietIP, pClientPort, msg);
        }
        else {
            Benutzer aktBenutzer = null;
            for(benutzer.toFirst(); benutzer.hasAccess() && aktBenutzer == null; benutzer.next()) {
                if(benutzer.getContent().gibAktIP().equals(pClientIP) && benutzer.getContent().gibAktPort() == Integer.parseInt(pClientPort)) {
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
                        send(pClietIP, pClientPort, msg);
                }
            }
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
    
    }
}