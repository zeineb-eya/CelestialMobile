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
import com.mycompany.myapp.entities.CategorieEquipement;
import com.mycompany.myapp.services.ServiceCategory;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    public ListTasksForm(Form previous) {
        setTitle("List category");
               Container List = new Container (BoxLayout.y());
 for (CategorieEquipement c : ServiceCategory.getInstance().getAllTasks()) {
            MultiButton mb = new MultiButton(c.toString());
            System.out.println(c.getId());
        Button update = new Button("update");
        update.setUIID("update");
            update.addActionListener(e -> new ModifierCategorieEquipement(c,previous).show());
        Button delete = new Button("delete");
        delete.setUIID("delete");
           delete.addActionListener(e -> new DeleteCategorieEquipement(c,previous).show());
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

