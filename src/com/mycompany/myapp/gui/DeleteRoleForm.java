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
public class DeleteRoleForm extends Form {   
 public DeleteRoleForm(Role u,Form current){
          setTitle("delete Personne");
        setLayout(BoxLayout.y());
//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
        //TextField tfID = new TextField(String.valueOf(u.getId()), "PersonneId");
        Button btnSubmit = new Button("Delete");
        Button btnret = new Button("return");
     btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
               // } else {
                    //System.out.println(tfID.getText());
//                    Event t = new Event(tfID.getText());
                    System.out.println(u.getId());
                    System.out.println(u.getNom_role());
                    System.out.println("deleted account");
                    
                    if (ServiceRole.getInstance().deleteRole(u)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
               

            //}
        });
        addAll(btnSubmit,btnret);
     //  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
