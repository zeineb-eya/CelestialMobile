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
import com.mycompany.myapp.entities.Offre;

/**
 *
 * @author HP
 */
public class HomeOffreForm extends Form {
   Offre o;
    Resources res;
    
    public HomeOffreForm(Form current) {
    // current=this; //Back
        setTitle("Offres");
        setLayout(BoxLayout.y());
        
        
     Button btnAddOffre = new Button("Ajouter une offre");
     Button btnListOffres = new Button("Consulter les offres");
    // Button btntriOffreByReductionA = new Button("Ordered By Date");
    
    Button btnTriOffreReductionA = new Button("Offres");
    // Button triOffreDateDebutA = new Button("Offres");
    
    btnTriOffreReductionA.addActionListener(e-> new triOffreByDateDebutAForm().show());
   //  btnTriOffreReductionA.addActionListener(e-> new triOffreByDateFinAForm().show());
       
     btnAddOffre.addActionListener(e-> new AjoutOffreForm().show());
      btnListOffres.addActionListener(e-> new ListOffreForm(current));
      addAll(btnAddOffre,btnListOffres,btnTriOffreReductionA);
          
     
     
    
}
}  

