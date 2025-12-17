import Common.Queue;

public class main {
    private static char[] characters = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', ' '};
    private static String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..", "_"};

    public static void main(String[] args) {
        System.out.println(decode("Hallo Welt"));
        System.out.println(encode(".... .- .-.. .-.. --- _ .-- . .-.. - "));
    }

    public static String decode(String pNachricht) {
        String[] nachricht = new String[pNachricht.length()];

        for(int i = 0; i < pNachricht.length(); i++) {
            for(int j = 0; j < characters.length; j++) {
                if(pNachricht.toLowerCase().charAt(i) == characters[j]) {
                    nachricht[i] = morse[j];
                }
            }
        }

    return uebersetzeInString(nachricht);
    }

    public static String encode(String pNachricht) {
        Queue<String> nachricht = new Queue<>();

        int charStartIndex = 0;

        for(int i = 0; i < pNachricht.length(); i++) {
            if(pNachricht.charAt(i) == ' ') {
                String morsePart = pNachricht.substring(charStartIndex, i);
                for(int j = 0; j < morse.length; j++) {
                    if(morsePart.equals(morse[j])) {
                        nachricht.enqueue("" + characters[j]);
                    }
                }
            charStartIndex = i + 1;
            }

        }

        return uebersetzeInString(nachricht);
    }

    public static String uebersetzeInString(String[] pNachricht) {
        String nachricht = "";
        for(int i = 0; i < pNachricht.length; i++) {
            nachricht = nachricht + pNachricht[i] + " ";
        }

        return nachricht;
    }

    public static String uebersetzeInString(Queue<String> pNachricht) {
        String nachricht = "";
        while (!pNachricht.isEmpty()) {
            nachricht = nachricht + pNachricht.front();
            pNachricht.dequeue();
        }

        return nachricht;
    }
}