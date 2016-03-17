package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceTest {

	public static final User GUEST_USER = null;
	public static final User UNSED_USER = null;
	private static final User OTHER_USER = new User();
	private static final Trip TO_SPAIN = new Trip();
	private static final Trip TO_LONDON = new Trip();
	private User loggedInUser;
	private User REGISTERED_USER = new User();

	private TripService tripService;


	@Before
	public void initialise() {
		tripService = new TestableTripService();

	}

	@Test(expected = UserNotLoggedInException.class) public void
    should_throw_an_exception_when_user_is_not_logged_in() {
		loggedInUser = GUEST_USER;

		tripService.getTripsByUser(UNSED_USER);
		loggedInUser = REGISTERED_USER;
	}


	@Test public void
	should_return_friends_trips_when_user_are_friends() {
		User friend = new User();
		friend.addFriend(loggedInUser);
		friend.addTrip(TO_SPAIN);
		friend.addTrip(TO_LONDON);

		List<Trip> friendTrips  = tripService.getTripsByUser(friend);

	    assertThat(friendTrips.size(), is(2));
	}

	@Test public void
	should_not_return_any_trips_when_users_are_not_friends() {
		User friend = new User();
		friend.addFriend(OTHER_USER);
		friend.addTrip(TO_SPAIN);

		List<Trip> friendTrips  = tripService.getTripsByUser(friend);

	}

	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> findTripsBy(User user) {
			return user.trips();
		}
	}
}
