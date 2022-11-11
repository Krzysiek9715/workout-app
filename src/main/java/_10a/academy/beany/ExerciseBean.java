package _10a.academy.beany;


import _10a.academy.controller.ExerciseController;
import _10a.academy.controller.UserController;
import _10a.academy.model.BodyPartEnum;
import _10a.academy.model.Exercise;
import _10a.academy.model.ExerciseOnTraining;
import _10a.academy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class ExerciseBean implements Serializable {


    Logger logger = LoggerFactory.getLogger(ExerciseBean.class);

    @EJB
    ExerciseController exerciseController;

    private List<Exercise> exerciseList;

    private String exName;

    private BodyPartEnum bodyPartEnum;

    private List<BodyPartEnum> bodyPartEnumList = Arrays.asList(BodyPartEnum.values());

    @PostConstruct
    public void init(){
        logger.info("Pobieram liste uzytkownikow");
        exerciseList = exerciseController.getAllExercise();
    }

    /* Gettery i Settery *******************************************************************************/
    public ExerciseController getExerciseController() {
        return exerciseController;
    }

    public void setExerciseController(ExerciseController exerciseController) {
        this.exerciseController = exerciseController;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public BodyPartEnum getBodyPartEnum() {
        return bodyPartEnum;
    }

    public void setBodyPartEnum(BodyPartEnum bodyPartEnum) {
        this.bodyPartEnum = bodyPartEnum;
    }

    public List<BodyPartEnum> getBodyPartEnumList() {
        return bodyPartEnumList;
    }

    public void setBodyPartEnumList(List<BodyPartEnum> bodyPartEnumList) {
        this.bodyPartEnumList = bodyPartEnumList;
    }

    /* Metody *******************************************************************************/

    public String addExercise(){
        exerciseController.addExercise(new Exercise(exName,bodyPartEnum));
        return "index.xhtml?faces-redirect=true";
    }

}
