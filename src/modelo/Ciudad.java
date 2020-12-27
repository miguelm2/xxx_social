package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ciudad")
	private int idCiudad;

	private String nombre;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="id_pais")
	private Pai pai;

	public Ciudad() {
	}

	public int getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pai getPai() {
		return this.pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}

}