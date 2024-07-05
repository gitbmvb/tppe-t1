import static org.junit.Assert.assertEquals;
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

@RunWith(Parameterized.class)
public class ClientTest {

    private Database db;
    private EState state;
    private EAddressPlace address;
    private String name;
    private int expectedResult;
    private Class<? extends Client> clientClass;

    public ClientTest(Class<? extends Client> clientClass, EState state, EAddressPlace address, String name, int expectedResult) {
        this.clientClass = clientClass;
        this.state = state;
        this.address = address;
        this.name = name;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                { DefaultClient.class, EState.MG, EAddressPlace.Capital, "André Lanna", 1 },
                { PrimeClient.class, EState.BA, EAddressPlace.Inside, "Kyllian Mbappe", 1 },
                { SpecialClient.class, EState.DF, EAddressPlace.Capital, "Kvaratskelia", 1 },
                { DefaultClient.class, EState.DF, EAddressPlace.Inside, "André Balada", 1 }
        });
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
        db.getClients().clear();
    }

    @Test
    public void registerClient() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<? extends Client> constructor = clientClass.getConstructor(String.class, EState.class, EAddressPlace.class);
        Client client = constructor.newInstance(name, state, address);
        db.addClient(client);
        assertEquals(expectedResult, db.getClients().size());
    }
}
