// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.ujob.domain;

import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Titulacion;

privileged aspect Titulacion_Roo_JavaBean {
    
    public Integer Titulacion.getId() {
        return this.id;
    }
    
    public void Titulacion.setId(Integer id) {
        this.id = id;
    }
    
    public String Titulacion.getDescripcion() {
        return this.descripcion;
    }
    
    public void Titulacion.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Demandante Titulacion.getDemandante() {
        return this.demandante;
    }
    
    public void Titulacion.setDemandante(Demandante demandante) {
        this.demandante = demandante;
    }
    
}
