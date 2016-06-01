// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.ujob.domain;

import es.uca.iw.ujob.domain.DemandanteDataOnDemand;
import es.uca.iw.ujob.domain.Titulacion;
import es.uca.iw.ujob.domain.TitulacionDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TitulacionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TitulacionDataOnDemand: @Component;
    
    private Random TitulacionDataOnDemand.rnd = new SecureRandom();
    
    private List<Titulacion> TitulacionDataOnDemand.data;
    
    @Autowired
    DemandanteDataOnDemand TitulacionDataOnDemand.demandanteDataOnDemand;
    
    public Titulacion TitulacionDataOnDemand.getNewTransientTitulacion(int index) {
        Titulacion obj = new Titulacion();
        setDescripcion(obj, index);
        return obj;
    }
    
    public void TitulacionDataOnDemand.setDescripcion(Titulacion obj, int index) {
        String descripcion = "descripcion_" + index;
        obj.setDescripcion(descripcion);
    }
    
    public Titulacion TitulacionDataOnDemand.getSpecificTitulacion(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Titulacion obj = data.get(index);
        Integer id = obj.getId();
        return Titulacion.findTitulacion(id);
    }
    
    public Titulacion TitulacionDataOnDemand.getRandomTitulacion() {
        init();
        Titulacion obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getId();
        return Titulacion.findTitulacion(id);
    }
    
    public boolean TitulacionDataOnDemand.modifyTitulacion(Titulacion obj) {
        return false;
    }
    
    public void TitulacionDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Titulacion.findTitulacionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Titulacion' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Titulacion>();
        for (int i = 0; i < 10; i++) {
            Titulacion obj = getNewTransientTitulacion(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
