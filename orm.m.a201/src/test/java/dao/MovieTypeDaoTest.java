package dao;

import org.example.dao.MovieTypeDao;
import org.example.dao.impl.MovieDaoImpl;
import org.example.dao.impl.MovieTypeDaoImpl;
import org.example.dao.impl.TypeDaoImpl;
import org.example.entities.Movie;
import org.example.entities.MovieType;
import org.example.entities.Type;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class MovieTypeDaoTest {
    MovieTypeDao movieTypeDao;
    MovieDaoImpl movieDao;
    TypeDaoImpl typeDao;

    Movie movie;
    MovieType movieType;
    Type type;

    @Before
    public void setUp(){
        movieTypeDao = new MovieTypeDaoImpl();
        movieDao = new MovieDaoImpl();
        typeDao = new TypeDaoImpl();

        movie = new Movie();
        movieType = new MovieType();

        // Add new movie
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

        movieDao.saveOrUpdate(movie);


        // Add new type of movie
        type = new Type();
        type.setName("Action");
        type.setDescription("Has exciting fight scenes");
        typeDao.saveOrUpdate(type);

        movieType.setMovie(movie);
        movieType.setType(type);
        movieType.setMtDescription("This is description");
    }

    @Test
    public void tc1_save_success(){
        assertTrue(movieTypeDao.saveOrUpdate(movieType));
    }

    @Test
    public void tc2_save_fail(){
        boolean isSuccess = false;
        try {
            movieType.setMovie(null);
            isSuccess = movieTypeDao.saveOrUpdate(movieType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertFalse(isSuccess);
    }

    @Test
    public void tc3_getAll_success(){
        List<MovieType> movieTypeList = movieTypeDao.findAll();
        assertNotNull(movieTypeList);
        assertFalse(movieTypeList.isEmpty());
    }

    @Test
    public void tc4_getAllByMovieId_success(){
        List<MovieType> movieTypeList = movieTypeDao.findByMovieId(3);
        System.out.println(movieTypeList.size());
        movieTypeList.forEach(movieType -> System.out.println(movieType.getType().getTypeId()));
        assertNotNull(movieTypeList);
        assertFalse(movieTypeList.isEmpty());
    }

    @Test
    public void tc4_getAllByTypeId_success(){
        List<MovieType> movieTypeList = movieTypeDao.findByTypeId(2);
        movieTypeList.forEach(movieType -> System.out.println(movieType.getMovie().getMovieId()));
        assertNotNull(movieTypeList);
        assertFalse(movieTypeList.isEmpty());
    }


    @Test
    public void tc5_deleteByTypeId_success(){
        int typeId = 3;
        assertTrue(movieTypeDao.deleteByTypeId(typeId));
    }

    @Test
    public void tc6_update_success(){
        int typeId = 1;
        int movieId = 3;
//        movieType = movieTypeDao.
    }
}
