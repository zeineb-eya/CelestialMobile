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
import com.mycompany.myapp.entities.CategorieEquipement;
import com.mycompany.myapp.services.ServiceCategory;

/**
 *
 * @author 21628
 */
public class ModifierCategorieEquipement extends Form {
    
 Form current;

   public ModifierCategorieEquipement(CategorieEquipement c , Form previous) {
        setTitle("edit category");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(c.getId()), "Category id");
        TextField tfNom = new TextField(c.getNom_categorie_equipement(), "Category name");
        Button btnValider = new Button("edit Category");
        Button btnRet = new Button("Return");
        btnRet.addActionListener(e-> new HomeForm().show() );
        

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().length() == 0)  {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    
                        CategorieEquipement c = new CategorieEquipement(Integer.parseInt(id.getText()), tfNom.getText());
                        System.out.println(c.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceCategory.getInstance().modifierCategorieEquipement(c)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                   

                }

            }
            
        });
        

        addAll(id, tfNom,  btnValider,btnRet);
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.previous.showBack());

    }
}
