package ar.edu.unlam;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Vacunatorio {

	private String nombre;
	private Set<Paciente> pacientes;
	private final Integer CANTIDAD_VACUNAS_COVID=2;
	private final Integer CANTIDAD_VACUNAS=2;

	public Vacunatorio(String nombre) {
		this.nombre=nombre;
		this.pacientes= new TreeSet<>();
	}
	

	public Set<Paciente> getPacientes() {
		return pacientes;
	}


	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}


	public Boolean pacienteVacunasCovidCompletas(Paciente marcelo, Vacuna vacunas) {
		if(marcelo.cantidadDeVacunasAplicadas().equals(CANTIDAD_VACUNAS_COVID) && vacunas.getTipo().equals(TipoVacuna.COVID)) {
			return true;
		}
		return false;
	}

	public Boolean pacienteParaSegundaDosisDeCovid(Paciente marcelo, Covid19Primer covidUno, Covid19Segunda covidSegundo) throws NoCovidVaccineException, vacunaInexistente {
		if(marcelo.buscarVacunas(covidUno) != null && marcelo.buscarVacunas(covidSegundo) != null) {
			if(marcelo.getVacunas().contains(covidUno)) {
				marcelo.ingresarVacunas(covidSegundo);
			}
		}
		
		throw new NoCovidVaccineException();
	}

	public Boolean verificarSiPuedeRecibirVacunaRubeola(Paciente marcelo, Enfermedad rubiolaEnfermedad, Vacuna rubiola) throws NoPuedeRecibirVacuna, EnfermedadInexistente {
		if(marcelo.buscarEnfermedad(rubiolaEnfermedad) != null) {
			throw new NoPuedeRecibirVacuna();	
		}else {
			marcelo.ingresarVacunas(rubiola);
		}
		return false;
	}

	public Boolean comprobarSiTieneMasDeDosVacunasDistintas(Paciente marcelo, Vacuna vacuna) throws vacunaInexistente, NoMoreVaccine {
			if(marcelo.buscarVacunas(vacuna) != null) {
				if(marcelo.getVacunas().size()<=CANTIDAD_VACUNAS) {
					return true;
				}
			}
		throw new NoMoreVaccine();
	}

	public void ingresarPacientesVacunados(Paciente paciente) {
		if(buscarPaciente(paciente) == null) {
			this.pacientes.add(paciente);
		}
		
	}

	private Paciente buscarPaciente(Paciente paciente) {
		for (Paciente pacientes : pacientes) {
			if(pacientes.getDni().equals(paciente.getDni())) {
				return pacientes;
			}
		}
		return null;
	}


}
