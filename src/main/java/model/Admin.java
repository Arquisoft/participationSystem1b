package model;

public class Admin extends AbstractParticipant{

	public Admin(String name, String surname, String mail, String login, String password) {
		super(name, surname, mail, login, password);
	}

	public void modifyQuery(int id){
		//bd. update bla bla bla...
	}
	
	public void deleteQuery(int id){
		//bd. delete bla bla bla..
	}
	
	@Override
	public void saveParticipant() {
		//bd. insert bla bla bla...
	}
}
