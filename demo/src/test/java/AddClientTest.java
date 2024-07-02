import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.example.Database.Database;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;

@RunWith(Parameterized.class)
public class AddClientTest {

    public Database db;
    public EState state;
    public EAddressPlace address;
    public String name;
    public Integer result;

    public AddClientTest(EState state, EAddressPlace address, String name, int result) {
        this.state = state;
        this.address = address;
        this.name = name;
        this.result = result;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                { EState.MG, EAddressPlace.Capital, "André Lanna", 1 },
                { EState.BA, EAddressPlace.Inside, "Kyllian Mbappe", 1 },
                { EState.DF, EAddressPlace.Capital, "Kvaratskelia", 1 },
                { EState.DF, EAddressPlace.Inside, "André Balada", 1 }
        };

        return Arrays.asList(parameters);
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
    }

    @After
    public void cleanDb() {
        this.db.getClients().clear();
    }

    @Test
    public void registerADefaultClient() {
        db.addDefaultClient(name, state, address);
        assertTrue(db.getClients().size() == result);
    }

    @Test
    public void registerAPrimeClient() {
        db.addPrimeClient(name, state, address);
        assertTrue(db.getClients().size() == result);
    }
}