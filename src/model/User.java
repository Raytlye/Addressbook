package model;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studiengang;
	private String nachname;
	private String vorname;
	private int pid;
	private boolean vegan;
	
	public User(String studiengang, String nachname, String vorname, int pid, boolean vegan) {
		
		this.studiengang = studiengang;
		this.nachname = nachname;
		this.vorname = vorname;
		this.pid = pid;
		this.vegan = vegan;
		
	}

	public String getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public boolean getVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

}
