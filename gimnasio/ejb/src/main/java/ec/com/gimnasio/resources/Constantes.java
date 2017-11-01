package ec.com.gimnasio.resources;

public class Constantes {
	
	public static final String UNCHECKED = "unchecked";
	
	//Para acceder remotamente si estan en un mismo servidor
	public static final String URL_SEGURIDADES = "control-acceso-ear/control-acceso-ejb";
	
	public static final String REGISTRO_ACTIVO = "A";
	public static final int REGISTRO_ACTIVO_NUMERO = 1;
	public static final String REGISTRO_INACTIVO = "I";

	//CONSTANTE PARA ENFERMEDADES CATASTRÓFICAS
	public static final String ENFERMEDADES_CATASTROFICAS = "ENCA";
	// CONSTANTE PARA OTRAS VULNERABILIDADES
	public static final String OTRAS_VULNERABILIDADES = "VULN";
	// CONSTANTE PARA AÑOS SIN ESTUDIAR
	public static final String ANIOS_SIN_ESTUDIAR = "SINE";
	// CONSTANTE PARA RAZONES POR LAS QUE DEJO DE ESTUDIAR
	public static final String RAZONES_NO_ESTUDIO = "RNST";
	//CONSTANTES PARA EVALUACION UDAI;
	public static final String TIPO_DISCAPACIDAD = "DICA";
	public static final String TIPO_DIFICULTAD_APRENDIZAJE = "DIAP";
	public static final String GRADO_ADAPTACION = "GRAD";
	//CONSTANTES DE TIPOS DE DISCAPACIDAD
	public static final long CODIGO_DISCAPACIDAD_VISUAL = 210;
	public static final long CODIGO_DISCAPACIDAD_AUDITIVA = 211;
	public static final long CODIGO_DISCAPACIDAD_INTELECTUAL = 212;
	public static final long CODIGO_DISCAPACIDAD_FISICA = 214;
	public static final long CODIGO_DISCAPACIDAD_MULTIDISCAPACIDAD = 216;
	public static final long CODIGO_DISCAPACIDAD_SORDOCEGUERA = 320;
	public static final String TIPO_AYUDA_VISUAL = "TAVI";
	public static final String TIPO_AYUDA_AUDITIVA = "TAAD";
	public static final String TIPO_AYUDA_INTELECTUAL = "TAIN";
	public static final String TIPO_AYUDA_FISICA = "TAFI";
	public static final String TIPO_AYUDA_OTRO = "TAAT";
	//CÓDIGOS DE REGISTRO ACTIVO E INACTIVO
	public static final boolean REGISTRO_ACTIVO_ESQUEMA_NUEVO = true;
	//PARÁMETRO RÉGIMEN
	public static final long CODIGO_REGIMEN_SIERRA = 1;
	public static final long CODIGO_REGIMEN_COSTA = 2;
	//provincia por regimen
	public static final long PROVINCIA_SIERRA = 0L;
	public static final long PROVINCIA_COSTA = 1L;
	public static final long PROVINCIA_AMBOS_CICLOS = 2L;
	//Opciones página Mapa
	public static final String TIPO_VIVIENDA = "TIVI";
	public static final String FORMA_VIVIENDA = "FOVI";
	public static final String CLASE_VIVIENDA = "CLVI";
	public static final String URBANO = "URBANA";
	public static final String AREA = "AREA";
	public static final int CODIGO_URBANA = 37;
	//CONSTANTES PARA ZONA URBANA Y RURAL
	public static final String ZONA_URBANA = "U";
	public static final String ZONA_RURAL = "R";
	//Suministro
	public static final String SUMINISTRO_AGUA = "A";
	public static final String SUMINISTRO_LUZ = "L";
	//CONSTANTES PARA VALIDAR LA BÚSQUEDA SI ES POR CALLES O SITIOS DE INTERÉS
	public static final String CONSULTA_POR_CALLES="CALLES";
	public static final String CONSULTA_POR_SITIO_INTERES="SITIO";
	public static final String CODIGO_CANTON_QUITO="1701";
	public static final String SELECCION_OTRO = "OTRO";	
	//Constantes para los tipos de inscripciones en el proceso
	public static final String INSCRIPCION_GRUPO_HERMANOS = "G";
	public static final String INSCRIPCION_INDIVIDUAL = "I";
	public static final String INSCRIPCION_CON_HERMANO = "H";
	public static final String INSCRIPCION_RURAL= "R";
	public static final String MATRICULA_AUTOMATICA= "M";
	public static final String ASIGNACION_MATEMATICA= "M";
	public static final String CUPOS_ASIGNACION_MATEMATICA= "C";
	public static final String MATRICULA_AUTOMATICA_NOCTURNA= "N";
	public static final String CUPO_DISPONIBLE= "D";
	//
	public static final String ACTIVACION_CAPA_CALLE = "CAL";	
	public static final String ACTIVACION_CAPA_MANZANA = "MAN";	
	public static final String ACTIVACION_CAPA_INTERES = "SIT";	
	public static final String ACTIVACION_CAPA_LOCALIDADES = "LOC";	
	public static final String ACTIVACION_CAPA_SECTORESBARRIOS = "SEC";
	public static final Integer TIPO_PROCESO_TRASLADO_A_FISCAL = 3;
	//PARÁMETRO CÓDIGO ECUADOR DE TABLA INS_PAIS  
	public static final long CODIGO_ECUADOR = 345;
	// NIVELES
	public static final long NIVEL_CIENCIAS = 7L;
	public static final long NIVEL_TECNICO = 8L;
	public static final long NIVEL_GRUPO_3 = 1L;
	public static final long NIVEL_GRUPO_4 = 2L;
	public static final long NIVEL_PRIMERO = 3L;
	public static final long NIVEL_NO_ESCOLARIZADA = 54L;
	public static final int REGISTRO_ACTIVO_ASIGCEN = 1;
	//CONSTANTE PARA LAS CÉDULAS DEL SISTEMA EDUCATIVO
	public static final String CEDULA_SIGEE="0000000000";
	
