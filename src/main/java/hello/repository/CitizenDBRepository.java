package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.model.CitizenDB;


@Repository
public interface CitizenDBRepository extends JpaRepository<CitizenDB, Long>{

}
