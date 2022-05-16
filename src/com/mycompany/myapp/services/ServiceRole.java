/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Role;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Cipher
 */
public class ServiceRole {
    public ArrayList<Role> Role;
    
    public static ServiceRole instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRole() {
         req = new ConnectionRequest();
    }

    public static ServiceRole getInstance() {
        if (instance == null) {
            instance = new ServiceRole();
        }
        return instance;
    }

    public boolean addRole(Role t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/role/Addroles/json?nom_role="+t.getNom_role()+"&description_role="+t.getDescription_role();
    //statics une classe java 

       req.setUrl(url);
       //nebaath requete bch nahki maa serveur
       req.setPost(false); //nekhdmou bel getmch post automatique yemchi bel post 
       req.addArgument("nom_role", t.getNom_role());
       req.addArgument("description_role", t.getDescription_role());
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //recuperer le resultat 
        //wait estana el reponse w baed taada lel instruction ejeya
        return resultOK;
    }

    public ArrayList<Role> parseRoles(String jsonText){
        try {
            Role=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> RoleListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)RoleListJson.get("root");
            for(Map<String,Object> obj : list){
                Role t = new Role();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               //t.setNom_utilisateur(obj.get("nom_utilisateur").toString());               

              
              t.setDescription_role(obj.get("description_role").toString());
               if (obj.get("description_role")==null)
              t.setDescription_role("null");
                else
 t.setNom_role(obj.get("nom_role").toString());
                 if (obj.get("nom_role")==null)
              t.setNom_role("null");
               
                //t.setNom_utilisateur(obj.get("nom_utilisateur").toString());               
                //t.setAdresse_utilisateur(obj.get("adresse_utilisateur").toString());
               //t.setNumero_utilisateur(obj.get("Numero_utilisateur").toString());
                //t.setPassword(obj.get("password").toString());
               
          //    String email= obj.get("mail_utilisateur").toString() ;
            //    t.setMail_utilisateur(email);
                //String password = obj.get("password").toString();
                //t.setPassword(password);
               // String Telephone= obj.get("Numero_utilisateur").toString() ;
                //t.setNumero_utilisateur(Telephone);
               // String Sudo= obj.get("sudo_utilisateur").toString() ;
             // t.setSudo_utilisateur(Sudo);
//                 String Adresse= obj.get("adresse_utilisateur").toString() ;
//                t.setNumero_utilisateur(Adresse);              
//                 String Prenom= obj.get("prenom_utilisateur").toString() ;
//                t.setNumero_utilisateur(Prenom);
//                 String nom= obj.get("nom_utilisateur").toString() ;
//                t.setNom_utilisateur(nom);
                
                Role.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Role;
    }
   public ArrayList<Role> getAllRoles(){
        //String url = Statics.BASE_URL+"/tasks/";
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/role/Allroles/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Role= parseRoles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Role;
    }
 public boolean UpdateRole(Role u) {
        String url = Statics.BASE_URL + "/role/updateRoleJSON/"+ u.getId() +"?nom_role=" + u.getNom_role()  + "&description_role=" + u.getDescription_role();
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
  public boolean deleteRole(Role u) {
        String url = Statics.BASE_URL + "/role/deleteRoleJSON/"+ u.getId() ;
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
}
