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
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;

@RunWith(Parameterized.class)
public class Requirement1 {

    public Database db;
    public EState state;
    public EAddressPlace address;
    public String name;
    public int result;

    public Requirement1(EState state, EAddressPlace address, String name, int result) {
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
    public void ItShouldRegisterADefaultClient() {
        Address a = new Address(state, address);
        DefaultClient c = new DefaultClient(name, a);
        db.addClient(c);
        assertTrue(db.getClients().size() == result);
    }

    @Test
    public void ItShouldRegisterAPrimeClient() {
        Address a = new Address(state, address);
        PrimeClient c = new PrimeClient(name, a);
        db.addClient(c);
        assertTrue(db.getClients().size() == result);
    }
}