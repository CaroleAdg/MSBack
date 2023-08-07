package fr.istic.taa.dto;

public class FormulaDailyMenuStatusDto {
	Long formulaId;
	int status;
	
	
	public FormulaDailyMenuStatusDto() {
		
	}
	
	public Long getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(Long formulaId) {
		this.formulaId = formulaId;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
