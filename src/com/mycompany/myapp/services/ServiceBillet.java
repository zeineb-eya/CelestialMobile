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
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HP
 */
public class ServiceBillet {
    public ArrayList<Billet> billets;
    
     //singleton 
    public static ServiceBillet instance=null;
    public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;
    
    public ServiceBillet() {
         req = new ConnectionRequest();
    }
    public static ServiceBillet getInstance() {
        if (instance == null) {
            instance = new ServiceBillet();
        }
        return instance;
    }
    
//    public ServiceBillet() {
//        req = new ConnectionRequest();
//    }

     public boolean addBillet(Billet b) {
        System.out.println(b);
        System.out.println("********");
        String url = Statics.BASE_URL + "/billet/AddBillets/json/" + b.getLocalisation()+"?chair_billet=" + b.getChairBillet() + "&voyage_num=" + b.getVoyageNum()+ "&terminal=" + b.getTerminal()+ "&portail=" + b.getPortail()+ "&embarquement=" + b.getEmbarquement()+ "&localisation=" + b.getLocalisation();

    
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
     
     public ArrayList<Billet> parseBillets(String jsonText){
        try {
            billets=new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Billet b = new Billet();
                float id = Float.parseFloat(obj.get("id").toString());
                b.setId((int)id);
                b.setChairBillet(((int)Float.parseFloat(obj.get("chair_billet").toString())));
                b.setVoyageNum(((int)Float.parseFloat(obj.get("voyage_num").toString())));
                b.setTerminal(((int)Float.parseFloat(obj.get("terminal").toString())));
                b.setPortail(((int)Float.parseFloat(obj.get("portail").toString())));
                //b.setEmbarquement(obj.get("embarquement").toString());
                
//                if (obj.get("embarquement")==null)
//                    b.setEmbarquement("null");
//                else
                b.setEmbarquement(obj.get("embarquement").toString());
//                float localisation = Float.parseFloat(obj.get("localisation").toString());
//                b.setLocalisation((int)localisation);
              // b.setLocalisation(((int)Float.parseFloat(obj.get("localisation").toString())));
                billets.add(b);
            }
            
            
        } catch (IOException ex) {
            
        }
        return billets;
    }
   
      public ArrayList<Billet> getAllBillets(){
        
        String url = Statics.BASE_URL+"/billet/AllBillets/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                billets = parseBillets(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return billets;
    }
      
      public boolean deleteBillet(Billet b) {
        String url = Statics.BASE_URL + "/billet/DeleteBillets/json/" + b.getId();
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
      
      public boolean updateBillet(Billet b) {
        String url = Statics.BASE_URL + "/billet/UpdateBillets/json/" + b.getId()+"/"+b.getLocalisation()+"?id=" + b.getId() +"&chair_billet=" + b.getChairBillet() + "&voyage_num=" + b.getVoyageNum()+ "&terminal=" + b.getTerminal()+ "&portail=" + b.getPortail()+ "&embarquement=" + b.getEmbarquement()+ "&localisation=" + b.getLocalisation();
//création de l'URL
       // String url = Statics.BASE_URL + "billet/UpdateBillets/json/" + b.getId();
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
      public Billet DetailBillet(Billet Billet) {
        String url = Statics.BASE_URL + "/billet/DetailBillets/json/" +  Billet.getId();
       req.setUrl(url);
        req.setPost(false);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                Billet.setVoyageNum((Integer.parseInt(obj.get("voyage_num").toString())));
                Billet.setTerminal((Integer.parseInt(obj.get("terminal").toString())));
                Billet.setPortail((Integer.parseInt(obj.get("portail").toString())));
                Billet.setEmbarquement(obj.get("embarquement").toString());
                //Billet.setLocalisation(((int)Float.parseFloat(obj.get("localisation").toString())));

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Billet;
    }
      
}

