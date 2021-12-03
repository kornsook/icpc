package edu.baylor.cs.se.hibernate.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Team;
import java.util.List;

@Component
public interface ContestRepository 
         extends CrudRepository<Contest, Long> {
		
}

