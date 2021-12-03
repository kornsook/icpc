package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.services.Services;
import edu.baylor.cs.se.hibernate.repository.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;



//Ignore this as it is Spring and not Java EE (Jax-RS) controller
@RestController
@RequestMapping(produces="application/json")
@CrossOrigin(origins="http://localhost:4200")
public class MyController {

    private Services services;
    private ContestRepository contestRepo;
    private PersonRepository  personRepo;
    private TeamRepository  teamRepo;

    //you should generally favour constructor/setter injection over field injection
    @Autowired
    public MyController(Services services, ContestRepository contestRepo, PersonRepository  personRepo, TeamRepository  teamRepo){
        this.services = services;
        this.contestRepo = contestRepo;
        this.personRepo = personRepo;
        this.teamRepo = teamRepo;
    }

    //very bad practise - using GET method to insert something to DB
    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate() throws ParseException{
        services.populate(contestRepo, personRepo, teamRepo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{contestId}", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeamsByContest(@PathVariable Long contestId){
        return new ResponseEntity(services.getTeamsByContest(contestId, teamRepo),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/countstudentsbyage", method = RequestMethod.GET)
    public ResponseEntity<HashMap<Integer, Integer>> countStudentsByAge(){
        return new ResponseEntity(services.countStudentsByAge(personRepo),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/occupancy_capacity_contests", method = RequestMethod.GET)
    public ResponseEntity<List<HashMap>> getOccupancyAndCapacityContests(){
        return new ResponseEntity(services.getCapacityAndNumberOfTeamsByContest(contestRepo),HttpStatus.OK);
    }
    
    @PostMapping(value="/register", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody ObjectNode objectNode) {
    	Long contestId = Long.parseLong(objectNode.get("contestId").asText());    	
    	Long teamId = Long.parseLong(objectNode.get("teamId").asText());
    	Team team;
    	if(!teamRepo.existsById(teamId)) {
    		return new ResponseEntity("The team does not exist.", HttpStatus.OK); 
    	}
    	team = teamRepo.findById(teamId).get();
    	if(services.registration(contestId, team, contestRepo, teamRepo)) {
    		return new ResponseEntity("Success.", HttpStatus.OK); 
    	}
    	else {
    		return new ResponseEntity("Fail.", HttpStatus.OK); 
    	}
    }
    
    @PostMapping(value="/editteam", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> editTeam(@RequestBody ObjectNode objectNode) {
    	Long teamId = Long.parseLong(objectNode.get("teamId").asText());
    	String name = objectNode.get("name").asText();
    	Team team = teamRepo.findById(teamId).get();
    	team.setName(name);
    	if(services.editTeam(team, teamRepo)) {
    		return new ResponseEntity("Success.", HttpStatus.OK); 
    	}
    	else {
    		return new ResponseEntity("Fail.", HttpStatus.OK); 
    	}
    }
    
    @PostMapping(value="/editcontest", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> editContest(@RequestBody ObjectNode objectNode) {
    	Long contestId = Long.parseLong(objectNode.get("contestId").asText());
    	String name = objectNode.get("name").asText();
    	int capacity = Integer.parseInt(objectNode.get("capacity").asText());
    	Contest contest= contestRepo.findById(contestId).get();
    	contest.setName(name);
    	contest.setCapacity(capacity);
    	if(services.editContest(contest, contestRepo)) {
    		return new ResponseEntity("Success.", HttpStatus.OK); 
    	}
    	else {
    		return new ResponseEntity("Fail.", HttpStatus.OK); 
    	}
    }
    
    @PostMapping(value="/seteditable", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> setEditable(@RequestBody ObjectNode objectNode) {
    	Long contestId = Long.parseLong(objectNode.get("contestId").asText());
    	Contest contest= contestRepo.findById(contestId).get();
    	services.setEditable(contest, contestRepo);
    	return new ResponseEntity("Success.", HttpStatus.OK);     	
    }
    
    @PostMapping(value="/setreadonly", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> setReadOnly(@RequestBody ObjectNode objectNode) {
    	Long contestId = Long.parseLong(objectNode.get("contestId").asText());
    	Contest contest= contestRepo.findById(contestId).get();
    	services.setReadOnly(contest, contestRepo);
    	return new ResponseEntity("Success.", HttpStatus.OK);     	
    }
    
    @PostMapping(value="/promote", consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> promote(@RequestBody ObjectNode objectNode) {
    	Long contestId = Long.parseLong(objectNode.get("contestId").asText());
    	Long teamId = Long.parseLong(objectNode.get("teamId").asText());
    	if(services.promote(contestId, teamId, contestRepo, teamRepo)) {
    		return new ResponseEntity("Success.", HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity("Fail.", HttpStatus.OK);
    	}    	     	
    }
    
    @RequestMapping(value = "/contest", method = RequestMethod.GET)
    public ResponseEntity<List<Contest>> getContests() throws ParseException{
        List<Contest> contests = (List<Contest>) contestRepo.findAll();        
        return ResponseEntity.ok().body(contests);
    }
    
    @PostMapping(path="/contest",consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Contest> createGame(@RequestBody Contest contest) {
    	Contest createdContest = contestRepo.save(contest);
    	System.out.println(contest.getDate());
    	System.out.println(contest.getRegistrationFrom());
        return ResponseEntity.ok().body(createdContest);
    }
    
    @PutMapping(path="/contest", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Contest> updateGame(@RequestBody Contest contest) {
    	Contest updatedContest = contestRepo.save(contest);
        return ResponseEntity.ok().body(updatedContest);
    }
    
    @DeleteMapping(path="/contest/delete/{id:[0-9][0-9]*}", produces="application/json")
    public ResponseEntity<Boolean> deleteGame(@PathVariable Long id) {
            Contest contest = contestRepo.findById(id).get();
            contestRepo.delete(contest);
            return ResponseEntity.ok().body(true);
    }
    
    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeams() throws ParseException{
        List<Team> teams = (List<Team>) teamRepo.findAll();
        return ResponseEntity.ok().body(teams);
    }
    
    @PostMapping(path="/team",consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
    	Team createdTeam = teamRepo.save(team);
        return ResponseEntity.ok().body(createdTeam);
    }
    
    @PutMapping(path="/team",consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
    	Team createdTeam = teamRepo.save(team);
        return ResponseEntity.ok().body(createdTeam);
    }

    @DeleteMapping(path="/team/delete/{id:[0-9][0-9]*}", produces="application/json")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable Long id) {
            Team team = teamRepo.findById(id).get();
            teamRepo.delete(team);
            return ResponseEntity.ok().body(true);
    }
    
}
