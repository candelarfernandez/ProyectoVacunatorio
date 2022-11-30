package ar.edu.unlam;

public class Vacuna {
	private String nombre;
	private TipoVacuna tipo;
	
	public Vacuna(String nombre, TipoVacuna tipo) {
		super();
		this.nombre = nombre;
		this.tipo=tipo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public TipoVacuna getTipo() {
		return tipo;
	}


	public void setTipo(TipoVacuna tipo) {
		this.tipo = tipo;
	}
	
	

}
