import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.io.*;



public class FileProcess implements Runnable{
   private Socket sock;
   private PrintWriter writer;
   private BufferedInputStream reader;
   private String filename;


   public FileProcess(Socket s, String f){
      this.sock = s;
      this.filename = f;
   }

   public void run(){
      System.err.println("Lancement du traitement de la connexion cliente");
      boolean closeConnexion = false;

      while(!sock.isClosed()){

         try {

            writer = new PrintWriter(sock.getOutputStream());

            reader = new BufferedInputStream(sock.getInputStream());

            //String response = read();

            InetSocketAddress remote = (InetSocketAddress)sock.getRemoteSocketAddress();

            String debug = "";

            debug = "Thread : " + Thread.currentThread().getName() + ". ";

            debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";

            debug += " Sur le port : " + remote.getPort() + ".\n";

            //debug += "\t -> Commande reçue : " + response + "\n";

            System.err.println("\n" + debug);

            System.out.println("File "+ this.filename);

            SPN spn = new SPN();

            FileInputStream flux = null;

            int octet = 0;

            short key = 1525;

            try{
               flux = new FileInputStream(this.filename);

               try{

                  while(flux.available() > 0){ 

                     octet = flux.read();

                     int tmp = spn.encrypt_block(octet, key);

                     System.err.println("octet " + (char)octet + " crypted " + (char)tmp);

                     writer.write(tmp);

                     writer.flush();
                  }

                  closeConnexion = true;

               }catch(IOException e){

                  System.err.println("Erreur lecture");

               }

            }catch(FileNotFoundException e){
               System.err.println("Erreur ouverture flux");
            }

            if(closeConnexion){

               //System.err.println("COMMANDE CLOSE DETECTEE ! ");

               flux.close();

               writer = null;

               reader = null;

               sock.close();

               break;

            }

         }catch(SocketException e){

            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");

            break;

         } catch (IOException e) {

            e.printStackTrace();

         }         

      }

   }

   /*private String read() throws IOException{      
     String response = "";
     int stream;
     byte[] b = new byte[4096];
     stream = reader.read(b);
     response = new String(b, 0, stream);
     return response;
     }*/

}
