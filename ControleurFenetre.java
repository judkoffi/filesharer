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

      //Selection du fichier a envoyer
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

      //Envoie du fichier
      if(evenement.getSource() == this.fenetre.getButtonEnvoyer()){
         System.out.println("On peut envoyer");

         int port = Integer.parseInt(this.fenetre.getChampPort().getText());

         Server serveur = new Server(port, filename);
         serveur.open();

      }


      //Enregistrer sous
      if(evenement.getSource() == this.fenetre.getButtonSaveAs()){

         if( (!this.fenetre.getChampPort().getText().equals("")) && (!this.fenetre.getChampsIp().getText().equals(""))  ){

            JFileChooser explorateur = new JFileChooser("./");

            int retour = explorateur.showSaveDialog(this.fenetre);

            if(retour == JFileChooser.APPROVE_OPTION){
               filename = explorateur.getSelectedFile().getAbsolutePath();

               this.fenetre.getButtonRecevoir().setBackground(Color.GREEN);
               this.fenetre.getButtonRecevoir().setEnabled(true);
            }

            if(retour == JFileChooser.CANCEL_OPTION){
               JOptionPane.showMessageDialog(this.fenetre,"Veuilez entrer le nom sous lequel sera enregistrer le fichier","information", JOptionPane.INFORMATION_MESSAGE);
            }
         }else{

            JOptionPane.showMessageDialog(this.fenetre,"Veuilez remplir l'ip et le port","information", JOptionPane.INFORMATION_MESSAGE);

         }
      }



      //Recevoir fichier 
      if(evenement.getSource() == this.fenetre.getButtonRecevoir()){
        System.out.println("On peut recevoir");

        String ip = this.fenetre.getChampsIp().getText();

        int port = Integer.parseInt(this.fenetre.getChampPort().getText());

        System.out.println("Port "+ port);

        System.out.println("IP "+ip);


        Thread t = new Thread(new Client(ip, port, filename));
        t.start();

      }

   }


}
