package es.uca.iw.ujob.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Entity(name = "experiencia")
@Table(name = "experiencia")
@RooJson
public class Experiencia {

    @Id
    @NotNull
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull
    @Column(name = "nombre_empresa")
    private String nombre_empresa;

    @NotNull
    @Column(name = "fecha_inicio")
    @Size(min = 10, max = 10)
    private String fecha_inicio;

    @NotNull
    @Column(name = "fecha_fin")
    @Size(min = 10, max = 10)
    private String fecha_fin;
}
