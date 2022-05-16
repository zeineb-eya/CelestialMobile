/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.gui.SessionManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.UpdateUserForm;
import com.mycompany.myapp.services.ServiceUser;

/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        
    
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
   
      
      int id = SessionManager.getId();
          System.out.println(id+"yes");
        String nom = SessionManager.getNom_utilisateur();
        String prenom = SessionManager.getPrenom_utilisateur();
        String pseudo = SessionManager.getSudo_utilisateur();
        String email = SessionManager.getMail_utilisateur();
        String password = SessionManager.getPassword();

       TextField username = new TextField(nom);
        username.setUIID("TextFieldBlack");
        addStringValue("Nom", username);
        TextField prenom1 = new TextField(prenom);
        prenom1.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom1);
         TextField pseudo1 = new TextField(pseudo);
        pseudo1.setUIID("TextFieldBlack");
        addStringValue("Peseudo", pseudo1);
        TextField email1 = new TextField(email);
        email1.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email1);
        
        TextField password1 = new TextField(password,"password",20,TextField.PASSWORD);
        password1.setUIID("TextFieldBlack");
        addStringValue("Password", password1);
       
       FloatingActionButton btnModifier = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
       btnModifier.setUIID("Button");
          btnModifier.addPointerPressedListener(l ->   { 
           
          SessionManager.setId((int)id);
           SessionManager.setNom_utilisateur(nom);     
            SessionManager.setPrenom_utilisateur(prenom); 
            SessionManager.setSudo_utilisateur(pseudo); 
            SessionManager.setMail_utilisateur(email); 
            SessionManager.setPassword(password); 
           
       User u = new User(id,
                  String.valueOf(nom).toString(),
                  String.valueOf(prenom).toString(),
                  String.valueOf(pseudo).toString(),
                  String.valueOf(email).toString(),
                  String.valueOf(password).toString()
                                 ); 
       //appel fonction modfier reclamation men articles
       
       if(ServiceUser.getInstance().UpdateUser(u)) { // if true
           new UpdateUserForm(u,current).show();
       }
      
         

       add(btnModifier);
        show();
    });
        
                    Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label(prenom, "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label(nom, "CenterSubTitle")
        );
        completedTasks.setUIID(nom);

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY( 
                                        new Label(nom+prenom, "Title"),
                                    new Label("UI/UX Designer", "SubTitle")
                                ) 
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
       btnModifier.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        btnModifier.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - btnModifier.getPreferredH() / 2);
        tb.setTitleComponent(btnModifier.bindFabToContainer(titleCmp, CENTER, BOTTOM));  
        setupSideMenu(res);
        }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
      
    }
}
