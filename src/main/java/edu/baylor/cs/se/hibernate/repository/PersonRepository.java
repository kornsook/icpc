package edu.baylor.cs.se.hibernate.repository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.baylor.cs.se.hibernate.model.Person;
import java.util.List;
import java.util.Date;

@Component
public interface PersonRepository 
         extends CrudRepository<Person, Long> {
	
	@Query("SELECT birthday FROM Person p WHERE p.memberTeam.size > 0")
	List<Date> getStudentsDOB();
}

