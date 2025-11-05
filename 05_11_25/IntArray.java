import java.lang.Math;

public class IntArray {
    private int[] array;


    public IntArray(int pLength) {
        array = new array[pLength];
        for(int i = 0; i < pLength; i++) {
            array[i] = (int) Math.random()*1000;
       }
    }

    public void mergesort() {
        mergesort(array);
    }
    public double messeMergesortLaufzeit() {
        // Stoppuhr starten
        // Mergesort auf array ausführen
        // Stoppuhr beenden
        // Die gemessene Zeit zurückgeben
        return 0.0;
    }
    private void mergesort(int[] pArray) {
        if(pArray.length < 2) {
            return;
        }
        
        int mid = pArray.length / 2;
        int startIndex = 0;
        int endIndex = pArray.length - 1;
        
        int[] linkerTeil = new int[mid];
        int[] rechterTeil = new int[mid + (pArray.length % 2)]; // Simon
        // int[] rechterTeil = new int[pArray.length - mid]; // Vera
        
        
        //Kopieren in zwei Teilarrays
        for(int i = 0; i < linkerTeil.length; i++) {
            linkerTeil[i] = pArray[i];
        }
        
        for(int i = 0; i < rechterTeil.length; i++) {
            rechterTeil[i] = pArray[i + mid];
        }
        
        //Rekursive zerlegen
        mergesort(linkerTeil);
        mergesort(rechterTeil);
        
        //Zusammenfügen
        merge(pArray, linkerTeil, rechterTeil);
    }
    
    private void merge(int[] pArray, int[] pLinkerTeil, int[] pRechterTeil) {
        int links = 0;
        int rechts = 0;
        
        //In beiden Teilarrays sind noch Zahlen, die einsortiert werden müssen
        while(links < pLinkerTeil.length && rechts < pRechterTeil.length) {
            if(pLinkerTeil[links] < pRechterTeil[rechts]) {
                pArray[links + rechts] = pLinkerTeil[links];
                links++;
                } 
                else {
                    pArray[links + rechts] = pRechterTeil[rechts];
                    rechts++;
                }            
        }
        //Nur noch im rechten Teilarray sind Zahlen, diese können einfach kopiert werden
        if(links >= pLinkerTeil.length) {
            for(int i = links+rechts; i < pArray.length; i++) {
                pArray[i] = pRechterTeil[rechts];
                rechts++;
            }
        }
        //Nur noch im linken Teilarray sind Zahlen, diese können einfach kopiert werden
        if(rechts >= pRechterTeil.length) {
            for(int i = links+rechts; i < pArray.length; i++) {
                pArray[i] = pLinkerTeil[links];
                links++;
            }
        }
    }
}
 