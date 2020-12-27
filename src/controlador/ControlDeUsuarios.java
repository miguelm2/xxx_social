package controlador;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import modelo.Pai;
import modelo.Publicacion;
import modelo.Administrador;
import modelo.Ciudad;
import modelo.Usuario;

@ManagedBean
public class ControlDeUsuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7241411234548739669L;
	private EntityManagerFactory emf;
	private EntityManager em;

	// variables persona registro / inicio
	private String correo;
	private String clave;
	private String busqueda;
	private String publicacion;

	private Usuario user ;
	private Usuario userRegistro;
	private Usuario userAmigo;

	private List<Ciudad> ciudades;
	private List<Usuario> resultado;
	private List<Publicacion> misPublicaciones;
	
	private Publicacion p;
	

	public Usuario getUserAmigo() {
		return userAmigo;
	}

	public void setUserAmigo(Usuario userAmigo) {
		this.userAmigo = userAmigo;
	}	
	public Publicacion getP() {
		return p;
	}

	public void setP(Publicacion p) {
		this.p = p;
	}

	// gets y sets
	public Usuario getUserRegistro() {
		return userRegistro;
	}

	public void setUserRegistro(Usuario userRegistro) {
		this.userRegistro = userRegistro;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return correo;
	}

	public void setUsuario(String usuario) {
		this.correo = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public List<Usuario> getResultado() {
		return resultado;
	}

	public void setResultado(List<Usuario> resultado) {
		this.resultado = resultado;
	}
	public String getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(String publicacion) {
		this.publicacion = publicacion;
	}

	public List<Publicacion> getMisPublicaciones() {
		return misPublicaciones;
	}

	public void setMisPublicaciones(List<Publicacion> misPublicaciones) {
		this.misPublicaciones = misPublicaciones;
	}
	
	
	
	
	// constructor

	public ControlDeUsuarios() {
		try {
			this.emf = Persistence.createEntityManagerFactory("xxx_social");
			this.em = this.emf.createEntityManager();
			user=new Usuario();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	
	
	// Metodos Privados

	@SuppressWarnings("unchecked")
	public List<Pai> consutarPaises() {

		String jpql = " SELECT p FROM Pai p ";
		Query query = this.em.createQuery(jpql);
		List<Pai> paises = query.getResultList();

		return paises;
	}

	public void consultarCiudades(String nombrePais) {

		String jpql = " SELECT p FROM Ciudad p 	WHERE p.pai.nombre = :pais";

		Query query = this.em.createQuery(jpql);
		query.setParameter("pais", nombrePais);

		ciudades = query.getResultList();

	}

	public boolean registro() {

		try {
			this.em.getTransaction().begin();

			this.userRegistro.setEstado(0);
			this.userRegistro.setFoto("foto.jpg");
			this.userRegistro.setSituacionSentimental("Casado");
			this.userRegistro.setCiudad("Bucaramanga");
			this.em.persist(this.userRegistro);

			this.em.getTransaction().commit();
			this.em = this.emf.createEntityManager();
			this.user = this.userRegistro;
			this.userRegistro = new Usuario();
			System.out.println("\n USUARIO REGISTRADO");

			return true;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;

	}

	public boolean inicio() {

		try {
			
			String jpql = " SELECT datos FROM Usuario datos WHERE datos.correo = :correo"
					+ " AND  datos.clave = :clave ";
			
			Query query = this.em.createQuery(jpql);
			
			query.setParameter("correo", this.correo);
			query.setParameter("clave", this.clave);
			
			this.user = (Usuario) query.getSingleResult();
			
			System.out.println("INICIO DE SESSION DE: " + this.correo);
			consultarPublicaciones();
			this.user.Datos();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("\n  ERROR!");
			System.out.println(e.toString());
			this.user = null;
		}
		
		
		
		return false;

	}

	public boolean salir() {
		try {
			
			this.user = new Usuario();
			this.userRegistro = new Usuario();
			this.correo = "";
			this.clave = "";
			System.out.println("\n SESION CERRADA!");
			return true;

		} catch (Exception e) {
			System.out.println("\n ERROR: " + e.toString());
			return false;

		}
	}

	public List<Administrador> consultarAdministradores() {
		String jpql = " SELECT datos FROM Administrador datos ";
		Query query = this.em.createQuery(jpql);
		List<Administrador> personas = query.getResultList();
		return personas;
	}

	public boolean actualizar() {

	
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.user);

			this.em.getTransaction().commit();
			this.em = this.emf.createEntityManager();
			System.out.println("\n DATOS ACTUALIZADOS CORRECTAMENTE");
			return true;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;

	}

	public boolean eliminar() {
		if (this.user != null) {
			try {
				this.em.getTransaction().begin();
				this.em.remove(this.user);
				this.em.getTransaction().commit();
				this.em = this.emf.createEntityManager();
				salir();
				System.out.println("Usuario Elimnado");
				return true;
			} catch (Exception e) {
				System.out.print("ERROR: ");
				System.out.println(e);
				return false;
			}
		}

		return false;
	}

	public boolean buscar() {

		if (this.busqueda != null) {

			try {

				String jpql = " SELECT datos FROM Usuario datos WHERE datos.correo LIKE :busqueda"
						+ " OR datos.ciudad LIKE :busqueda"
						+ " OR datos.pais  LIKE :busqueda" 
						+ " OR datos.situacionSentimental LIKE :busqueda ";
				
				
				Query query = this.em.createQuery(jpql);
				query.setParameter("busqueda", "%" + this.busqueda + "%");
				
				resultado = query.getResultList();

				this.busqueda = null;
					
				System.out.println("Buscando...");
				
				return true;

			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
				return false;
			}
		}
		return false;

	}


	
	public boolean publicar(){
		System.out.println("Publicando...");

		if(this.publicacion!=null){
		Publicacion nueva = new Publicacion();
		
		try {
			
			nueva.setUsuario(this.user);
			String d = "2011-03-22";
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			
			nueva.setFecha(date);
			nueva.setPublicacion(this.publicacion);
			nueva.setFoto("perfil.png");
			this.em.getTransaction().begin();

			this.em.persist(nueva);

			this.em.getTransaction().commit();
			this.em = this.emf.createEntityManager();
		
			nueva=new Publicacion();
			
			System.out.println("\n PUBLICACION REALIZADA");
		
			this.publicacion=null;
			
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ey!", "Publicacion realizada Exitosamente!"));

   consultarPublicaciones();
	        
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		
		}
		return false;
	}
	
	
	public void consultarPublicaciones(){
		try{
			System.out.println("Consultando...");

			if(this.user!=null){
		String jpql = " SELECT p FROM Publicacion p WHERE p.usuario.idUsuario = :id ORDER BY p.idPublicacion DESC";

		Query query = this.em.createQuery(jpql);
		
		query.setParameter("id", this.user.getIdUsuario());

		misPublicaciones = query.getResultList();
			}
		}catch(Exception e){
			System.out.println("Error: "+e.toString());
		}
		
	}

	
	
	public boolean sepuedeEditar(){
		if (this.p!=null){
			System.out.println("Publicacion# "+this.p.getIdPublicacion()+" se puede editar");
			return true;
		}
		System.out.println("No se puede");

		return false;
	}
	
	public boolean sepuedeVer(){
		if (this.p!=null){
			System.out.println("Publicacion# "+this.p.getIdPublicacion()+" se puede ver");
			return true;
		}
		System.out.println("No se puede");

		return false;
	}
	
	public boolean sepuedeVisitar(){
		if (this.userAmigo!=null){
			System.out.println("Usuario# "+this.userAmigo.getIdUsuario()+" se puede visitar");
			return true;
		}
		System.out.println("No se puede");
		return false;
	}

	
	public boolean editarPublicacion(){
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.p);

			this.em.getTransaction().commit();
			this.em = this.emf.createEntityManager();
			
			System.out.println("\n PUBLICACION ACTUALIZADA CORRECTAMENTE");
			this.p=null;
			
			consultarPublicaciones();
			return true;

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return true;
	}

	
	
	


}
