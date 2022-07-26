package com.sos.owo.domain.repository;

import com.sos.owo.domain.Exercise;
import com.sos.owo.domain.Goal;
import com.sos.owo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoalRepository {

    @PersistenceContext
    private final EntityManager em;

//    private EntityTransaction tx = em.getTransaction();


    public void saveGoal(int memberId,Goal goal){
        Member findMember = em.find(Member.class,memberId);
        goal.setMember(findMember);
        findMember.getGoalList().add(goal);
//        System.out.println(findMember.getGoalList());
        em.persist(findMember);
    }

    public void updateGoal(int memberId,Goal goal){
        Member findMember = em.find(Member.class,memberId);
        List<Goal> goalList = findMember.getGoalList();

        for (Goal g:goalList) {
            System.out.println(g.getExercise());
            if(g.getExercise() == goal.getExercise()){
                g.setHour(goal.getHour());
                break;

            }
        }
    }

    public void deleteGoal(int memberId, Exercise exercise){
        Member findMember = em.find(Member.class,memberId);
        List<Goal> goalList = findMember.getGoalList();

//        Goal findGoal = em.createQuery("delete from Goal g where g.member = :member_id and g.exercise =:exercise")
//                .setParameter("member_id",memberId)
//                .setParameter("exercise",exercise).executeUpdate();


        Goal goal = null;
        int pk = 0;

        int i = 0;
        int index = 0;
        for (Goal g:goalList) {
           // System.out.println(">>>>"+g.getId()+" "+g.getMember().getId()+" "+g.getExercise()+" "+g.getHour());
            if(g.getExercise()==exercise){
                goal = g;
                pk = goal.getId();
                index = i;
//                Goal goal = em.find(Goal.class,g.getId());
//                System.out.println(">>>>>>>"+goal.getId()+" "+goal.getMember()+" "+goal.getExercise()+" "+goal.getHour());
//                goalList.remove(g);
//                em.remove(goal);
//                em.flush();
//                tx.commit();
                break;
            }

            i++;
        }
        System.out.println(">>>>"+goal.getId()+" "+goal.getMember().getId()+" "+goal.getExercise()+" "+goal.getHour());
        goalList.remove(index);

    }
}
