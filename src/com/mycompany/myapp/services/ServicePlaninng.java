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
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author skanr
 */
public class ServicePlaninng {
    
    public ArrayList<Planinng> planinngs;
    
     //singleton 
    public static ServicePlaninng instance=null;
    public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;
    
    public ServicePlaninng() {
         req = new ConnectionRequest();
    }
    public static ServicePlaninng getInstance() {
        if (instance == null) {
            instance = new ServicePlaninng();
        }
        return instance;
    }
    
     public boolean addPlaninng(Planinng o) {
        System.out.println(o);
       
      // String url = Statics.BASE_URL + "offre/ajoutOffrejson/" + o.getNomOffre()+"?description_offre=" + o.getDescriptionOffre() + "&prix_offre=" + o.getPrixOffre()+ "&reduction=" + o.getReduction()+ "&date_debut_offre=" + o.getDateDebutOffre()+ "&date_fin_offre=" + o.getDateFinOffre();
  String url = Statics.BASE_URL + "/planinng/ajoutPlaninngjson?nom_planning="+o.getNomPlaninng()+"&description_planning="+o.getDescriptionPlaninng()+"&destination_planning="+o.getDestinationPlaninng()+"&prix_planning="+o.getPrixPlaninng()+"&periode_planning="+o.getPeriodePlaninng()+"&dateDebut_planning"+o.getDateDebutPlaninng()+"&dateFin_planning"+o.getDateFinPlaninng();
    
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
     
     
     //Affichage des offres
     
         public ArrayList<Planinng> parsePlaninngs(String jsonText){
        try {
            planinngs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Planinng o = new Planinng();
                float id = Float.parseFloat(obj.get("id").toString());
                o.setId((int)id);
                
                o.setNomPlaninng(obj.get("nom_planning").toString());
                o.setDescriptionPlaninng(obj.get("description_planning").toString());
                o.setDestinationPlaninng(obj.get("destination_planning").toString());
                o.setPrixPlaninng(((int)Float.parseFloat(obj.get("prix_planning").toString())));
                o.setPeriodePlaninng(((int)Float.parseFloat(obj.get("periode_planning").toString())));
                o.setDateDebutPlaninng(obj.get("dateDebut_planning").toString());
                o.setDateFinPlaninng(obj.get("dateFin_planning").toString());
               
                planinngs.add(o);
            }
            
            
        } catch (IOException ex) {
            
        }
        return planinngs;
    }
   
      public ArrayList<Planinng> displayPlaninngs(){
        
        String url = Statics.BASE_URL+"/planinng/AllPlaninngs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                planinngs = parsePlaninngs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return planinngs;
    }
      
      
      //delete
      
        public boolean deletePlaninng(Planinng o) {
    
        String url = Statics.BASE_URL + "/planinng/deletePlaninngjson?id="+o.getId();
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
        
        //update
         
      public boolean modifPlaninng(Planinng o) { 
              
        String url = Statics.BASE_URL + "/planinng/modifPlaninngjson/"+o.getId()+"?&nom_planning="+o.getNomPlaninng()+"&description_planning="+o.getDescriptionPlaninng()+"&destination_planning="+o.getDestinationPlaninng()+"&prix_planning="+o.getPrixPlaninng()+"&periode_planning="+o.getPeriodePlaninng()+"&dateDebut_planning"+o.getDateDebutPlaninng()+"&dateFin_planning"+o.getDateFinPlaninng();
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
      
      //detail offre
      
      public Planinng DetailPlaninng(Planinng o) {
        String url = Statics.BASE_URL + "/planinng/detailPlaninngjson/"+o.getId();
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
             
               o.setNomPlaninng(obj.get("nom_planning").toString());
                o.setDescriptionPlaninng(obj.get("description_planning").toString());
                o.setDestinationPlaninng(obj.get("destination_planning").toString());
                o.setPrixPlaninng(((int)Float.parseFloat(obj.get("prix_planning").toString())));
                o.setPeriodePlaninng(((int)Float.parseFloat(obj.get("periode_planning").toString())));
                o.setDateDebutPlaninng(obj.get("dateDebut_planning").toString());
                o.setDateFinPlaninng(obj.get("dateFin_planning").toString());
              

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return o;
    }
      public ArrayList<Planinng> sortPlaninngdatedebut() {
        String url = Statics.BASE_URL + "/planinng/listPlanByDate"; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                planinngs = parsePlaninngs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return planinngs;
    }
      

}
