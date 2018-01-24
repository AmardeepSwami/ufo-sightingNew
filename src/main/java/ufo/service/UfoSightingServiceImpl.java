package ufo.service;

import java.util.List;
import java.util.stream.Collectors;

import ufo.UfoSightingUtil;
import ufo.dto.UfoSighting;



public class UfoSightingServiceImpl implements UfoSightingService {
    /**
     * Returns all the sightings in the tsv file.
     */
    public List<UfoSighting> getAllSightings() {
         
        List<String> sightingstrList = UfoSightingUtil.getUfoSightingsData();
        List<UfoSighting> sightings = UfoSightingUtil.processUfoSightingInfo(sightingstrList);
        
        return sightings;
    }

   /**
     * Search for sightings happened in the specified year and month;
     *
     * @param yearSeen  The year when the sighting happened
     * @param monthSeen The month when the sightings happened
     */
    public List<UfoSighting> search(int yearSeen, int monthSeen) {
        List<UfoSighting> result = getAllSightings().stream() // convert UfoSighting list into stream
                .filter(ufoSighting -> ufoSighting.getDateSeen().startsWith(String.valueOf(yearSeen).concat(String.valueOf(monthSeen)))) // filter stream on date seen
                .collect(Collectors.toList()); // convert filtered stream into UfoSighting list
        return result;
    }
}
