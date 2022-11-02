package _10a.academy.beany;


import _10a.academy.controller.ExOnTrainController;
import _10a.academy.controller.ExerciseController;
import _10a.academy.controller.TrainingController;
import _10a.academy.controller.UserController;
import _10a.academy.model.Exercise;
import _10a.academy.model.ExerciseOnTraining;
import _10a.academy.model.TrainingDetails;
import _10a.academy.model.User;
import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class AddTrainingBean implements Serializable {

    Logger log = LoggerFactory.getLogger(getClass());
    @EJB
    TrainingController trainingController;

    @EJB
    ExOnTrainController exOnTrainController;

    @EJB
    UserController userController;

    @EJB
    ExerciseController exerciseController;

    @Inject @Param(name = "id")
    private Long userId;


    private ExerciseOnTraining newExerciseOnTraining;

    private String date;

    private User user;

    private List<Exercise> exerciseList;

    private String bodyPart;

    private Exercise choosedExercise;

    private List<ExerciseOnTraining> exerciseOnTrainingList;

    private ExerciseOnTraining exerciseOnTraining;




    @PostConstruct
    public void init(){
      user = userController.getUserById(userId);
      exerciseList = exerciseController.getAllExercise();
    }


    /* GETTERY I SETTERY *********************************************************************************/



    public ExerciseOnTraining getNewExerciseOnTraining() {
        return newExerciseOnTraining;
    }

    public void setNewExerciseOnTraining(ExerciseOnTraining newExerciseOnTraining) {
        this.newExerciseOnTraining = newExerciseOnTraining;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
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

    public ExerciseOnTraining getExerciseOnTraining() {
        return exerciseOnTraining;
    }

    public void setExerciseOnTraining(ExerciseOnTraining exerciseOnTraining) {
        this.exerciseOnTraining = exerciseOnTraining;
    }
    /* METODY *********************************************************************************/


    public String addTraining(){
        log.info("start add: ");
        log.info("added user: {}", user);
        TrainingDetails trainingToAdd = new TrainingDetails(date,user);
        log.info("added date: {}", date);
        log.info("I want to add training: {}", trainingToAdd);
        trainingController.addTraining(trainingToAdd);
        return "addExercises.xhtml?userId="+user.getUserId()+"&trainId="+trainingToAdd.getTrainingId()+"&faces-redirect=true";
    }







}
