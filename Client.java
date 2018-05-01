import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.io.*;


public class Client implements Runnable{
   private String saveAsFilename;

   private Socket connexion;
   private PrintWriter writer;
   private InputStream reader;
   private FileOutputStream fluxSorti;


   public Client(String host, int port, String file){
      try{
         this.connexion = new Socket(host, port);
         this.saveAsFilename = file;
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   @Override
   public void run(){
      int octet = 0;
      short key = 1525;

      SPN spn = new SPN();

      try {

         this.writer = new PrintWriter(this.connexion.getOutputStream(), true);
         this.reader = this.connexion.getInputStream();

         this.fluxSorti = new FileOutputStream(this.saveAsFilename);
         
         try{
            String tmp = "";


            while( !(tmp = this.read()).equals("")){
               System.out.println("Read "+ tmp);
               octet = spn.decrypt_block((int)(tmp.charAt(0)), key);
               this.fluxSorti.write(octet);
               this.fluxSorti.flush();
            }

            try{
               this.reader.close();
               this.fluxSorti.close();
            }catch(IOException e){
               System.err.println("Erreur fermeture fichier"); 
            }

         }catch(IOException e){
            System.err.println("Erreur ecriture");
         }




      } catch (IOException e1) {
         e1.printStackTrace();
      }
   }

   public String read() throws IOException{      

      String response = "";

      int stream;

      byte[] b = new byte[2];

      stream = reader.read(b);

      if(stream != -1 || stream != 0){
         response = new String(b, 0, stream); 
      }

      return response;

   }  

}
