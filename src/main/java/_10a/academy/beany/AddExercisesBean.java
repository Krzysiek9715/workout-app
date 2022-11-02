package _10a.academy.beany;


import _10a.academy.controller.ExOnTrainController;
import _10a.academy.controller.ExerciseController;
import _10a.academy.controller.TrainingController;
import _10a.academy.controller.UserController;
import _10a.academy.model.Exercise;
import _10a.academy.model.ExerciseOnTraining;
import _10a.academy.model.TrainingDetails;
import _10a.academy.model.User;
import jdk.jfr.Name;
import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.JoinType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AddExercisesBean implements Serializable {


    Logger log = LoggerFactory.getLogger(getClass());


    @Inject @Param(name="userId")
    private Long userId;

    @Inject @Param(name = "trainId")
    private Long trainingId;

    @EJB
    UserController userController;

    @EJB
    TrainingController trainingController;

    @EJB
    ExerciseController exerciseController;
    @EJB
    ExOnTrainController exOnTrainController;


    private User user;

    private TrainingDetails trainingDetails;


    private List<Exercise> exerciseList;

    private Exercise choosedExercise;

    private Long exerciseId;

    private List<ExerciseOnTraining> exerciseOnTrainingList;

    private Integer seriesNumber;

    private Integer repetitions;

    private Integer weight;

    private Long exOnTrainId;




    @PostConstruct
    public void init(){
        user = userController.getUserById(userId);
        trainingDetails = trainingController.getTrainingById(trainingId);
        exerciseList = exerciseController.getAllExercise();
        choosedExercise = exerciseList.get(0);
        exerciseOnTrainingList = exOnTrainController.getAllExOnTrain(trainingId);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TrainingDetails getTrainingDetails() {
        return trainingDetails;
    }

    public void setTrainingDetails(TrainingDetails trainingDetails) {
        this.trainingDetails = trainingDetails;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Exercise getChoosedExercise() {
        return choosedExercise;
    }

    public void setChoosedExercise(Exercise choosedExercise) {
        this.choosedExercise = choosedExercise;
    }

    public List<ExerciseOnTraining> getExerciseOnTrainingList() {
        return exerciseOnTrainingList;
    }

    public void setExerciseOnTrainingList(List<ExerciseOnTraining> exerciseOnTrainingList) {
        this.exerciseOnTrainingList = exerciseOnTrainingList;
    }

    public Integer getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(Integer seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getExOnTrainId() {
        return exOnTrainId;
    }

    public void setExOnTrainId(Long exOnTrainId) {
        this.exOnTrainId = exOnTrainId;
    }

    /* Metody ***********************************************************************************/

    public void addToExerciseList(){
        log.info("Start add to list exOnTrain");
        Exercise exToAdd = exerciseController.getExerciseById(exerciseId);
        log.info("I got exercise {}", exToAdd);
        ExerciseOnTraining exerciseOnTraining = new ExerciseOnTraining(seriesNumber,repetitions,weight,trainingDetails,exToAdd);
        exOnTrainController.addExerciseOnTraining(exerciseOnTraining);
        getExercisesForActualTrain(trainingId);
    }


    public void changeExercise(){
        log.info("mam id: {}", exerciseId);
        this.choosedExercise = exerciseController.getExerciseById(exerciseId);
    }

    public void getExercisesForActualTrain(Long trainingId){
        exerciseOnTrainingList = exOnTrainController.getAllExOnTrain(trainingId);
    }

    public void deleteExOnTra(Long id){
        log.info("mam takie id: {}", id);
        exOnTrainController.delete(id);

        exerciseOnTrainingList = exOnTrainController.getAllExOnTrain(trainingId);
    }

}
