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
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
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
public class DetailBilletForm extends Form {
    Form current;
     ServiceBillet ServiceBillet = new ServiceBillet();
    
     DetailBilletForm(Billet b) {
                 /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Randonnee Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
       
        Label ChairBilletLabel = new Label("Chair number :"+b.getChairBillet());
        Label VoyageNumLabel = new Label("voyage number :"+b.getVoyageNum());
        Label TerminalLabel = new Label("Terminal :"+b.getTerminal());
        Label PortailLabel = new Label("Portail :"+b.getPortail());
        Label EmbarquementLabel = new Label("Embarquement :"+b.getEmbarquement());
        Label LocalisationLabel = new Label("localisation :"+b.getLocalisation());
       
        Button showButton = new Button("Reserver");
        showButton.addActionListener(evt -> {
           new AddReservationForm().show();
        });
        addAll(ChairBilletLabel, VoyageNumLabel, TerminalLabel,PortailLabel, EmbarquementLabel,  LocalisationLabel,showButton);
        /* *** *BACK BUTTON* *** */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
//        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
//            //SENDING EMAIL
//            Display.getInstance().sendMessage(new String[]{""}, "Let's participate this!", new Message("Check out this Ticket: " + b.getChairBillet() + " it's trip number: " + b.getVoyageNum() + " date "+b.getLocalisation()));
//        });
    }

}
