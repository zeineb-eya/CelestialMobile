/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.services.ServiceRole;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Cipher
 */
public class ListRolesForm extends Form {
     public ListRolesForm(Form previous) {
        setTitle("List roles");
//spanlabel yaamel retour a la ligne 
       Container List = new Container (BoxLayout.y());
        List.setScrollableY(true);
       
        for (Role u : ServiceRole.getInstance().getAllRoles()) {
            MultiButton mb = new MultiButton(u.toString());
            //System.out.println(user.getId());
        Button update = new Button("update");
        update.setUIID("update");
            update.addActionListener(e -> new UpdateRoleForm(u,previous).show());
        Button delete = new Button("delete");
        delete.setUIID("delete");
            delete.addActionListener(e -> new DeleteRoleForm(u,previous).show());
//            for(int i = 0; i < user; i++)
//            {
//                System.out.println();
//                }
            add(mb);
            add(delete);
            add(update);
            }
                this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
