package es.uca.iw.ujob.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class UserRole {

    /**
     */
    @NotNull
    private String name;
    
    public static Collection<UserRole> findUserRole2() {
        List<UserRole> usr = new ArrayList<UserRole>();
        UserRole usuario = UserRole.findUserRole((long) 3);
        usr.add(usuario);
        return usr;
    }
}