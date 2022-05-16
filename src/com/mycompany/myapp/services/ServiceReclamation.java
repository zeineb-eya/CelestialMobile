/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> reclamations;
    
     //singleton 
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;
    
    public ServiceReclamation() {
         req = new ConnectionRequest();
    }
    
      public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
      
            public boolean addReclamation(Reclamation r) {
        System.out.println(r);
      // String url = Statics.BASE_URL + "offre/ajoutOffrejson/" + o.getNomOffre()+"?description_offre=" + o.getDescriptionOffre() + "&prix_offre=" + o.getPrixOffre()+ "&reduction=" + o.getReduction()+ "&date_debut_offre=" + o.getDateDebutOffre()+ "&date_fin_offre=" + o.getDateFinOffre();
  String url = Statics.BASE_URL + "/reclamation/ajoutReclamationjson/1?description_reclamation="+r.getDescriptionReclamation()+"&user="+r.getUser()+"&date_reclamation"+r.getDateReclamation();
    
       req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
            
            //Affichage des reclam
     
         public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                
               /* float etat_reclamation = Float.parseFloat(obj.get("etat_reclamation").toString());
                r.setEtatReclamation((int)etat_reclamation);*/
                
                r.setDescriptionReclamation(obj.get("description_reclamation").toString());
                
                 if(r.getEtatReclamation() == "" ) {
            r.setEtatReclamation("non Traitée");
        }
        else 
            r.setEtatReclamation("Traitée");
         // r.setEtatReclamation(obj.get("etat_reclamation").toString());
                r.setDateReclamation(obj.get("date_reclamation").toString());
             
                reclamations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
         
          public ArrayList<Reclamation> displayReclamations(){
        
        String url = Statics.BASE_URL+"/reclamation/AllReclamations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
      
  //delete reclam
      
        public boolean deleteReclamation(Reclamation r) {

        String url = Statics.BASE_URL + "/reclamation/deleteReclamationjson/"+r.getId();
        //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
    
         //update reclam
         
      public boolean modifReclamation(Reclamation r) { 
           String url = Statics.BASE_URL + "/reclamation/modifReclamationjson/"+r.getId()+"?&description_reclamation="+r.getDescriptionReclamation();
   
      // String url = Statics.BASE_URL + "/reclamation/modifReclamationjson/" +"id="+r.getId()+"/"+"?description_reclamation="+r.getDescriptionReclamation();
//création de l'URL
      
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
    //detail reclam
     /*  public Reclamation DetailReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/detailReclamationjson/"+r.getId();
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
             
                r.setDescriptionReclamation(obj.get("description_reclamation").toString());
                r.setDateReclamation(obj.get("date_reclamation").toString());
                r.setEtatReclamation(obj.get("etat_reclamation").toString());
               
              

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
    }*/
      
}

