package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.services.ServiceRole;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cipher
 */
public class AddRoleForm extends Form {
     public AddRoleForm(Form previous) {
        setTitle("Add a new role");
        setLayout(BoxLayout.y());
        
        TextField tfnom_role = new TextField("","Nom_Role");
        TextField tfdescription_role= new TextField("", "description_role");
        Button btnValider = new Button("Add role");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom_role.getText().length()==0)||(tfdescription_role.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Role t = new Role(tfnom_role.getText().toString(),tfdescription_role.getText().toString());
                        if( ServiceRole.getInstance().addRole(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom_role,tfdescription_role,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
