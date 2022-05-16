/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.services.ServiceRole;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Cipher
 */
public class UpdateRoleForm extends Form {
    public UpdateRoleForm(Role u,Form previous)
    { setTitle("update Personne");
        setLayout(BoxLayout.y());
          Font mediumItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);

//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
    TextField Id = new TextField(String.valueOf(u.getId()), "UserID");
    TextField Nom = new TextField(u.getDescription_role(), "Description", 20, TextField.ANY);
    TextField Prenom = new TextField(u.getNom_role(), "Nom Role", 20, TextField.ANY);

    

    
        Button btnSubmit = new Button("Update");
        Button btnret = new Button("return");
        
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nom.getText().length() == 0)) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
               } else {
                     Role u = new Role(Integer.parseInt(Id.getText()),Nom.getText(),Prenom.getText());
                    System.out.println(Id.getText());
                    System.out.println(Nom.getText());
                    System.out.println(Prenom.getText());
                    
          


                    System.out.println("updated account");
                    
                    if (ServiceRole.getInstance().UpdateRole(u)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
               
            
            }
        });
        addAll( Id,Nom,Prenom,btnSubmit,btnret);
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
    
    
}
