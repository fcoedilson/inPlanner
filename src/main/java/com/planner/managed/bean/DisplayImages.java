package com.planner.managed.bean;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.planner.user.service.UserService;

@ManagedBean
@ApplicationScoped
public class DisplayImages {

    @EJB
    private UserService service;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getRenderResponse()) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("studentId");
            User u = studentService.find(Long.valueOf(studentId));
            return new DefaultStreamedContent(new ByteArrayInputStream(student.getImage()));
        }
    }

}