package es.uca.iw.ujob.web;
import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Empresa;
import es.uca.iw.ujob.domain.Inscripcion;
import es.uca.iw.ujob.domain.Oferta;
import es.uca.iw.ujob.domain.Users;
import es.uca.iw.ujob.domain.estadoInscripcion;
import es.uca.iw.ujob.domain.estadoOferta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/inscripcions")
@Controller
@RooWebScaffold(path = "inscripcions", formBackingObject = Inscripcion.class)
public class InscripcionController {
	
    @RequestMapping(value = "/anadirInscripcion")
    public String anadirInscripcion(@RequestParam("oferta_id") int id, @RequestParam("usuario_nombre") String username,Model uiModel) throws ParseException {
		Users usuario = Users.buscarUsuarioNombre(username);
		Inscripcion inscripcion = new Inscripcion();
		if(Demandante.findDemandante(usuario.getDni()) != null){
			inscripcion.setDemandante(Demandante.findDemandante(usuario.getDni()));
	        inscripcion.setEstado(estadoInscripcion.RECIBIDA);
	        inscripcion.setFecha_inscripcion(new Date());
	        inscripcion.setOferta(Oferta.findOferta(id));
	
	        List<Inscripcion> lista = Inscripcion.findAllInscripcions();
			if(!(lista.contains(inscripcion))){
				inscripcion.persist();
			}
		}
    	return "inscripcions/inscripcionCreada";
    }
    
    @RequestMapping(value = "/mostrarInscripciones")
    public String mostrarInscripciones(@RequestParam("usuario_nombre") String nombre, Model uiModel) {
    	Users usuario = Users.buscarUsuarioNombre(nombre);
    	uiModel.addAttribute("inscripcions", Inscripcion.findAllInscripcionsByDni(usuario.getEmpresa().getCif()));
        addDateTimeFormatPatterns(uiModel);
        return "inscripcions/list";
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Inscripcion inscripcion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, inscripcion);
            return "inscripcions/create";
        }
        uiModel.asMap().clear();
        inscripcion.persist();
        sendMessage("iw2016ujob@gmail.com", "Inscripción en uJob", inscripcion.getDemandante().getEmail(), 
        		"Su inscripcion en uJob ha sido satisfactoria." + "Querido usuario:" + inscripcion.getDemandante() + 
        		"le informamos de que su inscripcion se ha llevado a cabo correctamente. Inscrito en la oferta para la empresa: " +
        				inscripcion.getOferta().getEmpresa() + "con estado :" + inscripcion.getEstado());

        return "redirect:/inscripcions/" + encodeUrlPathSegment(inscripcion.getId().toString(), httpServletRequest);
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
