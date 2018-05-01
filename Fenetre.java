import java.awt.*;
import javax.swing.*;


public class Fenetre extends JFrame{

   private JTextField champAddrIp; 
   private JTextField champPort;
   private JButton envoyer;
   private JButton parcourir;
   private JButton recevoir;
   private JButton saveAs;


   public Fenetre(int action, String titre){
      super(titre);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      if(action == 1){
         this.setSize(400,100);
         this.envoie();
      }

      if(action == 2){
         this.setSize(400,200);
         this.reception();
      }
   }


   public void envoie(){
      System.out.println("Envoie de fichier");

      JLabel port = new JLabel("Port");
      this.champPort = new JTextField();

      this.parcourir = new JButton("Parcourir");

      this.envoyer = new JButton("Envoyer");
      this.envoyer.setBackground(Color.RED);
      this.envoyer.setEnabled(false);

      JPanel panneauButton = new JPanel(new GridLayout(1,2));
      panneauButton.add(this.parcourir);
      panneauButton.add(this.envoyer);

      JPanel panneau = new JPanel(new GridLayout(3,1));


      ControleurFenetre controleur = new ControleurFenetre(this);

      parcourir.addActionListener(controleur);
      envoyer.addActionListener(controleur);

      panneau.add(port);
      panneau.add(champPort);
      panneau.add(panneauButton);

      this.add(panneau);
   }


   public void reception(){
      System.out.println("Reception de fichier");
      JLabel etiquetteAddrIp = new JLabel("IP");
      this.champAddrIp = new JTextField();

      JLabel port = new JLabel("Port");
      this.champPort = new JTextField();

      this.saveAs = new JButton("Enregistrer sous");

      this.recevoir = new JButton("Recevoir");
      this.recevoir.setBackground(Color.RED);
      this.recevoir.setEnabled(false);

      JPanel panneauButton = new JPanel(new GridLayout(1,2));
      panneauButton.add(this.recevoir);
      panneauButton.add(this.saveAs);

      JPanel panneau = new JPanel(new GridLayout(5,1));

      ControleurFenetre controleur = new ControleurFenetre(this);

      this.recevoir.addActionListener(controleur);
      this.saveAs.addActionListener(controleur);

      panneau.add(etiquetteAddrIp);
      panneau.add(champAddrIp);
      panneau.add(port);
      panneau.add(champPort);
      panneau.add(panneauButton);

      this.add(panneau);
   }


   public JTextField getChampsIp(){
      return this.champAddrIp;
   }

   public JTextField getChampPort(){
      return this.champPort;
   }

   public JButton getButtonEnvoyer(){
      return this.envoyer;
   }

   public JButton getButtonParcourir(){
      return this.parcourir;
   }

   public JButton getButtonRecevoir(){
      return this.recevoir;
   }

   public JButton getButtonSaveAs(){
      return this.saveAs;
   }
}
