package fr.banque.models;

public class Titulaire {
	private int code;
	private String intitule;
	
	public Titulaire(int code, String intitule) {
		super();
		this.code = code;
		this.intitule = intitule;
		
	}
	
	public Titulaire() {
		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Override
	public String toString() {
		return "Titulaire [code=" + code + ", intitule=" + intitule + "]";
	}
	
	
}
