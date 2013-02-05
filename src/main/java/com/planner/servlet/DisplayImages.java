package com.planner.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.planner.managed.bean.UserManagedBean;
import com.planner.model.User;
import com.planner.user.dao.UserDAO;
import com.planner.user.service.IUserService;
import com.planner.user.service.UserService;
@RequestScoped
public class DisplayImages extends HttpServlet {
 
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -3234512189258259085L;
	
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get last uploaded image
        //try {
            String idStr = req.getParameter( "userid" );
            
            int id = Integer.parseInt( idStr );
            
            WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
            
            
            UserDAO userService = (UserDAO) springContext.getBean( "UserDAO" );
            User u = userService.getUserById( id );
            
            System.out.println( u.getEmailAddress() );
            
            if (u.getImage() != null)
            {
            	resp.getOutputStream().write( u.getImage() );
            	resp.getOutputStream().close();
            }
             
       // } catch (Exception e) {
        //    // Display error message
         //   resp.getWriter().write(e.getMessage());
          //  resp.getWriter().close();
       // }
         
    }
}