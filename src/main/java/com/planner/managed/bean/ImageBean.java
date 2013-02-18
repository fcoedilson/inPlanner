package com.planner.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.planner.model.User;
import com.planner.user.service.IUserService;

@Controller
public class ImageBean extends AbstractController implements Serializable
{
	private static final long serialVersionUID = 7333037180812094983L;
	
	private IUserService userService;
	@Autowired
	public void setUserService( IUserService userService )
	{
		this.userService = userService;
	}
	
	@RequestMapping( value="/fetchImage.htm", method = RequestMethod.GET )
	public byte[] getImage( @PathVariable int imageId)
	{
		User u = userService.getUserById( imageId );
		return u.getImage();
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().write( "test" );
		return null;
	}
	

}
