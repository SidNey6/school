package c26_05_08;

import java.time.LocalDateTime;
import _shared.Server;

public class DaytimeServer extends Server {
    
    public DaytimeServer() {
        super(13); // operiert auf Port 13
    }

    public void processMessage(String pIp, int pPort, String pMessage) {
    }
    
    public void processClosingConnection(String pIp, int pPort) {
    }

    public void processNewConnection(String pIp, int pPort) {
        send(pIp, pPort, LocalDateTime.now().toString());
        closeConnection(pIp, pPort);
    }
}