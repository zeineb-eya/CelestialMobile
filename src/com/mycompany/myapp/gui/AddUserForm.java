/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Cipher
 */
public class AddUserForm extends Form{
      public AddUserForm(Form previous) {
        setTitle("Register");
        setLayout(BoxLayout.y());
        
        TextField tfnom_utilisateur = new TextField("","Nom");
        TextField tfprenom_utilisateur= new TextField("", "Prenom");
                TextField tfadresse_utilisateur= new TextField("", "Adresse");
                                TextField tfmail_utilisateur= new TextField("", "Mail");

                TextField tfsudo_utilisateur= new TextField("", "Sudo");
                              TextField tfpassword_utilisateur= new TextField("", "Password");

        TextField tftel_utilisateur= new TextField("", "Telephone");

        Button btnValider = new Button("Add User");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom_utilisateur.getText().length()==0)||(tfprenom_utilisateur.getText().length()==0)||(tfsudo_utilisateur.getText().length()==0)||(tfadresse_utilisateur.getText().length()==0)||(tfmail_utilisateur.getText().length()==0)||(tfpassword_utilisateur.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User t = new User(tfmail_utilisateur.getText().toString(), tfsudo_utilisateur.getText().toString(),tfnom_utilisateur.getText().toString(),tfprenom_utilisateur.getText().toString(),tfadresse_utilisateur.getText().toString(),tfpassword_utilisateur.getText().toString(),tftel_utilisateur.getText().toString());
                        if( ServiceUser.getInstance().addUsers(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           System.out.println(t);
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom_utilisateur,tfprenom_utilisateur,tfadresse_utilisateur,tfmail_utilisateur,tfsudo_utilisateur,tfpassword_utilisateur,tftel_utilisateur,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}