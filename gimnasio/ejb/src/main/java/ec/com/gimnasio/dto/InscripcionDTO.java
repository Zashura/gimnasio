package ec.com.gimnasio.dto;

public class InscripcionDTO {
	
	private long codSede;
	private String descSede;
	private long codDisciplina;
	private String descDisciplina;
	private long codHorario;
	private String descHorario;
	private long disiCodigo;
	
	public InscripcionDTO() {
	}
	
	public InscripcionDTO(long codSede, String descSede,long codDisciplina, String descDisciplina,long codHorario, String descHorario,long disiCodigo) {
		this.codSede = codSede;
		this.descSede = descSede;
		this.codDisciplina = codDisciplina;
		this.descDisciplina = descDisciplina;
		this.codHorario = codHorario;
		this.descHorario = descHorario;
		this.disiCodigo=disiCodigo;
	}

	public long getCodSede() {
		return codSede;
	}

	public void setCodSede(long codSede) {
		this.codSede = codSede;
	}

	public String getDescSede() {
		return descSede;
	}

	public void setDescSede(String descSede) {
		this.descSede = descSede;
	}

	public long getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(long codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getDescDisciplina() {
		return descDisciplina;
	}

	public void setDescDisciplina(String descDisciplina) {
		this.descDisciplina = descDisciplina;
	}

	public long getCodHorario() {
		return codHorario;
	}

	public void setCodHorario(long codHorario) {
		this.codHorario = codHorario;
	}

	public String getDescHorario() {
		return descHorario;
	}

	public void setDescHorario(String descHorario) {
		this.descHorario = descHorario;
	}

	public long getDisiCodigo() {
		return disiCodigo;
	}

	public void setDisiCodigo(long disiCodigo) {
		this.disiCodigo = disiCodigo;
	}
}
