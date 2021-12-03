package edu.baylor.cs.se.hibernate.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.model.Team.State;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;

import edu.baylor.cs.se.hibernate.repository.*;

//Spring annotations, feel free to ignore it
@Repository
@Transactional


public class Services {
	
	@PersistenceContext
    private EntityManager em;
	
    public void populate(ContestRepository contestRepo, PersonRepository personRepo, TeamRepository teamRepo) throws ParseException{
    	Person contestant1 = createPerson(1L,"Joe", "08/22/2000", "joe@gmail.com", "Baylor university");
    	Person contestant2 = createPerson(2L,"James", "09/22/2000", "james@gmail.com", "Baylor university");
    	Person contestant3 = createPerson(3L,"Jim", "10/22/2000", "jim@gmail.com", "Baylor university");
    	Person contestant4 = createPerson(4L,"Korn", "11/22/1992", "korn@gmail.com", "Syracuse university");
    	Person contestant5= createPerson(5L,"Kyle", "08/23/2001", "kyle@gmail.com", "Syracuse university");
    	Person contestant6 = createPerson(6L,"Kelly", "08/24/2002", "kelly@gmail.com", "Syracuse university");
    	Person contestant7 = createPerson(7L,"Ben", "08/25/2004", "ben@gmail.com", "Rice university");
    	Person contestant8 = createPerson(8L,"Brandon", "08/27/1999", "bradon@gmail.com", "Riceuniversity");
    	Person contestant9 = createPerson(9L,"Bell", "08/12/2005", "bell@gmail.com", "Rice university");
    	Person contestant10 = createPerson(10L,"Brook", "08/25/2004", "ben@gmail.com", "Rice university");
    	Person contestant11 = createPerson(11L,"Dim", "08/27/1999", "bradon@gmail.com", "Riceuniversity");
    	Person contestant12 = createPerson(12L,"Sam", "08/12/2005", "bell@gmail.com", "Rice university");
    	Person contestant13 = createPerson(13L,"Carl", "08/25/2004", "ben@gmail.com", "Rice university");
    	Person contestant14 = createPerson(14L,"Louis", "08/27/1999", "bradon@gmail.com", "Riceuniversity");
    	Person contestant15 = createPerson(15L,"Tum", "08/12/2005", "bell@gmail.com", "Rice university");
    	Person contestant16 = createPerson(16L,"Linda", "08/25/2004", "ben@gmail.com", "Rice university");
    	Person contestant17 = createPerson(17L,"Salsa", "08/27/1999", "bradon@gmail.com", "Riceuniversity");
    	
    	Person coach1 = createPerson(18L,"Tim", "12/22/1992", "tim@gmail.com", "Baylor university");
    	Person coach2 = createPerson(19L,"Tom", "08/22/1990", "tom@gmail.com", "Syracuse university");
    	Person coach3 = createPerson(20L,"Tammy", "08/22/1989", "tammy@gmail.com", "Rice university");
    	Person coach4 = createPerson(21L,"Junior", "12/22/1992", "tim@gmail.com", "Baylor university");
    	Person coach5 = createPerson(22L,"Kruskal", "08/22/1990", "tom@gmail.com", "Syracuse university");
    	Person coach6 = createPerson(23L,"Kim", "08/22/1989", "tammy@gmail.com", "Rice university");
    	Person manager = createPerson(24L,"Elvis", "08/22/1980", "elvis@gmail.com", "Harvard university");    	    
    	
    	Contest contest = new Contest();
    	contest.setId(1L);
    	contest.setName("R0029");
    	contest.setCapacity(5);
    	contest.setDate(parseDate("02/21/2022"));
    	contest.setRegistrationAllowed(true);
    	contest.setRegistrationFrom(parseDate("06/23/2021"));
    	contest.setRegistrationTo(parseDate("11/23/2021"));
    	contest.getManagers().add(manager);    	
    	contest.setWriteable(false);
    	
    	Contest subcontest = new Contest();
    	subcontest.setId(2L);
    	subcontest.setName("C0023");
    	subcontest.setCapacity(5);
    	subcontest.setDate(parseDate("01/05/2022"));
    	subcontest.setRegistrationAllowed(true);
    	subcontest.setRegistrationFrom(parseDate("06/23/2021"));
    	subcontest.setRegistrationTo(parseDate("11/23/2021"));
    	subcontest.getManagers().add(manager);
    	subcontest.setPreliminaryContest(contest);    	
    	subcontest.setWriteable(true);
    	
    	Contest contest2 = new Contest();
    	contest2.setId(3L);
    	contest2.setName("X0021");
    	contest2.setCapacity(2);
    	contest2.setDate(parseDate("01/05/2022"));
    	contest2.setRegistrationAllowed(true);
    	contest2.setRegistrationFrom(parseDate("06/23/2021"));
    	contest2.setRegistrationTo(parseDate("11/23/2021"));
    	contest2.getManagers().add(manager);
    	contest2.setWriteable(false);
    	
    	Team team1 = new Team();
    	team1.setId(1L);
    	team1.setName("Bear");
    	team1.setRank(3);
    	team1.setState(State.ACCEPTED);
    	team1.getMembers().add(contestant1);
    	team1.getMembers().add(contestant2);
    	team1.getMembers().add(contestant3);
    	team1.setContest(subcontest);
    	team1.setCoach(coach1);    	
    	
    	Team team2 = new Team();
    	team2.setId(2L);
    	team2.setName("Orange");
    	team2.setRank(2);
    	team2.setState(State.ACCEPTED);
    	team2.getMembers().add(contestant4);
    	team2.getMembers().add(contestant5);
    	team2.getMembers().add(contestant6);
    	team2.setContest(subcontest);
    	team2.setCoach(coach2);    	
    	
    	Team team3 = new Team();
    	team3.setId(3L);
    	team3.setName("Power rat");
    	team3.setRank(4);
    	team3.setState(State.ACCEPTED);
    	team3.getMembers().add(contestant7);
    	team3.getMembers().add(contestant8);
    	team3.getMembers().add(contestant9);
    	team3.setContest(subcontest);
    	team3.setCoach(coach3);  
    	
    	Team team4 = new Team();
    	team4.setId(4L);
    	team4.setName("Killer");
    	team4.setRank(3);
    	team4.setState(State.ACCEPTED);
    	team4.getMembers().add(contestant10);
    	team4.getMembers().add(contestant11);
    	team4.getMembers().add(contestant12);
    	team4.setContest(contest2);
    	team4.setCoach(coach4);  
    	
    	Team team5 = new Team();
    	team5.setId(5L);
    	team5.setName("Strong sand");
    	team5.setRank(3);
    	team5.setState(State.ACCEPTED);
    	team5.getMembers().add(contestant13);
    	team5.getMembers().add(contestant14);
    	team5.getMembers().add(contestant15);
    	team5.setContest(contest2);
    	team5.setCoach(coach5);  
    	
    	Team team6 = new Team();
    	team6.setId(6L);
    	team6.setName("Power cat");
    	team6.setRank(4);
    	team6.setState(State.ACCEPTED);
    	team6.getMembers().add(contestant3);
    	team6.getMembers().add(contestant16);
    	team6.getMembers().add(contestant17);
    	team6.setCoach(coach6);  
    	
    	personRepo.save(contestant1);
    	personRepo.save(contestant2);
    	personRepo.save(contestant3);
    	personRepo.save(contestant4);
    	personRepo.save(contestant5);
    	personRepo.save(contestant6);
    	personRepo.save(contestant7);
    	personRepo.save(contestant8);
    	personRepo.save(contestant9);
    	personRepo.save(contestant10);
    	personRepo.save(contestant11);
    	personRepo.save(contestant12);
    	personRepo.save(contestant13);
    	personRepo.save(contestant14);
    	personRepo.save(contestant15);
    	personRepo.save(contestant16);
    	personRepo.save(contestant17);
    	personRepo.save(coach1);
    	personRepo.save(coach2);
    	personRepo.save(coach3);
    	personRepo.save(coach4);
    	personRepo.save(coach5);
    	personRepo.save(coach6);
    	personRepo.save(manager);

    	teamRepo.save(team1);
    	teamRepo.save(team2);
    	teamRepo.save(team3);
    	teamRepo.save(team4);
    	teamRepo.save(team5);
    	teamRepo.save(team6);
    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<Team> getTeamsByContest(Long contestId, TeamRepository teamRepo){
        return teamRepo.findTeamsByContestId(contestId);
        //return em.createNamedQuery("Team.findTemaByContest").setParameter("contest",contestId).getResultList();
    }

    /**
     * List of courses with more than 2 students (3 and more)
     */

    public HashMap<Integer, Integer> countStudentsByAge(PersonRepository personRepo){
    	List<Date> dob_list =  personRepo.getStudentsDOB();
    	//List<Date> dob_list =  em.createNamedQuery("Person.getStudentDOB").getResultList();
    	HashMap<Integer, Integer> ans = new HashMap<>();
    	for(int i = 0;i < dob_list.size();i++) {
    		int age = convertDOBtoAge(dob_list.get(i));
    		System.out.println(age);
    		if(ans.containsKey(age)) {
    			ans.put(age, ans.get(age)+1);
    		}
    		else {
    			ans.put(age, 1);
    		}
    	}
    	return ans;
    }
    public List<HashMap> getCapacityAndNumberOfTeamsByContest(ContestRepository contestRepo) {
    	List<Contest> contests = (List<Contest>)contestRepo.findAll();
    	//List<Contest> contests = em.createNamedQuery("Contest.getCapacityAndTeamsByContest").getResultList();
    	List<HashMap> ans = new ArrayList();
    	for(int i = 0; i < contests.size();i++) {
    		Contest contest = contests.get(i);
    		HashMap<String, Integer> temp = new HashMap<>();
    		temp.put("Capacity", contest.getCapacity());
    		temp.put("Occupancy", contest.getTeams().size());
    		ans.add(temp);
    	}
    	return ans;
    }
    
    public Boolean registration(Long contestId, Team team, ContestRepository contestRepo, TeamRepository teamRepo) {
    	if(team.getMembers().size() != 3 || team.getCoach() == null) {
    		return false;
    	}
    	Contest contest = contestRepo.findById(contestId).get();
    	List<Team> teams = (List<Team>) contest.getTeams();
    	if(contest.getCapacity() <= teams.size()) {
    		return false;
    	}
    	List<Person> listCheck = (List<Person>) team.getMembers();
    	listCheck.add(team.getCoach());
    	for(int i = 0;i < listCheck.size()-1;i ++) {
    		Person student = listCheck.get(i);
    		int age = convertDOBtoAge(student.getBirthday());
    		if(age >= 24) {
    			return false;
    		}
    		for(Team dif_team : teams) {
    			for(Person dif_student : dif_team.getMembers()) {
    				if(student.getId() == dif_student.getId()) {
    					return false;
    				}
    			}
    		}
    		for(int j = i+1;j < listCheck.size();j++) {
    			if(student.getId() == listCheck.get(j).getId()) {
    				return false;
    			}
    		}
    	}  
    	team.setContest(contest);
    	teamRepo.save(team);
    	return true;
    }
    
    public Boolean editTeam(Team team, TeamRepository teamRepo) {
    	Contest contest = teamRepo.findById(team.getId()).get().getContest();
    	if(contest != null) {
    		if(!contest.getWriteable()) {
    			return false; 
    		}
    	}
    	teamRepo.save(team);
    	return true;
    }
    
    public Boolean editContest(Contest contest, ContestRepository contestRepo) {
		if(!contest.getWriteable()) {
			return false; 
		}
    	contestRepo.save(contest);
    	return true;
    }
    
    public void setEditable(Contest contest, ContestRepository contestRepo) {
    	contest.setWriteable(true);    	
    	contestRepo.save(contest);
    }
    
    public void setReadOnly(Contest contest, ContestRepository contestRepo) {
    	contest.setWriteable(false);
    	contestRepo.save(contest);
    }
    
    public Boolean promote(Long supercontestId, Long teamId, ContestRepository contestRepo, TeamRepository teamRepo) {
    	Contest supercontest = contestRepo.findById(supercontestId).get();
    	Team team = teamRepo.findById(teamId).get();
    	if(supercontest.getCapacity() <= supercontest.getTeams().size() || !registration(supercontestId, team, contestRepo, teamRepo)) {
    		return false;
    	}
    	Team cloned_team = new Team();
    	cloned_team.setCoach(team.getCoach());
    	cloned_team.setContest(supercontest);
    	cloned_team.getMembers().addAll(team.getMembers());
    	cloned_team.setName(team.getName());
    	if(team.getRank() < 5)
    		cloned_team.setRank(team.getRank()+1);
    	cloned_team.setState(team.getState());
    	cloned_team.setId(teamRepo.count()+1);
    	cloned_team.setClone(team);
    	teamRepo.save(cloned_team);
    	return true;
    }
    //The following methods are used only in this class
    
    private int convertDOBtoAge(Date DOB) {
		LocalDate date_dob_local = convertToLocalDateViaInstant(DOB);
		int age = Period.between(date_dob_local, LocalDate.now()).getYears();
		return age;
    }
    
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    private Date parseDate(String date) throws ParseException {
    	return new SimpleDateFormat("MM/dd/yyyy").parse(date);
    }
    
    private Person createPerson(Long id, String name, String birthday, String email, String university) throws ParseException{
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(birthday));
        person.setEmail(email);
        person.setUniversity(university);
        return person;
    }
}

