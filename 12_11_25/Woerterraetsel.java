import java.util.Random;

public class Woerterraetsel {
    private Character[] raetsel;
    private String[] loesung;
    private String[] woerter;
    
    public boolean pruefeLoesung(String pLoesung) {
        return loesung.equals(pLoesung);
    }
    public void raetselAusgeben() {
        if(raetsel == null) {
            return;
        }
        for(Character aktChar : raetsel) {
            char p = aktChar != null ? aktChar : '_';
            System.out.print(p);
        }
        System.out.println();
    }
    
    public void woerterAusgeben() {
        String tmp = "";
        for(String wort : woerter) {
            tmp += (wort + " ");     
        }
        System.out.println(tmp.substring(0, tmp.length()-1));
    }
    
    private String[] ermittleWoerter(String pLoesung) {
            if(pLoesung == null) return null;
            
            List<String> alleWoerter = new List<String>();
            
            String[] zerteilteLoesung = pLoesung.split("[ ,.!?;]+");
            int anzahl = 0;
            for(String aktZeichenkette : zerteilteLoesung) {
                if(istBuchstabe(aktZeichenkette.charAt(0))) {
                    alleWoerter.append(aktZeichenkette);
                    anzahl++;
                }
            }
            String[] erg = new String[anzahl];
            int i = 0;
            for(alleWoerter.toFirst(); alleWoerter.hasAccess(); alleWoerter.next()) {
                erg[i] = alleWoerter.getContent();
                i++;
            }
            return erg;
    }
    
    private void woerterTauschen() {
            if(woerter != null) {
                Random r = new Random();
                int anzahl = woerter.length;
                for(int i = 0; i < anzahl*anzahl;i++) {
                    int x = r.nextInt(anzahl);
                    int y = r.nextInt(anzahl);
                    tausche(woerter, x, y);
                }    
            }        
    }
    
    private Character[] generiereRaetsel(String pLoesung) {
        if(pLoesung == null) return null;
        
        Character[] neuesRaetsel = new Character[pLoesung.length()];
        Random r = new Random();
        for(int i = 0; i < pLoesung.length(); i++) {
            char aktChar = pLoesung.charAt(i);
            if(!istBuchstabe(aktChar)) {
                neuesRaetsel[i] = aktChar;
            }
            else {
                int k = r.nextInt(1000)+10;
                int j = r.nextInt(10)+1;
                if(k % j == 0) {
                    neuesRaetsel[i] = aktChar;
                }
                else {
                    neuesRaetsel[i] = null;
                }
            }
        }
        return neuesRaetsel;
    }
    public Woerterraetsel(String pLoesung) {
        if(pLoesung == null) {
            raetsel = null;
            woerter = null;
            loesung = null;
        }
        else {
            loesung = ermittleWoerter(pLoesung);
            raetsel = generiereRaetsel(pLoesung);
            woerter = ermittleWoerter(pLoesung);
            woerterTauschen();
        }
    }

    private boolean istBuchstabe(char pZeichen) {
        if(('a' <= pZeichen && 'z' >= pZeichen) ||
        'A' <= pZeichen && 'Z' >= pZeichen) {
            return true;
        }
        return false;
    }
    
    public List<String[]> generiereAlleWortkombinationen() {
        List<String[]> pKombinationen = new List<String[]>();
        String[] copy = woerter.clone();
        
        permutiere(pKombinationen, copy, 0);
        return pKombinationen;
    }
    
    private String bruteforce() {
        List<String[]> alleKombinationen = generiereAlleWortkombinationen();
        String tmp = String.join(" ", loesung);
        for(alleKombinationen.toFirst(); alleKombinationen.hasAccess(); alleKombinationen.next()) {
            String tmp2 = String.join(" ", alleKombinationen.getContent());
            if(tmp.equals(tmp2)) {
                return tmp2;
            }
        }
        return null;
    }
    private void ausgabeArray(String[] pWoerter) {
        for(String wort : pWoerter) {
            System.out.print(wort + " ");
        }
        System.out.println();
    }
    
    private void permutiere(List<String[]> pKombinationen, String[] pWoerter, int index) {
        if (index == pWoerter.length - 1) {
            pKombinationen.append(pWoerter);
        }
        
        for (int i = index; i < pWoerter.length; i++) {
            tausche(pWoerter, index, i);
            permutiere(pKombinationen, pWoerter.clone(), index + 1);
            tausche(pWoerter, index, i);
        }
    }

    private void tausche(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public void start() {
        raetselAusgeben();
        woerterAusgeben();
        String erg = bruteforce();
        erg = erg!=null ? erg : "Keine Lösung gefunden";
        System.out.println(erg);
    }
}
 