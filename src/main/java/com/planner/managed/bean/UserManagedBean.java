package com.planner.managed.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	
	
	public List<User> getUserList()
	{
		userList = new ArrayList<User>();
		for (User u : getUserService().getUsers())
		{
			getUserService().getUserById( u.getUserID() );
			
			byte[] img = u.getImage();
			
			if (img != null)
			{
				InputStream in = new ByteArrayInputStream( img );
				u.setDisplayImage( new DefaultStreamedContent( in, "image/jpeg" ));
			}
			
			userList.add( u );
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