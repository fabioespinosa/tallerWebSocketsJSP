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
@WebServlet(urlPatterns="/logout")
public class LogoutServlet  extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
                
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
               
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);


    }
    
    
    
}
