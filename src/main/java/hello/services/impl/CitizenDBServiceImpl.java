package hello.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hello.model.CitizenDB;
import hello.repository.CitizenDBRepository;
import hello.services.CitizenDBService;

public class CitizenDBServiceImpl implements CitizenDBService{
	
	@Autowired
	private CitizenDBRepository citizenDBRepository;

	@Override
	public CitizenDB getCitizenDB(String email) {
		return citizenDBRepository.findByEmail(email);
	}

}
