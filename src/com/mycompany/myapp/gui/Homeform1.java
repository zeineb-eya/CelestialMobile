/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
/**
 *
 * @author Cipher
 */
public class Homeform1 extends Form {
    
    public Homeform1(Form current) {
      
Resources res;
//Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddRole = new Button("Add Role");
        Button btnListRoles = new Button("List Roles");
        Button btnAddUsers = new Button("Add Users");
         Button btnShowUsers = new Button("Show Users");
          Button btnSearchUsers = new Button("Search Users");


        User u = new User();
        btnAddRole.addActionListener(e-> new AddRoleForm(current).show());
        btnListRoles.addActionListener(e-> new ListRolesForm(current).show());
        btnAddUsers.addActionListener(e-> new AddUserForm(current).show());
        btnShowUsers.addActionListener(e-> new ListUsersForm(current).show());
        btnSearchUsers.addActionListener(e-> new SearchForm(current).show());



       
        addAll(btnAddRole,btnListRoles,btnAddUsers,btnShowUsers,btnSearchUsers);
        

    }
}
