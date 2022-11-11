package _10a.academy.model;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.security.PrivateKey;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "EXERCISES")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXERCISE_ID", nullable = false)
    private Long exerciseId;

    @Column(name = "EXERCISE_NAME")
    private String name;

    @Column(name = "BODY_PART")
    private BodyPartEnum bodyPart;

    @OneToMany(mappedBy = "exercise", cascade = {CascadeType.ALL})
    Set<ExerciseOnTraining> exerciseOnTrainingSetEx;

    public Exercise() {
    }

    public Exercise(String name, BodyPartEnum bodyPart) {
        this.name = name;
        this.bodyPart = bodyPart;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyPartEnum getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Set<ExerciseOnTraining> getExerciseOnTrainingSetEx() {
        return exerciseOnTrainingSetEx;
    }

    public void setExerciseOnTrainingSetEx(Set<ExerciseOnTraining> exerciseOnTrainingSetEx) {
        this.exerciseOnTrainingSetEx = exerciseOnTrainingSetEx;
    }

    @Override
    public String toString() {
        return "Ćwiczenie:" + name
                + ", czesc ciała:" + bodyPart;
    }
}
