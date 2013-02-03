package com.planner.user.dao;

import java.util.List;
import com.planner.model.User;
import org.hibernate.SessionFactory;

public class UserDAO implements IUserDAO {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}


	public void setSessionFactory( SessionFactory sessionFactory )
	{
        this.sessionFactory = sessionFactory;
    }


	public void addUser( User user )
	{
		getSessionFactory().getCurrentSession().save( user );
	}


	public void deleteUser( User user )
	{
		getSessionFactory().getCurrentSession().delete( user );
	}


	public void updateUser( User user )
	{
		getSessionFactory().getCurrentSession().update( user );
	}


	@SuppressWarnings("rawtypes")
	public User getUserById( int id )
	{
		List list = getSessionFactory().getCurrentSession().createQuery( "from USERS where user_id=?" ).setParameter( 0, id ).list();
		return (User)list.get(0);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsers()
	{
		List list = getSessionFactory().getCurrentSession().createQuery( "from User" ).list();
		return list;
	}

}
