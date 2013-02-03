package com.planner.managed.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import com.planner.model.User;
import com.planner.user.service.IUserService;

@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	List<User> userList;
	private User userInfo = new User();
	private UploadedFile file;
	
	public String addUserAction()
	{
		try
		{
			userInfo.setImage( file.getContents() );
			userInfo.setImageName( file.getFileName() );
			getUserService().addUser( userInfo );
			
			return SUCCESS;
		}
		catch ( DataAccessException e )
		{
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	
	public StreamedContent getImage() throws IOException
	{
        FacesContext context = FacesContext.getCurrentInstance();

        //if (context.getRenderResponse())
        //{
         //   // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
         //   return new DefaultStreamedContent();
       // }
       // else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        
        Map<String, String> test = context.getExternalContext().getRequestParameterMap();
        
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            byte[] image = getUserService().getUserById( Integer.valueOf(id) ).getImage();
            return new DefaultStreamedContent(new ByteArrayInputStream( image ));
       // }
    }
	
	
	public List<User> getUserList()
	{
		userList = new ArrayList<User>();
		userList.addAll( getUserService().getUsers());
		
		for (User u : userList)
		{
			u.getImage();
		}
		return userList;
	}
	

	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService( IUserService userService )
	{
		this.userService = userService;
	}

	
	public User getUserInfo()
	{
		return userInfo;
	}
	public void setUserInfo( User userInfo )
	{
		this.userInfo = userInfo;
	}


	public UploadedFile getFile()
	{
		return file;
	}
	public void setFile( UploadedFile file )
	{
		this.file = file;
	}
}