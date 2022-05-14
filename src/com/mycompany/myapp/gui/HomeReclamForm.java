/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;

/**
 *
 * @author HP
 */
public class HomeReclamForm extends Form {
    Reclamation r;
    Resources res;
    
    public HomeReclamForm(Form current) {
    // current=this; //Back
        setTitle("Reclamations");
        setLayout(BoxLayout.y());
        
        
     Button btnAddReclamation = new Button("Réclamer");
    Button btnListReclamations = new Button("Consulter les reclamations");
      
       Button btnStatReclam = new Button("Statistiques des réclamations");
    
        
     btnAddReclamation.addActionListener(e-> new AjoutReclamForm().show());
     
     btnListReclamations.addActionListener(e-> new ListReclamForm(current));
   btnStatReclam.addActionListener(e-> new StatistiqueReclamForm(current).show());
      addAll(btnAddReclamation, btnListReclamations,btnStatReclam);
          
     
     
    
}
    
}


