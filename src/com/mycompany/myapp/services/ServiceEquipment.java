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
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEquipment {

    public ArrayList<Equipement> cat;

    public static ServiceEquipment instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEquipment() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipment getInstance() {
        if (instance == null) {
            instance = new ServiceEquipment();
        }
        return instance;
    }

    public boolean addEquipment(Equipement E) {
        System.out.println(E);
        System.out.println("********");
        String url = Statics.BASE_URL +"equipementt/AddEquipment?nom_equipement=" + E.getNom_equipement()+"&etat_equipement="+ E.getEtat_equipement()+"&description_equipement="+ E.getDescription_equipement()+"&categorieEquipement="+ E.getCategorie_equipement()+"&image_equipement="+ E.getImage_equipement();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);
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

    public ArrayList<Equipement> parseEquipments(String jsonText) {
        try {
            cat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> EquipmentsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) EquipmentsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Equipement E = new Equipement();
                float id = Float.parseFloat(obj.get("id").toString());
                E.setId((int) id);
                E.setNom_equipement(obj.get("nomEquipement").toString());
                E.setEtat_equipement(obj.get("etatEquipement").toString());
                E.setDescription_equipement(obj.get("descriptionEquipement").toString());
                E.setCategorie_equipement(obj.get("categorieEquipement").toString());
                E.setImage_equipement(obj.get("imageEquipement").toString());
                
    
                cat.add(E);
            }
        } catch (IOException ex) {
        }
        return cat;
    }

    public ArrayList<Equipement> getAllEquipments() {
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL + "equipementt/AllEquipments";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseEquipments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
             public boolean  Delete(Equipement E){
       String url = Statics.BASE_URL + "equipementt/deleteEquipmentJSON/" +E.getId();
  
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

      
}
public boolean modifierCategorieEquipement(Equipement E) {
        String url = Statics.BASE_URL +"equipementt/updateEquipmentJSON/"+E.getId()+"?nom_equipement="+ E.getNom_equipement()+"&etat_equipement="+ E.getEtat_equipement()+"&description_equipement="+ E.getDescription_equipement()+"&categorieEquipement="+ E.getCategorie_equipement()+"&image_equipement="+ E.getImage_equipement();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
          
}
