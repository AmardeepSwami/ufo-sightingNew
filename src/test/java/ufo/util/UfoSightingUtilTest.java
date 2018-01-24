package ufo.util;

import org.junit.Before;
import org.junit.Test;
import ufo.dto.UfoSighting;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ufo.UfoSightingUtil.*;

public class UfoSightingUtilTest {
    private UfoSighting ufoSighting;
    private String ufoSightingStr;

    @Before
    public void setUp(){
        ufoSightingStr = "19951009\t19951009\t Iowa City, IA\t\t\tMan repts. witnessing &quot;flash, followed by a classic UFO, w/ a tailfin at back.&quot; Red color on top half of tailfin. Became triangular.";
        ufoSighting = new UfoSighting("19951009", "19951009", " Iowa City, IA", "", "", "Man repts. witnessing &quot;flash, followed by a classic UFO, w/ a tailfin at back.&quot; Red color on top half of tailfin. Became triangular.");
    }

    @Test
    public void getUfoSightingFileTest() {
        assertNotNull(getUfoSightingFile());
    }

    @Test
    public void getUfoSightingDATATest() {
        assertNotNull(getUfoSightingsData());
    }
    
    @Test
    public void processUfoSightingInfoTest() {
        UfoSighting ufoSightingNew = processUfoSightingInfo(ufoSightingStr);
        assertNotNull(ufoSightingNew);
        assertEquals(ufoSighting.getDateSeen(), ufoSightingNew.getDateSeen());
        assertEquals(ufoSighting.getDateReported(), ufoSightingNew.getDateReported());
        assertEquals(ufoSighting.getDescription(), ufoSightingNew.getDescription());
        assertEquals(ufoSighting.getShape(), ufoSightingNew.getShape());
        assertEquals(ufoSighting.getDuration(), ufoSightingNew.getDuration());
        assertEquals(ufoSighting.getPlaceSeen(), ufoSightingNew.getPlaceSeen());

    }
}
