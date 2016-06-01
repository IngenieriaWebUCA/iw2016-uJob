package es.uca.iw.ujob.web;
import es.uca.iw.ujob.domain.Curriculum;
import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Empresa;
import es.uca.iw.ujob.domain.Oferta;
import es.uca.iw.ujob.domain.Users;
import es.uca.iw.ujob.domain.estadoOferta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/curriculums")
@Controller
@RooWebScaffold(path = "curriculums", formBackingObject = Curriculum.class)
public class CurriculumController {
	
	
	@RequestMapping(value = "/anadirCurriculum")
    public String anadirCurriculum(@RequestParam("curriculum_descripcion") String descripcion, @RequestParam("curriculum_formacion") String formacion, @RequestParam("usuario_nombre") String username, Model uiModel) throws ParseException {
		Users usuario = Users.buscarUsuarioNombre(username);
		Curriculum curriculum = new Curriculum();
		// Puesto, tipo,fecha inicio, fecha caducidad, sueldo, nvacantes,estado,empresa
		curriculum.setFormacion(formacion);
		curriculum.setDescripcion(descripcion);
		curriculum.setDemandante(Demandante.findDemandante(usuario.getDni()));
		curriculum.persist();
    	return "curriculums/anadirCurriculum";
    }
	
	
}
