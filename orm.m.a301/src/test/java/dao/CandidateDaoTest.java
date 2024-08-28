package dao;

import org.example.dao.CandidateDao;
import org.example.entities.Candidate;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


public class CandidateDaoTest {
    Candidate candidate;
    CandidateDao candidateDao;

    @Before
    public void setUp() {
        candidateDao = new CandidateDao();
        // if(candidateDao.insertCandidate()) System.out.println("Candidate inserted");
    }

    @Test
    public void findCandidateBySkillAndLevel(){
        String skill = "Angular";
        int level = 2;
        List<Candidate> candidateList = candidateDao.getCandidateBySkillAndLevel(skill, level);
        assertNotNull(candidateList);
        assertFalse(candidateList.isEmpty());
    }

    @Test
    public void findCandidateByForeignLanguageAndSkill(){
        String language = "Japanese";
        String skill = "Python/ML";
        List<Candidate> candidateList = candidateDao.getCandidateByForeignLanguageAndSkill(language, skill);
        assertNotNull(candidateList);
        assertFalse(candidateList.isEmpty());
    }

    @Test
    public void findCandidateBySkillAndEntryTestResult(){
        String skill = "Java";
        LocalDate date = LocalDate.of(2020, 8, 1);
        List<Candidate> candidateList = candidateDao.getCandidateBySkillAndEntryTestResult(skill, date);
        assertFalse(candidateList.isEmpty());
    }

    @Test
    public void findCandidatePassInterview(){
        LocalDate date = LocalDate.of(2020, 8, 15);
        List<Candidate> candidateList = candidateDao.getCandidatePassInterview(date);
        assertFalse(candidateList.isEmpty());
    }

    @Test
    public void updateCandidate(){
        assertTrue(candidateDao.updateCandidateInactive());
    }

    @Test
    public void getCandidatePagination(){
        int page = 1;
        int size = 3;
        List<Candidate> candidateList = candidateDao.findCandidateAndPagination(page, size);
        candidateList.forEach(System.out::println);
        assertFalse(candidateList.isEmpty());
        assertEquals(candidateList.size(), size);
    }
}
