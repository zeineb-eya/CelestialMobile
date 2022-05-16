/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipment;

/**
 *
 * @author 21628
 */
public class ModifierEquipment extends Form {
    
 Form current;

   public ModifierEquipment(Equipement ee , Form previous) {
        setTitle("edit category");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(ee.getId()), "Equipment id");
        TextField tfNom = new TextField("","Equipment name");
        TextField tfEtat = new TextField("","Equipment state");
        TextField tfDesc = new TextField("","Equipment description");
        TextField tfImg = new TextField("","Equipment image");
        TextField tfCat = new TextField("","Equipment Category");
        Button btnValider = new Button("edit Category");
        Button btnRet = new Button("Return");
        btnRet.addActionListener(e-> new HomeForm().show() );
        

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().length() == 0)  {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Equipement ee = new Equipement(Integer.parseInt(id.getText()), tfNom.getText(), tfEtat.getText(), tfDesc.getText(), tfImg.getText(), tfCat.getText());
                        System.out.println(ee.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceEquipment.getInstance().modifierCategorieEquipement(ee)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
            
        });
        addAll(id, tfNom,  btnValider,btnRet);
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.previous.showBack());

    }
}
