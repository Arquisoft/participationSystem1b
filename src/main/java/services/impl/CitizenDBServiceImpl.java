package services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import model.CitizenDB;
import repository.CitizenDBRepository;
import services.CitizenDBService;

public class CitizenDBServiceImpl implements CitizenDBService{
	
	@Autowired
	private CitizenDBRepository citizenDBRepository;

	@Override
	public CitizenDB getCitizenDB(String email) {
		return citizenDBRepository.findByEmail(email);
	}
}
