package c26_01_07;

import _shared.BinaryTree;

public class Main {

    private String[][] morseData;
    private int longestCode = 0;
    private BinaryTree<MorseNode> morseTree;

    // Konstruktor
    public Main() {
        morseData =  new String[][]{ // morseData befüllen
                {"a", ".-"}, {"b", "-..."}, {"c", "-.-."}, {"d", "-.."}, {"e", "."}, {"f", "..-."},
                {"g", "--."}, {"h", "...."}, {"i", ".."}, {"j", ".---"}, {"k", "-.-"}, {"l", ".-.."},
                {"m", "--"}, {"n", "-."}, {"o", "---"}, {"p", ".--."}, {"q", "--.-"}, {"r", ".-."},
                {"s", "..."}, {"t", "-"}, {"u", "..-"}, {"v", "...-"}, {"w", ".--"}, {"x", "-..-"},
                {"y", "-.--"}, {"z", "--.."}, {" ", "----"}
        };

        morseTree = new BinaryTree<>(); // morseTree initialisieren
        for(String[] data : morseData) { // longestCode aktuell bringen (basierend auf morseData)
            if(data[1].length() > longestCode) {
                longestCode = data[1].length();
            }
        }
    }


    private BinaryTree<MorseNode> constructTree(String pos) {

        // Rekursionsanker
        if(pos.length() > longestCode) {
            return new BinaryTree<>();
        }

        // Rekursionsschritt
        BinaryTree<MorseNode> newTree = new BinaryTree<>(new MorseNode()); // morseNode = Wrapper für Character, Content darf nicht Null sein, weil sonst die Kinder nicht konstruiert werden können
        for (String[] ch : morseData) {
            if (pos.equals(ch[1])) { // falls die Node einen Character inne haben soll
                newTree.setContent(new MorseNode(ch[0].charAt(0)));
                break;
            }
        }

        newTree.setLeftTree(constructTree(pos + ".")); // linke Seite (".")
        newTree.setRightTree(constructTree(pos + "-")); // rechte Seite ("-")
        return newTree;
    }

    private String encodeMessage(String message) {
        String encodedMessage = "";
        String[] codes = message.split(" ");
        for(String components : codes) {
            Character encodedChar;
            BinaryTree<MorseNode> currentTree = morseTree;
            for(int i = 0; i < components.length(); i++) {
                if(components.charAt(i) == '.') {
                    currentTree = currentTree.getLeftTree();  // ← currentTree, nicht morseTree!
                } else if(components.charAt(i) == '-') {
                    currentTree = currentTree.getRightTree(); // ← currentTree, nicht morseTree!
                }
            }
            // Erst NACH der Schleife den Wert hinzufügen
            if(currentTree.getContent() != null && currentTree.getContent().getValue() != null) { // wenn nicht null oder value in morseNode nicht null (sollte nicht vorkommen)
                encodedMessage = encodedMessage + currentTree.getContent().getValue();
            }
        }
        return encodedMessage;
    }

    public static void main(String[] args) {
        Main application = new Main();
        application.morseTree = application.constructTree("");
        application.printBinaryTree(application.morseTree);

        // Test encodeMessage (Morse → Text)
        System.out.println("\nDecode Test:");
        System.out.println(".... .- .-.. .-.. --- = " + application.encodeMessage(".... .- .-.. .-.. ---"));
    }

    /*
    * printBinaryTree Funktion
    * Gibt den Binärbaum aus
    * KI generiert - keine Ahnung wie die funktioniert
    * */


    private void printBinaryTree(BinaryTree<MorseNode> pTree) {
        if (pTree == null || pTree.isEmpty()) {
            return;
        }

        // Alle Ebenen sammeln
        java.util.List<java.util.List<String>> levels = new java.util.ArrayList<>();
        java.util.Queue<BinaryTree<MorseNode>> queue = new java.util.LinkedList<>();
        queue.add(pTree);

        int maxDepth = longestCode + 1;

        for (int depth = 0; depth < maxDepth; depth++) {
            java.util.List<String> level = new java.util.ArrayList<>();
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                BinaryTree<MorseNode> node = queue.poll();

                if (node == null || node.isEmpty()) {
                    level.add(" ");
                    queue.add(null);
                    queue.add(null);
                } else {
                    String val = node.getContent().getValue() != null
                            ? node.getContent().getValue().toString()
                            : "○";
                    level.add(val);
                    queue.add(node.getLeftTree());
                    queue.add(node.getRightTree());
                }
            }
            levels.add(level);
        }

        // Baum ausgeben
        int width = (int) Math.pow(2, maxDepth);
        for (int i = 0; i < levels.size(); i++) {
            java.util.List<String> level = levels.get(i);
            int spacing = width / (int) Math.pow(2, i + 1);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < level.size(); j++) {
                // Leerzeichen vor dem Element
                for (int s = 0; s < spacing; s++) sb.append(" ");
                sb.append(level.get(j));
                // Leerzeichen nach dem Element
                for (int s = 0; s < spacing - 1; s++) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }


}