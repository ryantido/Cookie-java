/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mon.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing or invalid.");
            return;
        }

        if ("accept".equalsIgnoreCase(action)) {
            setCookie(response, "cookieAccepted", "true", 3600); // 1 heure
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else if ("reject".equalsIgnoreCase(action)) {
            setCookie(response, "cookieAccepted", "false", 0); // Suppression du cookie
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else {
            // Action non reconnue
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported action: " + action);
        }
    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge); 
        cookie.setPath("/"); 
        //cookie.setHttpOnly(true); // Bloquez le Js
        // cookie.setSecure(true); // pour HTTPS
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to manage cookie consent (accept/reject).";
    }
}
