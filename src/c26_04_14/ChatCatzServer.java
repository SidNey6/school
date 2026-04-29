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
        initTestBenutzer();
    }

    private void initTestBenutzer() {
        benutzer.append(new Benutzer("alice", "pass123"));
        benutzer.append(new Benutzer("bob", "pass456"));
        benutzer.append(new Benutzer("charlie", "pass789"));
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
        String users = "USERLIST ";
        for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
            users += benutzer.getContent().gibName() + ",";
        }
        if(users.endsWith(",")) {
            users = users.substring(0, users.length() - 1);
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), users);
    }

    private void bearbeiteLOGIN(String pClientIP, int pClientPort, String pMessage) {
        if(pMessage.indexOf("|") < 0) {
            send(pClientIP, pClientPort, "-ERR Ungültiges Format (Nutzer|Passwort)");
            return;
        }
        String[] creds = pMessage.substring(pMessage.indexOf(" ") + 1).split("\\|");
        String username = creds[0];
        String password = creds[1];
        
        Benutzer loggenderBenutzer = null;
        for(benutzer.toFirst(); benutzer.hasAccess(); benutzer.next()) {
            if(benutzer.getContent().gibName().equals(username)) {
                loggenderBenutzer = benutzer.getContent();
                break;
            }
        }
        
        if(loggenderBenutzer != null) {
            // Check if already logged in from another IP
            if(loggenderBenutzer.gibAktIP() != null && (!loggenderBenutzer.gibAktIP().equals(pClientIP) || loggenderBenutzer.gibAktPort() != pClientPort)) {
                send(pClientIP, pClientPort, "-ERR Benutzer ist bereits an einem anderen Ort eingeloggt");
                return;
            }

            loggenderBenutzer.einloggen(pClientIP, pClientPort, password);
            if(loggenderBenutzer.gibAktIP() != null) {
                send(pClientIP, pClientPort, "+OK Erfolgreich eingeloggt");
            } else {
                send(pClientIP, pClientPort, "-ERR Passwort falsch");
            }
        } else {
            send(pClientIP, pClientPort, "-ERR Benutzer nicht gefunden");
        }
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, "+OK ChatCatz");
    }

 public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String msg = "";
        if(pMessage == null || pMessage.equals("")) {
            msg = "-ERR Keine Nachricht empfangen";
            send(pClientIP, pClientPort, msg);
            return;
        }

        String command = pMessage.indexOf(" ") > 0 ? pMessage.substring(0, pMessage.indexOf(" ")) : pMessage;

        if(command.equals("LOGIN")) {
            bearbeiteLOGIN(pClientIP, pClientPort, pMessage);
            return;
        }

        if(!isConnectedTo(pClientIP, pClientPort)) {
            msg = "-ERR Nicht eingeloggt";
            send(pClientIP, pClientPort, msg);
            return;
        }

        Benutzer aktBenutzer = null;
        for(benutzer.toFirst(); benutzer.hasAccess() && aktBenutzer == null; benutzer.next()) {
            if(benutzer.getContent().gibAktIP() != null && benutzer.getContent().gibAktIP().equals(pClientIP) && benutzer.getContent().gibAktPort() == pClientPort) {
                aktBenutzer = benutzer.getContent();
                break;
            }
        }
        
        if(aktBenutzer != null && aktBenutzer.istEingeloggt()) {
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
        } else {
            send(pClientIP, pClientPort, "-ERR Nicht eingeloggt");
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

    public static void main(String[] args) {
        int port = 6789;
        System.out.println("Starte ChatCatz Server auf Port " + port + "...");
        ChatCatzServer server = new ChatCatzServer(port);
        System.out.println("Server läuft.");
        System.out.println("Testnutzer: 'alice|pass123', 'bob|pass456', 'charlie|pass789'");
        System.out.println("Tippe 'exit' oder 'quit' um den Server zu beenden.");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                server.close();
                break;
            }
        }
        scanner.close();
        System.out.println("Server beendet.");
        System.exit(0);
    }
}