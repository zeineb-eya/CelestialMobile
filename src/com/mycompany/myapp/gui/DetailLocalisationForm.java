/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Localisation;
import com.mycompany.myapp.services.ServiceLocalisation;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.messaging.Message;
//import com.mycompany.myapp.services.ServiceReservation;

/**
 *
 * @author HP
 */
public class DetailLocalisationForm extends Form {
    Form current;
     ServiceLocalisation ServiceLocalisation = new ServiceLocalisation();
    
     DetailLocalisationForm(Localisation o) {
                 /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Randonnee Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
       
        Label labelpositionDepart_localisation = new Label("position depart :"+o.getPositionDepartLocalisation());
        Label labelpositionArivee_planning = new Label("position arrive :"+o.getPositionAriveePlanning());
        Label labelfusee = new Label("fusse :"+o.getFusee());
        Label labelheureDepart_localisation = new Label("heure depart :"+o.getHeureDepartLocalisation());
        Label labelheureArrivee_loacalisation = new Label("heure arrive :"+o.getHeureArriveeLoacalisation());
       
        Button showButton = new Button("Reserver");
        showButton.addActionListener(evt -> {
           new AjoutLocalisationForm().show();
        });
        addAll(labelpositionDepart_localisation, labelpositionArivee_planning, labelfusee,labelheureDepart_localisation, labelheureArrivee_loacalisation,showButton);
        /* *** *BACK BUTTON* *** */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
//        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
//            //SENDING EMAIL
//            Display.getInstance().sendMessage(new String[]{""}, "Let's participate this!", new Message("Check out this Ticket: " + b.getChairBillet() + " it's trip number: " + b.getVoyageNum() + " date "+b.getLocalisation()));
//        });
    }

}