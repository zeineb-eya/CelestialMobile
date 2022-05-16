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
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipment;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author HP
 */
public class ListEqForm extends Form {
     Form current;
    ArrayList<Equipement> EquipementArrayList = new ArrayList<>();
    ServiceEquipment EquipementService = new ServiceEquipment();
    public ListEqForm(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("My Reservations");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        EquipementArrayList = EquipementService.getAllEquipments();
        Collections.reverse(EquipementArrayList);
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
        }, 4);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Stats", FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new StatFormEquipment(current).show();
        });
        getToolbar().addCommandToOverflowMenu("Trier par etat", null, (evt) -> {
            removeAll();
            Collections.sort(EquipementArrayList, Equipement.statusComparator);
           
            fillData();
        });
         getToolbar().addCommandToOverflowMenu("Trier par Date", null, (evt) -> {
            removeAll();
           
            Collections.sort(EquipementArrayList, Equipement.statusComparator);
            fillData();
        });
         /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("My Equipements", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ListEqForm().show();
        });
    }

    public void fillData() {
        for (Equipement E : EquipementArrayList) {
             MultiButton multiButton = new MultiButton();
           
            multiButton.setTextLine2("State Equipement: "+E.getEtat_equipement());
            multiButton.setTextLine1("Name Equipement: "+E.getNom_equipement());
          //  multiButton.setTextLine4("User: "+reservation.getUser());
          //  multiButton.setTextLine3("Billet: "+reservation.getBillet());
            multiButton.setUIID(E.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           // multiButton.addActionListener(l -> new ReservationDetailForm(current, reservation).show());

            add(multiButton);
        }
    }
}
