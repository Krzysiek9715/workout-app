package _10a.academy.model;


import javax.inject.Named;
import javax.persistence.*;

@Entity
@Table(name = "EXERCISE_ON_TRAINING")
public class ExerciseOnTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXERCISE_ON_TRAINING_ID")
    private Long eOtId;

    @Column(name = "SERIES_NUMBER")
    private Integer seriesNumber;

    @Column(name = "REPETITIONS")
    private Integer repeat;

    @Column(name = "WEIGHT")
    private Integer weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAINING_ID")
    private TrainingDetails trainingDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercise exercise;

    public ExerciseOnTraining() {
    }

    public ExerciseOnTraining(Integer seriesNumber, Integer repeat, Integer weight, TrainingDetails trainingDetails, Exercise exercise) {
        this.seriesNumber = seriesNumber;
        this.repeat = repeat;
        this.weight = weight;
        this.trainingDetails = trainingDetails;
        this.exercise = exercise;
    }

    public ExerciseOnTraining(Long eOtId, Integer seriesNumber, Integer repeat, Integer weight, TrainingDetails trainingDetails, Exercise exercise) {
        this.eOtId = eOtId;
        this.seriesNumber = seriesNumber;
        this.repeat = repeat;
        this.weight = weight;
        this.trainingDetails = trainingDetails;
        this.exercise = exercise;
    }

    public Long geteOtId() {
        return eOtId;
    }

    public void seteOtId(Long eOtId) {
        this.eOtId = eOtId;
    }

    public Integer getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(Integer seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public TrainingDetails getTrainingDetails() {
        return trainingDetails;
    }

    public void setTrainingDetails(TrainingDetails trainingDetails) {
        this.trainingDetails = trainingDetails;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }


    @Override
    public String toString() {
        return "ExerciseOnTraining{" +
                "eOtId=" + eOtId +
                ", seriesNumber=" + seriesNumber +
                ", repeat=" + repeat +
                ", weight=" + weight +
                ", trainingDetails=" + trainingDetails +
                ", exercise=" + exercise +
                '}';
    }
}
