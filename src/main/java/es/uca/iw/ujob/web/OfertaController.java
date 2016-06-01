package es.uca.iw.ujob.web;
import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Empresa;
import es.uca.iw.ujob.domain.Localizacion;
import es.uca.iw.ujob.domain.Oferta;
import es.uca.iw.ujob.domain.Users;
import es.uca.iw.ujob.domain.estadoOferta;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import java.text.*;
@RequestMapping("/ofertas")
@Controller
@RooWebScaffold(path = "ofertas", formBackingObject = Oferta.class)
@RooWebFinder
public class OfertaController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Oferta oferta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, oferta);
            return "ofertas/create";
        }
        uiModel.asMap().clear();
        oferta.persist();
        return "redirect:/ofertas/" + encodeUrlPathSegment(oferta.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Oferta());
        return "ofertas/create";
    }
    
	@RequestMapping(value = "/anadirOferta")
    public String anadirOferta(@RequestParam("oferta_puesto") String puesto, @RequestParam("oferta_tipo") String tipo, @RequestParam("oferta_fini") String f_ini, @RequestParam("oferta_fcad") String f_cad, @RequestParam("oferta_sueldo") float sueldo, @RequestParam("oferta_nvacantes") int nvacantes, @RequestParam("oferta_estado") String estado, @RequestParam("usuario_nombre") String username,@RequestParam("oferta_perfil") String perfil, Model uiModel) throws ParseException {
		Users usuario = Users.buscarUsuarioNombre(username);
		Oferta oferta = new Oferta();
		// Puesto, tipo,fecha inicio, fecha caducidad, sueldo, nvacantes,estado,empresa
		
        oferta.setPuesto(puesto);
        oferta.setTipo_contrato(tipo);
        oferta.setPerfil(perfil);
        Date fechaini = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(f_ini);
        Date fechacad = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(f_cad);
        oferta.setFecha_inicio(fechaini);
        oferta.setFecha_caducidad(fechacad);
        oferta.setSueldo(sueldo);
        oferta.setN_vacantes(nvacantes);
        System.out.println(estado);
        if(estado == "Resuelta"){
        	oferta.setEstado(estadoOferta.RESUELTA);
        }
        else if(estado.equals("En espera")){
        	oferta.setEstado(estadoOferta.EN_ESPERA);
        }
        else if(estado.equals("Activa")){
        	oferta.setEstado(estadoOferta.ACTIVA);
        }
        else if(estado.equals("Detenida")){
        	oferta.setEstado(estadoOferta.DETENIDA);
        }
        else if(estado.equals("Cancelada")){
        	oferta.setEstado(estadoOferta.CANCELADA);
        }
        else if(estado.equals("En tramite")){
        	oferta.setEstado(estadoOferta.EN_TRAMITE);
        }
        else{
        	oferta.setEstado(estadoOferta.CANCELADA);
        }
        oferta.setEmpresa(Empresa.findEmpresa(usuario.getEmpresa().getCif()));
        oferta.persist();
    	return "ofertas/anadirOferta";
    }
	
	@RequestMapping(params = { "find=ByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion", "form" }, method = RequestMethod.GET)
    public String findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacionForm(Model uiModel) {
        uiModel.addAttribute("localizacions", Localizacion.findAllLocalizacions());
        return "ofertas/findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion";
    }   
    
	@RequestMapping(params = "find=ByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion", method = RequestMethod.GET)
    public String findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion(@RequestParam(value = "puesto", required = false) String puesto, @RequestParam(value = "tipo_contrato", required = false) String tipo_contrato, @RequestParam(value = "sueldo", required = false) Float sueldo, @RequestParam(value = "perfil", required = false) String perfil, @RequestParam(value = "localizacion", required = false) Localizacion localizacion, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("ofertas", Oferta.findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion(puesto, tipo_contrato, sueldo, perfil, localizacion, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Oferta.countFindOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion(puesto, tipo_contrato, sueldo, perfil, localizacion) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("ofertas", Oferta.findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoGreaterThanEqualsAndPerfilLikeAndLocalizacion(puesto, tipo_contrato, sueldo, perfil, localizacion, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "ofertas/list";
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
