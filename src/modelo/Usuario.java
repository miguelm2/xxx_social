package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private int idUsuario;

	private String ciudad;

	private String clave;

	private String correo;

	private int edad;

	private int estado;

	private String foto;

	private String nombre;

	private String pais;

	@Column(name="situacion_sentimental")
	private String situacionSentimental;

	//bi-directional many-to-one association to Amigo
	@OneToMany(mappedBy="usuario1")
	private List<Amigo> amigos1;

	//bi-directional many-to-one association to Amigo
	@OneToMany(mappedBy="usuario2")
	private List<Amigo> amigos2;

	//bi-directional many-to-one association to Chat
	@OneToMany(mappedBy="usuario1")
	private List<Chat> chats1;

	//bi-directional many-to-one association to Chat
	@OneToMany(mappedBy="usuario2")
	private List<Chat> chats2;

	//bi-directional many-to-one association to Publicacion
	@OneToMany(mappedBy="usuario")
	private List<Publicacion> publicacions;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="usuario1")
	private List<Solicitud> solicituds1;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="usuario2")
	private List<Solicitud> solicituds2;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSituacionSentimental() {
		return this.situacionSentimental;
	}

	public void setSituacionSentimental(String situacionSentimental) {
		this.situacionSentimental = situacionSentimental;
	}

	public List<Amigo> getAmigos1() {
		return this.amigos1;
	}

	public void setAmigos1(List<Amigo> amigos1) {
		this.amigos1 = amigos1;
	}

	public Amigo addAmigos1(Amigo amigos1) {
		getAmigos1().add(amigos1);
		amigos1.setUsuario1(this);

		return amigos1;
	}

	public Amigo removeAmigos1(Amigo amigos1) {
		getAmigos1().remove(amigos1);
		amigos1.setUsuario1(null);

		return amigos1;
	}

	public List<Amigo> getAmigos2() {
		return this.amigos2;
	}

	public void setAmigos2(List<Amigo> amigos2) {
		this.amigos2 = amigos2;
	}

	public Amigo addAmigos2(Amigo amigos2) {
		getAmigos2().add(amigos2);
		amigos2.setUsuario2(this);

		return amigos2;
	}

	public Amigo removeAmigos2(Amigo amigos2) {
		getAmigos2().remove(amigos2);
		amigos2.setUsuario2(null);

		return amigos2;
	}

	public List<Chat> getChats1() {
		return this.chats1;
	}

	public void setChats1(List<Chat> chats1) {
		this.chats1 = chats1;
	}

	public Chat addChats1(Chat chats1) {
		getChats1().add(chats1);
		chats1.setUsuario1(this);

		return chats1;
	}

	public Chat removeChats1(Chat chats1) {
		getChats1().remove(chats1);
		chats1.setUsuario1(null);

		return chats1;
	}

	public List<Chat> getChats2() {
		return this.chats2;
	}

	public void setChats2(List<Chat> chats2) {
		this.chats2 = chats2;
	}

	public Chat addChats2(Chat chats2) {
		getChats2().add(chats2);
		chats2.setUsuario2(this);

		return chats2;
	}

	public Chat removeChats2(Chat chats2) {
		getChats2().remove(chats2);
		chats2.setUsuario2(null);

		return chats2;
	}

	public List<Publicacion> getPublicacions() {
		return this.publicacions;
	}

	public void setPublicacions(List<Publicacion> publicacions) {
		this.publicacions = publicacions;
	}

	public Publicacion addPublicacion(Publicacion publicacion) {
		getPublicacions().add(publicacion);
		publicacion.setUsuario(this);

		return publicacion;
	}

	public Publicacion removePublicacion(Publicacion publicacion) {
		getPublicacions().remove(publicacion);
		publicacion.setUsuario(null);

		return publicacion;
	}

	public List<Solicitud> getSolicituds1() {
		return this.solicituds1;
	}

	public void setSolicituds1(List<Solicitud> solicituds1) {
		this.solicituds1 = solicituds1;
	}

	public Solicitud addSolicituds1(Solicitud solicituds1) {
		getSolicituds1().add(solicituds1);
		solicituds1.setUsuario1(this);

		return solicituds1;
	}

	public Solicitud removeSolicituds1(Solicitud solicituds1) {
		getSolicituds1().remove(solicituds1);
		solicituds1.setUsuario1(null);

		return solicituds1;
	}

	public List<Solicitud> getSolicituds2() {
		return this.solicituds2;
	}

	public void setSolicituds2(List<Solicitud> solicituds2) {
		this.solicituds2 = solicituds2;
	}

	public Solicitud addSolicituds2(Solicitud solicituds2) {
		getSolicituds2().add(solicituds2);
		solicituds2.setUsuario2(this);

		return solicituds2;
	}

	public Solicitud removeSolicituds2(Solicitud solicituds2) {
		getSolicituds2().remove(solicituds2);
		solicituds2.setUsuario2(null);

		return solicituds2;
	}
	
	public void Datos(){
		System.out.println(" Nombre: "+nombre+
				" Correo:"+correo+" id "+idUsuario);
		
	}

}