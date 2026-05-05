package c26_05_05;
import _shared.List;

public class Benutzer
{
   private String name;
   private String passwort;
   private String aktIP;
   private int aktPort;
   private List<Miau> miaus;
   private List<Benutzer> freunde;
   public Benutzer(String pName, String pPass){
       name = pName;
       passwort = pPass;
       miaus = new List<Miau>();
       freunde = new List<Benutzer>();
   }
   public String gibName(){
       return name;
   }
   public String gibAktIP(){
       return aktIP;
   }
   public int gibAktPort(){
       return aktPort;
   }
   public List<Miau> gibMiaus(){
       return miaus;
   }
   public void fuegeHinzu(Miau pMiau){
       if(pMiau != null){
           miaus.append(pMiau);
       }
   }
   public List<Benutzer> gibFreunde(){
       return freunde;
   }
   public void fuegeFreundHinzu(Benutzer pBenutzer){
       if(pBenutzer != null && !beinhaltet(freunde, pBenutzer)){
           freunde.append(pBenutzer);
       }
   }
   public void einloggen(String pAktIP, int pAktPort, String pPass){
       if (passwort.equals(pPass)){
           aktIP = pAktIP;
           aktPort = pAktPort;
        }
   }
   public void fuegeMiauHinzu(Miau pMiau){
       if(pMiau != null){
           miaus.append(pMiau);
       }
   }
   public void ausloggen(){
       aktIP = null;
       aktPort = 0;
   }
   public boolean istEingeloggt(){
       return aktIP != null;
   }
   private boolean beinhaltet(List<Benutzer> pListe, Benutzer pBenutzer){
       pListe.toFirst();
       while(pListe.hasAccess()){
           if(pListe.getContent() == pBenutzer){
               return true;
           }
           pListe.next();
       }
       return false;
   }
}