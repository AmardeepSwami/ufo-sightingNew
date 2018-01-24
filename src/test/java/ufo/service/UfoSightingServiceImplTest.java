package ufo.service;

import org.junit.Before;
import org.junit.Test;
import ufo.dto.UfoSighting;

import java.util.List;

import static org.junit.Assert.*;

public class UfoSightingServiceImplTest {
    private UfoSightingServiceImpl ufoSightingService;

    @Before
    public void setUp() throws Exception {
        ufoSightingService = new UfoSightingServiceImpl();
    }

    @Test
    public void getAllSightingsTest() {
        List<UfoSighting> sightings = ufoSightingService.getAllSightings();
        assertNotEquals(0,sightings.size());
        assertEquals(61391, sightings.size());
    }

    @Test
    public void searchForOct1995Test() {
        List<UfoSighting> sightings = ufoSightingService.search(1995,10);
        assertNotNull(sightings);
        assertNotEquals(0,sightings.size());
        assertEquals(107, sightings.size());
    }
}