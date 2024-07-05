import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.example.Database.Database;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;

@RunWith(Parameterized.class)
public class TaxesTest {
    private Database db;
    private Client client;
    private EPaymentMethod paymentMethod;
    private Double expectedICMS;
    private Double expectedMunicipal;

    public TaxesTest(Client client, EPaymentMethod paymentMethod, Double expectedICMS, Double expectedMunicipal) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.expectedICMS = expectedICMS;
        this.expectedMunicipal = expectedMunicipal;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Product p1 = new Product(1, "Caderno", "Caderno escolar", 1.00, "item", 1);
        Product p2 = new Product(2, "Coca Cola", "Coca zero", 2.00, "litro", 1);
        Product p3 = new Product(3, "Tomate", "Tomate para salada", 3.00, "kg", 1);
        DefaultClient client1 = new DefaultClient("João", EState.AC, EAddressPlace.Inside);
        client1.AddToCart(p1);
        DefaultClient client2 = new DefaultClient("João", EState.PB, EAddressPlace.Capital);
        client2.AddToCart(p1, p2);
        DefaultClient client3 = new DefaultClient("João", EState.GO, EAddressPlace.Capital);
        client3.AddToCart(p1, p2, p3);
        PrimeClient client4 = new PrimeClient("João", EState.SC, EAddressPlace.Inside);
        client4.AddToCart(p1, p2);
        PrimeClient client5 = new PrimeClient("João", EState.RS, EAddressPlace.Capital);
        client5.AddToCart(p1, p2, p3);
        PrimeClient client6 = new PrimeClient("João", EState.DF, EAddressPlace.Capital);
        client6.AddToCart(p1, p2, p3);

        Object[][] parameters = new Object[][] {
                { client1, EPaymentMethod.CashBack, 0.12, 0.04},
                { client2, EPaymentMethod.CreditCard, 0.36, 0.12},
                { client3, EPaymentMethod.Pix,  0.72, 0.24},
                { client4, EPaymentMethod.CashBack, 0.36, 0.12},
                { client5, EPaymentMethod.CreditCard, 0.72, 0.24},
                { client6, EPaymentMethod.Pix,  1.08, 0.0},
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
    public void calculateICMS() {
        Sale sale = new Sale(client, paymentMethod);
        EState state = sale.getClient().getAddress().getState();
        Double value = sale.getClient().getCart().getTotalValue();
        assertEquals(expectedICMS, sale.getTaxes().calculateICMS(state, value), 0.001);
    }

    @Test
    public void calculateMunicipal() {
        Sale sale = new Sale(client, paymentMethod);
        EState state = sale.getClient().getAddress().getState();
        Double value = sale.getClient().getCart().getTotalValue();
        assertEquals(expectedMunicipal, sale.getTaxes().calculateMunicipal(state, value), 0.001);
    }
}
