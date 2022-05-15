/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.CategorieEquipement;
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipment;

/**
 *
 * @author bhk
 */
public class AddEquipment extends Form{

    public AddEquipment(Form previous) {
        setTitle("Add a new Equipment");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Equipment name");
        TextField tfEtat = new TextField("","Equipment state");
        TextField tfDesc = new TextField("","Equipment description");
        TextField tfImg = new TextField("","Equipment image");
        TextField tfCat = new TextField("","Equipment Category");
        
        Button btnValider = new Button("Add Equipment");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)&&(tfDesc.getText().length()==0)&&(tfImg.getText().length()==0)&&(tfEtat.getText().length()==0)&&(tfCat.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Equipement e = new Equipement(tfNom.getText().toString(),tfEtat.getText().toString(),tfDesc.getText().toString(),tfImg.getText().toString(),tfCat.getText().toString());
                        if( ServiceEquipment.getInstance().addEquipment(e))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfEtat,tfDesc,tfImg,tfCat,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
