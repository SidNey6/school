public class main {
    private static char[] characters = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', ' '};
    private static String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..", " "};

    public static void main(String[] args) {
        System.out.println(decode("Hallo Welt"));
        System.out.println(encode(".... .- .-.. .-.. --- null .-- . .-.. -"));
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
        for(int i = 0; i < morse.length; i++) {
            pNachricht.replaceAll(morse[i], ""+characters[i]);
        }

        return pNachricht;

    }

    public static String uebersetzeInString(String[] pNachricht) {
        String nachricht = "";
        for(int i = 0; i < pNachricht.length; i++) {
            nachricht = nachricht + pNachricht[i] + " ";
        }

        return nachricht;
    }

}