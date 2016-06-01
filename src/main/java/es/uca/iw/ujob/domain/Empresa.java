package es.uca.iw.ujob.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@Table(name = "empresa")
@RooJpaActiveRecord(finders = { "findEmpresasByNombreLike" })
@RooJson
public class Empresa implements Serializable {

    /**
     */
    @Id
    @NotNull
    @Column(name = "cif", unique = true)
    @Size(min = 9, max = 9)
    private String cif;

    /**
     */
    @NotNull
    @Column(name = "nombre", unique = true)
    @Size(min = 1, max = 255)
    private String nombre;

    /**
     */
    @NotNull
    @Column(name = "email")
    @Size(max = 255)
    private String email;

    /**
     */
    @Column(name = "n_empleados")
    private Integer n_empleados;

    /**
     */
    @Column(name = "otros")
    private String otros;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Set<Localizacion> localizaciones = new HashSet<Localizacion>(0);

    public static TypedQuery<Empresa> findEmpresasByNombreLike(String nombre, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) {
            nombre = "%";
        }
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Empresa.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Empresa AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)");
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<Empresa> q = em.createQuery(queryBuilder.toString(), Empresa.class);
        q.setParameter("nombre", nombre);
        return q;
    }
}
