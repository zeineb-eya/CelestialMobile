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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;
import com.mycompany.myapp.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Cipher
 */
public class ServiceUser {
     public ArrayList<User> User;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
}
  public boolean addUsers(User u) {
        System.out.println(u);
        System.out.println("********");
        String url = Statics.BASE_URL + "/AddUsers/json?nom_utilisateur=" + u.getNom_utilisateur() + "&Numero_utilisateur" + u.getNumero_utilisateur()+ "&adresse_utilisateur=" + u.getAdresse_utilisateur() + "&prenom_utilisateur=" + u.getNom_utilisateur() + "&prenom_utilisateur=" + u.getPrenom_utilisateur()+"&mail_utilisateur=" + u.getMail_utilisateur()+"&sudo_utilisateur=" +u.getSudo_utilisateur()+"&password="+u.getPassword();
      // String url = Statics.BASE_URL + "create";
        System.out.println("********");
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("nom_utilisateur", u.getNom_utilisateur());
       req.addArgument("prenom_utilisateur", u.getPrenom_utilisateur());
              req.addArgument("adresse_utilisateur", u.getAdresse_utilisateur());
    req.addArgument("Numero_utilisateur", u.getNumero_utilisateur());
        req.addArgument("sudo_utilisateur", u.getSudo_utilisateur());
                req.addArgument("mail_utilisateur", u.getMail_utilisateur());
                        req.addArgument("password", u.getPassword());





//       req.addArgument("username", u.getUsername());
//       req.addArgument("email", u.getEmail()+"");
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
  
   public ArrayList<User> parseUsers(String jsonText){
        try {
            User=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UserListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UserListJson.get("root");
            for(Map<String,Object> obj : list){
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               //t.setNom_utilisateur(obj.get("nom_utilisateur").toString());               

              
              t.setMail_utilisateur(obj.get("mail_utilisateur").toString());
               if (obj.get("sudo_utilisateur")==null)
              t.setSudo_utilisateur("null");
                else
 t.setSudo_utilisateur(obj.get("sudo_utilisateur").toString());
                 if (obj.get("nom_utilisateur")==null)
              t.setNom_utilisateur("null");
                else
 t.setNom_utilisateur(obj.get("nom_utilisateur").toString());
                  if (obj.get("prenom_utilisateur")==null)
              t.setPrenom_utilisateur("null");
                else
 t.setPrenom_utilisateur(obj.get("prenom_utilisateur").toString());
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
                
                User.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return User;
    }
   public ArrayList<User> getAllUsers(){
        //String url = Statics.BASE_URL+"/tasks/";
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/AllUsers/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                User = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }
    public boolean deleteUser(User u) {
        String url = Statics.BASE_URL + "/deleteUserJSON/"+ u.getId() ;
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
      public boolean UpdateUser(User u) {
        String url = Statics.BASE_URL + "/updateUserJSON/"+ u.getId() +"?nom_utilisateur=" + u.getNom_utilisateur()  + "&prenom_utilisateur=" + u.getPrenom_utilisateur()+"&mail_utilisateur=" + u.getMail_utilisateur();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                               System.out.println("User mmodifié");

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
      public void signin(TextField email,TextField password,Resources rs) {
        
        
        String url = Statics.BASE_URL+"/loginUser/json?mail_utilisateur="+email.getText().toString()+"&password="+password.getText().toString();
       
              req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                 System.out.println(user.size());
                //Session 

                float id = Float.parseFloat(user.get("id").toString());
                 SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setNom_utilisateur(user.get("nomUtilisateur").toString());
                SessionManager.setPrenom_utilisateur(user.get("prenomUtilisateur").toString());
                SessionManager.setMail_utilisateur(user.get("username").toString());
                SessionManager.setPassword(user.get("password").toString());
               if(user.size() >0 ) // l9a user
               //      System.out.println("wlh mata5dem");
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new ProfileForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                System.out.println("erreur");
                
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
                
               
                    
                

}
