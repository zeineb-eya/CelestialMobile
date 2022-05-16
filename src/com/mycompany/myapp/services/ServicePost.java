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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author khawl
 */
public class ServicePost {
 public ArrayList<Post> Post;
    public ArrayList<Post> posts;
    public static ServicePost instance = null;
    private ConnectionRequest req;

    Resources res;
    public static boolean resultOk = true;

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;

    }

    public ServicePost() {
        req = new ConnectionRequest();
    }

    public ArrayList<Post> parseTasks(String jsonText) {
        try {
            posts = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return posts;
    }

    public ArrayList<Post> getAllPosts() {
        String url = Statics.BASE_URL + "/post/Allposts/json";
      req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Post = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Post;
            
    }

    public boolean deletePost(int id) {
        String url = Statics.BASE_URL + "/post/delete/post/json?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean modifierPost(Post post) {
        String url = Statics.BASE_URL + "/post/edit/post/json/" + post.getId() + "?nom=" + post.getNom() + "&description=" + post.getDescription_post() + "&image=" + post.getImg_post();
        // String url =null;  
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

    public void activate(Post c) {

        String url = Statics.BASE_URL + "/cartefidelite/activate/carte?id=" + c.getId();

        req.setUrl(url);

        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData());
            if (new String(req.getResponseData()).equals(new String('"' + "false" + '"'))) {
                System.out.println("Carte ne peut pas etre  activé");
                Dialog.show("Period of activation is not terminated", "Date expiration encore valable", null);

            } else {
                System.out.println("Carte activé");
                Dialog.show("periode renouvellé", "Date expiration a été renouvellé par +3ans", null);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public boolean addPost(Post u) {
        String url = Statics.BASE_URL + "/post/AddPosts/json?nom=" + u.getNom() + "&description_post=" + u.getDescription_post() + "&img_post=" + u.getImg_post();

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

    ///metier traj3 commentaire par post relation fk post et commentaire 
    public ArrayList<Commentaire> getAllCommentaire(Post p) {
        String url = Statics.BASE_URL + "/commentaire/Allcommentaires/post/json?id=" + p.getId();

        ArrayList<Commentaire> result = new ArrayList<>();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("commentaire");
                    System.out.println("lissst" + mapReclamations);
                    ///retour json 
                    for (Map<String, Object> obj : listOfMaps) {
                        Commentaire re = new Commentaire();

                        String message = obj.get("msgCommentaire").toString();
                        String dateCommentaire = obj.get("dateCommentaire").toString();

                        float id = Float.parseFloat(obj.get("id").toString());
                        re.setId((int) id);
                        re.setMsg_commentaire(message);
                        re.setDate_commentaire(dateCommentaire);
                        re.setPost(p);
                        result.add(re);
                        System.out.println("data " + obj.get("msgCommentaire").toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
   public ArrayList<Post> parseUsers(String jsonText){
        try {
            Post=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UserListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UserListJson.get("root");
            for(Map<String,Object> obj : list){
                Post t = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               //t.setNom_utilisateur(obj.get("nom_utilisateur").toString());               

              
              t.setNom(obj.get("nom").toString());
               if (obj.get("nom")==null)
              t.setNom("null");
                else
 t.setDescription_post(obj.get("description_post").toString());
                 if (obj.get("description_post")==null)
              t.setDescription_post("null");
                else
 t.setImg_post(obj.get("img_post").toString());
                  if (obj.get("img_post")==null)
              t.setImg_post("null");
                 Post.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Post;
    }


}
