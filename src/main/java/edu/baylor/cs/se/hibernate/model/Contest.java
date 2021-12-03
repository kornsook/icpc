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
            name = "Contest.getCapacityAndTeamsByContest",
            query = "SELECT c FROM Contest c"
    )
})


//Obsolete with Java 8 @Repeatable annotation
@Entity
@Table(name="Contest")
public class Contest {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String name;

    @Column(nullable = false)
    private int capacity;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column
    private Boolean registration_allowed;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date registration_from;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date registration_to;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="contest")
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Team> teams = new ArrayList();
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CONTEST_CONTEST",
    joinColumns = { @JoinColumn(name = "CONTEST_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
    inverseJoinColumns = { @JoinColumn(name = "PRELIMINARY_CONTEST_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Contest preliminary_contest;
    
    @OneToMany(mappedBy ="preliminary_contest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Contest> next_contest = new ArrayList();
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CONTEST_MANAGER",
    joinColumns = { @JoinColumn(name = "CONTEST_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
    inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Person> managers = new ArrayList();        
    
    @Column
    private Boolean writeable;
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public void setManagers(List<Person> managers) {
        this.managers = managers;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public List<Team> getTeams() {
        return teams;
    }
    
    public List<Person> getManagers() {
        return managers;
    }
    
    public Contest getPreliminaryContest() {
    	return preliminary_contest;
    }
    
    public Boolean getWriteable() {
    	return writeable;
    }
    
    public Date getRegistrationFrom() {
    	return registration_from;
    }
    
    public Date getRegistrationTo() {
    	return registration_to;
    }
    
    public Date getDate() {
    	return date;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setRegistrationAllowed(Boolean registration_allowed) {
        this.registration_allowed = registration_allowed;
    }
    
    public void setRegistrationFrom(Date registration_from) {
        this.registration_from = registration_from;
    }
    
    public void setRegistrationTo(Date registration_to) {
        this.registration_to= registration_to;
    }
    
    public void setPreliminaryContest(Contest contest) {
    	this.preliminary_contest = contest;
    }
    
    public void setWriteable(Boolean writeable) {
    	this.writeable = writeable;
    }
}
