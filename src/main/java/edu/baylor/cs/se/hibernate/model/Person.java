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
            name = "Person.getStudentDOB",
            query = "SELECT birthday FROM Person p WHERE p.memberTeam.size > 0"
    )
})

//Obsolete with Java 8 @Repeatable annotation
@Entity
@Table(name="Person")
public class Person {

    @Id
    private Long id;

    @Column(nullable = false)
    private Date birthday;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String university;
    
    @ManyToMany(mappedBy = "managers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Contest> manageContests = new ArrayList();
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Team> coachTeam = new ArrayList();
    
    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Team> memberTeam = new ArrayList();

    public void setId(Long id) {
    	this.id = id;
    }
    public void setContests(List<Contest> contests) {
        this.manageContests = contests;
    }
    
    public void setCoachTeam(List<Team> teams) {
        this.coachTeam = teams;
    }
    
    public void setMemberTeam(List<Team> teams) {
        this.memberTeam = teams;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getUniversity() {
        return university;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setUniversity(String university) {
        this.university = university;
    }

}
