package hello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.CitizenDB;


@Repository
public interface CitizenDBRepository extends CrudRepository<CitizenDB, Long>{

	CitizenDB findByEmail(String email);

}
