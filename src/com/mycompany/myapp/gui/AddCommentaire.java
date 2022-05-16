/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceCommentaire;
import com.mycompany.myapp.services.ServicePost;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
  * @author khawl
 */
public class AddCommentaire extends BaseForm {
    
    Form current;
    public AddCommentaire(Resources res ,Post p) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Commentaire");
        getContentPane().setScrollVisible(false);
        
        
//        super.addSideMenu(res);
        
        TextField commentaire = new TextField("", " Votre Commentaire" , 60 , TextField.ANY);
      
             //  TextField dateexpiration =new TextField(String.valueOf(r.getDateexpiration()), "Date expiration " , 20 , TextField.ANY);
 
        
        
        
        
        commentaire.setUIID("NewsTopLine");
        
        
        commentaire.setSingleLineTextArea(true);
       
        // dateexpiration.setSingleLineTextArea(true);
        
        Button btnAjouter = new Button("Ajouter");
       btnAjouter.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnAjouter.addPointerPressedListener(l ->   { 
           Commentaire r = new Commentaire();
           r.setMsg_commentaire(commentaire.getText());
           r.setPost(p);
           sendMail(res,p);//ken mna3tilhach mat5dimj
          
     // r.setDateexpiration(r.getDateexpiration());
      
       
       //appel fonction modfier  men service
       
       if(ServiceCommentaire.getInstance().addCommentaire(p.getId(), r)) { // if true
           new ListPost(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListPost(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
       
      
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(commentaire),
                createLineSeparator(),
               
                
                createLineSeparator(),//ligne de séparation
                
                btnAjouter,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
    
    
        
  public void sendMail(Resources res,Post p) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            //propriete definie mail 
            msg.setFrom(new InternetAddress("Un commentaire a été ajouté <monEmail@domaine.com>"));//titre message fo9
            msg.setRecipients(Message.RecipientType.TO, "zaineb.bachouch@esprit.tn");//send mail
            msg.setSubject("Commentaire ajouté ");//objet mail
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           //String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Un nouveau commentaire a été ajouté au post "+ p.getNom();//issm post
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"zaineb.bachouch@esprit.tn","213JFT1987");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
}