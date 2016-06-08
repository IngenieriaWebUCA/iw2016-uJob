package es.uca.iw.ujob.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "titulacion")
@RooJson
public class Titulacion implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Demandante demandante;
    
    public static Titulacion findTitulacionNombre(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("Nombre no introducido");
        TypedQuery<Titulacion> q = entityManager().createQuery("SELECT o FROM Titulacion AS o WHERE o.descripcion LIKE ('"+nombre+"')", Titulacion.class);
        Titulacion tit = q.getSingleResult();
        return tit;
    }
}
