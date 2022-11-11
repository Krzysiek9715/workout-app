package _10a.academy.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "TRAINING_DETAILS")
public class TrainingDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINING_ID", nullable = false)
    private Long trainingId;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;


    @OneToMany(mappedBy = "trainingDetails", cascade = CascadeType.ALL)
    private Set<ExerciseOnTraining> exerciseOnTrainingSetTra;

    public TrainingDetails() {
    }

    public TrainingDetails(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date =date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Set<ExerciseOnTraining> getExerciseOnTrainingSetTra() {
        return exerciseOnTrainingSetTra;
    }

    public void setExerciseOnTrainingSetTra(Set<ExerciseOnTraining> exerciseOnTrainingSetTra) {
        this.exerciseOnTrainingSetTra = exerciseOnTrainingSetTra;
    }

    @Override
    public String toString() {
        return "Szczegóły treningu: " +
                "data: " + date + '\'' +
                ", użytkownik: " + user;
    }

}

