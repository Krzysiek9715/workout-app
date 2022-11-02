package _10a.academy.beany;

import _10a.academy.controller.ExOnTrainController;
import _10a.academy.controller.TrainingController;
import _10a.academy.controller.UserController;
import _10a.academy.model.Exercise;
import _10a.academy.model.ExerciseOnTraining;
import _10a.academy.model.TrainingDetails;
import _10a.academy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class UserBean implements Serializable {

    Logger logger = LoggerFactory.getLogger(UserBean.class);

    @EJB
    UserController userController;

    @EJB
    ExOnTrainController exOnTrainController;
    User userToChoose;

    Long idUserToShow;

    User userChoosed;
    private List<User> userList;

    private List<Long> userIdList;

    private List<TrainingDetails> trainingDetailsList;

    private List<ExerciseOnTraining> exerciseOnTrainingList;


    /* init metoda ************************************************************************************/

    @PostConstruct
    public void init() {
        logger.info("Pobieram liste uzytkownikow");
        userList = userController.getAllUsers();
        logger.info("Lista uzytkownikow: {}", userList.toString());
        userIdList = userList.stream().map(User::getUserId).collect(Collectors.toList());
        userChoosed = userController.getUserById(userList.stream().findFirst().get().getUserId());
        logger.info("Lista idkow: {}", userIdList.toString());
        idUserToShow = userList.stream().findFirst().get().getUserId();
    }


    /* GETTERY I SETTERY ************************************************************************************/

    public User getUserToChoose() {
        return userToChoose;
    }

    public void setUserToChoose(User userToChoose) {
        this.userToChoose = userToChoose;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUserChoosed() {
        return userChoosed;
    }

    public void setUserChoosed(User userChoosed) {
        this.userChoosed = userChoosed;
    }

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }

    public Long getIdUserToShow() {
        return idUserToShow;
    }

    public void setIdUserToShow(Long idUserToShow) {
        this.idUserToShow = idUserToShow;
    }

    public List<TrainingDetails> getTrainingDetailsList() {
        return trainingDetailsList;
    }

    public void setTrainingDetailsList(List<TrainingDetails> trainingDetailsList) {
        this.trainingDetailsList = trainingDetailsList;
    }

    public List<ExerciseOnTraining> getExerciseOnTrainingList() {
        return exerciseOnTrainingList;
    }

    public void setExerciseOnTrainingList(List<ExerciseOnTraining> exerciseOnTrainingList) {
        this.exerciseOnTrainingList = exerciseOnTrainingList;
    }

    /* METODY ************************************************************************************/

    public void getAllTrainingForUser() {
        logger.info("przypisałem id: {}", idUserToShow);
        userChoosed = userController.getUserById(idUserToShow);
        getTrainingDetalisForUser(userChoosed.getUserId(), 0L);
    }

    public void getTrainingDetalisForUser(Long userId, Long trainingId) {
        logger.info("make getTrainingDetalisForUser method with user id {}, and training id {}", userId, trainingId);
        User user = userController.getUserById(userId);
        logger.info("znalazłem usera {},{}", user, idUserToShow);
        if (user.getUserId() == idUserToShow) {
            logger.info("Warunek spelniony idusertoshow {}", idUserToShow);
            exerciseOnTrainingList = exOnTrainController.getAllExOnTrain(trainingId);
            logger.info("Lista treningow to: {}", exerciseOnTrainingList);
        } else {
            trainingDetailsList = Collections.emptyList();
            logger.info("warunek niespelniony");
        }
    }


    public String goToAddTraining(){
        return "addTraining.xhtml?id="+idUserToShow+"&faces-redirect=true";
    }
}
