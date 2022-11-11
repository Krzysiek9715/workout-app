package _10a.academy.beany;

import _10a.academy.controller.TrainingController;
import _10a.academy.model.TrainingDetails;
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
public class TrainingDetailsBean implements Serializable {

    Logger logger = LoggerFactory.getLogger(TrainingDetailsBean.class);

    @EJB
    TrainingController trainingController;


    private List<TrainingDetails> trainingList;


    @PostConstruct
    public void init() {
//        logger.info("Pobieram liste treningów");
//        trainingList = trainingController.getAllTraining();
//        logger.info("Lista treningów: {}", trainingList);
    }


    public List<TrainingDetails> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<TrainingDetails> trainingList) {
        this.trainingList = trainingList;
    }


}


