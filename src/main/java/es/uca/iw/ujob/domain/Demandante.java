package es.uca.iw.ujob.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@Table(name = "demandante")
@RooJpaActiveRecord(finders = { "findDemandantesByExperienciaLikeAndTitulaciones" })
@RooJson
public class Demandante implements Serializable {

    /**
     */
    @Id
    @NotNull
    @Column(name = "dni", unique = true)
    @Size(min = 9, max = 9)
    private String dni;

    /**
     */
    @NotNull
    @Column(name = "nombre")
    private String nombre;

    /**
     */
    @NotNull
    @Size(min = 1)
    @Column(name = "sexo")
    private String sexo;

    /**
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    /**
     */
    @NotNull
    @Column(name = "direccion")
    @Size(min = 1, max = 255)
    private String direccion;

    /**
     */
    @NotNull
    @Column(name = "email")
    @Size(max = 255)
    private String email;

    /**
     */
    @NotNull
    @Column(name = "telefono")
    @Size(min = 9, max = 9)
    private String telefono;

    @NotNull
    @Column(name = "experiencia")
    @Size(max = 255)
    private String experiencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demandante")
    private Set<Curriculum> curriculums = new HashSet<Curriculum>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demandante")
    private Set<Inscripcion> inscripciones = new HashSet<Inscripcion>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demandante")
    private Set<Titulacion> titulaciones = new HashSet<Titulacion>(0);

    @OneToOne
    private Users usuario;

    public static TypedQuery<Demandante> findDemandantesByExperienciaLikeAndTitulaciones(String experiencia, Set<Titulacion> titulaciones, String sortFieldName, String sortOrder) {
        if (experiencia == null || experiencia.length() == 0) {
            experiencia = "%";
        }
        experiencia = experiencia.replace('*', '%');
        if (experiencia.charAt(0) != '%') {
            experiencia = "%" + experiencia;
        }
        if (experiencia.charAt(experiencia.length() - 1) != '%') {
            experiencia = experiencia + "%";
        }
        EntityManager em = Demandante.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Demandante AS o WHERE LOWER(o.experiencia) LIKE LOWER(:experiencia)");
        if (titulaciones != null) {
            queryBuilder.append(" AND");
        }
        if (titulaciones != null) {
            for (int i = 0; i < titulaciones.size(); i++) {
                if (i > 0) queryBuilder.append(" AND");
                queryBuilder.append(" :titulaciones_item").append(i).append(" MEMBER OF o.titulaciones");
            }
        }
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" " + sortOrder);
            }
        }
        TypedQuery<Demandante> q = em.createQuery(queryBuilder.toString(), Demandante.class);
        q.setParameter("experiencia", experiencia);
        if (titulaciones != null) {
            int titulacionesIndex = 0;
            for (Titulacion _titulacion : titulaciones) {
                q.setParameter("titulaciones_item" + titulacionesIndex++, _titulacion);
            }
        }
        return q;
    }
}
