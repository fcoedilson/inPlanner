package com.planner.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Comparable<User>
{
	private int userID;
	private String firstName;
	private String lastName;
	private String nickName;
	private String emailAddress;
	private String imageName;
	private byte[] image;
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID", unique = true, nullable = false)
	public int getUserID()
	{
		return userID;
	}
	public void setUserID( int userID )
	{
		this.userID = userID;
	}
	

	@Column(name="FIRST_NAME", unique = true, nullable = false)
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}
	

	@Column(name="LAST_NAME", unique = true, nullable = false)
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}
	
	
	@Column(name="NICK_NAME", unique = true, nullable = false)
	public String getNickName()
	{
		return nickName;
	}
	public void setNickName( String nickName )
	{
		this.nickName = nickName;
	}
	
	
	@Column(name="EMAIL_ADDRESS", unique = true, nullable = false)
	public String getEmailAddress()
	{
		return emailAddress;
	}
	public void setEmailAddress( String emailAddress )
	{
		this.emailAddress = emailAddress;
	}
	
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name="IMAGE")
	public byte[] getImage()
	{
		return image;
	}
	public void setImage( byte[] image )
	{
		this.image = image;
	}
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name="IMAGE_NAME")
	public String getImageName()
	{
		return imageName;
	}
	public void setImageName( String imageName )
	{
		this.imageName = imageName;
	}
	
	
	public int compareTo(User o)
	{
		int retVal;
		
		retVal = this.firstName.compareTo( o.getFirstName() );
		
		if (retVal == 0)
		{
			retVal = this.lastName.compareTo( o.getLastName() );
		}
		
		return retVal;
	}
}
