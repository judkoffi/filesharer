import java.io.*;
import javax.swing.*;
import java.awt.*;

public class SPN {

   private File fichier;
   private String mode;
   private final int[] substitution = {2,5,7,0,11,1,4,12,3,6,8,14,9,13,15,10};
   private final int[] permutation = {4,3,2,1,0,7,6,5};
   private final int[] inverseSubstitution = {3,5,0,8,6,1,9,2,10,12,15,4,7,13,11,14};
   private int[] inversePermutation;

   public SPN(String filename, String type){
      this.fichier = new File(filename);
      this.mode = type;
      this.inversePermutation = new int[8];
   }

   public  int doSubst(int octet){
      int res = 0x0;
      int mask = 0xf;

      int droite = (octet&mask);
      int gauche = (octet>>4);

      int substDroite = this.substitution[droite];
      int substGauche = this.substitution[gauche];

      res = (substDroite | (substGauche << 4));
      return res;
   }

   public  int doInvSubst(int octet){
      int res = 0x0;
      int mask = 0xf;

      int droite = (octet&mask);
      int gauche = (octet>>4);

      int substDroite = this.inverseSubstitution[droite];
      int substGauche = this.inverseSubstitution[gauche];

      res = (substDroite | (substGauche << 4));
      return res;
   }



   public int doPerm(int octet){
      int m = 0x1;
      int res = 0x0;
      int i;
      int tmp;

      for(i=0; i<8; i++){
         tmp = (( octet >> i ) & m);
         res =  ((tmp << this.permutation[i]) | res);
      }
      return res;
   }

   public void buildInvPerm(){
      for(int i = 0; i < this.inversePermutation.length; i++){
         this.inversePermutation[this.permutation[i]] = i;
      }
   }

   public int encrypt_block(int block, short k){
      int res = 0;
      int[] key = new int[2];
      int mask = 0xff;
      int i;

      key[0] = (k >> 8);
      key[1] = (k & mask);

      for(i = 0; i < 2;i++){
         int tmp =  ((block)^key[i]);
         tmp =  doSubst(tmp);
         res =  doPerm(tmp);
      }
      return res;
   }

   public int decrypt_block(int block, short k){
      int res = 0;
      int[] key = new int[2];
      int mask = 0xff;
      int i;

      key[0] = (k >> 8);
      key[1] = (k & mask);

      for(i = 0; i < 2;i++){
         int tmp  =  doPerm(block);
         res =  doInvSubst(tmp);
         res =  (res^key[i]);
      }
      return res;
   }


   public void makeCryptage(){
      FileInputStream flux = null;
      FileOutputStream sorti = null;


      JFrame fenetre = new JFrame("Progress");
      JLabel etq = new JLabel("En cours");

      fenetre.setSize(200,100);
      fenetre.setLocationRelativeTo(null);
      
      etq.setOpaque(true);
      etq.setBackground(Color.BLACK);
      etq.setHorizontalAlignment(JLabel.CENTER);

      fenetre.add(etq, BorderLayout.CENTER);
      fenetre.setVisible(true);

      int octet = 0;
      short key = 1525;

      try{
         flux = new FileInputStream(this.fichier);

         sorti = new FileOutputStream(this.fichier.getName() + ".cryp");

         try{
            while(flux.available() > 0){ 
              octet = flux.read();
               int tmp = encrypt_block(octet, key);
               sorti.write(tmp);
            }
            
            try{
               Thread.sleep(1000);
            }catch(InterruptedException e){  }

            fenetre.dispose();
            sorti.close();
            flux.close();

         }catch(IOException e){
            System.err.println("Erreur lecture");
         }

      }catch(FileNotFoundException e){
         System.err.println("Erreur ouverture flux");
      }
   }

   public void makeDecryptage(){
      FileInputStream flux = null;
      FileOutputStream sorti = null;


      JFrame fenetre = new JFrame("Progress");
      JLabel etq = new JLabel("En cours");

      fenetre.setSize(200,100);
      fenetre.setLocationRelativeTo(null);
     
      etq.setOpaque(true);
      etq.setHorizontalAlignment(JLabel.CENTER);
      etq.setBackground(Color.BLACK);

      fenetre.add(etq, BorderLayout.CENTER);
         fenetre.setVisible(true);

      int octet = 0;
      short key = 1525;
      try{
         flux = new FileInputStream(this.fichier);

         sorti = new FileOutputStream(this.fichier.getName().substring(0,this.fichier.getName().length()-5));

         try{
            while(flux.available() > 0){ 
               octet = flux.read();
               int tmp = decrypt_block(octet, key);
               sorti.write(tmp);
            }
            try{
               Thread.sleep(1000);
            }catch(InterruptedException e){  }

            fenetre.dispose();
            sorti.close();
            flux.close();

         }catch(IOException e){
            System.err.println("Erreur lecture");
         }

      }catch(FileNotFoundException e){
         System.err.println("Erreur ouverture flux");
      }

   }

}
