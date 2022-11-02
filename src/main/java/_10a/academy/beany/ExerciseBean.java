package _10a.academy.beany;


import _10a.academy.controller.ExerciseController;
import _10a.academy.controller.UserController;
import _10a.academy.model.Exercise;
import _10a.academy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ExerciseBean implements Serializable {


    Logger logger = LoggerFactory.getLogger(ExerciseBean.class);

    @EJB
    ExerciseController exerciseController;

    private List<Exercise> exerciseList;

    @PostConstruct
    public void init(){
        logger.info("Pobieram liste uzytkownikow");
        exerciseList = exerciseController.getAllExercise();
    }

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
}
