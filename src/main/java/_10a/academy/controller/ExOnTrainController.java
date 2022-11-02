package _10a.academy.controller;


import _10a.academy.model.ExerciseOnTraining;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Stateless
public class ExOnTrainController {

    @PersistenceContext
    EntityManager entityManager;


    public List<ExerciseOnTraining> getAllExOnTrain(Long trainingId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExerciseOnTraining> criteriaQuery = criteriaBuilder.createQuery(ExerciseOnTraining.class);
        Root<ExerciseOnTraining> exerciseOnTrainingRoot = criteriaQuery.from(ExerciseOnTraining.class);
        exerciseOnTrainingRoot.fetch("exercise", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(exerciseOnTrainingRoot)
                .where(criteriaBuilder.equal(exerciseOnTrainingRoot.get("trainingDetails"), trainingId))
                .distinct(true))
                .getResultList();
    }

    public ExerciseOnTraining getAllExOnTrainById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExerciseOnTraining> criteriaQuery = criteriaBuilder.createQuery(ExerciseOnTraining.class);
        Root<ExerciseOnTraining> exerciseOnTrainingRoot = criteriaQuery.from(ExerciseOnTraining.class);
        exerciseOnTrainingRoot.fetch("exercise", JoinType.LEFT);
        exerciseOnTrainingRoot.fetch("trainingDetails", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(exerciseOnTrainingRoot)
                        .where(criteriaBuilder.equal(exerciseOnTrainingRoot.get("eOtId"), id))
                        .distinct(true))
                .getSingleResult();
    }

    public void addExerciseOnTraining(ExerciseOnTraining exerciseOnTraining){
        entityManager.merge(exerciseOnTraining);
    }

    public void delete(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<ExerciseOnTraining> criteriaDelete = criteriaBuilder.createCriteriaDelete(ExerciseOnTraining.class);
        Root<ExerciseOnTraining> exerciseOnTrainingRoot = criteriaDelete.from(ExerciseOnTraining.class);
        criteriaDelete.where(criteriaBuilder.equal(exerciseOnTrainingRoot.get("eOtId"),id));
        this.entityManager.createQuery(criteriaDelete).executeUpdate();
    }




}
