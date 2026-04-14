import _shared.Server;
import _shared.List;

public class ChatCatzServer extends Server {

    private List<Benutzer> benutzer = new List<Benutzer>();

    public ChatCatzServer(pPort int) {}

    private void bearbeiteADDFRIEND(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteMEOW(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURR(Benutzer pBenutzer, String pMessage) {}
    private void bearbeitePURRSOF(Benutzer pBenutzer, String pMessage) {}
    private void bearbeiteUSERS(Benutzer pBenutzer) {}

    public void processNewConnection(String pClientIP, int pClientPort) {}
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {}
    public void processClosingConnection(String pClientIP, int pClientPort) {}
}