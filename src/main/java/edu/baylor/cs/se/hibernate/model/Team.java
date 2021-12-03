package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@NamedQueries({
    @NamedQuery(
            name = "Team.findTemaByContest",
            query = "SELECT c FROM Team c WHERE c.contest.id = :contest"
    )
})
//Obsolete with Java 8 @Repeatable annotation
@Entity
@Table(name="Team")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int rank;
    
    @Column(nullable = false)
    private State state;
    
    public static enum State {
        ACCEPTED, PENDING, CANCELED
      }
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Contest contest;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "TEAM_PERSON",
            joinColumns = { @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Person> members = new ArrayList();
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "TEAM_COACH",
            joinColumns = { @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Person coach;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Team clone;
    
    public void setId(Long id) {
    	this.id = id;
    }
    public void setContest(Contest contest) {
        this.contest = contest;
    }
    
    public void setCoach(Person coach) {
        this.coach = coach;
    }
    
    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public List<Person> getMembers() {
    	return members;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Person getCoach() {
        return coach;
    }

    public int getRank() {
        return rank;
    }
    
    public State getState() {
        return state;
    }
    
    public Contest getContest() {
        return contest;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setClone(Team clone) {
        this.clone = clone;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public void setState(State state) {
        this.state = state;
    }
}
