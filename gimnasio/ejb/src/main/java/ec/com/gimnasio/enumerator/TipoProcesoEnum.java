package ec.com.gimnasio.enumerator;

public enum TipoProcesoEnum {
	 
	OFERTA(-2, "Oferta"), 
	CENSO(0, "Censo"), 
	NO_ACTIVIDAD(-1, "No Actividad"), 
	MATRICULA_AUTOMATICA(1, "Matricula Automática"), 
	SECRETARIA(2, "Asignacion Paralelos"), 
	POR_ENCADENAR(3, "Por Encadenar"), 
	ENCADENAMIENTO(4, "Encadenamiento"), 
	ASIGNACION(5, "Asignación"), 
	MATEMATICO(6, "Modelo Matemático"), 
	REASIGNACION(7, "Reasignación"), 
	TRASLADO(8, "Traslado"), 
	EXTRAORDINARIAS(9, "Inscripciones Extraordinarias"),
	CENSO_EXTRAORDINARIO(10, "Censo Extraordinario"),
	CONTINUAS(11, "Inscripciones Continuas"),
	TRASLADO_JORNADA(12, "Traslado de Jornadas"),
	TRASLADO_NIVEL(13, "Traslado de Nivel"),
	INDIVIDUAL_ESPECIAL(14, "Asignacion Individual Especial"),
	INDIVIDUAL_VULNERABILIDAD(15, "Asignacion Individual Vulnerabilidad"),
	INDIVIDUAL_EXAMEN(18, "Asignacion Individual Examen de Ubicacion"),
	INDIVIDUAL_OTRO(19, "Asignacion Individual Otros"),
	INDIVIDUAL_RURAL(20, "Asignacion Rural Individual"),
	INDIVIDUAL_HERMANO_FISCAl(21, "Asignacion Hermano Fiscal"),
	INDIVIDUAL_GRUPO_HERMANO(22, "Asignacion Grupo Hermanos"),
	ASIGNACION_PROTEGIDO(17, "Portegido- Oculto"),
	MIES(55,"Por encadenadar mies")
	;
	
	//16 proceso inivel, 17 ocultos
	//23 athena
	//25 y 26 no fueron encadenados y no matriculados 
	//27 bilingue
	//28 matricula nocturna
	// 29  asigancion nocturna
	
	private int codigo;
	private String descripcion;

	private TipoProcesoEnum(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
