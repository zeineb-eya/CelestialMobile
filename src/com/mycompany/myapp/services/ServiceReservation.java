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
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HP
 */
public class ServiceReservation {
     public ArrayList<Reservation> reservations;
    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
     /*private ServiceReservation() {
         req = new ConnectionRequest();
    }*/
          public ServiceReservation() {
         req = new ConnectionRequest();
    }
     public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
     
     public boolean addReservation(Reservation r) {
        System.out.println(r);
        System.out.println("********");
     
      // String url = Statics.BASE_URL + "reservation/AddReservations/json/"+r.getUser()+"/"+r.getBillet()+"?date_reservation=" + r.getDateReservation()+"&Etat_reservation=" + r.getEtatReservation()+ "&user=" + r.getUser()+ "&billet=" + r.getBillet();
       String url = Statics.BASE_URL + "/reservation/AddReservations/json/"+r.getUser()+"/"+r.getBillet();
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
     
     public ArrayList<Reservation> parseReservations(String jsonText){
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reservation r = new Reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                r.setDateReservation(obj.get("date_reservation").toString());
                r.setEtatReservation(obj.get("Etat_reservation").toString());
               // r.setUser(((int)Float.parseFloat(obj.get("user").toString())));
               // r.setBillet(((int)Float.parseFloat(obj.get("billet").toString())));
                reservations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }
   //temchi
      public ArrayList<Reservation> getAllReservations(){
        //String url = Statics.BASE_URL+"/billets/";
        String url = Statics.BASE_URL+"/reservation/AllReservations/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
      //temchi
        public boolean deleteReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/DeleteReservations/json/" + r.getId();
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
      
      public boolean updateReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/UpdateReservations/json/" + r.getId()+"/"+r.getUser()+"/"+r.getBillet()+"?id=" + r.getId() +"&user=" + r.getUser()+ "&billet=" + r.getBillet();
       // String url = Statics.BASE_URL + "reservation/UpdateReservations/json/" + r.getId()+"/"+r.getUser()+"/"+r.getBillet()+"?id=" + r.getId() +"&date_reservation=" + r.getDateReservation()+"&Etat_reservation=" + r.getEtatReservation();
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
      public Reservation DetailReservation(Reservation Reservation) {
        String url = Statics.BASE_URL + "/reservation/DetailReservations/json/" +  Reservation.getId()+ "/"+Reservation.getUser();
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                Reservation.setDateReservation((obj.get("date_reservation").toString()));
                Reservation.setEtatReservation((obj.get("Etat_reservation").toString()));
//                Reservation.setUser(((int)Float.parseFloat(obj.get("user").toString())));
//                Reservation.setBillet(((int)Float.parseFloat(obj.get("Billet").toString())));
              
              

            } catch (IOException ex) {
                System.out.println("error related to sql :" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservation;
    }
    public ArrayList<Reservation> showOrderedByDate() {
        String url = Statics.BASE_URL + "/reservation/listReservationByDate/api/showOrdered"; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    public ArrayList<Reservation> showOrderedByMail() {
        String url = Statics.BASE_URL + "/reservation/listReservationByMail/api/showOrdered"; // Add Symfony URL Here
       req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    public ArrayList<Reservation> showOrderedByEtat() {
        String url = Statics.BASE_URL + "/reservation/listReservationByEtat/api/showOrdered"; // Add Symfony URL Here
       req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
     public boolean confirmeReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/traiterreservation/api/" + r.getId()+"/"+ r.getUser()+"/"+ r.getBillet();
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
    public boolean cancelReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/cancelreservation/api/" + r.getId()+"/"+ r.getUser()+"/"+ r.getBillet();
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
//       public boolean sendMail(int id) {
//
//        String url = Statics.BASE_URL +"reservation/" + "ssendMailReservations/json?id=" + id;
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl(url);
//        con.addResponseListener((NetworkEvent evt) -> {
//            ServiceReservation ser = new ServiceReservation();
//            response = new String(con.getResponseData());
//            System.out.println("response : " + response);
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return response.contains("email sent");
//    }
}
