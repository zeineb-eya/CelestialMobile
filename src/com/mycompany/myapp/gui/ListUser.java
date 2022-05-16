/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author Cipher
 */
public class ListUser extends Form {
   Form current;
     public ListUser (Form current) {
        
       current = createForm();
     }
   
    public Form createForm() {

        Toolbar.setGlobalToolbar(true);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = tempForm.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME,  e -> new HomeReservationForm(current).show());
        
        tb.addMaterialCommandToSideMenu("User", FontImage.MATERIAL_EVENT, e -> {
            new ListUser(current);
        });
        

        FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fa.createSubFAB(FontImage.MATERIAL_ADD_TASK, "").addActionListener(e -> {
            new AddReservationForm().show();
        });
        
        fa.bindFabToContainer(tempForm.getContentPane());

        tempForm.setTitle("Users");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 9);

        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label labelId = new Label("Id");
        Label labelnom= new Label("nom");
        Label labelprenom= new Label("prenom");
        Label labeladresse = new Label("adresse");
        Label labelemail = new Label("mail");
        Label labelpassword = new Label("password");
       
        
        Label labelEdit = new Label("Edit");
        Label labelDelete = new Label("Delete");
        Label labelConfirm = new Label("Confirm");
        Label labelCancel = new Label("Cancel");
        
        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelId);
        HeadConainter.add(labelnom);

        HeadConainter.add(labelprenom);
        HeadConainter.add(labeladresse);
        HeadConainter.add(labelemail);
        HeadConainter.add(labelpassword);

        
        
        HeadConainter.add(labelEdit);
        HeadConainter.add(labelDelete);
        HeadConainter.add(labelConfirm);
        HeadConainter.add(labelCancel);
//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeReview());
        tempForm.add(HeadConainter);

        ArrayList<User> Users = ServiceUser.getInstance().getAllUsers();
        for (User r : Users) {
            //delete button
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lSupprimer.setIcon(supprimerImage);
            lSupprimer.setTextPosition(RIGHT);
            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppresion");

                if (dig.show("Suppression", "Voulez vous supprimer Votre Compte ?", "Annuler", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }

                // appel de la fonction delete du service Experience
                if (ServiceUser.getInstance().deleteUser(r)) {

                    new ListUser(current);
                }
            });
            //update Button
            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);
            //click update button
            lModifier.addPointerPressedListener(l -> {
                //new UpdateFormReservation(r).show();
            });
            //lConfirm button
            /*Label lConfirm = new Label(" ");
            lConfirm.setUIID("NewsTopLine");
            Style confirmStyle = new Style(lConfirm.getUnselectedStyle());
            confirmStyle.setFgColor(0xf21f1f);
            FontImage confirmImage = FontImage.createMaterial(FontImage.MATERIAL_CHECK, confirmStyle);
            lConfirm.setIcon(confirmImage);
            lConfirm.setTextPosition(RIGHT);
            //click delete icon
            lConfirm.addPointerPressedListener(l -> {

                // appel de la fonction delete du service Experience
                if (ServiceReservation.getInstance().confirmeReservation(r)) {

                    new ListReservationForm(current);
                }
            });*/
            //lCancel button
           /* Label lCancel = new Label(" ");
            lCancel.setUIID("NewsTopLine");
            Style CancelStyle = new Style(lCancel.getUnselectedStyle());
            CancelStyle.setFgColor(0xf21f1f);
            FontImage CancelImage = FontImage.createMaterial(FontImage.MATERIAL_CHECK, CancelStyle);
            lCancel.setIcon(confirmImage);
            lCancel.setTextPosition(RIGHT);
            //click delete icon
            lCancel.addPointerPressedListener(l -> {

                // appel de la fonction delete du service Experience
                if (ServiceReservation.getInstance().cancelReservation(r)) {

                    new ListReservationForm(current);
                }
            });*/
            Container BodyConainter = new Container(gridLayout);
             
                    
            BodyConainter.add(new Label(String.valueOf(r.getId())));
            
       
  
           
            BodyConainter.add(new Label(String.valueOf(r.getNom_utilisateur())));
            BodyConainter.add(new Label(String.valueOf(r.getPrenom_utilisateur())));
            BodyConainter.add(new Label(String.valueOf(r.getSudo_utilisateur())));
            BodyConainter.add(new Label(String.valueOf(r.getMail_utilisateur())));
            BodyConainter.add(new Label(String.valueOf(r.getPassword())));


            BodyConainter.add(lSupprimer);
            BodyConainter.add(lModifier);
            //BodyConainter.add(lConfirm);
            //BodyConainter.add(lCancel);
            
            tempForm.add(BodyConainter);

        }
        tempForm.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : tempForm.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                tempForm.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : tempForm.getContentPane()) {
                    Container mb = (Container) cmp;
                    if (!(mb.getComponentAt(0) instanceof Button)) {
                        Label label1 = (Label) mb.getComponentAt(0);
                        String line1 = label1.getText();
                        Label label2 = (Label) mb.getComponentAt(1);
                        String line2 = label2.getText();
                        Label label3 = (Label) mb.getComponentAt(2);
                        String line3 = label3.getText();
                        Label label4 = (Label) mb.getComponentAt(3);
                        String line4 = label4.getText();
                        Label label5 = (Label) mb.getComponentAt(4);
                        String line5 = label5.getText();
                        Label label6 = (Label) mb.getComponentAt(5);
                        String line6 = label6.getText();
                        Label label7 = (Label) mb.getComponentAt(6);
                        String line7 = label7.getText();
                        Label label8 = (Label) mb.getComponentAt(7);
                        String line8 = label8.getText();
                        Label label9 = (Label) mb.getComponentAt(8);
                        String line9 = label9.getText();
                        boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                                || line2 != null && line2.toLowerCase().indexOf(text) > -1
                                || line3 != null && line3.toLowerCase().indexOf(text) > -1
                                || line4 != null && line4.toLowerCase().indexOf(text) > -1
                                || line5 != null && line5.toLowerCase().indexOf(text) > -1
                                || line6 != null && line6.toLowerCase().indexOf(text) > -1
                                || line7 != null && line7.toLowerCase().indexOf(text) > -1
                                || line8 != null && line8.toLowerCase().indexOf(text) > -1
                                || line9 != null && line9.toLowerCase().indexOf(text) > -1;
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            }
        }, 9);

        Button Comment = new Button("ajouter User");
       Comment.addActionListener(e -> new AddReservationForm());
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new HomeReservationForm(current).showBack()); // Revenir vers l'interface précédente
        return tempForm;

    }}
