package es.uca.iw.ujob.web;
import es.uca.iw.ujob.domain.UserRole;
import es.uca.iw.ujob.domain.Users;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/userses")
@Controller
@RooWebScaffold(path = "userses", formBackingObject = Users.class)
@RooWebFinder
public class UsersController {
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        List<String[]> dependencies = new ArrayList<String[]>();
        uiModel.addAttribute("users", new Users());
        uiModel.addAttribute("userrol",UserRole.findUserRole2());
        uiModel.addAttribute("dependencies", dependencies);
        return "userses/create";
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Users users, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, users);
            return "userses/create";
        }
        uiModel.asMap().clear();
        users.persist();
        sendMessage("iw2016ujob@gmail.com", "Registro en uJob", users.getEmail(), "Su registro en uJob como usuario:" + users.getDni() + "ha sido satisfactorio.");

        return "redirect:/userses/" + encodeUrlPathSegment(users.getDni().toString(), httpServletRequest);
    }
    
    @Autowired
    private transient MailSender mailTemplate;

    public void sendMessage(String mailFrom, String subject, String mailTo, String message) {
        org.springframework.mail.SimpleMailMessage mailMessage = new org.springframework.mail.SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setSubject(subject);
        mailMessage.setTo(mailTo);
        mailMessage.setText(message);
        mailTemplate.send(mailMessage);
    }
    
}