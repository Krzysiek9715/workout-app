package _10a.academy.model;


import _10a.academy.beany.TrainingDetailsBean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<TrainingDetails> trainingDetailsList;


    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TrainingDetails> getTrainingDetailsList() {
        return trainingDetailsList;
    }

    public void setTrainingDetailsList(List<TrainingDetails> trainingDetailsList) {
        this.trainingDetailsList = trainingDetailsList;
    }

    @Override
    public String toString() {
        return userId+" "+name + " email:" + email;
    }
}
