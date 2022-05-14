/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.services.ServicePlaninng;
import java.util.ArrayList;

/**
 *
 * @author skanr
 */
public class ListPlaninngForm extends  Form {
    
    Form current;
     public ListPlaninngForm(Form current) {
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

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME,  e -> new HomePlaninngForm(current).show());
        
        tb.addMaterialCommandToSideMenu("Planning", FontImage.MATERIAL_EVENT, e -> {
            new ListPlaninngForm(current);
        });
     
 FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fa.createSubFAB(FontImage.MATERIAL_ADD_TASK, "").addActionListener(e -> {
            new AjoutPlaninngForm().show();
        });
        
        fa.bindFabToContainer(tempForm.getContentPane());

        tempForm.setTitle("Planinngs");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 12);
       // gridLayout.setHgap(25);

         Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        Label labelId = new Label("id");
        Label labelnom_planning = new Label("Nom");
        Label labeldescription_planning = new Label("Description");
        Label labeldestination_planning = new Label("Destination");
        Label labelprix_planning = new Label("Prix");
        Label labelperiode_planning = new Label("periode");
        Label labeldateDebut_planning = new Label("Date deb");
        Label labeldateFin_planning = new Label("Date fin");
        
        Label labelModifier = new Label("Modifier");
        Label labelSupprimer = new Label("Supprimer");
        Label labelDetails = new Label("Details");
        
        Container HeadConainter = new Container(gridLayout);
        
        HeadConainter.add(labelId);
        HeadConainter.add(labelnom_planning);
        HeadConainter.add(labeldescription_planning);
        HeadConainter.add(labeldestination_planning);
        HeadConainter.add(labelprix_planning);
        HeadConainter.add(labelperiode_planning);
        HeadConainter.add(labeldateDebut_planning);
        HeadConainter.add(labeldateFin_planning);
        
        HeadConainter.add(labelModifier);
        HeadConainter.add(labelSupprimer);
        HeadConainter.add(labelDetails);
        
        tempForm.add(HeadConainter);
        
        
        ArrayList<Planinng> Planinngs = ServicePlaninng.getInstance().displayPlaninngs();
        for (Planinng o : Planinngs) {
            
            //bouttn suppression
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lSupprimer.setIcon(supprimerImage);
            lSupprimer.setTextPosition(RIGHT);
            //on-click delete icon
           lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppresion");

                if (dig.show("Suppression", "Voulez vous supprimer le planinng ?", "Annuler", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }

                // lena chn3aytou l fonction delete 
                if (ServicePlaninng.getInstance().deletePlaninng(o)) {

                    new ListPlaninngForm(current);
                }
            });
           
            // butoon l modif
           Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);
            //click update button
            lModifier.addPointerPressedListener(l -> {
                new ModifPlaninngForm(o).show();
            });
           
            //boutton detail offre
            Label lDetails  = new Label(" ");
            lDetails.setUIID("NewsTopLine");
            Style DetailsStyle = new Style(lDetails.getUnselectedStyle());
            DetailsStyle.setFgColor(0xf7ad02);
            FontImage dFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, DetailsStyle);
            lDetails.setIcon(dFontImage);
            lDetails.setTextPosition(LEFT);
            //on click ala butoon details
            lDetails.addPointerPressedListener(l -> {
                new DetailPlaninngForm(o).show();
            });
            
            Container BodyConainter = new Container(gridLayout);
             
                    
            BodyConainter.add(new Label(String.valueOf(o.getId())));
            String nom_planning = String.valueOf(o.getNomPlaninng());
            String description_planning = String.valueOf(o.getDescriptionPlaninng());
            String destination_planning = String.valueOf(o.getDestinationPlaninng());
            String prix_planning = String.valueOf(o.getPrixPlaninng());
            String periode_planning = String.valueOf(o.getPeriodePlaninng());
            String dateDebut_planning = String.valueOf(o.getDateDebutPlaninng());
            String dateFin_planning = String.valueOf(o.getDateFinPlaninng());
            
            BodyConainter.add(new Label(nom_planning));
            BodyConainter.add(new Label(description_planning));
            BodyConainter.add(new Label(destination_planning));
            BodyConainter.add(new Label(prix_planning));
            BodyConainter.add(new Label(periode_planning));
            BodyConainter.add(new Label(dateDebut_planning));
            BodyConainter.add(new Label(dateFin_planning));

            BodyConainter.add(lSupprimer);
            BodyConainter.add(lModifier);
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
                        /*Label label10 = (Label) mb.getComponentAt(9);
                        String line10 = label10.getText();
                        Label label11 = (Label) mb.getComponentAt(10);
                        String line11 = label11.getText();*/
                        boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                                || line2 != null && line2.toLowerCase().indexOf(text) > -1
                                || line3 != null && line3.toLowerCase().indexOf(text) > -1
                                || line4 != null && line4.toLowerCase().indexOf(text) > -1
                                || line5 != null && line5.toLowerCase().indexOf(text) > -1
                                || line6 != null && line6.toLowerCase().indexOf(text) > -1
                                || line7 != null && line7.toLowerCase().indexOf(text) > -1
                                || line8 != null && line8.toLowerCase().indexOf(text) > -1
                                || line9 != null && line9.toLowerCase().indexOf(text) > -1;
                               /* || line10 != null && line10.toLowerCase().indexOf(text) > -1
                                || line11 != null && line11.toLowerCase().indexOf(text) > -1;*/
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            }
        }, 4);

        Button Comment = new Button("ajouter Billet");
        Comment.addActionListener(e -> new AjoutPlaninngForm());
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new HomePlaninngForm(current).showBack()); // Revenir vers l'interface précédente
        return tempForm;

    }
}