package es.uca.iw.ujob.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@Table(name = "oferta")
@RooJson
@RooJpaActiveRecord(finders = { "findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoBetweenAndPerfilLikeAndLocalizacion" })
public class Oferta implements Serializable {

    /**
     */
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     */
    @NotNull
    @Column(name = "puesto")
    @Size(min = 1, max = 255)
    private String puesto;

    /**
     */
    @NotNull
    @Column(name = "tipo_contrato")
    @Size(min = 1, max = 50)
    private String tipo_contrato;

    /**
     */
    @NotNull
    @Column(name = "sueldo")
    private Float sueldo;

    /**
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    /**
     */
    @NotNull
    @Column(name = "perfil")
    @Size(max = 255)
    private String perfil;

    /**
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    @Column(name = "fecha_caducidad")
    private Date fecha_caducidad;

    @ManyToOne
    @JoinColumn(name = "cif_empresa")
    private Empresa empresa;

    /**
     */
    @NotNull
    @Enumerated
    @Column(name = "estado")
    private estadoOferta estado;

    /**
     */
    @NotNull
    @Column(name = "n_vacantes")
    private Integer n_vacantes;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oferta")
    private Set<Inscripcion> inscripciones = new HashSet<Inscripcion>(0);

    /**
     */
    @ManyToOne
    @JoinColumn(name = "localizacion")
    private Localizacion localizacion;
    
    public static TypedQuery<Oferta> findOfertasByPuestoLikeAndTipo_contratoLikeAndSueldoBetweenAndPerfilLikeAndLocalizacion(String puesto, String tipo_contrato, Float minSueldo, Float maxSueldo, String perfil, Localizacion localizacion, String sortFieldName, String sortOrder) {
    
    	if (puesto == null || puesto.length() == 0) {
            puesto = "%"; 
    	}
        puesto = puesto.replace('*', '%');
        if (puesto.charAt(0) != '%') {
            puesto = "%" + puesto;
        }
        if (puesto.charAt(puesto.length() - 1) != '%') {
            puesto = puesto + "%";
        }
        
        if (tipo_contrato == null || tipo_contrato.length() == 0) {
            tipo_contrato = "%";
        }
        tipo_contrato = tipo_contrato.replace('*', '%');
        if (tipo_contrato.charAt(0) != '%') {
            tipo_contrato = "%" + tipo_contrato;
        }
        if (tipo_contrato.charAt(tipo_contrato.length() - 1) != '%') {
            tipo_contrato = tipo_contrato + "%";
        }
        
        if (perfil == null || perfil.length() == 0) {
            perfil = "%";
        }
        perfil = perfil.replace('*', '%');
        if (perfil.charAt(0) != '%') {
            perfil = "%" + perfil;
        }
        if (perfil.charAt(perfil.length() - 1) != '%') {
            perfil = perfil + "%";
        }
        
        StringBuilder queryBuilder;
        
        if(minSueldo == null && maxSueldo == null && localizacion == null){
        	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato) AND LOWER(o.perfil) LIKE LOWER(:perfil)");
        
        }else{
        	if(minSueldo != null && maxSueldo == null && localizacion == null){
            	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo >= :minSueldo AND LOWER(o.perfil) LIKE LOWER(:perfil)");

        	}else{
        		if(minSueldo == null && maxSueldo != null && localizacion == null){
                	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo <= :maxSueldo  AND LOWER(o.perfil) LIKE LOWER(:perfil)");

        		}else{
        			if(minSueldo == null && maxSueldo == null && localizacion != null){
        	        	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND LOWER(o.perfil) LIKE LOWER(:perfil)  AND o.localizacion = :localizacion");

        			}else{
        				if(minSueldo != null && maxSueldo != null && localizacion == null){
        		        	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo BETWEEN :minSueldo AND :maxSueldo  AND LOWER(o.perfil) LIKE LOWER(:perfil)");

        				}else{
        					if(minSueldo != null && maxSueldo == null && localizacion != null){
        			        	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo >= :minSueldo  AND LOWER(o.perfil) LIKE LOWER(:perfil)  AND o.localizacion = :localizacion");

        					}else{
        						if(minSueldo == null && maxSueldo != null && localizacion != null){
        				        	queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo <= :maxSueldo  AND LOWER(o.perfil) LIKE LOWER(:perfil)  AND o.localizacion = :localizacion");

        						}else{
        							queryBuilder = new StringBuilder("SELECT o FROM Oferta AS o WHERE LOWER(o.puesto) LIKE LOWER(:puesto)  AND LOWER(o.tipo_contrato) LIKE LOWER(:tipo_contrato)  AND o.sueldo BETWEEN :minSueldo AND :maxSueldo  AND LOWER(o.perfil) LIKE LOWER(:perfil)  AND o.localizacion = :localizacion");
        						}
        						
        					}
        				}
        			}
        		}
        	}
        }
        EntityManager em = Oferta.entityManager();
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<Oferta> q = em.createQuery(queryBuilder.toString(), Oferta.class);
        q.setParameter("puesto", puesto);
        q.setParameter("tipo_contrato", tipo_contrato);
        
