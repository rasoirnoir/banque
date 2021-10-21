package fr.banque.models;
//sana

public class TypeCompte {
	private int code;
	private String intitule;
	
	public TypeCompte(int code, String intitule) {
		super();
		this.code = code;
		this.intitule = intitule;
		
	}
	
	public TypeCompte() {
		
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
		return "TypeCompte [code=" + code + ", intitule=" + intitule + "]";
	}
	
	
}
