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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
/**
 *
 * @author HP
 */
public class HomeForm extends Form {
    Billet b;
    Resources res;
    public HomeForm(Form current) {
        
      // current=this; //Back
        setTitle("Home");
        setLayout(BoxLayout.y());
           /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home planning", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomePlaninngForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Home Localisation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeLocalisationForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Home Billet", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
         getToolbar().addCommandToLeftSideMenu("Home Reservation", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeReservationForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Billet", FontImage.createMaterial(FontImage.MATERIAL_MOVIE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("My Reservation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ListResForm().show();
        });
        
        add(new Label("Choose an option"));
        Button btnAddBillet = new Button("Add Billet");
        Button btnListBillets = new Button("List Billets");
     //   Button btnStatistiquePieReservationForm = new Button("Statistique Pie");
      
        
        btnAddBillet.addActionListener(e-> new AddBilletForm().show());
        btnListBillets.addActionListener(e-> new ListBilletForm(current));
        //btnStatistiquePieReservationForm.addActionListener(e-> new StatistiquePieReservationForm(res).show());
        addAll(btnAddBillet,btnListBillets);
          
        
    }
    
}