	//PARENTESCOS
	public static final String TIPOS_PARENTESCOS = "PARE";
	public static final long PARENTESCO_PADRE = 19;
	public static final long PARENTESCO_MADRE = 20;
	public static final long PARENTESCO_OTRO = 86;
	public static final long PARENTESCO_AUTOREPRESENTACION = 60;
	
	//PROVINCIAS
	public static final int NUMERO_PROVINCIAS_ECUADOR = 24;
	//PARÁMETRO SOSTENIMIENTO
	public static final long SOSTENIMIENTO_FISCAL = 1;
	
	public static final String FORMATO_FECHA_WS_REG_CIVIL = "dd/MM/yyyy";
	//CONSTANTE ESTADO ACIVO
	public static final long ESTADO_ACTIVO = 1L;
	//Constantes carga de archivos
	public static final int TAMANIO_MEGA = 1048576;
	public static final int CANTIDAD_MEGA = 2;
	//Cosntantes de tipo de periodo
	public static final String PERIODO_ACTUAL = "A";
	public static final String PERIODO_SIGUIENTE = "S";
	public static final int ANIO_CERO = 0;
	public static final int ANIO_UNO = 1;
	public static final int ANIO_EJECUTADO = 4;
	//estado proyecto inversion
	public static final String ESTADO_APROBADO = "Aprobado";
	public static final String ESTADO_RECHAZADO = "Rechazado";
	public static final String ESTADO_EN_REVISION = "En revisión";
	public static final String ESTADO_VIGENTE = "Vigente";
	public static final String INCREMENTO = "Autoriza Incremento";
	//tipos de usuario
	public static final String TIPO_INSTITUCION_EDUCATIVA = "Institución Educativa";
	public static final String DISTRITO = "Distrito";
	//mensajes de insercion
	public static final String LABEL_CORRECTO = "Correcto";
	public static final String LABEL_ERROR = "Error";
	public static final String REGISTRO_CREADO = "Registro guardado con exito.";
	public static final String REGISTRO_ACTUALIZADO = "Registro actualizado con exito.";
	public static final String ERROR_CREACION = "Error al guardar el registro.";
	public static final String ERROR_CARGA_ARCHIVO = "Tiene que cargar un archivo.";
	
	public static final String OFE_MESES="0 - 36 meses";
	public static final String INICIAL="Inicial";
	public static final String GENERAL_BASICA="General Básica";
	public static final String BACHILLERATO="Bachillerato";
	public static final String BASICA_SUPERIOR="Básica Superior (8vo a 10mo)";
	public static final int FORM_INGRESADO = 0;
	public static final int FORM_FINALIZADO = 1;
	public static final int FORM_SUSTITUTIVA = 2;
	public static final int FORM_CERRADO = 3;
	//mensaje tipo proceso
	public static final String TIPO_PRESUPUESTADO="PRESUPUESTO";
	public static final String TIPO_EJECUTADO="EJECUTADO";
	//tipo institucion
	public static final String TIPO_ANIO_ANTERIOR="Institución Educativa que tiene valores de pensiones y matrícula asignados en el año lectivo anterior";
	public static final String TIPO_EDUCATIVA_NUEVA="Institución Educativa nueva.";
	public static final String TIPO_ART18="Solicita reajuste de valores de acuerdo al Art. 18";
	public static final String TIPO_PRIMERA_VEZ="Solicita por primera vez el cobro de valores.";
}
