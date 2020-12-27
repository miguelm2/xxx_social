package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mensaje database table.
 * 
 */
@Entity
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_mensaje")
	private int idMensaje;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Lob
	private String mensaje;

	//bi-directional many-to-one association to Chat
	@ManyToOne
	@JoinColumn(name="id_chat")
	private Chat chat;

	public Mensaje() {
	}

	public int getIdMensaje() {
		return this.idMensaje;
	}

	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

}