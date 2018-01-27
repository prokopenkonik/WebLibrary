package com.company.model.util;

import com.company.model.dao.constants.SqlQueries;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlQueriesManagerTest {
    @Test
    public void getQuery() throws Exception {
        String actual = "SELECT * FROM authors;";
        String expected = SqlQueriesManager.getQuery(SqlQueries.GET_ALL_AUTHORS);
        assertEquals(expected, actual);
    }
}