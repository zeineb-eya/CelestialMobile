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
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.ServiceOffre;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author HP
 */
public class triOffreByDateDebutAForm extends Form{
    
 Form current;
    ArrayList<Offre> offreArrayList = new ArrayList<>();
    ServiceOffre serviceOffre = new ServiceOffre();
    public triOffreByDateDebutAForm(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Offres");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        offreArrayList = serviceOffre.displayOffres();
        Collections.reverse(offreArrayList);
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
       
       
        getToolbar().addCommandToOverflowMenu("Trier selon nom", null, (evt) -> {
            removeAll();
            Collections.sort(offreArrayList, Offre.triOffreNomA);
            fillData();
        });
      
        getToolbar().addCommandToOverflowMenu("Trier selon date debut", null, (evt) -> {
            removeAll();
            Collections.sort(offreArrayList, Offre.triOffreDateDebutA);
            fillData();
        });
        
        getToolbar().addCommandToOverflowMenu("Trier selon date fin", null, (evt) -> {
            removeAll();
            Collections.sort(offreArrayList, Offre.triOffreDateFinA);
            fillData();
        });
      
         
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeOffreForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Randonnee", FontImage.createMaterial(FontImage.MATERIAL_MOVIE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Reclamation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new triOffreByDateDebutAForm().show();
        });
    }
  public void fillData() {
        for (Offre o : offreArrayList) {
             MultiButton multiButton = new MultiButton();
             
            multiButton.setTextLine1(o.getNomOffre()+"nom offre");
            multiButton.setTextLine2(o.getDateDebutOffre()+"Date debut");
             multiButton.setTextLine3(o.getDateFinOffre()+"");
             multiButton.setTextLine4(o.getPrixOffre()+"");
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
