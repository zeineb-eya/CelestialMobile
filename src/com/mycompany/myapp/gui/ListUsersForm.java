/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Cipher
 */
public class ListUsersForm extends Form  {
    public ListUsersForm(Form previous)
    { setTitle ("List Users");
//    SpanLabel sp = new SpanLabel();
//    sp.setText( ServiceUser.getInstance().getAllUsers().toString());
//    add(sp);
//   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

  // show list 
       
        Container List = new Container (BoxLayout.y());
        List.setScrollableY(true);
       /* *
SEARCHBAR
* */
      

        for (User u : ServiceUser.getInstance().getAllUsers()) {
            MultiButton mb = new MultiButton(u.toString());
            //System.out.println(user.getId());
        Button update = new Button("update");
        update.setUIID("update");
            update.addActionListener(e -> new UpdateUserForm(u,previous).show());
        Button delete = new Button("delete");
        delete.setUIID("delete");
            delete.addActionListener(e -> new DeleteUserForm(u,previous).show());
//            for(int i = 0; i < user; i++)
//            {
//                System.out.println();
//                }
            add(mb);
            add(delete);
            add(update);
            } }}
      
          
        //this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    
    
    
