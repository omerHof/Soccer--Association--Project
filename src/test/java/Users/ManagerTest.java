package Users;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class ManagerTest {
    Manager manager;

    @Before
    public void setUp() throws Exception {
        manager = new Manager("m", "1234", "m m", "m.com");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void changePlayerRole() {
        LocalDate date11 = LocalDate.of(1994, 6, 28);
        Player player = new Player("almostOwner1", "1234", "playerName", "player.com", date11, "player_that_plays");
        assertEquals(player.getCourtRole(), "player_that_plays");
        manager.changePlayerRole(player, "player_that_plays_and_plays_again");
        assertEquals(player.getCourtRole(), "player_that_plays_and_plays_again");
    }
}
