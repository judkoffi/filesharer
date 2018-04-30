import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;

public class ControleurFenetre implements ActionListener{
   private String filename;
   private Fenetre fenetre;

   public ControleurFenetre(Fenetre f){
      this.fenetre = f;
   }


   @Override
   public void actionPerformed(ActionEvent evenement){

      if(evenement.getSource() == this.fenetre.getButtonParcourir()){

         if(!this.fenetre.getChampPort().getText().equals("")){

            JFileChooser explorateur = new JFileChooser("./");

            int retour = explorateur.showOpenDialog(this.fenetre);

            if(retour == JFileChooser.APPROVE_OPTION){
               filename = explorateur.getSelectedFile().getAbsolutePath();

               this.fenetre.getButtonEnvoyer().setBackground(Color.GREEN);
               this.fenetre.getButtonEnvoyer().setEnabled(true);
            }

            if(retour == JFileChooser.CANCEL_OPTION){
               JOptionPane.showMessageDialog(this.fenetre,"Veuilez chosir un fichier","information", JOptionPane.INFORMATION_MESSAGE);
            }


         }else{

            JOptionPane.showMessageDialog(this.fenetre,"Veuilez remplir le port","information", JOptionPane.INFORMATION_MESSAGE);

         }
      }

      if(evenement.getSource() == this.fenetre.getButtonEnvoyer()){
         System.out.println("On peut envoyer");

         int port = Integer.parseInt(this.fenetre.getChampPort().getText());

         Server serveur = new Server(port, filename);
         serveur.open();

      }

   }
}
