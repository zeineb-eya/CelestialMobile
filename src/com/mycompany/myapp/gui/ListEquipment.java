/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipment;

/**
 *
 * @author bhk
 */
public class ListEquipment extends Form {

    public ListEquipment(Form previous) {
        setTitle("List Equipments");
               Container List = new Container (BoxLayout.y());
 for (Equipement E : ServiceEquipment.getInstance().getAllEquipments()) {
            MultiButton mb = new MultiButton(E.toString());
            System.out.println(E.getId());
        Button update = new Button("update");
        update.setUIID("update");
            update.addActionListener(e -> new ModifierEquipment(E,previous).show());
        Button delete = new Button("delete");
        delete.setUIID("delete");
           delete.addActionListener(e -> new DeleteEquipment(E,previous).show());
           add(mb);
           add(delete);
           add(update);
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceCategory.getInstance().getAllTasks().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }}}

//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceCategory.getInstance().getAllTasks().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());        
//}

