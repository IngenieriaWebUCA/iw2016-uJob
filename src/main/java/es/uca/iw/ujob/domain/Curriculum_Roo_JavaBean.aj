// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.ujob.domain;

import es.uca.iw.ujob.domain.Curriculum;
import es.uca.iw.ujob.domain.Demandante;

privileged aspect Curriculum_Roo_JavaBean {
    
    public Integer Curriculum.getId() {
        return this.id;
    }
    
    public void Curriculum.setId(Integer id) {
        this.id = id;
    }
    
    public String Curriculum.getDescripcion() {
        return this.descripcion;
    }
    
    public void Curriculum.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String Curriculum.getFormacion() {
        return this.formacion;
    }
    
    public void Curriculum.setFormacion(String formacion) {
        this.formacion = formacion;
    }
    
    public Demandante Curriculum.getDemandante() {
        return this.demandante;
    }
    
    public void Curriculum.setDemandante(Demandante demandante) {
        this.demandante = demandante;
    }
    
}