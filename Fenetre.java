import java.awt.*;
import javax.swing.*;


public class Fenetre extends JFrame{


   public Fenetre(int action, String titre){
      super(titre);
      this.setSize(400,150);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      if(action == 1){
        this.envoie();
      }

      if(action == 2){
        this.reception();
      }
   }


   public void envoie(){
      System.out.println("Envoie de fichier");
      
      JLabel etiquetteAddrIp = new JLabel("IP");
      JTextField champAddrIp = new JTextField();

      JLabel port = new JLabel("Port");
      JTextField champPort = new JTextField();

      JButton parcourir = new JButton("Parcourir");
      
      JButton envoyer = new JButton("Envoyer");
      envoyer.setBackground(Color.RED);

      JPanel panneauButton = new JPanel(new GridLayout(1,2));
      panneauButton.add(parcourir);
      panneauButton.add(envoyer);

      JPanel panneau = new JPanel(new GridLayout(5,1));

      panneau.add(etiquetteAddrIp);
      panneau.add(champAddrIp);
      panneau.add(port);
      panneau.add(champPort);
      panneau.add(panneauButton);

      this.add(panneau);
   
   }


   public void reception(){
      System.out.println("Reception de fichier");


   }
}
