package services;

import model.CitizenDB;

public interface CitizenDBService {

	public CitizenDB getCitizenDB(String email);
	public CitizenDB createCitizenDB(CitizenDB citizenDB);
}
