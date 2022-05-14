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
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.services.ServicePlaninng;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ASUS
 */
public class listPlanByPrix extends Form  {
    
    Form current;
    ArrayList<Planinng> planinngArrayList = new ArrayList<>();
    ServicePlaninng ServicePlaninng = new ServicePlaninng();
    public listPlanByPrix(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Planinngs");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        planinngArrayList = ServicePlaninng.displayPlaninngs();
        Collections.reverse(planinngArrayList);
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
                     String line3 = mb.getTextLine3();
                    String line4 = mb.getTextLine4();
                    // String line5 = mb.getTextLine5();
                    
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 6);
        
         /* *** *OVERFLOW MENU* *** */
       /* getToolbar().addCommandToOverflowMenu("Stats", FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new StatFormReservation(current).show();
        });*/
        getToolbar().addCommandToOverflowMenu("Trier selon date debut", null, (evt) -> {
            removeAll();
            Collections.sort(planinngArrayList, Planinng.sortPlaninngdatedebut);
            fillData();
        });
       
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomePlaninngForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Randonnee", FontImage.createMaterial(FontImage.MATERIAL_MOVIE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("My Reservation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new listPlanByPrix().show();
        });
    }
  public void fillData() {
        for (Planinng o : planinngArrayList) {
             MultiButton multiButton = new MultiButton();
             
            multiButton.setTextLine1(o.getNomPlaninng());
            multiButton.setTextLine3(o.getDateDebutPlaninng()+"dateDebut_planning");
             multiButton.setTextLine2(o.getPrixPlaninng()+"");
             multiButton.setTextLine4(o.getPeriodePlaninng()+"");
           /* multiButton.setTextLine3(o.getPrixOffre()+"");
            multiButton.setTextLine4(o.getReduction()+"");*/
          
            //multiButton.setTextLine1(o.getDateFinOffre()+"Date fin");
            multiButton.setUIID(o.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           // multiButton.addActionListener(l -> new ReservationDetailForm(current, reservation).show());
            add(multiButton);
        }
    }
}