/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author HP
 */
public class ListBiForm extends Form {
     Form current;
   
    ArrayList<Billet> BilletArrayList = new ArrayList<>();
    ServiceBillet BilletService = new ServiceBillet();
    public ListBiForm(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("My Reservations");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        BilletArrayList = BilletService.getAllBillets();
        Collections.reverse(BilletArrayList);
        fillData();
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 5);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Stats", FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new StatFormReservation(current).show();
        });
        getToolbar().addCommandToOverflowMenu("Trier par Date", null, (evt) -> {
            removeAll();
            Collections.sort(BilletArrayList, Billet.DateComparator);
            fillData();
        });
       
         /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Billet", FontImage.createMaterial(FontImage.MATERIAL_MOVIE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("My Reservation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ListResForm().show();
        });
    }

    public void fillData() {
        for (Billet Billet : BilletArrayList) {
             MultiButton multiButton = new MultiButton();
           
            multiButton.setTextLine1("Place: "+Billet.getChairBillet());
            multiButton.setTextLine3("Voyage num: "+Billet.getVoyageNum());
            multiButton.setTextLine4("Terminal: "+Billet.getTerminal());
           // multiButton.setTextLine5("Portail: "+Billet.getPortail());
            multiButton.setTextLine2("Embarquement: "+Billet.getEmbarquement());
            multiButton.setUIID(Billet.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new DetailBilletForm(Billet).show());
            add(multiButton);
        }
    }
}
