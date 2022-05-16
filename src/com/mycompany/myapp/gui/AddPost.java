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
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServicePost;

/**
 *
 * @author khawl
 */
public class AddPost extends BaseForm {
    
    Form current;
    public AddPost(Resources res ) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Post");
        getContentPane().setScrollVisible(false);
        
        
//        super.addSideMenu(res);
        
        TextField nom = new TextField("", "Nom Post" , 20 , TextField.ANY);
        TextField description = new TextField("", "Description Post " , 20 , TextField.NUMERIC);
               TextField image = new TextField("" , "Image Post" , 20 , TextField.ANY);
             //  TextField dateexpiration =new TextField(String.valueOf(r.getDateexpiration()), "Date expiration " , 20 , TextField.ANY);
 
        
        
        
        // formulaire design
        nom.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        image.setUIID("NewsTopLine");
        //dateexpiration.setUIID("NewsTopLine");
        
        nom.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);
        // dateexpiration.setSingleLineTextArea(true);
        
        Button btnAjouter = new Button("Ajouter");
       btnAjouter.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnAjouter.addPointerPressedListener(l ->   { 
           Post r = new Post();
           r.setNom(nom.getText());
           r.setDescription_post(description.getText());
           r.setImg_post(image.getText());
     //  r.setDateexpiration(r.getDateexpiration());
      
       
       //appel fonction modfier  men service
       
       if(ServicePost.getInstance().addPost(r)) { // if true
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
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
               
                  new FloatingHint(image),
                createLineSeparator(),
                
                createLineSeparator(),//ligne de séparation
                
                btnAjouter,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
    }
}