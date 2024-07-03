import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.example.Database.Database;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Client.SpecialClient;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;

@RunWith(Parameterized.class)
public class ClientTest {

    public Database db;
    public EState state;
    public EAddressPlace address;
    public String name;
    public Integer result;
    public Class<? extends Client> clientClass;

    public ClientTest(Class<? extends Client> clientClass, EState state, EAddressPlace address, String name,
            int result) {
        this.clientClass = clientClass;
        this.state = state;
        this.address = address;
        this.name = name;
        this.result = result;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                { DefaultClient.class, EState.MG, EAddressPlace.Capital, "André Lanna", 1 },
                { PrimeClient.class, EState.BA, EAddressPlace.Inside, "Kyllian Mbappe", 2 },
                { SpecialClient.class, EState.DF, EAddressPlace.Capital, "Kvaratskelia", 3 },
                { DefaultClient.class, EState.DF, EAddressPlace.Inside, "André Balada", 4 }
        };

        return Arrays.asList(parameters);
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
    }

    @Test
    public void registerADefaultClient() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<? extends Client> constructor = clientClass.getConstructor(String.class, Address.class);
        Client c = constructor.newInstance(name, new Address(state, address));
        db.addClient(c);
        assertTrue(db.getClients().size() == result);
    }
}