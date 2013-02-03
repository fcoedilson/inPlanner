package com.planner.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.planner.model.User;
import com.planner.user.dao.IUserDAO;


@Transactional(readOnly = true)
public class UserService implements IUserService
{
	IUserDAO userDAO;
	
	@Transactional(readOnly = false)
	public void addUser( User user )
	{
		getUserDAO().addUser( user );
	}

	
	@Transactional(readOnly = false)
	public void deleteUser( User user )
	{
		getUserDAO().deleteUser( user );
	}
	
	
	@Transactional(readOnly = false)
	public void updateUser( User user )
	{
		getUserDAO().updateUser( user );
	}
	
	

	public User getUserById( int id )
	{
		return getUserDAO().getUserById( id );
	}

	

	public List<User> getUsers()
	{	
		return getUserDAO().getUsers();
	}

	
	public IUserDAO getUserDAO()
	{
		return userDAO;
	}

	
	public void setUserDAO( IUserDAO userDAO )
	{
		this.userDAO = userDAO;
	}
}
