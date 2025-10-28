public class Potenz {
    private int basis;
    private int exponent;
    private int wert;
    
    public Potenz(int pBasis, int pExponent) {
        basis = pBasis;
        exponent = pExponent;
        wert = potenz();
    }
    
    private int potenz() {
        int erg = 1;
        for(int i = 0; i < exponent; i++) {
            erg = erg * basis;
        }
        return erg;
    }
    
    
    private int potenz2(int pBasis, int pExponent) {
        if(pExponent == 0) {
            return 1;
        }
        else {
            return pBasis * potenz2(pBasis, pExponent-1);
        }
    }
    
    private int potenz3(int pBasis, int pExponent) {
        if(pExponent == 0) {
            return 1;
        } else {
            if(pExponent % 2 == 0) {
                return potenz3(pBasis/2) * potenz3(pBasis/2)
            } else {
                return pBasis * potenz3(pBasis/2) * potenz3(pBasis/2)
            }
        }
    }
    
    
    public String toString() {
        return "" + basis + "^" + exponent + " = " + wert;
    }
    
    public void ausgeben() {
        System.out.println(this);
    }
}
 