package ar.edu.unlam;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TestCases {

	@Test
	public void testQuecompruebaQueElPAcienteNoPoseeVacunas() {
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		
		Integer esperado =0;
		Integer actual = marcelo.cantidadDeVacunasAplicadas();
		
		assertEquals(actual, esperado);
	}
	
	@Test
	public void testQuecompruebaQueElPAcientePoseeLasDosDosisDelCovid() {
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		Vacuna coviduno = new Covid19Primer("covidUno", TipoVacuna.COVID);
		Vacuna coviddos = new Covid19Segunda("covidDos", TipoVacuna.COVID);
		
		marcelo.ingresarVacunas(coviduno);
		marcelo.ingresarVacunas(coviddos);
		

		
		assertTrue(vacunarte.pacienteVacunasCovidCompletas(marcelo, coviduno));
		assertTrue(vacunarte.pacienteVacunasCovidCompletas(marcelo, coviddos));
	}
	
	@Test(expected = NoCovidVaccineException.class)
	public void testQuecompruebaQueElPAcienteNoPoseelaPrimerDosisYNoSePuedeAplicarLaSegunda() throws NoCovidVaccineException, vacunaInexistente {
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		Covid19Primer coviduno = new Covid19Primer("covidUno", TipoVacuna.COVID);
		Covid19Segunda coviddos = new Covid19Segunda("covidDos", TipoVacuna.COVID);
		marcelo.ingresarVacunas(coviduno);
		marcelo.ingresarVacunas(coviddos);
		
		assertTrue(vacunarte.pacienteParaSegundaDosisDeCovid(marcelo, coviduno, coviddos));
	}
	
	@Test (expected = NoPuedeRecibirVacuna.class)
	public void testQuecompruebaQueElPAcienteTuboRubeolaAnteriormenteYNoSePuedeVacunar() throws NoPuedeRecibirVacuna, EnfermedadInexistente {
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		Vacuna rubeola = new Rubeola("rubeola", TipoVacuna.RUBEOLA);
		Enfermedad rubeolaEnfermedad = new Enfermedad(1);
		
		marcelo.ingresarAlListadoDeEnfermedades(rubeolaEnfermedad);
		
		vacunarte.verificarSiPuedeRecibirVacunaRubeola(marcelo, rubeolaEnfermedad, rubeola);
	}
	
	@Test
	public void testQuecompruebaQueElPAcienteNoPoseeMasDeDOsVacunas() throws NoMoreVaccine, vacunaInexistente{
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		Covid19Primer coviduno = new Covid19Primer("covidUno", TipoVacuna.COVID);
		Covid19Segunda coviddos = new Covid19Segunda("covidDos", TipoVacuna.COVID);
		Rubeola rubeola = new Rubeola("Rubeola", TipoVacuna.RUBEOLA);
		HepatitisA hepatitisA = new HepatitisA("hepatitisuno", TipoVacuna.HEPATITIS);
		
		marcelo.ingresarVacunas(coviduno);
		marcelo.ingresarVacunas(hepatitisA);
		
		
		vacunarte.comprobarSiTieneMasDeDosVacunasDistintas(marcelo, coviduno);
		vacunarte.comprobarSiTieneMasDeDosVacunasDistintas(marcelo, hepatitisA);
	}
	
	@Test (expected = NoMoreVaccine.class)
	public void testQuecompruebaQueElPAcientePoseeMasDeDOsVacunas() throws NoMoreVaccine, vacunaInexistente{
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente marcelo = new Paciente("Marcelo", 222555);
		Covid19Primer coviduno = new Covid19Primer("covidUno", TipoVacuna.COVID);
		Covid19Segunda coviddos = new Covid19Segunda("covidDos", TipoVacuna.COVID);
		Rubeola rubeola = new Rubeola("Rubeola", TipoVacuna.RUBEOLA);
		HepatitisA hepatitisA = new HepatitisA("hepatitisuno", TipoVacuna.HEPATITIS);
		
		marcelo.ingresarVacunas(coviduno);
		marcelo.ingresarVacunas(hepatitisA);
		marcelo.ingresarVacunas(rubeola);
		
		
		vacunarte.comprobarSiTieneMasDeDosVacunasDistintas(marcelo, coviduno);
		vacunarte.comprobarSiTieneMasDeDosVacunasDistintas(marcelo, hepatitisA);
		vacunarte.comprobarSiTieneMasDeDosVacunasDistintas(marcelo, rubeola);
	}
	

	@Test
	public void testQuecompruebaQueLosPacientesEstenOrdenadosPorNombre() {
		
		Vacunatorio vacunarte = new Vacunatorio("VacunARTE");
		Paciente fabiana = new Paciente("fabiana", 444);
		Paciente carla = new Paciente("carla", 333);
		Paciente marcelo = new Paciente("marcelo", 222);
		Covid19Primer coviduno = new Covid19Primer("covidUno", TipoVacuna.COVID);
		Covid19Segunda coviddos = new Covid19Segunda("covidDos", TipoVacuna.COVID);
		Rubeola rubeola = new Rubeola("Rubeola", TipoVacuna.RUBEOLA);
		HepatitisA hepatitisA = new HepatitisA("hepatitisuno", TipoVacuna.HEPATITIS);
		
		marcelo.ingresarVacunas(coviduno);
		fabiana.ingresarVacunas(hepatitisA);
		carla.ingresarVacunas(rubeola);
		vacunarte.ingresarPacientesVacunados(fabiana);
		vacunarte.ingresarPacientesVacunados(carla);
		vacunarte.ingresarPacientesVacunados(marcelo);
		
		
		Set<Paciente> pacientesLista = vacunarte.getPacientes();
		pacientesLista.add(marcelo);
		pacientesLista.add(fabiana);
		pacientesLista.add(carla);
		for (Paciente paciente : pacientesLista) {
			assertEquals(paciente.getNombre(), carla.getNombre());
				break;
		}
		
	}
	


}
