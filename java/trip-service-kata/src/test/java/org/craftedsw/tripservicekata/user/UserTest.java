package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.UserBuilder;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private static final User PETER = new User();
    private static final User RICK = new User();
    private static final User LOUISA = new User();

    @Test
    public void
    should_tell_us_if_users_are_NOT_friends() {
        User user = UserBuilder.aUser()
                .friendsWith(PETER)
                .build();

        assertThat(user.isFriendWith(RICK), is(false));
    }

    @Test
    public void
    should_tell_us_if_users_are_friends() {
        User user = UserBuilder.aUser()
                .friendsWith(PETER, LOUISA)
                .build();

        assertThat(user.isFriendWith(LOUISA), is(true));
    }
}
