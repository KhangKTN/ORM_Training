package dao;

import org.example.dao.RoomDetailDaoImpl;
import org.example.entities.CinemaRoom;
import org.example.entities.RoomDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomDetailDaoTest {
    RoomDetailDaoImpl roomDetailDao;
    CinemaRoom cinemaRoom;
    RoomDetail roomDetail;

    @BeforeEach
    void setUp() {
        roomDetailDao = new RoomDetailDaoImpl();
        cinemaRoom = new CinemaRoom();
        cinemaRoom.setName("Room Test 1");
        cinemaRoom.setSeatQuantity(50);

        roomDetail = new RoomDetail();
        roomDetail.setRoomRate(5);
        roomDetail.setCinemaRoom(cinemaRoom);
        roomDetail.setActiveDate(LocalDate.of(2024, 8, 20));
        roomDetail.setDescription("This is description");
    }

    @Test
    void tc1_saveOrUpdate_success(){
        assertTrue(roomDetailDao.saveOrUpdate(roomDetail));
    }

    @Test
    void tc2_saveOrUpdate_nullCinemaRoom(){
        roomDetail.setCinemaRoom(null);
        assertFalse(roomDetailDao.saveOrUpdate(roomDetail));
    }

    @Test
    void tc3_saveOrUpdate_nullIdOfCinemaRoom(){
        cinemaRoom.setId(0);
        roomDetail.setCinemaRoom(cinemaRoom);
        assertFalse(roomDetailDao.saveOrUpdate(roomDetail));
    }

    @Test
    void tc4_findById_success(){
        int roomDetailId = 1;
        assertTrue(roomDetailDao.getById((long) roomDetailId).getId() == roomDetailId);
    }

    @Test
    void tc5_findById_idNotExists(){
        int roomDetailId = 1000;
        assertNull(roomDetailDao.getById((long) roomDetailId));
    }

    @Test
    void tc6_findAll_success(){
        List<RoomDetail> roomDetailList = roomDetailDao.getAll();
        roomDetailList.forEach(roomDetail -> {
            System.out.println(roomDetail.getCinemaRoom().getName());
        });
    }

    @Test
    void tc7_deleteById_success(){
        int roomDetailId = 1;
        assertTrue(roomDetailDao.deleteById(roomDetailId));
    }

    @Test
    void tc8_deleteById_null(){
        int roomDetailId = 1000;
        assertFalse(roomDetailDao.deleteById(roomDetailId));
    }
}
