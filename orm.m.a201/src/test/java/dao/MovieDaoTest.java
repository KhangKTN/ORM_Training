package dao;

import org.example.dao.impl.MovieDaoImpl;
import org.example.entities.Movie;
import org.example.entities.MovieType;
import org.example.entities.Type;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class MovieDaoTest {
    Movie movie;
    MovieDaoImpl movieDao;

    @Before
    public void setUp() {
        movieDao = new MovieDaoImpl();
        movie = new Movie();

        movie.setActor("Actor");
        movie.setDirector("Director");
        movie.setMovieProductionCompany("Production Company");
        movie.setVersion("Version 1.0");
        movie.setMovieNameVn("MovieNameVn");
        movie.setMovieNameEng("MovieNameEng");
        movie.setDuration(BigDecimal.valueOf(123));
        movie.setFromDate(LocalDate.of(2024, 8,23));
        movie.setToDate(LocalDate.of(2024, 8, 24));
        movie.setContent("This is content");
        movie.setLargeImage("/large.png");
        movie.setSmallImage("/small.png");
    }

    @Test
    public void tc1_saveOrUpdate_success(){
        boolean isSucceed = movieDao.saveOrUpdate(movie);
        System.out.println(movie.getMovieId());
        assertTrue(isSucceed);
    }

    @Test
    public void tc2_save_dateInvalid(){
        boolean success = false;
        try {
            movie.setToDate(LocalDate.of(2025, 2, 30));
            success = movieDao.saveOrUpdate(movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertFalse(success);
    }

    @Test
    public void tc3_save_invalidActorName(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i <= 100; i++) stringBuilder.append("Ha Nam");
        boolean isSucceed = false;
        try {
            movie.setActor(stringBuilder.toString());
            isSucceed = movieDao.saveOrUpdate(movie);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        assertFalse(isSucceed);
    }

    @Test
    public void tc4_save_durationInvalid(){
        movie.setDuration(BigDecimal.valueOf(1234)); // decimal(5,2)
        boolean isSucceed = false;
        try {
            isSucceed = movieDao.saveOrUpdate(movie);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        assertFalse(isSucceed);
    }

    @Test
    public void tc5_save_emptyInfo(){
        Movie emptyMovie = new Movie();
        assertTrue(movieDao.saveOrUpdate(emptyMovie));
    }

    @Test
    public void tc6_getById_success(){
        long id = 3;
        movie = movieDao.getById(id);
        if(movie == null) return;
        assertEquals(movieDao.getById(id).getMovieId(), id);
    }

    @Test
    public void tc7_getById_fail(){
        long id = 1000;
        movie = movieDao.getById(id);
        assertNull(movie);
    }

    @Test
    public void tc8_update_success(){
        movie.setMovieId(1);
        movie.setVersion("Version 2.0");
        assertTrue(movieDao.saveOrUpdate(movie));
    }

    @Test
    public void tc9_delete_success(){
        movie = movieDao.getById(1L);
        if(movie == null) return;
        assertTrue(movieDao.deleteById(movie.getMovieId()));
    }

    @Test
    public void tc10_delete_fail(){
        long movieId = 1000;
        movie = movieDao.getById(movieId);
        if(movie != null) return;
        assertFalse(movieDao.deleteById((int) movieId));
    }

    @Test
    public void tc11_getAll_success(){
        List<Movie> movies = movieDao.getAll();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());
    }
}
