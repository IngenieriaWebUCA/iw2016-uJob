package es.uca.iw.ujob.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@Table(name = "users")
@RooJpaActiveRecord(finders = { "findUsersesByUsernameEquals" })
@RooJson(deepSerialize = true)
public class Users {

    /**
     */
    @Size(min = 6)
    private String username;

    /**
     */
    @Size(min = 8)
    private String password;

    /**
     */
    @Size(min = 8, max = 9)
    @Id
    private String dni;

    /**
     */
    @NotNull
    private String email;

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private Boolean enable;

    @NotNull
    @ManyToOne
    private UserRole userRole;

    public static Collection<Users> findUsers2(String dni) {
        List<Users> usr = new ArrayList<Users>();
        Users usuario = Users.findUsers(dni);
        usr.add(usuario);
        return usr;
    }
    public static Users buscarUsuarioNombre(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = Users.entityManager();
        TypedQuery<Users> q = em.createQuery("SELECT o FROM Users AS o WHERE o.username = :username", Users.class);
        q.setParameter("username", username);
        Users usuario = q.getSingleResult();
        return usuario;
    }

    /**
     */
    @ManyToOne
    private Empresa empresa;
}
