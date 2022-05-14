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

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.AddBilletForm;
import com.mycompany.myapp.gui.ListBilletForm;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.HomeReservationForm;
import com.mycompany.myapp.gui.HomePlaninngForm;
import com.mycompany.myapp.gui.HomeLocalisationForm;
import com.mycompany.myapp.gui.HomeOffreForm;
import com.mycompany.myapp.gui.HomeReclamForm;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
Form current;
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
         current=this; //Back 
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Jennifer Wilson", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Planning", FontImage.MATERIAL_TRENDING_UP,  e -> new HomePlaninngForm(current).show());
        getToolbar().addMaterialCommandToSideMenu("  locations", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeLocalisationForm(current).show());
        getToolbar().addMaterialCommandToSideMenu("  Billet", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeForm(current).show());
        getToolbar().addMaterialCommandToSideMenu("  Reservation", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeReservationForm(current).show());
         //getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_TRENDING_UP, e -> showOtherForm(res));
  //  getToolbar().addMaterialCommandToSideMenu("  Reservation", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeReclamForm(current).show());
  getToolbar().addMaterialCommandToSideMenu("  Offre", FontImage.MATERIAL_TRENDING_UP,  e -> new HomeOffreForm(current).show());
        //getToolbar().addMaterialCommandToSideMenu("  Equipements", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
