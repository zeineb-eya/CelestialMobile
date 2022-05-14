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
import com.mycompany.myapp.entities.Localisation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author skanr
 */
public class ServiceLocalisation {
    
    public ArrayList<Localisation> localisations;
    
     //singleton 
    public static ServiceLocalisation instance=null;
    public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;
    
    public ServiceLocalisation() {
         req = new ConnectionRequest();
    }
    public static ServiceLocalisation getInstance() {
        if (instance == null) {
            instance = new ServiceLocalisation();
        }
        return instance;
    }
    
     public boolean addLocalisation(Localisation o) {
        System.out.println(o);
       
      // String url = Statics.BASE_URL + "offre/ajoutOffrejson/" + o.getNomOffre()+"?description_offre=" + o.getDescriptionOffre() + "&prix_offre=" + o.getPrixOffre()+ "&reduction=" + o.getReduction()+ "&date_debut_offre=" + o.getDateDebutOffre()+ "&date_fin_offre=" + o.getDateFinOffre();
  String url = Statics.BASE_URL + "/localisation/ajoutLocalisationjson?positionDepart_localisation="+o.getPositionDepartLocalisation()+"&positionArivee_planning="+o.getPositionAriveePlanning()+"&fusee="+o.getFusee()+"&heureDepart_localisation="+o.getHeureDepartLocalisation()+"&heureArrivee_loacalisation="+o.getHeureArriveeLoacalisation();
    
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
     
         public ArrayList<Localisation> parseLocalisations(String jsonText){
        try {
            localisations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Localisation o = new Localisation();
                float id = Float.parseFloat(obj.get("id").toString());
                o.setId((int)id);
                
                o.setPositionDepartLocalisation(obj.get("positionDepart_localisation").toString());
                o.setPositionAriveePlanning(obj.get("positionArivee_planning").toString());
                o.setFusee(obj.get("fusee").toString());
                o.setHeureDepartLocalisation(obj.get("heureDepart_localisation").toString());
                o.setHeureArriveeLoacalisation(obj.get("heureArrivee_loacalisation").toString());
                
                localisations.add(o);
            }
            
            
        } catch (IOException ex) {
            
        }
        return localisations;
    }
   
      public ArrayList<Localisation> displayLocalisations(){
        
        String url = Statics.BASE_URL+"/localisation/AllLocalisations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                localisations = parseLocalisations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return localisations;
    }
      
      
      //delete
      
        public boolean deleteLocalisation(Localisation o) {
    
        String url = Statics.BASE_URL + "/localisation/deleteLocalisationjson?id="+o.getId();
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
         
      public boolean modifLocalisation(Localisation o) { 
              
        String url = Statics.BASE_URL + "/localisation/modifLocalisationjson/"+o.getId()+"?&positionDepart_localisation="+o.getPositionDepartLocalisation()+"&positionArivee_planning="+o.getPositionAriveePlanning()+"&fusee="+o.getFusee()+"&heureDepart_localisation="+o.getHeureDepartLocalisation()+"&heureArrivee_loacalisation="+o.getHeureArriveeLoacalisation();
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
      
      public Localisation DetailLocalisation(Localisation o) {
        String url = Statics.BASE_URL + "/localisation/detailLocalisationjson/"+o.getId();
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
             
                o.setPositionDepartLocalisation(obj.get("positionDepart_localisation").toString());
                o.setPositionAriveePlanning(obj.get("positionArivee_planning").toString());
                o.setFusee(obj.get("fusee").toString());
                o.setHeureDepartLocalisation(obj.get("heureDepart_localisation").toString());
                o.setHeureArriveeLoacalisation(obj.get("heureArrivee_loacalisation").toString());
              

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return o;
    }
      

}
