package ar.edu.unlam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Paciente implements Comparable<Paciente>{

	private String nombre;
	private Integer dni;
	private List<Vacuna> vacunas;
	private Set<Enfermedad> enfermedades;

	public Paciente(String nombre, Integer dni) {
		// TODO Auto-generated constructor stub
		this.nombre=nombre;
		this.dni=dni;
		this.vacunas= new ArrayList<>();
		this.enfermedades= new HashSet<>();
	}

	public Integer cantidadDeVacunasAplicadas() {
		return this.vacunas.size();
	}

	public void ingresarVacunas(Vacuna vacuna) {
		this.vacunas.add(vacuna);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public List<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}
	

	public Set<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(Set<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public Vacuna buscarVacunas(Vacuna vacuna) throws vacunaInexistente {
		for (Vacuna vacunas : vacunas) {
			if(vacunas.getNombre().equals(vacuna.getNombre())) {
				return vacuna;
			}
		}
		throw new vacunaInexistente();
	}

	public void ingresarAlListadoDeEnfermedades(Enfermedad rubeolaEnfermedad) {
		this.enfermedades.add(rubeolaEnfermedad);
	}
	public Enfermedad buscarEnfermedad(Enfermedad enfermedad) throws EnfermedadInexistente {
		for (Enfermedad enfermedades : enfermedades) {
			if(enfermedades.getIdEnfermedad().equals(enfermedad.getIdEnfermedad())) {
				return enfermedad;
			}
		}throw new EnfermedadInexistente();
	}

	@Override
	public int compareTo(Paciente o) {
		// TODO Auto-generated method stub
		return getNombre().compareTo(o.getNombre());
	}
	
	

}
