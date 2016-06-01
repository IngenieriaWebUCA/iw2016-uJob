package es.uca.iw.ujob.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "inscripcion")
@RooJson
public class Inscripcion implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Enumerated
    @Column(name = "estado")
    private estadoInscripcion estado;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    @Column(name = "fecha_inscripcion")
    private Date fecha_inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_oferta")
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Demandante demandante;

    public static List<Inscripcion> findAllInscripcionsByDni(String cif) {
        return entityManager().createQuery("SELECT i FROM Inscripcion AS i, Oferta AS o WHERE(i.oferta.id=o.id) and o.empresa.cif LIKE ('" + cif + "')", Inscripcion.class).getResultList();
    }
}
