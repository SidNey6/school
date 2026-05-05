package c26_05_05;
import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server{
    
    private List<Benutzer> benutzer;
    private int meowID = 0;
    
    public ChatCatzServer(int pPort){
        super(pPort);
        benutzer = new List<Benutzer>();
        benutzer.append(new Benutzer("userNova"  , "pass123"));
        benutzer.append(new Benutzer("luckyFox"  , "fox123"));
        benutzer.append(new Benutzer("blueSky7"  , "sky123"));
        benutzer.append(new Benutzer("pixelMax"  , "max123"));
        benutzer.append(new Benutzer("sunnyDay"  , "sun123"));
        benutzer.append(new Benutzer("techLeo"   , "leo123"));
        benutzer.append(new Benutzer("greenLeaf" , "leaf123"));
        benutzer.append(new Benutzer("nightOwl"  , "owl123"));
        benutzer.append(new Benutzer("fastRunner", "run123"));
        benutzer.append(new Benutzer("coolWave"  , "wave123"));
        System.out.println("Server gestartet auf localhost:"+pPort);
    }
        
    
    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage){
        Benutzer aktUser = findeUserName(pMessage);
        if(aktUser == null){
            String msg = "-ERR unbekannt";
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        }
        else{
            aktUser.fuegeFreundHinzu(pBenutzer);
            String msg = "+OK";
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        }
    }
    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage){
        List<Benutzer> freunde = pBenutzer.gibFreunde();
        String msg = "+OK";
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
        
        Miau miau = new Miau(pMessage, meowID);

        msg = "MEOWING " + meowID + "|" + pBenutzer.gibName() + "|" + pMessage;
        for(freunde.toFirst(); freunde.hasAccess(); freunde.next()){
            send(freunde.getContent().gibAktIP(), freunde.getContent().gibAktPort(), msg);
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), msg);
    }
    private void bearbeitePURR(Benutzer pBenutzer, String pMessage){
        for(benutzer.toFirst(); benutzer.hasAccess();benutzer.next()){
            Benutzer aktBenutzer = benutzer.getContent();
            List<Miau> miaus = aktBenutzer.gibMiaus(); 
            for(miaus.toFirst(); miaus.hasAccess(); miaus.next()){
                Miau aktMiau = miaus.getContent();
                if(pMessage.equals(aktMiau.gibMiauID()+"")){
                    aktMiau.fuegeSchnurrerHinzu(pBenutzer);
                    send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "+OK");
                    return;
                }
            }
        }
        send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR unbekannt");
    }
    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage){
        int erg = 0; 
        List<Benutzer> schnurrer = null;

        for(benutzer.toFirst(); benutzer.hasAccess();benutzer.next()){
            Benutzer aktBenutzer = benutzer.getContent();
            List<Miau> miaus = aktBenutzer.gibMiaus(); 
            
            for(miaus.toFirst(); miaus.hasAccess(); miaus.next()){
                Miau aktMiau = miaus.getContent();
                if(pMessage.equals(aktMiau.gibMiauID()+"")){
                    schnurrer = aktMiau.gibSchnurrer();
                }
            }
        }
        if(schnurrer == null || schnurrer.isEmpty()){
            send(pBenutzer.gibAktIP(), pBenutzer.gibAktPort(), "-ERR unbekannt");
        }else{
            for(schnurrer.toFirst();schnurrer.hasAccess(); schnurrer.next()){
                erg++;
            }
            send(pBenutzer.gibAktIP(),pBenutzer.gibAktPort(),"PURRS "+pMessage+"|"+erg);
        }          
    }
    
    
    private void bearbeiteUSERS(Benutzer pBenutzer){
        String erg = "USERLIST ";
        for(benutzer.toFirst();benutzer.hasAccess();benutzer.next()){
            if(benutzer.getContent().istEingeloggt()){
                erg = erg + "*";
            }
            erg = erg + benutzer.getContent().gibName() + "|";  
        }
        erg = erg.substring(0, erg.length() - 1);
        send(pBenutzer.gibAktIP(),pBenutzer.gibAktPort(), erg );
    }
    

    public void processNewConnection(String pClientIP, int pClientPort){
        send(pClientIP, pClientPort, "+OK ChatCatz");
    }
    public void processMessage(String pClientIP, int pClientPort, String pMessage){
        String command = pMessage.substring(0, pMessage.indexOf(" "));
        String message = pMessage.substring(pMessage.indexOf(" ")+1, pMessage.length());
        
        
        if(command.equals("LOGIN")){
            String[] temp = message.split("\\|");
            Benutzer nutzer = findeUserName(temp[0]);
            System.out.println("LOGIN Versuch von " + temp[0]);
            System.out.println(command + " " + message);
            if(nutzer == null){
                send(pClientIP, pClientPort, "-ERR unbekannt");
                return;
            }
            System.out.println("LOGIN Versuch von " + temp[0]);
            if(nutzer.istEingeloggt()){
                send(pClientIP, pClientPort, "-ERR bereits eingeloggt");
                return;
            }
            nutzer.einloggen(pClientIP, pClientPort, temp[1]);
            if(nutzer.istEingeloggt()){
                send(pClientIP, pClientPort, "+OK");
            }
            else{
                send(pClientIP, pClientPort, "-ERR Passwort falsch");
                return;
            } 
        }
        Benutzer nutzer = findeBenutzer(pClientIP, pClientPort);
        if(nutzer.istEingeloggt()){
            
            if(command.equals("ADDFRIEND")){
                bearbeiteADDFRIEND(nutzer, message);
            }else if(command.equals("MEOW")){
                bearbeiteMEOW(nutzer, message);
            }else if(command.equals("PURR")){
                bearbeitePURR(nutzer, message);
            }else if(command.equals("PURRSOF")){
                bearbeitePURRSOF(nutzer, message);
            }else if(command.equals("USERS")){
                bearbeiteUSERS(nutzer);
            }else{
                String msg = "-ERR Unbekannter Befehl";
                send(pClientIP, pClientPort, msg);
            }
        }
    }   
    public void processClosingConnection(String pClientIP, int pClientPort){
        Benutzer nutzer = findeBenutzer(pClientIP, pClientPort);
        if(nutzer != null) {
            nutzer.ausloggen();
            closeConnection(pClientIP, pClientPort);
        }
    }

    private Benutzer findeUserName(String pUserName){
        for(benutzer.toFirst();benutzer.hasAccess();benutzer.next()){
            if(benutzer.getContent().gibName().equals(pUserName)){
                return benutzer.getContent();
            }
        }
        return null;
    }
    private Benutzer findeBenutzer(String pClientIP, int pClientPort){
        if(pClientIP == null || pClientPort == 0) {
            return null;
        }
        if(benutzer.isEmpty()){
            return null;
        }
        for(benutzer.toFirst();benutzer.hasAccess();benutzer.next()){
            Benutzer aktBenutzer = benutzer.getContent();
            if(aktBenutzer == null || aktBenutzer.gibAktIP() == null || aktBenutzer.gibAktPort() < 0){
                return null;
            }
            if(aktBenutzer.gibAktIP().equals(pClientIP) && aktBenutzer.gibAktPort() == pClientPort ){
                return aktBenutzer;
            }
        }
        return null;
    }
}
