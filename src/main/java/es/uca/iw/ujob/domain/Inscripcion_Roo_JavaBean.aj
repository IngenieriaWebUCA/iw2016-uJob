// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.ujob.domain;

import es.uca.iw.ujob.domain.Demandante;
import es.uca.iw.ujob.domain.Inscripcion;
import es.uca.iw.ujob.domain.Oferta;
import es.uca.iw.ujob.domain.estadoInscripcion;
import java.util.Date;

privileged aspect Inscripcion_Roo_JavaBean {
    
    public Integer Inscripcion.getId() {
        return this.id;
    }
    
    public void Inscripcion.setId(Integer id) {
        this.id = id;
    }
    
    public estadoInscripcion Inscripcion.getEstado() {
        return this.estado;
    }
    
    public void Inscripcion.setEstado(estadoInscripcion estado) {
        this.estado = estado;
    }
    
    public Date Inscripcion.getFecha_inscripcion() {
        return this.fecha_inscripcion;
    }
    
    public void Inscripcion.setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }
    
    public Oferta Inscripcion.getOferta() {
        return this.oferta;
    }
    
    public void Inscripcion.setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
    public Demandante Inscripcion.getDemandante() {
        return this.demandante;
    }
    
    public void Inscripcion.setDemandante(Demandante demandante) {
        this.demandante = demandante;
    }
    
}