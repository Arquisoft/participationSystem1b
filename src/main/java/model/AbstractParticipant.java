package model;

public abstract class AbstractParticipant {
	
	private String name;
	private String surname;
	private String mail;
	private String login;
	private String password;
	
	public AbstractParticipant(String name,String surname,String mail,String login,String password){
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public abstract void saveParticipant();
}
