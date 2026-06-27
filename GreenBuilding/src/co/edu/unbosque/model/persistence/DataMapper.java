package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Apartamento;
import co.edu.unbosque.model.ApartamentoDTO;
import co.edu.unbosque.model.Arrendatario;
import co.edu.unbosque.model.ArrendatarioDTO;
import co.edu.unbosque.model.CampanaAmbiental;
import co.edu.unbosque.model.CampanaAmbientalDTO;
import co.edu.unbosque.model.Conjunto;
import co.edu.unbosque.model.ConjuntoDTO;
import co.edu.unbosque.model.Incidente;
import co.edu.unbosque.model.IncidenteDTO;
import co.edu.unbosque.model.Mascota;
import co.edu.unbosque.model.MascotaDTO;
import co.edu.unbosque.model.Notificacion;
import co.edu.unbosque.model.NotificacionDTO;
import co.edu.unbosque.model.Obligacion;
import co.edu.unbosque.model.ObligacionDTO;
import co.edu.unbosque.model.Pago;
import co.edu.unbosque.model.PagoDTO;
import co.edu.unbosque.model.Paquete;
import co.edu.unbosque.model.PaqueteDTO;
import co.edu.unbosque.model.Parqueadero;
import co.edu.unbosque.model.ParqueaderoDTO;
import co.edu.unbosque.model.Participacion;
import co.edu.unbosque.model.ParticipacionDTO;
import co.edu.unbosque.model.Propietario;
import co.edu.unbosque.model.PropietarioDTO;
import co.edu.unbosque.model.RegistroConsumoAgua;
import co.edu.unbosque.model.RegistroConsumoAguaDTO;
import co.edu.unbosque.model.RegistroConsumoEnergia;
import co.edu.unbosque.model.RegistroConsumoEnergiaDTO;
import co.edu.unbosque.model.RegistroReciclaje;
import co.edu.unbosque.model.RegistroReciclajeDTO;
import co.edu.unbosque.model.RegistroVisita;
import co.edu.unbosque.model.RegistroVisitaDTO;
import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.model.ReporteDTO;
import co.edu.unbosque.model.Reserva;
import co.edu.unbosque.model.ReservaDTO;
import co.edu.unbosque.model.Residente;
import co.edu.unbosque.model.ResidenteDTO;
import co.edu.unbosque.model.SolicitudMantenimiento;
import co.edu.unbosque.model.SolicitudMantenimientoDTO;
import co.edu.unbosque.model.Torre;
import co.edu.unbosque.model.TorreDTO;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.Vehiculo;
import co.edu.unbosque.model.VehiculoDTO;
import co.edu.unbosque.model.Visitante;
import co.edu.unbosque.model.VisitanteDTO;
import co.edu.unbosque.model.ZonaComun;
import co.edu.unbosque.model.ZonaComunDTO;

