// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.ujob.web;

import es.uca.iw.ujob.web.EmpresaController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

privileged aspect EmpresaController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByNombreLike", "form" }, method = RequestMethod.GET)
    public String EmpresaController.findEmpresasByNombreLikeForm(Model uiModel) {
        return "empresas/findEmpresasByNombreLike";
    }
    
}
