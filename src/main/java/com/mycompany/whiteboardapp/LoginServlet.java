/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.whiteboardapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fabioespinosa
 */
@WebServlet(urlPatterns="/login")
public class LoginServlet  extends HttpServlet{
    private ArrayList<String> sesiones = new ArrayList<String>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);


    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        String usuario = request.getParameter("usuario");
        
        
        request.setAttribute("usuario", usuario);
        if(!sesiones.contains(usuario)) sesiones.add(usuario);
        request.setAttribute("sesiones", sesiones.toString());
        

        request.getRequestDispatcher("/WEB-INF/views/whiteboard.jsp").forward(request, response);

//        for(int i=0; i< sesiones.size(); i++){
//            String actual = sesiones.get(i);
//            if(actual.equals(usuario)){
//                //Redirect
//                request.getRequestDispatcher("/WEB-INF/views/whiteboard.jsp").forward(request, response);
//            }
//            else if(usuario.length()>0){
//                sesiones.add(usuario);
//                request.getRequestDispatcher("/WEB-INF/views/whiteboard.jsp").forward(request, response);
//
//                //Redirect a nueva sesion
//            }
//        }

    }
    
    public void quitarSesion(String usuario) {
        sesiones.remove(usuario);
    }
}