/**
 * Clase utilitaria que centraliza la conversion entre entidades del dominio y
 * sus respectivos objetos de transferencia de datos (DTO) para todas las
 * entidades del sistema GreenBuilding Manager. Proporciona metodos estaticos
 * para convertir objetos individuales y listas completas en ambas direcciones:
 * entidad a DTO y DTO a entidad.
 * <br>
 * <b>pre</b> Las entidades y DTOs deben estar correctamente instanciados antes
 * de invocar cualquier metodo de conversion. <br>
 * <b>post</b> Se retorna un nuevo objeto con los valores copiados del objeto
 * origen. El objeto origen no es modificado. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class DataMapper {

	//CONJUNTO

	/**
	 * Convierte un objeto Conjunto a su representacion DTO.
	 * <b>pre</b> entidad != null y sus atributos basicos deben estar inicializados. <br>
	 * <b>post</b> Se retorna un ConjuntoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Conjunto a convertir. entidad != null
	 * @return ConjuntoDTO con los datos copiados de la entidad
	 */
	public static ConjuntoDTO convertirConjuntoAConjuntoDTO(Conjunto entidad) {
		ConjuntoDTO dto = new ConjuntoDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setDireccion(entidad.getDireccion());
		dto.setCiudad(entidad.getCiudad());
		dto.setTelefono(entidad.getTelefono());
		dto.setCorreo(entidad.getCorreo());
		return dto;
	}

	/**
	 * Convierte un ConjuntoDTO a su entidad de dominio Conjunto.
	 * <b>pre</b> dto != null y sus atributos basicos deben estar inicializados. <br>
	 * <b>post</b> Se retorna un Conjunto con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ConjuntoDTO a convertir. dto != null
	 * @return Conjunto con los datos copiados del DTO
	 */
	public static Conjunto convertirConjuntoDTOAConjunto(ConjuntoDTO dto) {
		Conjunto entidad = new Conjunto();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setDireccion(dto.getDireccion());
		entidad.setCiudad(dto.getCiudad());
		entidad.setTelefono(dto.getTelefono());
		entidad.setCorreo(dto.getCorreo());
		return entidad;
	}

	/**
	 * Convierte una lista de Conjunto a una lista de ConjuntoDTO.
	 * <b>pre</b> listaEntidad != null. Puede estar vacia. <br>
	 * <b>post</b> Se retorna una lista de ConjuntoDTO con los datos de cada entidad. <br>
	 *
	 * @param listaEntidad Lista de objetos Conjunto a convertir. listaEntidad != null
	 * @return ArrayList de ConjuntoDTO equivalentes
	 */
	public static ArrayList<ConjuntoDTO> convertirListaConjuntoAListaConjuntoDTO(ArrayList<Conjunto> listaEntidad) {
		ArrayList<ConjuntoDTO> listaDTO = new ArrayList<>();
		for (Conjunto entidad : listaEntidad) {
			listaDTO.add(convertirConjuntoAConjuntoDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ConjuntoDTO a una lista de Conjunto.
	 * <b>pre</b> listaDTO != null. Puede estar vacia. <br>
	 * <b>post</b> Se retorna una lista de Conjunto con los datos de cada DTO. <br>
	 *
	 * @param listaDTO Lista de objetos ConjuntoDTO a convertir. listaDTO != null
	 * @return ArrayList de Conjunto equivalentes
	 */
	public static ArrayList<Conjunto> convertirListaConjuntoDTOAListaConjunto(ArrayList<ConjuntoDTO> listaDTO) {
		ArrayList<Conjunto> listaEntidad = new ArrayList<>();
		for (ConjuntoDTO dto : listaDTO) {
			listaEntidad.add(convertirConjuntoDTOAConjunto(dto));
		}
		return listaEntidad;
	}

	//TORRE

	/**
	 * Convierte un objeto Torre a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un TorreDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Torre a convertir. entidad != null
	 * @return TorreDTO con los datos copiados de la entidad
	 */
	public static TorreDTO convertirTorreATorreDTO(Torre entidad) {
		TorreDTO dto = new TorreDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setNumeroPisos(entidad.getNumeroPisos());
		dto.setConjunto(entidad.getConjunto());
		return dto;
	}

	/**
	 * Convierte un TorreDTO a su entidad de dominio Torre.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Torre con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto TorreDTO a convertir. dto != null
	 * @return Torre con los datos copiados del DTO
	 */
	public static Torre convertirTorreDTOATorre(TorreDTO dto) {
		Torre entidad = new Torre();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setNumeroPisos(dto.getNumeroPisos());
		entidad.setConjunto(dto.getConjunto());
		return entidad;
	}

	/**
	 * Convierte una lista de Torre a una lista de TorreDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de TorreDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Torre a convertir. listaEntidad != null
	 * @return ArrayList de TorreDTO equivalentes
	 */
	public static ArrayList<TorreDTO> convertirListaTorreAListaTorreDTO(ArrayList<Torre> listaEntidad) {
		ArrayList<TorreDTO> listaDTO = new ArrayList<>();
		for (Torre entidad : listaEntidad) {
			listaDTO.add(convertirTorreATorreDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de TorreDTO a una lista de Torre.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Torre equivalente. <br>
	 *
	 * @param listaDTO Lista de TorreDTO a convertir. listaDTO != null
	 * @return ArrayList de Torre equivalentes
	 */
	public static ArrayList<Torre> convertirListaTorreDTOAListaTorre(ArrayList<TorreDTO> listaDTO) {
		ArrayList<Torre> listaEntidad = new ArrayList<>();
		for (TorreDTO dto : listaDTO) {
			listaEntidad.add(convertirTorreDTOATorre(dto));
		}
		return listaEntidad;
	}

	//APARTAMENTO

	/**
	 * Convierte un objeto Apartamento a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ApartamentoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Apartamento a convertir. entidad != null
	 * @return ApartamentoDTO con los datos copiados de la entidad
	 */
	public static ApartamentoDTO convertirApartamentoAApartamentoDTO(Apartamento entidad) {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId(entidad.getId());
		dto.setNumero(entidad.getNumero());
		dto.setPiso(entidad.getPiso());
		dto.setTorre(entidad.getTorre());
		dto.setEstado(entidad.getEstado());
		dto.setPropietario(entidad.getPropietario());
		dto.setArrendatario(entidad.getArrendatario());
		return dto;
	}

	/**
	 * Convierte un ApartamentoDTO a su entidad de dominio Apartamento.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Apartamento con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ApartamentoDTO a convertir. dto != null
	 * @return Apartamento con los datos copiados del DTO
	 */
	public static Apartamento convertirApartamentoDTOAApartamento(ApartamentoDTO dto) {
		Apartamento entidad = new Apartamento();
		entidad.setId(dto.getId());
		entidad.setNumero(dto.getNumero());
		entidad.setPiso(dto.getPiso());
		entidad.setTorre(dto.getTorre());
		entidad.setEstado(dto.getEstado());
		entidad.setPropietario(dto.getPropietario());
		entidad.setArrendatario(dto.getArrendatario());
		return entidad;
	}

	/**
	 * Convierte una lista de Apartamento a una lista de ApartamentoDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ApartamentoDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Apartamento a convertir. listaEntidad != null
	 * @return ArrayList de ApartamentoDTO equivalentes
	 */
	public static ArrayList<ApartamentoDTO> convertirListaApartamentoAListaApartamentoDTO(
			ArrayList<Apartamento> listaEntidad) {
		ArrayList<ApartamentoDTO> listaDTO = new ArrayList<>();
		for (Apartamento entidad : listaEntidad) {
			listaDTO.add(convertirApartamentoAApartamentoDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ApartamentoDTO a una lista de Apartamento.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Apartamento equivalente. <br>
	 *
	 * @param listaDTO Lista de ApartamentoDTO a convertir. listaDTO != null
	 * @return ArrayList de Apartamento equivalentes
	 */
	public static ArrayList<Apartamento> convertirListaApartamentoDTOAListaApartamento(
			ArrayList<ApartamentoDTO> listaDTO) {
		ArrayList<Apartamento> listaEntidad = new ArrayList<>();
		for (ApartamentoDTO dto : listaDTO) {
			listaEntidad.add(convertirApartamentoDTOAApartamento(dto));
		}
		return listaEntidad;
	}

	//PROPIETARIO

	/**
	 * Convierte un objeto Propietario a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un PropietarioDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Propietario a convertir. entidad != null
	 * @return PropietarioDTO con los datos copiados de la entidad
	 */
	public static PropietarioDTO convertirPropietarioAPropietarioDTO(Propietario entidad) {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setCedula(entidad.getCedula());
		dto.setTelefono(entidad.getTelefono());
		dto.setCorreo(entidad.getCorreo());
		dto.setContactoEmergencia(entidad.getContactoEmergencia());
		dto.setTelefonoEmergencia(entidad.getTelefonoEmergencia());
		dto.setDireccionCorrespondencia(entidad.getDireccionCorrespondencia());
		dto.setEsResidente(entidad.isEsResidente());
		dto.setResponsablePago(entidad.isResponsablePago());
		dto.setEsConsejo(entidad.isEsConsejo());
		return dto;
	}

	/**
	 * Convierte un PropietarioDTO a su entidad de dominio Propietario.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Propietario con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto PropietarioDTO a convertir. dto != null
	 * @return Propietario con los datos copiados del DTO
	 */
	public static Propietario convertirPropietarioDTOAPropietario(PropietarioDTO dto) {
		Propietario entidad = new Propietario();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setCedula(dto.getCedula());
		entidad.setTelefono(dto.getTelefono());
		entidad.setCorreo(dto.getCorreo());
		entidad.setContactoEmergencia(dto.getContactoEmergencia());
		entidad.setTelefonoEmergencia(dto.getTelefonoEmergencia());
		entidad.setDireccionCorrespondencia(dto.getDireccionCorrespondencia());
		entidad.setEsResidente(dto.isEsResidente());
		entidad.setResponsablePago(dto.isResponsablePago());
		entidad.setEsConsejo(dto.isEsConsejo());
		return entidad;
	}

	/**
	 * Convierte una lista de Propietario a una lista de PropietarioDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de PropietarioDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Propietario a convertir. listaEntidad != null
	 * @return ArrayList de PropietarioDTO equivalentes
	 */
	public static ArrayList<PropietarioDTO> convertirListaPropietarioAListaPropietarioDTO(
			ArrayList<Propietario> listaEntidad) {
		ArrayList<PropietarioDTO> listaDTO = new ArrayList<>();
		for (Propietario entidad : listaEntidad) {
			listaDTO.add(convertirPropietarioAPropietarioDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de PropietarioDTO a una lista de Propietario.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Propietario equivalente. <br>
	 *
	 * @param listaDTO Lista de PropietarioDTO a convertir. listaDTO != null
	 * @return ArrayList de Propietario equivalentes
	 */
	public static ArrayList<Propietario> convertirListaPropietarioDTOAListaPropietario(
			ArrayList<PropietarioDTO> listaDTO) {
		ArrayList<Propietario> listaEntidad = new ArrayList<>();
		for (PropietarioDTO dto : listaDTO) {
			listaEntidad.add(convertirPropietarioDTOAPropietario(dto));
		}
		return listaEntidad;
	}

	//ARRENDATARIO

	/**
	 * Convierte un objeto Arrendatario a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ArrendatarioDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Arrendatario a convertir. entidad != null
	 * @return ArrendatarioDTO con los datos copiados de la entidad
	 */
	public static ArrendatarioDTO convertirArrendatarioAArrendatarioDTO(Arrendatario entidad) {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setCedula(entidad.getCedula());
		dto.setTelefono(entidad.getTelefono());
		dto.setCorreo(entidad.getCorreo());
		dto.setContactoEmergencia(entidad.getContactoEmergencia());
		dto.setTelefonoEmergencia(entidad.getTelefonoEmergencia());
		dto.setApartamento(entidad.getApartamento());
		dto.setFechaInicioContrato(entidad.getFechaInicioContrato());
		dto.setFechaFinContrato(entidad.getFechaFinContrato());
		dto.setActivo(entidad.isActivo());
		return dto;
	}

	/**
	 * Convierte un ArrendatarioDTO a su entidad de dominio Arrendatario.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Arrendatario con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ArrendatarioDTO a convertir. dto != null
	 * @return Arrendatario con los datos copiados del DTO
	 */
	public static Arrendatario convertirArrendatarioDTOAArrendatario(ArrendatarioDTO dto) {
		Arrendatario entidad = new Arrendatario();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setCedula(dto.getCedula());
		entidad.setTelefono(dto.getTelefono());
		entidad.setCorreo(dto.getCorreo());
		entidad.setContactoEmergencia(dto.getContactoEmergencia());
		entidad.setTelefonoEmergencia(dto.getTelefonoEmergencia());
		entidad.setApartamento(dto.getApartamento());
		entidad.setFechaInicioContrato(dto.getFechaInicioContrato());
		entidad.setFechaFinContrato(dto.getFechaFinContrato());
		entidad.setActivo(dto.isActivo());
		return entidad;
	}

	/**
	 * Convierte una lista de Arrendatario a una lista de ArrendatarioDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ArrendatarioDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Arrendatario a convertir. listaEntidad != null
	 * @return ArrayList de ArrendatarioDTO equivalentes
	 */
	public static ArrayList<ArrendatarioDTO> convertirListaArrendatarioAListaArrendatarioDTO(
			ArrayList<Arrendatario> listaEntidad) {
		ArrayList<ArrendatarioDTO> listaDTO = new ArrayList<>();
		for (Arrendatario entidad : listaEntidad) {
			listaDTO.add(convertirArrendatarioAArrendatarioDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ArrendatarioDTO a una lista de Arrendatario.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Arrendatario equivalente. <br>
	 *
	 * @param listaDTO Lista de ArrendatarioDTO a convertir. listaDTO != null
	 * @return ArrayList de Arrendatario equivalentes
	 */
	public static ArrayList<Arrendatario> convertirListaArrendatarioDTOAListaArrendatario(
			ArrayList<ArrendatarioDTO> listaDTO) {
		ArrayList<Arrendatario> listaEntidad = new ArrayList<>();
		for (ArrendatarioDTO dto : listaDTO) {
			listaEntidad.add(convertirArrendatarioDTOAArrendatario(dto));
		}
		return listaEntidad;
	}

	//RESIDENTE

	/**
	 * Convierte un objeto Residente a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ResidenteDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Residente a convertir. entidad != null
	 * @return ResidenteDTO con los datos copiados de la entidad
	 */
	public static ResidenteDTO convertirResidenteAResidenteDTO(Residente entidad) {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setCedula(entidad.getCedula());
		dto.setTelefono(entidad.getTelefono());
		dto.setCorreo(entidad.getCorreo());
		dto.setContactoEmergencia(entidad.getContactoEmergencia());
		dto.setTelefonoEmergencia(entidad.getTelefonoEmergencia());
		dto.setApartamento(entidad.getApartamento());
		dto.setTipo(entidad.getTipo());
		dto.setActivo(entidad.isActivo());
		return dto;
	}

	/**
	 * Convierte un ResidenteDTO a su entidad de dominio Residente.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Residente con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ResidenteDTO a convertir. dto != null
	 * @return Residente con los datos copiados del DTO
	 */
	public static Residente convertirResidenteDTOAResidente(ResidenteDTO dto) {
		Residente entidad = new Residente();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setCedula(dto.getCedula());
		entidad.setTelefono(dto.getTelefono());
		entidad.setCorreo(dto.getCorreo());
		entidad.setContactoEmergencia(dto.getContactoEmergencia());
		entidad.setTelefonoEmergencia(dto.getTelefonoEmergencia());
		entidad.setApartamento(dto.getApartamento());
		entidad.setTipo(dto.getTipo());
		entidad.setActivo(dto.isActivo());
		return entidad;
	}

	/**
	 * Convierte una lista de Residente a una lista de ResidenteDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ResidenteDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Residente a convertir. listaEntidad != null
	 * @return ArrayList de ResidenteDTO equivalentes
	 */
	public static ArrayList<ResidenteDTO> convertirListaResidenteAListaResidenteDTO(ArrayList<Residente> listaEntidad) {
		ArrayList<ResidenteDTO> listaDTO = new ArrayList<>();
		for (Residente entidad : listaEntidad) {
			listaDTO.add(convertirResidenteAResidenteDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ResidenteDTO a una lista de Residente.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Residente equivalente. <br>
	 *
	 * @param listaDTO Lista de ResidenteDTO a convertir. listaDTO != null
	 * @return ArrayList de Residente equivalentes
	 */
	public static ArrayList<Residente> convertirListaResidenteDTOAListaResidente(ArrayList<ResidenteDTO> listaDTO) {
		ArrayList<Residente> listaEntidad = new ArrayList<>();
		for (ResidenteDTO dto : listaDTO) {
			listaEntidad.add(convertirResidenteDTOAResidente(dto));
		}
		return listaEntidad;
	}

	//USUARIO

	/**
	 * Convierte un objeto Usuario a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un UsuarioDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Usuario a convertir. entidad != null
	 * @return UsuarioDTO con los datos copiados de la entidad
	 */
	public static UsuarioDTO convertirUsuarioAUsuarioDTO(Usuario entidad) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(entidad.getId());
		dto.setUsername(entidad.getUsername());
		dto.setContrasena(entidad.getContrasena());
		dto.setRol(entidad.getRol());
		dto.setConjunto(entidad.getConjunto());
		dto.setResidente(entidad.getResidente());
		dto.setActivo(entidad.isActivo());
		dto.setIntentosFallidos(entidad.getIntentosFallidos());
		return dto;
	}

	/**
	 * Convierte un UsuarioDTO a su entidad de dominio Usuario.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Usuario con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto UsuarioDTO a convertir. dto != null
	 * @return Usuario con los datos copiados del DTO
	 */
	public static Usuario convertirUsuarioDTOAUsuario(UsuarioDTO dto) {
		Usuario entidad = new Usuario();
		entidad.setId(dto.getId());
		entidad.setUsername(dto.getUsername());
		entidad.setContrasena(dto.getContrasena());
		entidad.setRol(dto.getRol());
		entidad.setConjunto(dto.getConjunto());
		entidad.setResidente(dto.getResidente());
		entidad.setActivo(dto.isActivo());
		entidad.setIntentosFallidos(dto.getIntentosFallidos());
		return entidad;
	}

	/**
	 * Convierte una lista de Usuario a una lista de UsuarioDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de UsuarioDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Usuario a convertir. listaEntidad != null
	 * @return ArrayList de UsuarioDTO equivalentes
	 */
	public static ArrayList<UsuarioDTO> convertirListaUsuarioAListaUsuarioDTO(ArrayList<Usuario> listaEntidad) {
		ArrayList<UsuarioDTO> listaDTO = new ArrayList<>();
		for (Usuario entidad : listaEntidad) {
			listaDTO.add(convertirUsuarioAUsuarioDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de UsuarioDTO a una lista de Usuario.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Usuario equivalente. <br>
	 *
	 * @param listaDTO Lista de UsuarioDTO a convertir. listaDTO != null
	 * @return ArrayList de Usuario equivalentes
	 */
	public static ArrayList<Usuario> convertirListaUsuarioDTOAListaUsuario(ArrayList<UsuarioDTO> listaDTO) {
		ArrayList<Usuario> listaEntidad = new ArrayList<>();
		for (UsuarioDTO dto : listaDTO) {
			listaEntidad.add(convertirUsuarioDTOAUsuario(dto));
		}
		return listaEntidad;
	}

	//VEHICULO 

	/**
	 * Convierte un objeto Vehiculo a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un VehiculoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Vehiculo a convertir. entidad != null
	 * @return VehiculoDTO con los datos copiados de la entidad
	 */
	public static VehiculoDTO convertirVehiculoAVehiculoDTO(Vehiculo entidad) {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId(entidad.getId());
		dto.setPlaca(entidad.getPlaca());
		dto.setTipo(entidad.getTipo());
		dto.setMarca(entidad.getMarca());
		dto.setModelo(entidad.getModelo());
		dto.setColor(entidad.getColor());
		dto.setApartamento(entidad.getApartamento());
		dto.setAutorizado(entidad.isAutorizado());
		dto.setTieneRestriccion(entidad.isTieneRestriccion());
		return dto;
	}

	/**
	 * Convierte un VehiculoDTO a su entidad de dominio Vehiculo.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Vehiculo con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto VehiculoDTO a convertir. dto != null
	 * @return Vehiculo con los datos copiados del DTO
	 */
	public static Vehiculo convertirVehiculoDTOAVehiculo(VehiculoDTO dto) {
		Vehiculo entidad = new Vehiculo();
		entidad.setId(dto.getId());
		entidad.setPlaca(dto.getPlaca());
		entidad.setTipo(dto.getTipo());
		entidad.setMarca(dto.getMarca());
		entidad.setModelo(dto.getModelo());
		entidad.setColor(dto.getColor());
		entidad.setApartamento(dto.getApartamento());
		entidad.setAutorizado(dto.isAutorizado());
		entidad.setTieneRestriccion(dto.isTieneRestriccion());
		return entidad;
	}

	/**
	 * Convierte una lista de Vehiculo a una lista de VehiculoDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de VehiculoDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Vehiculo a convertir. listaEntidad != null
	 * @return ArrayList de VehiculoDTO equivalentes
	 */
	public static ArrayList<VehiculoDTO> convertirListaVehiculoAListaVehiculoDTO(ArrayList<Vehiculo> listaEntidad) {
		ArrayList<VehiculoDTO> listaDTO = new ArrayList<>();
		for (Vehiculo entidad : listaEntidad) {
			listaDTO.add(convertirVehiculoAVehiculoDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de VehiculoDTO a una lista de Vehiculo.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Vehiculo equivalente. <br>
	 *
	 * @param listaDTO Lista de VehiculoDTO a convertir. listaDTO != null
	 * @return ArrayList de Vehiculo equivalentes
	 */
	public static ArrayList<Vehiculo> convertirListaVehiculoDTOAListaVehiculo(ArrayList<VehiculoDTO> listaDTO) {
		ArrayList<Vehiculo> listaEntidad = new ArrayList<>();
		for (VehiculoDTO dto : listaDTO) {
			listaEntidad.add(convertirVehiculoDTOAVehiculo(dto));
		}
		return listaEntidad;
	}

	//MASCOTA

	/**
	 * Convierte un objeto Mascota a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un MascotaDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Mascota a convertir. entidad != null
	 * @return MascotaDTO con los datos copiados de la entidad
	 */
	public static MascotaDTO convertirMascotaAMascotaDTO(Mascota entidad) {
		MascotaDTO dto = new MascotaDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setEspecie(entidad.getEspecie());
		dto.setRaza(entidad.getRaza());
		dto.setColor(entidad.getColor());
		dto.setApartamento(entidad.getApartamento());
		dto.setFechaVacunacion(entidad.getFechaVacunacion());
		dto.setVacunasAlDia(entidad.isVacunasAlDia());
		return dto;
	}

	/**
	 * Convierte un MascotaDTO a su entidad de dominio Mascota.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Mascota con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto MascotaDTO a convertir. dto != null
	 * @return Mascota con los datos copiados del DTO
	 */
	public static Mascota convertirMascotaDTOAMascota(MascotaDTO dto) {
		Mascota entidad = new Mascota();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setEspecie(dto.getEspecie());
		entidad.setRaza(dto.getRaza());
		entidad.setColor(dto.getColor());
		entidad.setApartamento(dto.getApartamento());
		entidad.setFechaVacunacion(dto.getFechaVacunacion());
		entidad.setVacunasAlDia(dto.isVacunasAlDia());
		return entidad;
	}

	/**
	 * Convierte una lista de Mascota a una lista de MascotaDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de MascotaDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Mascota a convertir. listaEntidad != null
	 * @return ArrayList de MascotaDTO equivalentes
	 */
	public static ArrayList<MascotaDTO> convertirListaMascotaAListaMascotaDTO(ArrayList<Mascota> listaEntidad) {
		ArrayList<MascotaDTO> listaDTO = new ArrayList<>();
		for (Mascota entidad : listaEntidad) {
			listaDTO.add(convertirMascotaAMascotaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de MascotaDTO a una lista de Mascota.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Mascota equivalente. <br>
	 *
	 * @param listaDTO Lista de MascotaDTO a convertir. listaDTO != null
	 * @return ArrayList de Mascota equivalentes
	 */
	public static ArrayList<Mascota> convertirListaMascotaDTOAListaMascota(ArrayList<MascotaDTO> listaDTO) {
		ArrayList<Mascota> listaEntidad = new ArrayList<>();
		for (MascotaDTO dto : listaDTO) {
			listaEntidad.add(convertirMascotaDTOAMascota(dto));
		}
		return listaEntidad;
	}

	//PARQUEADERO

	/**
	 * Convierte un objeto Parqueadero a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ParqueaderoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Parqueadero a convertir. entidad != null
	 * @return ParqueaderoDTO con los datos copiados de la entidad
	 */
	public static ParqueaderoDTO convertirParqueaderoAParqueaderoDTO(Parqueadero entidad) {
		ParqueaderoDTO dto = new ParqueaderoDTO();
		dto.setId(entidad.getId());
		dto.setNumero(entidad.getNumero());
		dto.setTipo(entidad.getTipo());
		dto.setEstado(entidad.getEstado());
		dto.setConjunto(entidad.getConjunto());
		dto.setVehiculoAsignado(entidad.getVehiculoAsignado());
		return dto;
	}

	/**
	 * Convierte un ParqueaderoDTO a su entidad de dominio Parqueadero.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Parqueadero con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ParqueaderoDTO a convertir. dto != null
	 * @return Parqueadero con los datos copiados del DTO
	 */
	public static Parqueadero convertirParqueaderoDTOAParqueadero(ParqueaderoDTO dto) {
		Parqueadero entidad = new Parqueadero();
		entidad.setId(dto.getId());
		entidad.setNumero(dto.getNumero());
		entidad.setTipo(dto.getTipo());
		entidad.setEstado(dto.getEstado());
		entidad.setConjunto(dto.getConjunto());
		entidad.setVehiculoAsignado(dto.getVehiculoAsignado());
		return entidad;
	}

	/**
	 * Convierte una lista de Parqueadero a una lista de ParqueaderoDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ParqueaderoDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Parqueadero a convertir. listaEntidad != null
	 * @return ArrayList de ParqueaderoDTO equivalentes
	 */
	public static ArrayList<ParqueaderoDTO> convertirListaParqueaderoAListaParqueaderoDTO(
			ArrayList<Parqueadero> listaEntidad) {
		ArrayList<ParqueaderoDTO> listaDTO = new ArrayList<>();
		for (Parqueadero entidad : listaEntidad) {
			listaDTO.add(convertirParqueaderoAParqueaderoDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ParqueaderoDTO a una lista de Parqueadero.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Parqueadero equivalente. <br>
	 *
	 * @param listaDTO Lista de ParqueaderoDTO a convertir. listaDTO != null
	 * @return ArrayList de Parqueadero equivalentes
	 */
	public static ArrayList<Parqueadero> convertirListaParqueaderoDTOAListaParqueadero(
			ArrayList<ParqueaderoDTO> listaDTO) {
		ArrayList<Parqueadero> listaEntidad = new ArrayList<>();
		for (ParqueaderoDTO dto : listaDTO) {
			listaEntidad.add(convertirParqueaderoDTOAParqueadero(dto));
		}
		return listaEntidad;
	}

	// ZONA COMUN

	/**
	 * Convierte un objeto ZonaComun a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ZonaComunDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto ZonaComun a convertir. entidad != null
	 * @return ZonaComunDTO con los datos copiados de la entidad
	 */
	public static ZonaComunDTO convertirZonaComunAZonaComunDTO(ZonaComun entidad) {
		ZonaComunDTO dto = new ZonaComunDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setTipo(entidad.getTipo());
		dto.setEstado(entidad.getEstado());
		dto.setConjunto(entidad.getConjunto());
		dto.setAforoMaximo(entidad.getAforoMaximo());
		dto.setCostoReserva(entidad.getCostoReserva());
		dto.setHoraApertura(entidad.getHoraApertura());
		dto.setHoraCierre(entidad.getHoraCierre());
		dto.setRequiereReserva(entidad.isRequiereReserva());
		return dto;
	}

	/**
	 * Convierte un ZonaComunDTO a su entidad de dominio ZonaComun.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un ZonaComun con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ZonaComunDTO a convertir. dto != null
	 * @return ZonaComun con los datos copiados del DTO
	 */
	public static ZonaComun convertirZonaComunDTOAZonaComun(ZonaComunDTO dto) {
		ZonaComun entidad = new ZonaComun();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setTipo(dto.getTipo());
		entidad.setEstado(dto.getEstado());
		entidad.setConjunto(dto.getConjunto());
		entidad.setAforoMaximo(dto.getAforoMaximo());
		entidad.setCostoReserva(dto.getCostoReserva());
		entidad.setHoraApertura(dto.getHoraApertura());
		entidad.setHoraCierre(dto.getHoraCierre());
		entidad.setRequiereReserva(dto.isRequiereReserva());
		return entidad;
	}

	/**
	 * Convierte una lista de ZonaComun a una lista de ZonaComunDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ZonaComunDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de ZonaComun a convertir. listaEntidad != null
	 * @return ArrayList de ZonaComunDTO equivalentes
	 */
	public static ArrayList<ZonaComunDTO> convertirListaZonaComunAListaZonaComunDTO(ArrayList<ZonaComun> listaEntidad) {
		ArrayList<ZonaComunDTO> listaDTO = new ArrayList<>();
		for (ZonaComun entidad : listaEntidad) {
			listaDTO.add(convertirZonaComunAZonaComunDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ZonaComunDTO a una lista de ZonaComun.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de ZonaComun equivalente. <br>
	 *
	 * @param listaDTO Lista de ZonaComunDTO a convertir. listaDTO != null
	 * @return ArrayList de ZonaComun equivalentes
	 */
	public static ArrayList<ZonaComun> convertirListaZonaComunDTOAListaZonaComun(ArrayList<ZonaComunDTO> listaDTO) {
		ArrayList<ZonaComun> listaEntidad = new ArrayList<>();
		for (ZonaComunDTO dto : listaDTO) {
			listaEntidad.add(convertirZonaComunDTOAZonaComun(dto));
		}
		return listaEntidad;
	}

	//VISITANTE

	/**
	 * Convierte un objeto Visitante a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un VisitanteDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Visitante a convertir. entidad != null
	 * @return VisitanteDTO con los datos copiados de la entidad
	 */
	public static VisitanteDTO convertirVisitanteAVisitanteDTO(Visitante entidad) {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setCedula(entidad.getCedula());
		dto.setTelefono(entidad.getTelefono());
		dto.setTipo(entidad.getTipo());
		dto.setApartamentoDestino(entidad.getApartamentoDestino());
		dto.setAutorizadoPor(entidad.getAutorizadoPor());
		dto.setFechaAutorizacion(entidad.getFechaAutorizacion());
		dto.setFechaVencimiento(entidad.getFechaVencimiento());
		dto.setActivo(entidad.isActivo());
		return dto;
	}

	/**
	 * Convierte un VisitanteDTO a su entidad de dominio Visitante.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Visitante con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto VisitanteDTO a convertir. dto != null
	 * @return Visitante con los datos copiados del DTO
	 */
	public static Visitante convertirVisitanteDTOAVisitante(VisitanteDTO dto) {
		Visitante entidad = new Visitante();
		entidad.setId(dto.getId());
		entidad.setNombre(dto.getNombre());
		entidad.setCedula(dto.getCedula());
		entidad.setTelefono(dto.getTelefono());
		entidad.setTipo(dto.getTipo());
		entidad.setApartamentoDestino(dto.getApartamentoDestino());
		entidad.setAutorizadoPor(dto.getAutorizadoPor());
		entidad.setFechaAutorizacion(dto.getFechaAutorizacion());
		entidad.setFechaVencimiento(dto.getFechaVencimiento());
		entidad.setActivo(dto.isActivo());
		return entidad;
	}

	/**
	 * Convierte una lista de Visitante a una lista de VisitanteDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de VisitanteDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Visitante a convertir. listaEntidad != null
	 * @return ArrayList de VisitanteDTO equivalentes
	 */
	public static ArrayList<VisitanteDTO> convertirListaVisitanteAListaVisitanteDTO(ArrayList<Visitante> listaEntidad) {
		ArrayList<VisitanteDTO> listaDTO = new ArrayList<>();
		for (Visitante entidad : listaEntidad) {
			listaDTO.add(convertirVisitanteAVisitanteDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de VisitanteDTO a una lista de Visitante.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Visitante equivalente. <br>
	 *
	 * @param listaDTO Lista de VisitanteDTO a convertir. listaDTO != null
	 * @return ArrayList de Visitante equivalentes
	 */
	public static ArrayList<Visitante> convertirListaVisitanteDTOAListaVisitante(ArrayList<VisitanteDTO> listaDTO) {
		ArrayList<Visitante> listaEntidad = new ArrayList<>();
		for (VisitanteDTO dto : listaDTO) {
			listaEntidad.add(convertirVisitanteDTOAVisitante(dto));
		}
		return listaEntidad;
	}

	//REGISTRO VISITA

	/**
	 * Convierte un objeto RegistroVisita a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un RegistroVisitaDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto RegistroVisita a convertir. entidad != null
	 * @return RegistroVisitaDTO con los datos copiados de la entidad
	 */
	public static RegistroVisitaDTO convertirRegistroVisitaARegistroVisitaDTO(RegistroVisita entidad) {
		RegistroVisitaDTO dto = new RegistroVisitaDTO();
		dto.setId(entidad.getId());
		dto.setVisitante(entidad.getVisitante());
		dto.setHoraEntrada(entidad.getHoraEntrada());
		dto.setHoraSalida(entidad.getHoraSalida());
		dto.setVigilanteRegistro(entidad.getVigilanteRegistro());
		return dto;
	}

	/**
	 * Convierte un RegistroVisitaDTO a su entidad de dominio RegistroVisita.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un RegistroVisita con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto RegistroVisitaDTO a convertir. dto != null
	 * @return RegistroVisita con los datos copiados del DTO
	 */
	public static RegistroVisita convertirRegistroVisitaDTOARegistroVisita(RegistroVisitaDTO dto) {
		RegistroVisita entidad = new RegistroVisita();
		entidad.setId(dto.getId());
		entidad.setVisitante(dto.getVisitante());
		entidad.setHoraEntrada(dto.getHoraEntrada());
		entidad.setHoraSalida(dto.getHoraSalida());
		entidad.setVigilanteRegistro(dto.getVigilanteRegistro());
		return entidad;
	}

	/**
	 * Convierte una lista de RegistroVisita a una lista de RegistroVisitaDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroVisitaDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de RegistroVisita a convertir. listaEntidad != null
	 * @return ArrayList de RegistroVisitaDTO equivalentes
	 */
	public static ArrayList<RegistroVisitaDTO> convertirListaRegistroVisitaAListaRegistroVisitaDTO(
			ArrayList<RegistroVisita> listaEntidad) {
		ArrayList<RegistroVisitaDTO> listaDTO = new ArrayList<>();
		for (RegistroVisita entidad : listaEntidad) {
			listaDTO.add(convertirRegistroVisitaARegistroVisitaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de RegistroVisitaDTO a una lista de RegistroVisita.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroVisita equivalente. <br>
	 *
	 * @param listaDTO Lista de RegistroVisitaDTO a convertir. listaDTO != null
	 * @return ArrayList de RegistroVisita equivalentes
	 */
	public static ArrayList<RegistroVisita> convertirListaRegistroVisitaDTOAListaRegistroVisita(
			ArrayList<RegistroVisitaDTO> listaDTO) {
		ArrayList<RegistroVisita> listaEntidad = new ArrayList<>();
		for (RegistroVisitaDTO dto : listaDTO) {
			listaEntidad.add(convertirRegistroVisitaDTOARegistroVisita(dto));
		}
		return listaEntidad;
	}

	//PAQUETE 

	/**
	 * Convierte un objeto Paquete a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un PaqueteDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Paquete a convertir. entidad != null
	 * @return PaqueteDTO con los datos copiados de la entidad
	 */
	public static PaqueteDTO convertirPaqueteAPaqueteDTO(Paquete entidad) {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId(entidad.getId());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setRemitente(entidad.getRemitente());
		dto.setApartamentoDestino(entidad.getApartamentoDestino());
		dto.setFechaRecepcion(entidad.getFechaRecepcion());
		dto.setVigilanteRecibio(entidad.getVigilanteRecibio());
		dto.setEstado(entidad.getEstado());
		dto.setFechaEntrega(entidad.getFechaEntrega());
		dto.setPersonaRecibio(entidad.getPersonaRecibio());
		return dto;
	}

	/**
	 * Convierte un PaqueteDTO a su entidad de dominio Paquete.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Paquete con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto PaqueteDTO a convertir. dto != null
	 * @return Paquete con los datos copiados del DTO
	 */
	public static Paquete convertirPaqueteDTOAPaquete(PaqueteDTO dto) {
		Paquete entidad = new Paquete();
		entidad.setId(dto.getId());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setRemitente(dto.getRemitente());
		entidad.setApartamentoDestino(dto.getApartamentoDestino());
		entidad.setFechaRecepcion(dto.getFechaRecepcion());
		entidad.setVigilanteRecibio(dto.getVigilanteRecibio());
		entidad.setEstado(dto.getEstado());
		entidad.setFechaEntrega(dto.getFechaEntrega());
		entidad.setPersonaRecibio(dto.getPersonaRecibio());
		return entidad;
	}

	/**
	 * Convierte una lista de Paquete a una lista de PaqueteDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de PaqueteDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Paquete a convertir. listaEntidad != null
	 * @return ArrayList de PaqueteDTO equivalentes
	 */
	public static ArrayList<PaqueteDTO> convertirListaPaqueteAListaPaqueteDTO(ArrayList<Paquete> listaEntidad) {
		ArrayList<PaqueteDTO> listaDTO = new ArrayList<>();
		for (Paquete entidad : listaEntidad) {
			listaDTO.add(convertirPaqueteAPaqueteDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de PaqueteDTO a una lista de Paquete.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Paquete equivalente. <br>
	 *
	 * @param listaDTO Lista de PaqueteDTO a convertir. listaDTO != null
	 * @return ArrayList de Paquete equivalentes
	 */
	public static ArrayList<Paquete> convertirListaPaqueteDTOAListaPaquete(ArrayList<PaqueteDTO> listaDTO) {
		ArrayList<Paquete> listaEntidad = new ArrayList<>();
		for (PaqueteDTO dto : listaDTO) {
			listaEntidad.add(convertirPaqueteDTOAPaquete(dto));
		}
		return listaEntidad;
	}

	//RESERVA

	/**
	 * Convierte un objeto Reserva a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ReservaDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Reserva a convertir. entidad != null
	 * @return ReservaDTO con los datos copiados de la entidad
	 */
	public static ReservaDTO convertirReservaAReservaDTO(Reserva entidad) {
		ReservaDTO dto = new ReservaDTO();
		dto.setId(entidad.getId());
		dto.setApartamento(entidad.getApartamento());
		dto.setZona(entidad.getZona());
		dto.setFecha(entidad.getFecha());
		dto.setHoraInicio(entidad.getHoraInicio());
		dto.setHoraFin(entidad.getHoraFin());
		dto.setEstado(entidad.getEstado());
		dto.setFechaSolicitud(entidad.getFechaSolicitud());
		dto.setMotivoCancelacion(entidad.getMotivoCancelacion());
		return dto;
	}

	/**
	 * Convierte un ReservaDTO a su entidad de dominio Reserva.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Reserva con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ReservaDTO a convertir. dto != null
	 * @return Reserva con los datos copiados del DTO
	 */
	public static Reserva convertirReservaDTOAReserva(ReservaDTO dto) {
		Reserva entidad = new Reserva();
		entidad.setId(dto.getId());
		entidad.setApartamento(dto.getApartamento());
		entidad.setZona(dto.getZona());
		entidad.setFecha(dto.getFecha());
		entidad.setHoraInicio(dto.getHoraInicio());
		entidad.setHoraFin(dto.getHoraFin());
		entidad.setEstado(dto.getEstado());
		entidad.setFechaSolicitud(dto.getFechaSolicitud());
		entidad.setMotivoCancelacion(dto.getMotivoCancelacion());
		return entidad;
	}

	/**
	 * Convierte una lista de Reserva a una lista de ReservaDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ReservaDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Reserva a convertir. listaEntidad != null
	 * @return ArrayList de ReservaDTO equivalentes
	 */
	public static ArrayList<ReservaDTO> convertirListaReservaAListaReservaDTO(ArrayList<Reserva> listaEntidad) {
		ArrayList<ReservaDTO> listaDTO = new ArrayList<>();
		for (Reserva entidad : listaEntidad) {
			listaDTO.add(convertirReservaAReservaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ReservaDTO a una lista de Reserva.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Reserva equivalente. <br>
	 *
	 * @param listaDTO Lista de ReservaDTO a convertir. listaDTO != null
	 * @return ArrayList de Reserva equivalentes
	 */
	public static ArrayList<Reserva> convertirListaReservaDTOAListaReserva(ArrayList<ReservaDTO> listaDTO) {
		ArrayList<Reserva> listaEntidad = new ArrayList<>();
		for (ReservaDTO dto : listaDTO) {
			listaEntidad.add(convertirReservaDTOAReserva(dto));
		}
		return listaEntidad;
	}

	//SOLICITUD MANTENIMIENTO

	/**
	 * Convierte un objeto SolicitudMantenimiento a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un SolicitudMantenimientoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto SolicitudMantenimiento a convertir. entidad != null
	 * @return SolicitudMantenimientoDTO con los datos copiados de la entidad
	 */
	public static SolicitudMantenimientoDTO convertirSolicitudASolicitudDTO(SolicitudMantenimiento entidad) {
		SolicitudMantenimientoDTO dto = new SolicitudMantenimientoDTO();
		dto.setId(entidad.getId());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setTipo(entidad.getTipo());
		dto.setPrioridad(entidad.getPrioridad());
		dto.setEstado(entidad.getEstado());
		dto.setAptSolicitante(entidad.getAptSolicitante());
		dto.setZonaAfectada(entidad.getZonaAfectada());
		dto.setTecnicoAsignado(entidad.getTecnicoAsignado());
		dto.setFechaRegistro(entidad.getFechaRegistro());
		dto.setFechaLimite(entidad.getFechaLimite());
		dto.setFechaCierre(entidad.getFechaCierre());
		dto.setObservaciones(entidad.getObservaciones());
		return dto;
	}

	/**
	 * Convierte un SolicitudMantenimientoDTO a su entidad de dominio SolicitudMantenimiento.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un SolicitudMantenimiento con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto SolicitudMantenimientoDTO a convertir. dto != null
	 * @return SolicitudMantenimiento con los datos copiados del DTO
	 */
	public static SolicitudMantenimiento convertirSolicitudDTOASolicitud(SolicitudMantenimientoDTO dto) {
		SolicitudMantenimiento entidad = new SolicitudMantenimiento();
		entidad.setId(dto.getId());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setTipo(dto.getTipo());
		entidad.setPrioridad(dto.getPrioridad());
		entidad.setEstado(dto.getEstado());
		entidad.setAptSolicitante(dto.getAptSolicitante());
		entidad.setZonaAfectada(dto.getZonaAfectada());
		entidad.setTecnicoAsignado(dto.getTecnicoAsignado());
		entidad.setFechaRegistro(dto.getFechaRegistro());
		entidad.setFechaLimite(dto.getFechaLimite());
		entidad.setFechaCierre(dto.getFechaCierre());
		entidad.setObservaciones(dto.getObservaciones());
		return entidad;
	}

	/**
	 * Convierte una lista de SolicitudMantenimiento a una lista de SolicitudMantenimientoDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de SolicitudMantenimientoDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de SolicitudMantenimiento a convertir. listaEntidad != null
	 * @return ArrayList de SolicitudMantenimientoDTO equivalentes
	 */
	public static ArrayList<SolicitudMantenimientoDTO> convertirListaSolicitudAListaSolicitudDTO(
			ArrayList<SolicitudMantenimiento> listaEntidad) {
		ArrayList<SolicitudMantenimientoDTO> listaDTO = new ArrayList<>();
		for (SolicitudMantenimiento entidad : listaEntidad) {
			listaDTO.add(convertirSolicitudASolicitudDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de SolicitudMantenimientoDTO a una lista de SolicitudMantenimiento.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de SolicitudMantenimiento equivalente. <br>
	 *
	 * @param listaDTO Lista de SolicitudMantenimientoDTO a convertir. listaDTO != null
	 * @return ArrayList de SolicitudMantenimiento equivalentes
	 */
	public static ArrayList<SolicitudMantenimiento> convertirListaSolicitudDTOAListaSolicitud(
			ArrayList<SolicitudMantenimientoDTO> listaDTO) {
		ArrayList<SolicitudMantenimiento> listaEntidad = new ArrayList<>();
		for (SolicitudMantenimientoDTO dto : listaDTO) {
			listaEntidad.add(convertirSolicitudDTOASolicitud(dto));
		}
		return listaEntidad;
	}

	//INCIDENTE 

	/**
	 * Convierte un objeto Incidente a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un IncidenteDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Incidente a convertir. entidad != null
	 * @return IncidenteDTO con los datos copiados de la entidad
	 */
	public static IncidenteDTO convertirIncidenteAIncidenteDTO(Incidente entidad) {
		IncidenteDTO dto = new IncidenteDTO();
		dto.setId(entidad.getId());
		dto.setTipo(entidad.getTipo());
		dto.setGravedad(entidad.getGravedad());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setApartamentoInvolucrado(entidad.getApartamentoInvolucrado());
		dto.setReportadoPor(entidad.getReportadoPor());
		dto.setFechaOcurrencia(entidad.getFechaOcurrencia());
		dto.setEstado(entidad.getEstado());
		dto.setGeneraMulta(entidad.isGeneraMulta());
		dto.setResolucion(entidad.getResolucion());
		return dto;
	}

	/**
	 * Convierte un IncidenteDTO a su entidad de dominio Incidente.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Incidente con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto IncidenteDTO a convertir. dto != null
	 * @return Incidente con los datos copiados del DTO
	 */
	public static Incidente convertirIncidenteDTOAIncidente(IncidenteDTO dto) {
		Incidente entidad = new Incidente();
		entidad.setId(dto.getId());
		entidad.setTipo(dto.getTipo());
		entidad.setGravedad(dto.getGravedad());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setApartamentoInvolucrado(dto.getApartamentoInvolucrado());
		entidad.setReportadoPor(dto.getReportadoPor());
		entidad.setFechaOcurrencia(dto.getFechaOcurrencia());
		entidad.setEstado(dto.getEstado());
		entidad.setGeneraMulta(dto.isGeneraMulta());
		entidad.setResolucion(dto.getResolucion());
		return entidad;
	}

	/**
	 * Convierte una lista de Incidente a una lista de IncidenteDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de IncidenteDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Incidente a convertir. listaEntidad != null
	 * @return ArrayList de IncidenteDTO equivalentes
	 */
	public static ArrayList<IncidenteDTO> convertirListaIncidenteAListaIncidenteDTO(ArrayList<Incidente> listaEntidad) {
		ArrayList<IncidenteDTO> listaDTO = new ArrayList<>();
		for (Incidente entidad : listaEntidad) {
			listaDTO.add(convertirIncidenteAIncidenteDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de IncidenteDTO a una lista de Incidente.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Incidente equivalente. <br>
	 *
	 * @param listaDTO Lista de IncidenteDTO a convertir. listaDTO != null
	 * @return ArrayList de Incidente equivalentes
	 */
	public static ArrayList<Incidente> convertirListaIncidenteDTOAListaIncidente(ArrayList<IncidenteDTO> listaDTO) {
		ArrayList<Incidente> listaEntidad = new ArrayList<>();
		for (IncidenteDTO dto : listaDTO) {
			listaEntidad.add(convertirIncidenteDTOAIncidente(dto));
		}
		return listaEntidad;
	}

	//OBLIGACION

	/**
	 * Convierte un objeto Obligacion a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ObligacionDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Obligacion a convertir. entidad != null
	 * @return ObligacionDTO con los datos copiados de la entidad
	 */
	public static ObligacionDTO convertirObligacionAObligacionDTO(Obligacion entidad) {
		ObligacionDTO dto = new ObligacionDTO();
		dto.setId(entidad.getId());
		dto.setApartamento(entidad.getApartamento());
		dto.setTipo(entidad.getTipo());
		dto.setMonto(entidad.getMonto());
		dto.setFechaGeneracion(entidad.getFechaGeneracion());
		dto.setFechaLimite(entidad.getFechaLimite());
		dto.setEstado(entidad.getEstado());
		dto.setConcepto(entidad.getConcepto());
		dto.setGeneradaPor(entidad.getGeneradaPor());
		return dto;
	}

	/**
	 * Convierte un ObligacionDTO a su entidad de dominio Obligacion.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Obligacion con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ObligacionDTO a convertir. dto != null
	 * @return Obligacion con los datos copiados del DTO
	 */
	public static Obligacion convertirObligacionDTOAObligacion(ObligacionDTO dto) {
		Obligacion entidad = new Obligacion();
		entidad.setId(dto.getId());
		entidad.setApartamento(dto.getApartamento());
		entidad.setTipo(dto.getTipo());
		entidad.setMonto(dto.getMonto());
		entidad.setFechaGeneracion(dto.getFechaGeneracion());
		entidad.setFechaLimite(dto.getFechaLimite());
		entidad.setEstado(dto.getEstado());
		entidad.setConcepto(dto.getConcepto());
		entidad.setGeneradaPor(dto.getGeneradaPor());
		return entidad;
	}

	/**
	 * Convierte una lista de Obligacion a una lista de ObligacionDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ObligacionDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Obligacion a convertir. listaEntidad != null
	 * @return ArrayList de ObligacionDTO equivalentes
	 */
	public static ArrayList<ObligacionDTO> convertirListaObligacionAListaObligacionDTO(
			ArrayList<Obligacion> listaEntidad) {
		ArrayList<ObligacionDTO> listaDTO = new ArrayList<>();
		for (Obligacion entidad : listaEntidad) {
			listaDTO.add(convertirObligacionAObligacionDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ObligacionDTO a una lista de Obligacion.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Obligacion equivalente. <br>
	 *
	 * @param listaDTO Lista de ObligacionDTO a convertir. listaDTO != null
	 * @return ArrayList de Obligacion equivalentes
	 */
	public static ArrayList<Obligacion> convertirListaObligacionDTOAListaObligacion(ArrayList<ObligacionDTO> listaDTO) {
		ArrayList<Obligacion> listaEntidad = new ArrayList<>();
		for (ObligacionDTO dto : listaDTO) {
			listaEntidad.add(convertirObligacionDTOAObligacion(dto));
		}
		return listaEntidad;
	}

	//PAGO

	/**
	 * Convierte un objeto Pago a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un PagoDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Pago a convertir. entidad != null
	 * @return PagoDTO con los datos copiados de la entidad
	 */
	public static PagoDTO convertirPagoAPagoDTO(Pago entidad) {
		PagoDTO dto = new PagoDTO();
		dto.setId(entidad.getId());
		dto.setApartamento(entidad.getApartamento());
		dto.setMonto(entidad.getMonto());
		dto.setFechaPago(entidad.getFechaPago());
		dto.setMedioPago(entidad.getMedioPago());
		dto.setReferencia(entidad.getReferencia());
		dto.setRegistradoPor(entidad.getRegistradoPor());
		return dto;
	}

	/**
	 * Convierte un PagoDTO a su entidad de dominio Pago.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Pago con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto PagoDTO a convertir. dto != null
	 * @return Pago con los datos copiados del DTO
	 */
	public static Pago convertirPagoDTOAPago(PagoDTO dto) {
		Pago entidad = new Pago();
		entidad.setId(dto.getId());
		entidad.setApartamento(dto.getApartamento());
		entidad.setMonto(dto.getMonto());
		entidad.setFechaPago(dto.getFechaPago());
		entidad.setMedioPago(dto.getMedioPago());
		entidad.setReferencia(dto.getReferencia());
		entidad.setRegistradoPor(dto.getRegistradoPor());
		return entidad;
	}

	/**
	 * Convierte una lista de Pago a una lista de PagoDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de PagoDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Pago a convertir. listaEntidad != null
	 * @return ArrayList de PagoDTO equivalentes
	 */
	public static ArrayList<PagoDTO> convertirListaPagoAListaPagoDTO(ArrayList<Pago> listaEntidad) {
		ArrayList<PagoDTO> listaDTO = new ArrayList<>();
		for (Pago entidad : listaEntidad) {
			listaDTO.add(convertirPagoAPagoDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de PagoDTO a una lista de Pago.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Pago equivalente. <br>
	 *
	 * @param listaDTO Lista de PagoDTO a convertir. listaDTO != null
	 * @return ArrayList de Pago equivalentes
	 */
	public static ArrayList<Pago> convertirListaPagoDTOAListaPago(ArrayList<PagoDTO> listaDTO) {
		ArrayList<Pago> listaEntidad = new ArrayList<>();
		for (PagoDTO dto : listaDTO) {
			listaEntidad.add(convertirPagoDTOAPago(dto));
		}
		return listaEntidad;
	}

	//CAMPANA AMBIENTAL

	/**
	 * Convierte un objeto CampanaAmbiental a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un CampanaAmbientalDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto CampanaAmbiental a convertir. entidad != null
	 * @return CampanaAmbientalDTO con los datos copiados de la entidad
	 */
	public static CampanaAmbientalDTO convertirCampanaACampanaDTO(CampanaAmbiental entidad) {
		CampanaAmbientalDTO dto = new CampanaAmbientalDTO();
		dto.setId(entidad.getId());
		dto.setConjunto(entidad.getConjunto());
		dto.setNombre(entidad.getNombre());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setTipo(entidad.getTipo());
		dto.setFechaInicio(entidad.getFechaInicio());
		dto.setFechaFin(entidad.getFechaFin());
		dto.setMeta(entidad.getMeta());
		dto.setEstado(entidad.getEstado());
		dto.setResultados(entidad.getResultados());
		return dto;
	}

	/**
	 * Convierte un CampanaAmbientalDTO a su entidad de dominio CampanaAmbiental.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un CampanaAmbiental con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto CampanaAmbientalDTO a convertir. dto != null
	 * @return CampanaAmbiental con los datos copiados del DTO
	 */
	public static CampanaAmbiental convertirCampanaDTOACampana(CampanaAmbientalDTO dto) {
		CampanaAmbiental entidad = new CampanaAmbiental();
		entidad.setId(dto.getId());
		entidad.setConjunto(dto.getConjunto());
		entidad.setNombre(dto.getNombre());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setTipo(dto.getTipo());
		entidad.setFechaInicio(dto.getFechaInicio());
		entidad.setFechaFin(dto.getFechaFin());
		entidad.setMeta(dto.getMeta());
		entidad.setEstado(dto.getEstado());
		entidad.setResultados(dto.getResultados());
		return entidad;
	}

	/**
	 * Convierte una lista de CampanaAmbiental a una lista de CampanaAmbientalDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de CampanaAmbientalDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de CampanaAmbiental a convertir. listaEntidad != null
	 * @return ArrayList de CampanaAmbientalDTO equivalentes
	 */
	public static ArrayList<CampanaAmbientalDTO> convertirListaCampanaAListaCampanaDTO(
			ArrayList<CampanaAmbiental> listaEntidad) {
		ArrayList<CampanaAmbientalDTO> listaDTO = new ArrayList<>();
		for (CampanaAmbiental entidad : listaEntidad) {
			listaDTO.add(convertirCampanaACampanaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de CampanaAmbientalDTO a una lista de CampanaAmbiental.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de CampanaAmbiental equivalente. <br>
	 *
	 * @param listaDTO Lista de CampanaAmbientalDTO a convertir. listaDTO != null
	 * @return ArrayList de CampanaAmbiental equivalentes
	 */
	public static ArrayList<CampanaAmbiental> convertirListaCampanaDTOAListaCampana(
			ArrayList<CampanaAmbientalDTO> listaDTO) {
		ArrayList<CampanaAmbiental> listaEntidad = new ArrayList<>();
		for (CampanaAmbientalDTO dto : listaDTO) {
			listaEntidad.add(convertirCampanaDTOACampana(dto));
		}
		return listaEntidad;
	}

	// PARTICIPACION 

	/**
	 * Convierte un objeto Participacion a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ParticipacionDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Participacion a convertir. entidad != null
	 * @return ParticipacionDTO con los datos copiados de la entidad
	 */
	public static ParticipacionDTO convertirParticipacionAParticipacionDTO(Participacion entidad) {
		ParticipacionDTO dto = new ParticipacionDTO();
		dto.setId(entidad.getId());
		dto.setCampana(entidad.getCampana());
		dto.setApartamento(entidad.getApartamento());
		dto.setResidente(entidad.getResidente());
		dto.setFechaParticipacion(entidad.getFechaParticipacion());
		dto.setObservacion(entidad.getObservacion());
		return dto;
	}

	/**
	 * Convierte un ParticipacionDTO a su entidad de dominio Participacion.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Participacion con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ParticipacionDTO a convertir. dto != null
	 * @return Participacion con los datos copiados del DTO
	 */
	public static Participacion convertirParticipacionDTOAParticipacion(ParticipacionDTO dto) {
		Participacion entidad = new Participacion();
		entidad.setId(dto.getId());
		entidad.setCampana(dto.getCampana());
		entidad.setApartamento(dto.getApartamento());
		entidad.setResidente(dto.getResidente());
		entidad.setFechaParticipacion(dto.getFechaParticipacion());
		entidad.setObservacion(dto.getObservacion());
		return entidad;
	}

	/**
	 * Convierte una lista de Participacion a una lista de ParticipacionDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ParticipacionDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Participacion a convertir. listaEntidad != null
	 * @return ArrayList de ParticipacionDTO equivalentes
	 */
	public static ArrayList<ParticipacionDTO> convertirListaParticipacionAListaParticipacionDTO(
			ArrayList<Participacion> listaEntidad) {
		ArrayList<ParticipacionDTO> listaDTO = new ArrayList<>();
		for (Participacion entidad : listaEntidad) {
			listaDTO.add(convertirParticipacionAParticipacionDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ParticipacionDTO a una lista de Participacion.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Participacion equivalente. <br>
	 *
	 * @param listaDTO Lista de ParticipacionDTO a convertir. listaDTO != null
	 * @return ArrayList de Participacion equivalentes
	 */
	public static ArrayList<Participacion> convertirListaParticipacionDTOAListaParticipacion(
			ArrayList<ParticipacionDTO> listaDTO) {
		ArrayList<Participacion> listaEntidad = new ArrayList<>();
		for (ParticipacionDTO dto : listaDTO) {
			listaEntidad.add(convertirParticipacionDTOAParticipacion(dto));
		}
		return listaEntidad;
	}

	//REGISTRO CONSUMO ENERGIA 

	/**
	 * Convierte un objeto RegistroConsumoEnergia a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un RegistroConsumoEnergiaDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto RegistroConsumoEnergia a convertir. entidad != null
	 * @return RegistroConsumoEnergiaDTO con los datos copiados de la entidad
	 */
	public static RegistroConsumoEnergiaDTO convertirEnergiaAEnergiaDTO(RegistroConsumoEnergia entidad) {
		RegistroConsumoEnergiaDTO dto = new RegistroConsumoEnergiaDTO();
		dto.setId(entidad.getId());
		dto.setConjunto(entidad.getConjunto());
		dto.setPeriodo(entidad.getPeriodo());
		dto.setFechaRegistro(entidad.getFechaRegistro());
		dto.setRegistradoPor(entidad.getRegistradoPor());
		dto.setConsumoKwh(entidad.getConsumoKwh());
		dto.setGeneracionSolarKwh(entidad.getGeneracionSolarKwh());
		dto.setConsumoNetoKwh(entidad.getConsumoNetoKwh());
		return dto;
	}

	/**
	 * Convierte un RegistroConsumoEnergiaDTO a su entidad de dominio RegistroConsumoEnergia.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un RegistroConsumoEnergia con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto RegistroConsumoEnergiaDTO a convertir. dto != null
	 * @return RegistroConsumoEnergia con los datos copiados del DTO
	 */
	public static RegistroConsumoEnergia convertirEnergiaDTOAEnergia(RegistroConsumoEnergiaDTO dto) {
		RegistroConsumoEnergia entidad = new RegistroConsumoEnergia();
		entidad.setId(dto.getId());
		entidad.setConjunto(dto.getConjunto());
		entidad.setPeriodo(dto.getPeriodo());
		entidad.setFechaRegistro(dto.getFechaRegistro());
		entidad.setRegistradoPor(dto.getRegistradoPor());
		entidad.setConsumoKwh(dto.getConsumoKwh());
		entidad.setGeneracionSolarKwh(dto.getGeneracionSolarKwh());
		entidad.setConsumoNetoKwh(dto.getConsumoNetoKwh());
		return entidad;
	}

	/**
	 * Convierte una lista de RegistroConsumoEnergia a una lista de RegistroConsumoEnergiaDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroConsumoEnergiaDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de RegistroConsumoEnergia a convertir. listaEntidad != null
	 * @return ArrayList de RegistroConsumoEnergiaDTO equivalentes
	 */
	public static ArrayList<RegistroConsumoEnergiaDTO> convertirListaEnergiaAListaEnergiaDTO(
			ArrayList<RegistroConsumoEnergia> listaEntidad) {
		ArrayList<RegistroConsumoEnergiaDTO> listaDTO = new ArrayList<>();
		for (RegistroConsumoEnergia entidad : listaEntidad) {
			listaDTO.add(convertirEnergiaAEnergiaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de RegistroConsumoEnergiaDTO a una lista de RegistroConsumoEnergia.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroConsumoEnergia equivalente. <br>
	 *
	 * @param listaDTO Lista de RegistroConsumoEnergiaDTO a convertir. listaDTO != null
	 * @return ArrayList de RegistroConsumoEnergia equivalentes
	 */
	public static ArrayList<RegistroConsumoEnergia> convertirListaEnergiaDTOAListaEnergia(
			ArrayList<RegistroConsumoEnergiaDTO> listaDTO) {
		ArrayList<RegistroConsumoEnergia> listaEntidad = new ArrayList<>();
		for (RegistroConsumoEnergiaDTO dto : listaDTO) {
			listaEntidad.add(convertirEnergiaDTOAEnergia(dto));
		}
		return listaEntidad;
	}

	//REGISTRO CONSUMO AGUA

	/**
	 * Convierte un objeto RegistroConsumoAgua a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un RegistroConsumoAguaDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto RegistroConsumoAgua a convertir. entidad != null
	 * @return RegistroConsumoAguaDTO con los datos copiados de la entidad
	 */
	public static RegistroConsumoAguaDTO convertirAguaAAguaDTO(RegistroConsumoAgua entidad) {
		RegistroConsumoAguaDTO dto = new RegistroConsumoAguaDTO();
		dto.setId(entidad.getId());
		dto.setConjunto(entidad.getConjunto());
		dto.setPeriodo(entidad.getPeriodo());
		dto.setFechaRegistro(entidad.getFechaRegistro());
		dto.setRegistradoPor(entidad.getRegistradoPor());
		dto.setConsumoMtCubico(entidad.getConsumoMtCubico());
		dto.setAguaLluviaMtCubico(entidad.getAguaLluviaMtCubico());
		dto.setConsumoNetoMtCubico(entidad.getConsumoNetoMtCubico());
		return dto;
	}

	/**
	 * Convierte un RegistroConsumoAguaDTO a su entidad de dominio RegistroConsumoAgua.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un RegistroConsumoAgua con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto RegistroConsumoAguaDTO a convertir. dto != null
	 * @return RegistroConsumoAgua con los datos copiados del DTO
	 */
	public static RegistroConsumoAgua convertirAguaDTOAAgua(RegistroConsumoAguaDTO dto) {
		RegistroConsumoAgua entidad = new RegistroConsumoAgua();
		entidad.setId(dto.getId());
		entidad.setConjunto(dto.getConjunto());
		entidad.setPeriodo(dto.getPeriodo());
		entidad.setFechaRegistro(dto.getFechaRegistro());
		entidad.setRegistradoPor(dto.getRegistradoPor());
		entidad.setConsumoMtCubico(dto.getConsumoMtCubico());
		entidad.setAguaLluviaMtCubico(dto.getAguaLluviaMtCubico());
		entidad.setConsumoNetoMtCubico(dto.getConsumoNetoMtCubico());
		return entidad;
	}

	/**
	 * Convierte una lista de RegistroConsumoAgua a una lista de RegistroConsumoAguaDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroConsumoAguaDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de RegistroConsumoAgua a convertir. listaEntidad != null
	 * @return ArrayList de RegistroConsumoAguaDTO equivalentes
	 */
	public static ArrayList<RegistroConsumoAguaDTO> convertirListaAguaAListaAguaDTO(
			ArrayList<RegistroConsumoAgua> listaEntidad) {
		ArrayList<RegistroConsumoAguaDTO> listaDTO = new ArrayList<>();
		for (RegistroConsumoAgua entidad : listaEntidad) {
			listaDTO.add(convertirAguaAAguaDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de RegistroConsumoAguaDTO a una lista de RegistroConsumoAgua.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroConsumoAgua equivalente. <br>
	 *
	 * @param listaDTO Lista de RegistroConsumoAguaDTO a convertir. listaDTO != null
	 * @return ArrayList de RegistroConsumoAgua equivalentes
	 */
	public static ArrayList<RegistroConsumoAgua> convertirListaAguaDTOAListaAgua(
			ArrayList<RegistroConsumoAguaDTO> listaDTO) {
		ArrayList<RegistroConsumoAgua> listaEntidad = new ArrayList<>();
		for (RegistroConsumoAguaDTO dto : listaDTO) {
			listaEntidad.add(convertirAguaDTOAAgua(dto));
		}
		return listaEntidad;
	}

	//REGISTRO RECICLAJE

	/**
	 * Convierte un objeto RegistroReciclaje a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un RegistroReciclajeDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto RegistroReciclaje a convertir. entidad != null
	 * @return RegistroReciclajeDTO con los datos copiados de la entidad
	 */
	public static RegistroReciclajeDTO convertirReciclajeAReciclajeDTO(RegistroReciclaje entidad) {
		RegistroReciclajeDTO dto = new RegistroReciclajeDTO();
		dto.setId(entidad.getId());
		dto.setConjunto(entidad.getConjunto());
		dto.setPeriodo(entidad.getPeriodo());
		dto.setFechaRegistro(entidad.getFechaRegistro());
		dto.setRegistradoPor(entidad.getRegistradoPor());
		dto.setKgPapel(entidad.getKgPapel());
		dto.setKgPlastico(entidad.getKgPlastico());
		dto.setKgVidrio(entidad.getKgVidrio());
		dto.setKgMetal(entidad.getKgMetal());
		dto.setKgOrganico(entidad.getKgOrganico());
		dto.setTotalKg(entidad.getTotalKg());
		return dto;
	}

	/**
	 * Convierte un RegistroReciclajeDTO a su entidad de dominio RegistroReciclaje.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un RegistroReciclaje con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto RegistroReciclajeDTO a convertir. dto != null
	 * @return RegistroReciclaje con los datos copiados del DTO
	 */
	public static RegistroReciclaje convertirReciclajeDTOAReciclaje(RegistroReciclajeDTO dto) {
		RegistroReciclaje entidad = new RegistroReciclaje();
		entidad.setId(dto.getId());
		entidad.setConjunto(dto.getConjunto());
		entidad.setPeriodo(dto.getPeriodo());
		entidad.setFechaRegistro(dto.getFechaRegistro());
		entidad.setRegistradoPor(dto.getRegistradoPor());
		entidad.setKgPapel(dto.getKgPapel());
		entidad.setKgPlastico(dto.getKgPlastico());
		entidad.setKgVidrio(dto.getKgVidrio());
		entidad.setKgMetal(dto.getKgMetal());
		entidad.setKgOrganico(dto.getKgOrganico());
		entidad.setTotalKg(dto.getTotalKg());
		return entidad;
	}

	/**
	 * Convierte una lista de RegistroReciclaje a una lista de RegistroReciclajeDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroReciclajeDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de RegistroReciclaje a convertir. listaEntidad != null
	 * @return ArrayList de RegistroReciclajeDTO equivalentes
	 */
	public static ArrayList<RegistroReciclajeDTO> convertirListaReciclajeAListaReciclajeDTO(
			ArrayList<RegistroReciclaje> listaEntidad) {
		ArrayList<RegistroReciclajeDTO> listaDTO = new ArrayList<>();
		for (RegistroReciclaje entidad : listaEntidad) {
			listaDTO.add(convertirReciclajeAReciclajeDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de RegistroReciclajeDTO a una lista de RegistroReciclaje.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de RegistroReciclaje equivalente. <br>
	 *
	 * @param listaDTO Lista de RegistroReciclajeDTO a convertir. listaDTO != null
	 * @return ArrayList de RegistroReciclaje equivalentes
	 */
	public static ArrayList<RegistroReciclaje> convertirListaReciclajeDTOAListaReciclaje(
			ArrayList<RegistroReciclajeDTO> listaDTO) {
		ArrayList<RegistroReciclaje> listaEntidad = new ArrayList<>();
		for (RegistroReciclajeDTO dto : listaDTO) {
			listaEntidad.add(convertirReciclajeDTOAReciclaje(dto));
		}
		return listaEntidad;
	}

	//REPORTE

	/**
	 * Convierte un objeto Reporte a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un ReporteDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Reporte a convertir. entidad != null
	 * @return ReporteDTO con los datos copiados de la entidad
	 */
	public static ReporteDTO convertirReporteAReporteDTO(Reporte entidad) {
		ReporteDTO dto = new ReporteDTO();
		dto.setId(entidad.getId());
		dto.setTipo(entidad.getTipo());
		dto.setTitulo(entidad.getTitulo());
		dto.setContenido(entidad.getContenido());
		dto.setFechaGeneracion(entidad.getFechaGeneracion());
		dto.setGeneradoPor(entidad.getGeneradoPor());
		dto.setConjunto(entidad.getConjunto());
		return dto;
	}

	/**
	 * Convierte un ReporteDTO a su entidad de dominio Reporte.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Reporte con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto ReporteDTO a convertir. dto != null
	 * @return Reporte con los datos copiados del DTO
	 */
	public static Reporte convertirReporteDTOAReporte(ReporteDTO dto) {
		Reporte entidad = new Reporte();
		entidad.setId(dto.getId());
		entidad.setTipo(dto.getTipo());
		entidad.setTitulo(dto.getTitulo());
		entidad.setContenido(dto.getContenido());
		entidad.setFechaGeneracion(dto.getFechaGeneracion());
		entidad.setGeneradoPor(dto.getGeneradoPor());
		entidad.setConjunto(dto.getConjunto());
		return entidad;
	}

	/**
	 * Convierte una lista de Reporte a una lista de ReporteDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de ReporteDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Reporte a convertir. listaEntidad != null
	 * @return ArrayList de ReporteDTO equivalentes
	 */
	public static ArrayList<ReporteDTO> convertirListaReporteAListaReporteDTO(ArrayList<Reporte> listaEntidad) {
		ArrayList<ReporteDTO> listaDTO = new ArrayList<>();
		for (Reporte entidad : listaEntidad) {
			listaDTO.add(convertirReporteAReporteDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de ReporteDTO a una lista de Reporte.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Reporte equivalente. <br>
	 *
	 * @param listaDTO Lista de ReporteDTO a convertir. listaDTO != null
	 * @return ArrayList de Reporte equivalentes
	 */
	public static ArrayList<Reporte> convertirListaReporteDTOAListaReporte(ArrayList<ReporteDTO> listaDTO) {
		ArrayList<Reporte> listaEntidad = new ArrayList<>();
		for (ReporteDTO dto : listaDTO) {
			listaEntidad.add(convertirReporteDTOAReporte(dto));
		}
		return listaEntidad;
	}

	//NOTIFICACION

	/**
	 * Convierte un objeto Notificacion a su representacion DTO.
	 * <b>pre</b> entidad != null. <br>
	 * <b>post</b> Se retorna un NotificacionDTO con los mismos valores de la entidad. <br>
	 *
	 * @param entidad Objeto Notificacion a convertir. entidad != null
	 * @return NotificacionDTO con los datos copiados de la entidad
	 */
	public static NotificacionDTO convertirNotificacionANotificacionDTO(Notificacion entidad) {
		NotificacionDTO dto = new NotificacionDTO();
		dto.setId(entidad.getId());
		dto.setDestinatario(entidad.getDestinatario());
		dto.setAsunto(entidad.getAsunto());
		dto.setCuerpo(entidad.getCuerpo());
		dto.setTipo(entidad.getTipo());
		dto.setFechaEnvio(entidad.getFechaEnvio());
		dto.setEnviada(entidad.isEnviada());
		dto.setErrorEnvio(entidad.getErrorEnvio());
		return dto;
	}

	/**
	 * Convierte un NotificacionDTO a su entidad de dominio Notificacion.
	 * <b>pre</b> dto != null. <br>
	 * <b>post</b> Se retorna un Notificacion con los mismos valores del DTO. <br>
	 *
	 * @param dto Objeto NotificacionDTO a convertir. dto != null
	 * @return Notificacion con los datos copiados del DTO
	 */
	public static Notificacion convertirNotificacionDTOANotificacion(NotificacionDTO dto) {
		Notificacion entidad = new Notificacion();
		entidad.setId(dto.getId());
		entidad.setDestinatario(dto.getDestinatario());
		entidad.setAsunto(dto.getAsunto());
		entidad.setCuerpo(dto.getCuerpo());
		entidad.setTipo(dto.getTipo());
		entidad.setFechaEnvio(dto.getFechaEnvio());
		entidad.setEnviada(dto.isEnviada());
		entidad.setErrorEnvio(dto.getErrorEnvio());
		return entidad;
	}

	/**
	 * Convierte una lista de Notificacion a una lista de NotificacionDTO.
	 * <b>pre</b> listaEntidad != null. <br>
	 * <b>post</b> Se retorna una lista de NotificacionDTO equivalente. <br>
	 *
	 * @param listaEntidad Lista de Notificacion a convertir. listaEntidad != null
	 * @return ArrayList de NotificacionDTO equivalentes
	 */
	public static ArrayList<NotificacionDTO> convertirListaNotificacionAListaNotificacionDTO(
			ArrayList<Notificacion> listaEntidad) {
		ArrayList<NotificacionDTO> listaDTO = new ArrayList<>();
		for (Notificacion entidad : listaEntidad) {
			listaDTO.add(convertirNotificacionANotificacionDTO(entidad));
		}
		return listaDTO;
	}

	/**
	 * Convierte una lista de NotificacionDTO a una lista de Notificacion.
	 * <b>pre</b> listaDTO != null. <br>
	 * <b>post</b> Se retorna una lista de Notificacion equivalente. <br>
	 *
	 * @param listaDTO Lista de NotificacionDTO a convertir. listaDTO != null
	 * @return ArrayList de Notificacion equivalentes
	 */
	public static ArrayList<Notificacion> convertirListaNotificacionDTOAListaNotificacion(
			ArrayList<NotificacionDTO> listaDTO) {
		ArrayList<Notificacion> listaEntidad = new ArrayList<>();
		for (NotificacionDTO dto : listaDTO) {
			listaEntidad.add(convertirNotificacionDTOANotificacion(dto));
		}
		return listaEntidad;
	}
}