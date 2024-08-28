package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ValidationException;
import org.example.HibernateUtils;
import org.example.entities.Candidate;
import org.example.entities.Interview;
import org.example.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CandidateDao {
    public boolean insertCandidate() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<String> nameList = Arrays.asList("Phan Dinh Khang", "Le Van Huy", "Le Van Hoang", "Le Loi", "Phan Thien Man");
            List<String> emailList = Arrays.asList("test@gmail.com", "test2@gmail.com", "test3@gmail.com", "test4@gmail.com", "test5@gmail.com");
            String phone = "012345678";

//            for(int i = 1; i <= 5; i++){
//                Candidate candidate = new Candidate();
//                candidate.setFull_name(nameList.get(i-1));
//                candidate.setEmail(emailList.get(i-1));
//                candidate.setPhone(phone + i);
//                candidate.setDate_of_birth(LocalDate.of(2002, 11,19));
//                candidate.setGraduation_year(LocalDate.of(2024, 1, 1));
//                candidate.setGender(1);
//                candidate.setSkill(i % 2 == 0 ? "Angular" : "Python/ML");
//                candidate.setForeign_language(i % 2 == 1 ? "Japanese" : "VietNam");
//                candidate.setLevel(2);
//                session.persist(candidate);
//            }

            Candidate candidate = new Candidate();
            candidate.setFull_name("Test");
            candidate.setEmail("test@gmail.abc");
            candidate.setPhone(phone + 9);
            candidate.setDate_of_birth(LocalDate.of(2002, 11,19));
            candidate.setGraduation_year(LocalDate.of(2024, 1, 1));
            candidate.setGender(1);
            candidate.setLevel(2);
            session.save(candidate);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException | ValidationException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }

    public List<Candidate> getCandidateBySkillAndLevel(String skill, int level) {
        List<Candidate> candidateList = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Candidate where skill = :skill and level = :level";
            candidateList = session.createQuery(hql, Candidate.class)
                    .setParameter("skill", skill)
                    .setParameter("level", level)
                    .list();
            return candidateList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return candidateList;
        }
    }

    public List<Candidate> getCandidateByForeignLanguageAndSkill(String language, String skill) {
        List<Candidate> candidateList = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Candidate where foreign_language = :language and skill = :skill";
            candidateList = session.createQuery(hql, Candidate.class)
                    .setParameter("language", language)
                    .setParameter("skill", skill)
                    .list();
            return candidateList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return candidateList;
        }
    }

    public List<Candidate> getCandidateBySkillAndEntryTestResult(String skill, LocalDate date) {
        List<Candidate> candidateList = new ArrayList<>();
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "Select c from Candidate c join c.entryTestList e where c.skill = :skill and e.date = :date";
            candidateList = session.createQuery(hql, Candidate.class)
                    .setParameter("skill", skill)
                    .setParameter("date", date)
                    .list();
            return candidateList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return candidateList;
        }
    }

    public List<Candidate> getCandidatePassInterview(LocalDate date) {
        List<Candidate> candidateList = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);

            Root<Candidate> rootCandidate = criteria.from(Candidate.class);
            Root<Interview> interviewRoot = criteria.from(Interview.class);
            criteria.select(rootCandidate);
            criteria.where(
                builder.and(
                    builder.equal(interviewRoot.get("candidate"), rootCandidate.get("candidate_id")),
                    builder.equal(interviewRoot.get("date"), date),
                    builder.equal(interviewRoot.get("interview_result"), "pass")
            ));

            Query<Candidate> query = session.createQuery(criteria);
            candidateList = query.getResultList();
            return candidateList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return candidateList;
        }
    }

    public boolean updateCandidateInactive(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Candidate> criteriaUpdate = cb.createCriteriaUpdate(Candidate.class);
            Root<Candidate> root = criteriaUpdate.from(Candidate.class);

            criteriaUpdate.set("remark", "inactive");
            criteriaUpdate.where(
                cb.and(
                    cb.isNull(root.get("email")),
                    cb.isNull(root.get("phone")),
                    cb.isNull(root.get("cv"))
                )
            );

            Transaction transaction = session.beginTransaction();
            int row = session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
            return row > 0;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }

    public List<Candidate> findCandidateAndPagination(int page, int size) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> rootCandidate = criteria.from(Candidate.class);
            criteria.select(rootCandidate);

            Query<Candidate> query = session.createQuery(criteria);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }
}
