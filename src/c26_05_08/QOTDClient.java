package c26_05_08;

import _shared.Client;

public class QOTDClient extends Client {
    
    public QOTDClient(String pIp) {
        super(pIp, 1717); // operiert auf Port 1717 (nicht-privilegiert)
    }

    public void processMessage(String pMessage) {
        System.out.println(pMessage);
    }

    public static void main(String[] args) {
        QOTDClient client = new QOTDClient("localhost");
    }
}