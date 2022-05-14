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

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ListBilletForm;
import com.mycompany.myapp.gui.HomeBilletFrontForm;
import com.mycompany.myapp.gui.HomeReservationForm;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceBillet;
/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {
    Form current;
    public WalkthruForm(Resources res) {
        super(new LayeredLayout());
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        
        Image notes = res.getImage("notes.png");
        Image duke = res.getImage("duke.png");
        
        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label("Planning", "WalkthruWhite"),
                new SpanLabel("Never miss an appointment, never forget about your " +
                                            "daily team meeting and remember when your favorite " +
                                            "team is playing.",  "WalkthruBody"),
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");
        
        walkthruTabs.addTab("", tab1);
//        Label bottomSpaceTab2 = new Label();
//        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
//                notesPlaceholder,
//                new Label("locations", "WalkthruWhite"),
//                new SpanLabel("Never miss an appointment, never forget about your " +
//                                            "daily team meeting and remember when your favorite " +
//                                            "team is playing.",  "WalkthruBody"),
//                bottomSpaceTab2
//        ));
//        tab2.setUIID("WalkthruTab2");
//        
//        walkthruTabs.addTab("", tab2);
        
        Label bottomSpaceTab3 = new Label();
        
        Container tab3 = BoxLayout.encloseY(
                new Label("Billet", "WalkthruWhite"),
                new HomeBilletFrontForm(current),
                bottomSpaceTab3
                
        );
        
        tab3.setUIID("WalkthruTab3");

        walkthruTabs.addTab("", tab3);
        
         Label bottomSpaceTab4 = new Label();
        
        Container tab4 = BoxLayout.encloseY(
                new Label("Reservation", "WalkthruWhite"),
                new HomeReservationForm(current),
                bottomSpaceTab4
                
        );
        
        tab4.setUIID("WalkthruTab4");

        walkthruTabs.addTab("", tab4);
//         Label bottomSpaceTab5 = new Label();
//        
//        Container tab5 = BoxLayout.encloseY(
//                new Label("Reclamation", "WalkthruWhite"),
//                //new HomeForm(current),
//                bottomSpaceTab5
//                
//        );
//        
//        tab5.setUIID("WalkthruTab5");
//
//        walkthruTabs.addTab("", tab5);
//       
//         Label bottomSpaceTab6 = new Label();
//        
//        Container tab6 = BoxLayout.encloseY(
//                new Label("Offers", "WalkthruWhite"),
//                //new HomeForm(current),
//                bottomSpaceTab6
//                
//        );
//        
//        tab6.setUIID("WalkthruTab6");
//
//        walkthruTabs.addTab("", tab6);
//        
//         Label bottomSpaceTab7 = new Label();
//        
//        Container tab7 = BoxLayout.encloseY(
//                new Label("Equipements", "WalkthruWhite"),
//                //new HomeForm(current),
//                bottomSpaceTab7
//                
//        );
//        
//        tab7.setUIID("WalkthruTab7");
//
//        walkthruTabs.addTab("", tab7);
//        
//         Label bottomSpaceTab8 = new Label();
//        
//        Container tab8 = BoxLayout.encloseY(
//                new Label("Blog", "WalkthruWhite"),
//                //new HomeForm(current),
//                bottomSpaceTab8
//                
//        );
//        
//        tab8.setUIID("WalkthruTab8");
//
//        walkthruTabs.addTab("", tab8);

      
      
        add(walkthruTabs);
        
        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Button skip = new Button("SKIP TUTORIAL");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new ProfileForm(res).show());
        
        Container southLayout = BoxLayout.encloseY(
                        radioContainer,
                        skip
                );
        add(BorderLayout.south(
                southLayout
        ));
        
        Component.setSameWidth(bottomSpace,/* bottomSpaceTab2,*/bottomSpaceTab3, bottomSpaceTab4/*, bottomSpaceTab5, bottomSpaceTab6, bottomSpaceTab7, bottomSpaceTab8*/, southLayout);
        Component.setSameHeight(bottomSpace/*, bottomSpaceTab2*/,bottomSpaceTab3, bottomSpaceTab4/*, bottomSpaceTab5, bottomSpaceTab6, bottomSpaceTab7, bottomSpaceTab8*/, southLayout);
        
        // visual effects in the first show
        addShowListener(e -> {
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
        });
    }    
}
