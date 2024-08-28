package dao;

import org.example.dao.impl.TypeDaoImpl;
import org.example.entities.Type;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TypeDaoTest {
    Type type;
    TypeDaoImpl typeDaoImpl;

    @Before
    public void init() {
        typeDaoImpl = new TypeDaoImpl();
        type = new Type();

        type.setName("Action");
        type.setDescription("Action thriller movies will have intense fighting scenes");
    }

    @Test
    public void tc1_save_success(){
        assertTrue(typeDaoImpl.saveOrUpdate(type));
    }

    @Test
    public void tc2_getById_success(){
        long typeId = 1;
        assertNotNull(typeDaoImpl.getById(typeId));
    }

    @Test
    public void tc3_getById_fail(){
        long typeId = 1000;
        assertNull(typeDaoImpl.getById(typeId));
    }

    @Test
    public void tc4_update_success(){
        type.setTypeId(1L);
        type.setDescription("Update description");
        assertTrue(typeDaoImpl.saveOrUpdate(type));
    }

    @Test
    public void tc5_update_fail(){
        type.setTypeId(100L);
        type.setDescription("Update description");
        assertFalse(typeDaoImpl.saveOrUpdate(type));
    }

    @Test
    public void tc6_getAll_success(){
        assertNotNull(typeDaoImpl.getAll());
        assertFalse(typeDaoImpl.getAll().isEmpty());
    }

    @Test
    public void tc7_delete_success(){
        assertTrue(typeDaoImpl.deleteById(1));
    }

    @Test
    public void tc8_delete_fail(){
        assertFalse(typeDaoImpl.deleteById(100));
    }
}
