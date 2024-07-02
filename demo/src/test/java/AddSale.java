import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.example.Database.Database;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Cart.Cart;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;

public class AddSale {
    private Database db;
    private int id;
    private Client client;
    private Cart cart;
    private EPaymentMethod paymentMethod;
    private int result;

    public AddSale(int id, Client client, Cart cart, EPaymentMethod paymentMethod, int result) {
        this.id = id;
        this.client = client;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.result = result;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                {10, new DefaultClient("João", new Address(EState.AC, EAddressPlace.Inside)), new Cart(), EPaymentMethod.CreditCard, 1 }
        };

        return Arrays.asList(parameters);
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
    }

    @After
    public void cleanDb() {
        this.db.getSales().clear();
    }

    @Test
    public void registerASale() {
        db.addSale(client, cart, paymentMethod);
        assertTrue(db.getSales().size() == result);
    }
}
