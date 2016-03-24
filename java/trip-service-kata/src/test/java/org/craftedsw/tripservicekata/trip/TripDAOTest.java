package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {
    
    @Test(expected = CollaboratorCallException.class) public void
    should_thrown_an_exception_when_retrieving_user_trips() {
        TripDAO tripDao = new TripDAO();

        tripDao.findTripsBy(new User());
    }

}
