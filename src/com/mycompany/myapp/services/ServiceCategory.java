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
import com.mycompany.myapp.entities.CategorieEquipement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCategory {

    public ArrayList<CategorieEquipement> cat;

    public static ServiceCategory instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategory() {
        req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public boolean addTask(CategorieEquipement c) {
        System.out.println(c);
        System.out.println("********");
        String url = Statics.BASE_URL +"categorie/equipement/new?nom_categorie_equipement=" + c.getNom_categorie_equipement();
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

    public ArrayList<CategorieEquipement> parseTasks(String jsonText) {
        try {
            cat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                CategorieEquipement c = new CategorieEquipement();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                    c.setNom_categorie_equipement(obj.get("nomCategorieEquipement").toString());               
                cat.add(c);
            }
        } catch (IOException ex) {
        }
        return cat;
    }

    public ArrayList<CategorieEquipement> getAllTasks() {
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL + "categorie/equipement/AllCategories";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
             public boolean  Delete(CategorieEquipement c){
       String url = Statics.BASE_URL + "categorie/equipement/deletecategoryJSON/" +c.getId();
  
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
public boolean modifierCategorieEquipement(CategorieEquipement c) {
        String url = Statics.BASE_URL +"categorie/equipement/updatecategoryJSON/"+c.getId()+"?nom_categorie_equipement=" + c.getNom_categorie_equipement();
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
