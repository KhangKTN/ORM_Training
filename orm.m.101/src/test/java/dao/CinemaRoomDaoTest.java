package dao;

import org.example.dao.CinemaRoomDaoImpl;
import org.example.entities.CinemaRoom;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CinemaRoomDaoTest {
    CinemaRoomDaoImpl cinemaRoomDao;
    CinemaRoom cinemaRoom;

    @BeforeEach
    public void initialize() {
        cinemaRoomDao = new CinemaRoomDaoImpl();

        cinemaRoom = new CinemaRoom();
        cinemaRoom.setName("Cinema Room");
        cinemaRoom.setSeatQuantity(10);
    }

    @Test
    public void tc1_saveOrUpdate_success() {
        boolean actual = cinemaRoomDao.saveOrUpdate(cinemaRoom);
        assertTrue(actual);
    }

    @Test
    public void tc2_saveOrUpdate_null() {
        cinemaRoom = null;
        boolean actual = cinemaRoomDao.saveOrUpdate(cinemaRoom);
        assertFalse(actual);
    }

    @Test
    public void tc3_getById_existsId() {
        int roomId = 1;
        cinemaRoom = cinemaRoomDao.getById((long) roomId);
        if(cinemaRoom == null) return;
        assertEquals(cinemaRoom.getId(), roomId);
    }

    @Test
    public void tc4_deleteById_existsId() {
        int roomId = 1;
        boolean actual = cinemaRoomDao.deleteById(roomId);
        assertTrue(actual);
    }

    @Test
    public void tc5_deleteById_invalidId() {
        int roomId = 1000;
        boolean actual = cinemaRoomDao.deleteById(roomId);
        assertFalse(actual);
    }
}
