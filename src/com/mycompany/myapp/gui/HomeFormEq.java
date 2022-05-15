/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CategorieEquipement;
import com.mycompany.myapp.entities.Equipement;


/**
 *
 * @author bhk
 */
public class HomeFormEq extends Form{
    Form current;

    public HomeFormEq() {
                current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        //product
        Button btnAddEquipment = new Button("Add Equipment");
        Button btnListEquipment = new Button("List Equipments");
       //category
        Button btnAddTask = new Button("Add Category");
        Button btnListTasks = new Button("List category");
        Button StatFormEquipment = new Button("List Equipments");

         
 
        
        
        //Equipment
        btnAddEquipment.addActionListener(e-> new AddEquipment(current).show());
        btnListEquipment.addActionListener(e-> new ListEquipment(current).show());
        StatFormEquipment.addActionListener(e-> new ListEqForm().show());
        addAll(btnAddEquipment,btnListEquipment);
        
        //category
        btnAddTask.addActionListener(e-> new AddCategory(current).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        addAll(btnAddTask,btnListTasks,StatFormEquipment);
        
        
    }

    public HomeFormEq(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    

    
}
