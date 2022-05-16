/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Cipher
 */
public class UpdateUserForm extends Form {
    public UpdateUserForm(User u,Form previous)
    { setTitle("update Personne");
        setLayout(BoxLayout.y());
//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
    TextField Id = new TextField(String.valueOf(u.getId()), "UserID");
    TextField Nom = new TextField(u.getNom_utilisateur(), "Nom", 20, TextField.ANY);
    TextField Prenom = new TextField(u.getPrenom_utilisateur(), "prenom", 20, TextField.ANY);
    TextField Email = new TextField(u.getAdresse_utilisateur(), "E-Mail", 20, TextField.EMAILADDR);

    

    
        Button btnSubmit = new Button("Update");
        Button btnret = new Button("return");
        
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
              
                     User u = new User(Integer.parseInt(Id.getText()),Nom.getText(),Prenom.getText(),Email.getText());
                    System.out.println(Id.getText());
                    System.out.println(Nom.getText());
                    System.out.println(Prenom.getText());
                    System.out.println(Email.getText());
                    System.out.println(u.getId());
                    System.out.println(u.getMail_utilisateur());
                    System.out.println(u.getNom_utilisateur());
                    System.out.println(u.getPrenom_utilisateur());
          


                    System.out.println("updated account");
                    
                    if (ServiceUser.getInstance().UpdateUser(u)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        System.out.println(u);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
               
            
            
        });
        addAll( Id,Nom,Prenom,Email,btnSubmit,btnret);
       this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
    
    
}
