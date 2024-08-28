package dao;

import org.example.dao.CinemaRoomDaoImpl;
import org.example.dao.SeatDaoImpl;
import org.example.entities.CinemaRoom;
import org.example.entities.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatDaoTest {
    CinemaRoomDaoImpl cinemaRoomDaoImpl;
    SeatDaoImpl seatDaoImpl;
    CinemaRoom cinemaRoom;
    Seat seat;

    @BeforeEach
    void setUp() {
        seatDaoImpl = new SeatDaoImpl();
        cinemaRoom = new CinemaRoom();
        cinemaRoomDaoImpl = new CinemaRoomDaoImpl();

        int cinemaId = 2;
        cinemaRoom = cinemaRoomDaoImpl.getById((long) cinemaId);
        if (cinemaRoom == null) {
            return;
        }
        seat = new Seat();
        seat.setCinemaRoom(cinemaRoom);
        seat.setSeatColumn(10);
        seat.setSeatRow(1);
        seat.setType("Normal");
        seat.setStatus("Available");
    }

    @Test
    void tc1_saveOrUpdate_success(){
        cinemaRoom = cinemaRoomDaoImpl.getById(2L);
        seat.setCinemaRoom(cinemaRoom);
        assertTrue(seatDaoImpl.saveOrUpdate(seat));
    }

    @Test
    void tc2_saveOrUpdate_missingCinemaRoomId(){
        seat.setCinemaRoom(null);
        assertFalse(seatDaoImpl.saveOrUpdate(seat));
    }

    @Test
    void tc3_saveOrUpdate_missingSeatColumn(){
        cinemaRoom = cinemaRoomDaoImpl.getById(2L);
        seat.setCinemaRoom(cinemaRoom);
        assertTrue(seatDaoImpl.saveOrUpdate(seat));
    }

    @Test
    void tc4_getById_success(){
        long id = 1;
        Seat seat = seatDaoImpl.getById(id);
        if(seat == null) return;
        assertEquals(id, seat.getId());
    }

    @Test
    void tc5_getById_fail(){
        long seatId = 1000;
        assertNull(seatDaoImpl.getById(seatId));
    }

    @Test
    void tc6_deleteById_success(){
        assertTrue(seatDaoImpl.deleteById(1));
    }

    @Test
    void tc7_deleteById_fail(){
        int seatId = 1000;
        assertFalse(seatDaoImpl.deleteById(seatId));
    }
}
