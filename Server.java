import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable {
   private int port;
   private ServerSocket sockServeur;
   public final int LITMIT_CONNEXION = 100;
   private boolean running;
   private String filename;



   public Server(int p, String f){
      try{
         this.sockServeur  = new ServerSocket(p, LITMIT_CONNEXION);
         this.running = true;
         this.filename = f;
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void open(){
      Thread t  = new Thread(this);
      t.start();
   }


   @Override
   public void run(){
     // while(this.running){
         try{
            System.out.println("Dans le run");
            Socket client = sockServeur.accept();

            Thread t = new Thread(new FileProcess(client,this.filename));
            t.start();

         }catch(IOException e){
            e.printStackTrace();
         }
     // }

      System.out.println("Plus dans le run");
      try {
         sockServeur.close();
      } catch (IOException e) {
         e.printStackTrace();
         this.sockServeur = null;
      }
   }

   public void close(){
      this.running = false;
   }

}
