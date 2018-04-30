import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ControleurFenetre implements ActionListener{

   private Fenetre fenetre;

   public ControleurFenetre(Fenetre f){
      this.fenetre = f;
   }







   @Override
   public void actionPerformed(ActionEvent evenement){

      if(evenement.getSource() == this.fenetre.getButtonParcourir()){

         if( (!this.fenetre.getChampsIp().getText().equals("")) && (!this.fenetre.getChampPort().getText().equals("")) ){

            String filename = "";

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

            JOptionPane.showMessageDialog(this.fenetre,"Veuilez remplir l'adresse IP et le port","information", JOptionPane.INFORMATION_MESSAGE);

         }
      }
   }
}
