package _10a.academy.controller;


import _10a.academy.model.Exercise;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ExerciseController {

    @PersistenceContext
    EntityManager entityManager;


    public List<Exercise> getAllExercise(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Exercise> criteriaQuery = criteriaBuilder.createQuery(Exercise.class);
        Root<Exercise> exerciseRoot = criteriaQuery.from(Exercise.class);
        exerciseRoot.fetch("exerciseOnTrainingSetEx", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(exerciseRoot).distinct(true)).getResultList();
    }


    public Exercise getExerciseById(Long exerciseId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Exercise> criteriaQuery = criteriaBuilder.createQuery(Exercise.class);
        Root<Exercise> exerciseRoot = criteriaQuery.from(Exercise.class);
        exerciseRoot.fetch("exerciseOnTrainingSetEx", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(exerciseRoot)
                .where(criteriaBuilder.equal(exerciseRoot.get("exerciseId"),exerciseId))).getSingleResult();
    }

    public void addExercise(Exercise exercise){
            entityManager.merge(exercise);

    }
}
