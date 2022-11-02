package _10a.academy.controller;


import _10a.academy.model.TrainingDetails;
import _10a.academy.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class TrainingController {


    @PersistenceContext
    EntityManager entityManager;

    public List<TrainingDetails> getAllTraining(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainingDetails> criteriaQuery = criteriaBuilder.createQuery(TrainingDetails.class);
        Root<TrainingDetails> trainingDetailsRoot = criteriaQuery.from(TrainingDetails.class);
        trainingDetailsRoot.fetch("exerciseOnTrainingSetTra", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(trainingDetailsRoot).distinct(true)).getResultList();
    }

    public TrainingDetails getTrainingById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainingDetails> criteriaQuery = criteriaBuilder.createQuery(TrainingDetails.class);
        Root<TrainingDetails> trainingDetailsRoot = criteriaQuery.from(TrainingDetails.class);
        trainingDetailsRoot.fetch("exerciseOnTrainingSetTra", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(trainingDetailsRoot).distinct(true)
                .where(criteriaBuilder.equal(trainingDetailsRoot.get("trainingId"), id)))
                .getSingleResult();
    }

    public List<TrainingDetails> getTrainingByUserId(User user){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainingDetails> criteriaQuery = criteriaBuilder.createQuery(TrainingDetails.class);
        Root<TrainingDetails> trainingDetailsRoot = criteriaQuery.from(TrainingDetails.class);
        trainingDetailsRoot.fetch("exerciseOnTrainingSetTra", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(trainingDetailsRoot).distinct(true)
                        .where(criteriaBuilder.equal(trainingDetailsRoot.get("user"), user)))
                .getResultList();
    }


    public void addTraining(TrainingDetails trainingDetails){
       entityManager.persist(trainingDetails);
       entityManager.flush();
    }




}
