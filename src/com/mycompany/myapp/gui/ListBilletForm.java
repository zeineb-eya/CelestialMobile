/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;

import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;

import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListBilletForm extends Form {
    Form current;
    Reservation res;
     public ListBilletForm(Form current) {
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

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME,  e -> new HomeForm(current).show());
        
        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_EVENT, e -> {
            new ListBilletForm(current);
        });
        

        FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fa.createSubFAB(FontImage.MATERIAL_ADD_TASK, "").addActionListener(e -> {
            new AddBilletForm().show();
        });
        
        fa.bindFabToContainer(tempForm.getContentPane());

        tempForm.setTitle("Billet");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 11);

        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label labelId = new Label("BilletId");
        Label labelchair_billet = new Label("chair_billet");
        Label labelvoyage_num = new Label("voyage_num");
        Label labelterminal = new Label("terminal");
        Label labelportail = new Label("portail");
        Label labelembarquement = new Label("embarquement");
        Label labellocalisation = new Label("localisation");
        
        Label labelEdit = new Label("Edit");
        Label labelDelete = new Label("Delete");
        Label labelReserve = new Label("Reserve");
        Label labelDetails = new Label("Details");
        
        Container HeadConainter = new Container(gridLayout);
        
        HeadConainter.add(labelId);
        HeadConainter.add(labelchair_billet);
        HeadConainter.add(labelvoyage_num);
        HeadConainter.add(labelterminal);
        HeadConainter.add(labelportail);
        HeadConainter.add(labelembarquement);
        HeadConainter.add(labellocalisation);
       
        HeadConainter.add( labelDelete);
        HeadConainter.add(labelEdit);
        HeadConainter.add(labelReserve);
        HeadConainter.add(labelDetails);
        
//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeReview());
        tempForm.add(HeadConainter);

        ArrayList<Billet> Billets = ServiceBillet.getInstance().getAllBillets();
        for (Billet r : Billets) {
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

                if (dig.show("Suppression", "Voulez vous supprimer l billet ?", "Annuler", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }

                // appel de la fonction delete du service Experience
                if (ServiceBillet.getInstance().deleteBillet(r)) {

                    new ListBilletForm(current);
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
                new UpdateFormBillet(r).show();
            });
            
            //Reserve Button
            Label lReserver = new Label(" ");
            lReserver.setUIID("NewsTopLine");
            Style ajouterStyle = new Style(lReserver.getUnselectedStyle());
            ajouterStyle.setFgColor(0xf7ad02);
            FontImage rFontImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, ajouterStyle);
            lReserver.setIcon(rFontImage);
            lReserver.setTextPosition(LEFT);
            //click reserve button
            lReserver.addPointerPressedListener(l -> {
                new AddReservationForm().show();
            });
            
            
            //Details Button
            Label lDetails  = new Label(" ");
            lDetails.setUIID("NewsTopLine");
            Style DetailsStyle = new Style(lDetails.getUnselectedStyle());
            DetailsStyle.setFgColor(0xf7ad02);
            FontImage dFontImage = FontImage.createMaterial(FontImage.MATERIAL_MORE, DetailsStyle);
            lDetails.setIcon(dFontImage);
            lDetails.setTextPosition(LEFT);
            //click Details button
            lDetails.addPointerPressedListener(l -> {
                new DetailBilletForm(r).show();
            });
             
            
            Container BodyConainter = new Container(gridLayout);
             
                    
            BodyConainter.add(new Label(String.valueOf(r.getId())));
//            String ChairBillet = String.valueOf(r.getChairBillet());
//            String VoyageNum = String.valueOf(r.getVoyageNum());
//            String Terminal = String.valueOf(r.getTerminal());
//            String Portail = String.valueOf(r.getPortail());
//            String Embarquement = String.valueOf(r.getEmbarquement());
//            String Localisation = String.valueOf(r.getLocalisation());
            
            BodyConainter.add(new Label(String.valueOf(r.getChairBillet())));
            BodyConainter.add(new Label(String.valueOf(r.getVoyageNum())));
            BodyConainter.add(new Label(String.valueOf(r.getTerminal())));
            BodyConainter.add(new Label(String.valueOf(r.getPortail())));
            BodyConainter.add(new Label(String.valueOf(r.getEmbarquement())));
            BodyConainter.add(new Label(String.valueOf(r.getLocalisation())));

            BodyConainter.add(lSupprimer);
            BodyConainter.add(lModifier);
            BodyConainter.add(lReserver);
            BodyConainter.add(lDetails);
            
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
                        Label label10 = (Label) mb.getComponentAt(9);
                        String line10 = label10.getText();
                        Label label11 = (Label) mb.getComponentAt(10);
                        String line11 = label11.getText();
                        boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                                || line2 != null && line2.toLowerCase().indexOf(text) > -1
                                || line3 != null && line3.toLowerCase().indexOf(text) > -1
                                || line4 != null && line4.toLowerCase().indexOf(text) > -1
                                || line5 != null && line5.toLowerCase().indexOf(text) > -1
                                || line6 != null && line6.toLowerCase().indexOf(text) > -1
                                || line7 != null && line7.toLowerCase().indexOf(text) > -1
                                || line8 != null && line8.toLowerCase().indexOf(text) > -1
                                || line9 != null && line9.toLowerCase().indexOf(text) > -1
                                || line10 != null && line10.toLowerCase().indexOf(text) > -1
                                || line11 != null && line11.toLowerCase().indexOf(text) > -1;
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            }
        }, 11);

        Button Comment = new Button("ajouter Billet");
        Comment.addActionListener(e -> new AddBilletForm());
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new HomeForm(current).showBack()); // Revenir vers l'interface précédente
        return tempForm;

    }
     
}
