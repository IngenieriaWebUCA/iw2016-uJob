package es.uca.iw.ujob.web;
import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Inscripcion;
import es.uca.iw.ujob.domain.Oferta;
import es.uca.iw.ujob.domain.Titulacion;
import es.uca.iw.ujob.domain.UserRole;
import es.uca.iw.ujob.domain.Users;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/demandantes")
@Controller
@RooWebScaffold(path = "demandantes", formBackingObject = Demandante.class)
@RooWebFinder
public class DemandanteController {

    @RequestMapping(value = "/{dni}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("dni") String dni, Users user, Model uiModel, HttpServletRequest request) {
        List<String[]> dependencies = new ArrayList<String[]>();
        uiModel.addAttribute("demandante", Demandante.findDemandante(dni));
        uiModel.addAttribute("usuario", Users.findUsers2(dni));
        uiModel.addAttribute("dependencies", dependencies);
        return "demandantes/update";
    }
    
    @RequestMapping(value = "/editarDemandante")
    public String editarDemandante(@RequestParam("usuario_nombre") String nombre, Model uiModel){
    	List<String[]> dependencies = new ArrayList<String[]>();
    	Users usuario = Users.buscarUsuarioNombre(nombre);
    	Collection<Users> usuarios = Users.findUsers2(usuario.getDni());
    	if(Demandante.findDemandante(usuario.getDni()) == null)
    		return "redirect:/demandantes?form";
    	else{
	    	uiModel.addAttribute("demandante", Demandante.findDemandante(usuario.getDni()));
	    	uiModel.addAttribute("usuario", usuarios);
	    	uiModel.addAttribute("dependencies",dependencies);
	    	return "demandantes/update";
    	}
    }
    
    @RequestMapping(value = "/mostrarOfertas")
    public String mostrarOfertas(@RequestParam("usuario_nombre") String nombre, Model uiModel) {
    	Users usuario = Users.buscarUsuarioNombre(nombre);
    	uiModel.addAttribute("ofertas", Oferta.findAllOfertasEmpresa(usuario.getEmpresa().getCif()));
    	System.out.println(Oferta.findAllOfertasEmpresa(usuario.getEmpresa().getCif()));
        addDateTimeFormatPatterns(uiModel);
        return "demandantes/mostrarOfertas";
    }
    
    @RequestMapping(value = "/buscarDemandanteOferta")
    public String buscarDemandanteOferta(@RequestParam("oferta") Oferta oferta, Model uiModel) {
    	String perfil = oferta.getPerfil();
    	Titulacion miTitulacion = Titulacion.findTitulacionNombre(perfil);
    	Set<Titulacion> tit = new HashSet<Titulacion>();
    	tit.add(miTitulacion);
    	return "redirect:/demandantes?find=ByExperienciaLikeAndTitulaciones&experiencia=&titulaciones="+miTitulacion.getId();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Demandante demandante, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, demandante);
            return "demandantes/create";
        }
        uiModel.asMap().clear();
        demandante.persist();
        return "redirect:/demandantes/" + encodeUrlPathSegment(demandante.getDni().toString(), httpServletRequest) + "?form";
    }
    
    @RequestMapping(params = { "find=ByExperienciaLikeAndTitulaciones", "form" }, method = RequestMethod.GET)
    public String findDemandantesByExperienciaLikeAndTitulacionesForm(Model uiModel) {
        uiModel.addAttribute("titulacions", Titulacion.findAllTitulacions());
        return "demandantes/findDemandantesByExperienciaLikeAndTitulaciones";
    }
    
    @RequestMapping(params = "find=ByExperienciaLikeAndTitulaciones", method = RequestMethod.GET)
    public String findDemandantesByExperienciaLikeAndTitulaciones(@RequestParam(value = "experiencia", required = false) String experiencia, @RequestParam(value = "titulaciones", required = false) Set<Titulacion> titulaciones, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
            uiModel.addAttribute("demandantes", Demandante.findDemandantesByExperienciaLikeAndTitulaciones(experiencia, titulaciones, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Demandante.countFindDemandantesByExperienciaLikeAndTitulaciones(experiencia, titulaciones) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("demandantes", Demandante.findDemandantesByExperienciaLikeAndTitulaciones(experiencia, titulaciones, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "demandantes/list";
    }
}
