/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.whiteboardapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author fabioespinosa
 */
@ServerEndpoint(value="/whiteboardendpoint/{user}", 
        encoders = {FigureEncoder.class}, 
        decoders = {FigureDecoder.class})
public class MyWhiteboard {
    
    public static ArrayList<String> usuarios = new ArrayList<String>();
    public static ArrayList<Set> sesiones = new ArrayList<Set>();
    //private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
            
    @OnMessage
    public void broadcastFigure(@PathParam("user")String user, Figure figure, Session session) throws IOException, EncodeException {
        
        for(int i=0;i<usuarios.size();i++){
            String actual =(String)usuarios.get(i);
            if(actual.equals(user)){
                
                Set<Session> coleccion = sesiones.get(i);
                System.out.println("broadcastFigure, to: "+ user);
                for (Session peer : coleccion) {
                    if (!peer.equals(session)) {
                        peer.getBasicRemote().sendObject(figure);
                    }
                }
                
            }
        }
    }

    @OnOpen
    public void onOpen(@PathParam("user")String user, Session peer) throws IOException, EncodeException {
        for(int i=0;i<usuarios.size();i++){
            String actual =(String)usuarios.get(i);
            if(actual.equals(user)){
                System.out.println("MATCH");
                sesiones.get(i).add(peer);
                return;
            }
        }
        usuarios.add(user);
        Set<Session> peersUser = Collections.synchronizedSet(new HashSet<Session>());
        peersUser.add(peer);
        sesiones.add(peersUser);
        System.out.println("tamano "+user);
        //Envia los usuarios conectados:
        if(usuarios.size()>0)
        peer.getBasicRemote().sendObject(usuarios.toString());
    }

    @OnError
    public void onError(Throwable t) {
    }

    @OnClose
    public void onClose(@PathParam("user")String user, Session peer) throws IOException {
        for(int i=0;i<usuarios.size();i++){
            String actual =(String)usuarios.get(i);
            if(actual.equals(user)){
                sesiones.get(i).remove(peer);
            }
        }
    }
    
}







//@ServerEndpoint(value="/whiteboardendpoint/{user}", 
//        encoders = {FigureEncoder.class}, 
//        decoders = {FigureDecoder.class})
//public class MyWhiteboard {
//   
//    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
//            
//    @OnMessage
//    public void broadcastFigure(@PathParam("user")String user, Figure figure, Session session) throws IOException, EncodeException {
//        System.out.println("broadcastFigure:  "+"to: "+ user);
//        for (Session peer : peers) {
//            if (!peer.equals(session)) {
//                peer.getBasicRemote().sendObject(figure);
//            }
//        }
//    }
//
//    @OnOpen
//    public void onOpen(@PathParam("user")String user, Session peer) throws IOException {
//        peers.add(peer);
//    }
//
//    @OnError
//    public void onError(Throwable t) {
//    }
//
//    @OnClose
//    public void onClose(@PathParam("user")String user, Session peer) throws IOException {
//        peers.remove(peer);
//    }
//}

