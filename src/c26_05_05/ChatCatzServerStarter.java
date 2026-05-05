package c26_05_05;
import _shared.Server;
import _shared.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p>
 * Klasse ChatCatzServerStarter
 * </p>
 * <p>
 * Lauffaehiges Programm fuer den ChatCatz-Server. Der Server wird auf einem
 * waehlbaren Port gestartet (Standard 50000) und ueber eine einfache
 * Kommandozeile bedient. Die folgenden Befehle stehen zur Verfuegung:
 * </p>
 *
 * <pre>
 *   help                     Zeigt diese Uebersicht.
 *   status                   Zeigt an, ob der Server geoeffnet ist.
 *   broadcast &lt;Nachricht&gt;    Sendet eine Nachricht an alle verbundenen Clients.
 *   send &lt;ip&gt; &lt;port&gt; &lt;msg&gt;  Sendet eine Nachricht gezielt an einen Client.
 *   close &lt;ip&gt; &lt;port&gt;        Trennt eine bestimmte Verbindung.
 *   stop                     Faehrt den Server herunter und beendet das Programm.
 *   quit / exit              wie stop.
 * </pre>
 *
 * Aufruf:
 * <pre>
 *   java ChatCatzServerStarter [Port]
 * </pre>
 */
public class ChatCatzServerStarter {
    private static final int STANDARD_PORT = 50000;

    public static void main(String[] args) {
        ChatCatzServer server = new ChatCatzServer(STANDARD_PORT);
        if (!server.isOpen()) {
            System.err.println("Server konnte auf Port " + STANDARD_PORT + " nicht gestartet werden.");
            System.exit(1);
            return;
        }

        System.out.println("ChatCatz-Server laeuft auf Port " + STANDARD_PORT + ".");
        System.out.println("Tippe 'help' fuer eine Befehlsuebersicht.");

        BufferedReader konsole = new BufferedReader(new InputStreamReader(System.in));
        boolean laeuft = true;
        while (laeuft) {
            System.out.print("> ");
            String zeile;
            try {
                zeile = konsole.readLine();
            } catch (Exception e) {
                break;
            }
            if (zeile == null) {
                break;
            }
            zeile = zeile.trim();
            if (zeile.isEmpty()) continue;

            String befehl;
            String rest;
            int leer = zeile.indexOf(" ");
            if (leer < 0) {
                befehl = zeile;
                rest = "";
            } else {
                befehl = zeile.substring(0, leer);
                rest = zeile.substring(leer + 1).trim();
            }

            switch (befehl.toLowerCase()) {
                case "help":
                case "?":
                    zeigeHilfe();
                    break;
                case "status":
                    System.out.println("Server ist " + (server.isOpen() ? "geoeffnet" : "geschlossen") + ".");
                    break;
                case "broadcast":
                    if (rest.isEmpty()) {
                        System.out.println("Verwendung: broadcast <Nachricht>");
                    } else {
                        server.sendToAll(rest);
                        System.out.println("Broadcast gesendet.");
                    }
                    break;
                case "send":
                    bearbeiteSend(server, rest);
                    break;
                case "close":
                    bearbeiteClose(server, rest);
                    break;
                case "stop":
                case "quit":
                case "exit":
                    laeuft = false;
                    break;
                default:
                    System.out.println("Unbekannter Befehl. 'help' fuer Uebersicht.");
            }
        }

        System.out.println("Server wird heruntergefahren ...");
        server.close();
        System.out.println("Auf Wiedersehen.");
    }

    private static void zeigeHilfe() {
        System.out.println("Verfuegbare Befehle:");
        System.out.println("  help                       Diese Uebersicht.");
        System.out.println("  status                     Zustand des Servers anzeigen.");
        System.out.println("  broadcast <Nachricht>      Nachricht an alle Clients.");
        System.out.println("  send <ip> <port> <msg>     Gezielt an einen Client senden.");
        System.out.println("  close <ip> <port>          Verbindung schliessen.");
        System.out.println("  stop | quit | exit         Server beenden.");
    }

    private static void bearbeiteSend(ChatCatzServer server, String rest) {
        // Erwartetes Format: <ip> <port> <nachricht>
        if (rest.isEmpty()) {
            System.out.println("Verwendung: send <ip> <port> <Nachricht>");
            return;
        }
        int leer1 = rest.indexOf(" ");
        if (leer1 < 0) {
            System.out.println("Verwendung: send <ip> <port> <Nachricht>");
            return;
        }
        String ip = rest.substring(0, leer1);
        String nachLeer1 = rest.substring(leer1 + 1).trim();

        int leer2 = nachLeer1.indexOf(" ");
        if (leer2 < 0) {
            System.out.println("Verwendung: send <ip> <port> <Nachricht>");
            return;
        }
        String portText = nachLeer1.substring(0, leer2);
        String nachricht = nachLeer1.substring(leer2 + 1);

        try {
            int port = Integer.parseInt(portText);
            server.send(ip, port, nachricht);
            System.out.println("Nachricht gesendet.");
        } catch (NumberFormatException e) {
            System.out.println("Ungueltiger Port: " + portText);
        }
    }

    private static void bearbeiteClose(ChatCatzServer server, String rest) {
        if (rest.isEmpty()) {
            System.out.println("Verwendung: close <ip> <port>");
            return;
        }
        int leer = rest.indexOf(" ");
        if (leer < 0) {
            System.out.println("Verwendung: close <ip> <port>");
            return;
        }
        String ip = rest.substring(0, leer);
        String portText = rest.substring(leer + 1).trim();
        try {
            int port = Integer.parseInt(portText);
            server.closeConnection(ip, port);
            System.out.println("Verbindung " + ip + ":" + port + " geschlossen.");
        } catch (NumberFormatException e) {
            System.out.println("Ungueltiger Port: " + portText);
        }
    }
}