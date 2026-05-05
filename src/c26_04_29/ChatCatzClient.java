package c26_04_29;

import _shared.Client;
import java.util.Scanner;

public class ChatCatzClient extends Client {

    private String serverIp;
    private int serverPort;

    public ChatCatzClient(String pServerIp, int pServerPort) {
        super(pServerIp, pServerPort);
    }

    public void sende(String command, String pMessage) {
        send(command + " " + pMessage);
    }

    public void processMessage(String pMessage) {
        int spaceIndex = pMessage.indexOf(" ");
        String command = spaceIndex >= 0 ? pMessage.substring(0, spaceIndex) : pMessage;
        String payload = spaceIndex >= 0 ? pMessage.substring(spaceIndex + 1) : "";
        String[] messageArray = pMessage.split("\\|");

        switch (command) {
            case "+OK":
                bearbeiteOK(payload);
                break;
            case "-ERR":
                bearbeiteERR(payload);
                break;
            case "MEOWING":
                if(messageArray.length >= 4) {
                    bearbeiteMEOWING(Integer.parseInt(messageArray[1]), messageArray[2], messageArray[3]);
                }
                break;
            case "PURRS":
                if(messageArray.length >= 3) {
                    bearbeitePURRS(Integer.parseInt(messageArray[1]), Integer.parseInt(messageArray[2]));
                }
                break;
            case "USERLIST":
                bearbeiteUSERLIST(payload);
                break;
        }
    }
    
    private void bearbeiteOK(String pMessage) {
        System.out.println(pMessage);
    }

    private void bearbeiteERR(String pMessage) {
        System.out.println(pMessage);
    }

    private void bearbeiteMEOWING(int pId, String pBenutzer, String pText) {
        System.out.println(pId + " | " + pBenutzer + " | " + pText);
    }

    private void bearbeitePURRS(int pId, int pP) {
        System.out.println(pId + " | " + pP);
    }

    private void bearbeiteUSERLIST(String pMessage) {
        System.out.println(pMessage);
    }

    private void sendeLOGIN(String pUser, String pPass) {
        sende("LOGIN", pUser + "|" + pPass);
    }

    private void sendADDFRIEND(String pUser) {
        sende("ADDFRIEND", pUser);
    }

    private void sendMEOW(String pMessage) {
        sende("MEOW", pMessage);
    }

    private void sendPURR(int pId) {
        sende("PURR", pId + "");
    }

    private void sendPURRSOF(int pId) {
        sende("PURRSOF", pId + "");
    }

    private void sendUSERS() {
        sende("USERS", "");
    }    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("IP-Adresse des Servers (z.B. localhost): ");
        String ip = scanner.nextLine();
        System.out.print("Port des Servers (z.B. 6789): ");
        int port = 6789;
        try {
            port = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ungültiger Port, verwende Standardport 6789.");
        }

        ChatCatzClient client = new ChatCatzClient(ip, port);
        System.out.println("Client gestartet. Verfügbare Befehle: LOGIN <user> <pass>, ADDFRIEND <user>, MEOW <msg>, PURR <id>, PURRSOF <id>, USERS, EXIT");

        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            if (input == null || input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String befehl = parts[0].toUpperCase();

            switch (befehl) {
                case "LOGIN":
                    if (parts.length > 1) {
                        String[] creds = parts[1].split("\\|");
                        if (creds.length == 2) {
                            client.sendeLOGIN(creds[0], creds[1]);
                        } else {
                            System.out.println("Verwendung: LOGIN <user> <pass>");
                        }
                    } else {
                        System.out.println("Verwendung: LOGIN <user> <pass>");
                    }
                    break;
                case "ADDFRIEND":
                    if (parts.length > 1) client.sendADDFRIEND(parts[1]);
                    else System.out.println("Verwendung: ADDFRIEND <user>");
                    break;
                case "MEOW":
                    if (parts.length > 1) client.sendMEOW(parts[1]);
                    else System.out.println("Verwendung: MEOW <msg>");
                    break;
                case "PURR":
                    if (parts.length > 1) {
                        try {
                            client.sendPURR(Integer.parseInt(parts[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("ID muss eine Zahl sein.");
                        }
                    } else System.out.println("Verwendung: PURR <id>");
                    break;
                case "PURRSOF":
                    if (parts.length > 1) {
                        try {
                            client.sendPURRSOF(Integer.parseInt(parts[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("ID muss eine Zahl sein.");
                        }
                    } else System.out.println("Verwendung: PURRSOF <id>");
                    break;
                case "USERS":
                    client.sendUSERS();
                    break;
                case "EXIT":
                    client.close();
                    running = false;
                    break;
                default:
                    System.out.println("Unbekannter Befehl.");
            }
        }
        scanner.close();
        System.out.println("Client beendet.");
        System.exit(0);
    }
}