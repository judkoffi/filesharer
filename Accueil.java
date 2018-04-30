import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil extends JFrame implements ActionListener{
   private JPanel conteneur;
   private Fenetre fenetre;

   public Accueil(String titre){
      super(titre);
      this.setSize(700,520);
      this.conteneur = new JPanel(new BorderLayout());
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


   public void init(){
      JLabel etiquette = new JLabel("Filesharer");
      JButton crypter = new JButton("Envoyer");
      JButton decrypter = new JButton("Recevoir");


      JLabel img = new JLabel(new ImageIcon("./imgs/shared-folder.png"));
      img.setOpaque(true);
      img.setBackground(Color.ORANGE);

      JPanel panneaButton = new JPanel(new GridLayout(1,2));

      crypter.addActionListener(this);
      decrypter.addActionListener(this);

      panneaButton.add(crypter);
      panneaButton.add(decrypter);

      etiquette.setOpaque(true);
      etiquette.setBackground(Color.GRAY);
      etiquette.setFont(new Font("Arial", Font.BOLD, 50));
      etiquette.setHorizontalAlignment(JLabel.CENTER);

      this.conteneur.add(img, BorderLayout.CENTER);
      this.conteneur.add(panneaButton, BorderLayout.SOUTH);
      this.add(this.conteneur);
      this.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent evenement){

      //Fermer la fenetre d'accueil
      this.dispose();


      if(evenement.getActionCommand().equals("Envoyer")){



         this.fenetre = new Fenetre(1, "Envoie de fichier");


         /*SPN spn = new SPN(filename, "crypter");
           spn.makeCryptage();
           JOptionPane.showMessageDialog(this.fenetre,"Fin du cryptage","infomartion", JOptionPane.INFORMATION_MESSAGE);*/
      }
      if(evenement.getActionCommand().equals("Recevoir")){

         this.fenetre = new Fenetre(2, "Reception de fichier");
         /*
            SPN spn = new SPN(filename, "decrypter");
            spn.makeDecryptage();
            JOptionPane.showMessageDialog(this.fenetre,"Fin du decryptage","information", JOptionPane.INFORMATION_MESSAGE);*/
      }
      this.fenetre.setVisible(true);
   }

}
