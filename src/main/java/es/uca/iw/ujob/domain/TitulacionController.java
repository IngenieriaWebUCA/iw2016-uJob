package es.uca.iw.ujob.domain;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/titulacions")
@Controller
@RooWebScaffold(path = "titulacions", formBackingObject = Titulacion.class)
public class TitulacionController {
}
