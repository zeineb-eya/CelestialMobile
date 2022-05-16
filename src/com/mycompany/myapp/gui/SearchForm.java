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
public class SearchForm extends Form  {
    public SearchForm(Form previous)
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
      
fillData();
        /*for (User u : ServiceUser.getInstance().getAllUsers()) {
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
            }*/
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
          
        //this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    } public void fillData() {
        for (User u : ServiceUser.getInstance().getAllUsers()) {
             MultiButton multiButton = new MultiButton();
           
            multiButton.setTextLine2("Nom User: "+u.getNom_utilisateur());
            multiButton.setTextLine1("Mail User: "+u.getMail_utilisateur());
             multiButton.setTextLine1("Prenom User: "+u.getPrenom_utilisateur());

          //  multiButton.setTextLine4("User: "+reservation.getUser());
          //  multiButton.setTextLine3("Billet: "+reservation.getBillet());
            multiButton.setUIID(u.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           // multiButton.addActionListener(l -> new ReservationDetailForm(current, reservation).show());

            add(multiButton);
        }
}
    }
    
    