        if(minSueldo != null){
        	q.setParameter("minSueldo", minSueldo);
        }

        if(maxSueldo != null){
        	q.setParameter("maxSueldo", maxSueldo);
        }

        q.setParameter("perfil", perfil);
        
        if (localizacion != null){
        	q.setParameter("localizacion", localizacion);
        }
        return q;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((fecha_caducidad == null) ? 0 : fecha_caducidad.hashCode());
        result = prime * result + ((fecha_inicio == null) ? 0 : fecha_inicio.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inscripciones == null) ? 0 : inscripciones.hashCode());
        result = prime * result + ((n_vacantes == null) ? 0 : n_vacantes.hashCode());
        result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
        result = prime * result + ((puesto == null) ? 0 : puesto.hashCode());
        result = prime * result + ((sueldo == null) ? 0 : sueldo.hashCode());
        result = prime * result + ((tipo_contrato == null) ? 0 : tipo_contrato.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Oferta other = (Oferta) obj;
        if (estado == null) {
            if (other.estado != null) return false;
        } else if (!estado.equals(other.estado)) return false;
        if (fecha_caducidad == null) {
            if (other.fecha_caducidad != null) return false;
        } else if (!fecha_caducidad.equals(other.fecha_caducidad)) return false;
        if (fecha_inicio == null) {
            if (other.fecha_inicio != null) return false;
        } else if (!fecha_inicio.equals(other.fecha_inicio)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (inscripciones == null) {
            if (other.inscripciones != null) return false;
        } else if (!inscripciones.equals(other.inscripciones)) return false;
        if (n_vacantes == null) {
            if (other.n_vacantes != null) return false;
        } else if (!n_vacantes.equals(other.n_vacantes)) return false;
        if (perfil == null) {
            if (other.perfil != null) return false;
        } else if (!perfil.equals(other.perfil)) return false;
        if (puesto == null) {
            if (other.puesto != null) return false;
        } else if (!puesto.equals(other.puesto)) return false;
        if (sueldo == null) {
            if (other.sueldo != null) return false;
        } else if (!sueldo.equals(other.sueldo)) return false;
        if (tipo_contrato == null) {
            if (other.tipo_contrato != null) return false;
        } else if (!tipo_contrato.equals(other.tipo_contrato)) return false;
        return true;
    }

    public static List<Oferta> findAllOfertasEmpresa(String cif) {
        System.out.println("SELECT o FROM Oferta AS o WHERE o.cif_empresa LIKE ('" + cif + "')");
        return entityManager().createQuery("SELECT o FROM Oferta AS o WHERE o.empresa.cif LIKE('" + cif + "')", Oferta.class).getResultList();
    }
}
