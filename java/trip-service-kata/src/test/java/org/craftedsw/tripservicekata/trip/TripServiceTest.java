package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

	public static final User GUEST_USER = null;
	public static final User UNSED_USER = null;
	private static final User OTHER_USER = new User();
	private static final Trip TO_SPAIN = new Trip();
	private static final Trip TO_LONDON = new Trip();
	private User REGISTERED_USER = new User();

	@Mock
	private TripDAO tripDAO;
	@InjectMocks @Spy
	private TripService tripService = new TripService();

	@Test(expected = UserNotLoggedInException.class) public void
    should_throw_an_exception_when_user_is_not_logged_in() {
		tripService.getFriendTrips(UNSED_USER, GUEST_USER);
	}


	@Test public void
	should_return_friends_trips_when_user_are_friends() {
		User friend = UserBuilder.aUser()
				.friendsWith(REGISTERED_USER)
				.withTrips(TO_SPAIN, TO_LONDON)
				.build();

		given(tripDAO.findTripsBy(friend)).willReturn(friend.trips());

		List<Trip> friendTrips  = tripService.getFriendTrips(friend, REGISTERED_USER);

	    assertThat(friendTrips.size(), is(2));
	}

	@Test public void
	should_not_return_any_trips_when_users_are_not_friends() {
		User friend = UserBuilder.aUser()
				.friendsWith(OTHER_USER)
				.withTrips(TO_SPAIN)
				.build() ;

		List<Trip> friendTrips  = tripService.getFriendTrips(friend, REGISTERED_USER);

		assertThat(friendTrips.size(), is(0));
	}
}
