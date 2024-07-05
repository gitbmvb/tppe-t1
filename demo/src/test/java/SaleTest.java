import static org.junit.Assert.assertEquals;
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
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Taxes;

@RunWith(Parameterized.class)
public class SaleTest {
    private Database db;
    private Client client;
    private EPaymentMethod paymentMethod;
    private int result;
    private Double expectedFreight;
    private Double expectedTaxes;

    public SaleTest(Client client, EPaymentMethod paymentMethod, int result, Double expectedFreight,
            Double expectedTaxes) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.result = result;
        this.expectedFreight = expectedFreight;
        this.expectedTaxes = expectedTaxes;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Product p1 = new Product(1, "Caderno", "Caderno escolar", 2.00, "item", 1);
        Product p2 = new Product(1, "Coca Cola", "Coca zero", 3.00, "litro", 1);
        Product p3 = new Product(1, "Tomate", "Tomate para salada", 4.00, "kg", 1);
        DefaultClient client1 = new DefaultClient("João", EState.AC, EAddressPlace.Inside);
        client1.AddToCart(p1);
        DefaultClient client2 = new DefaultClient("João", EState.PB, EAddressPlace.Capital);
        client2.AddToCart(p1, p2);
        DefaultClient client3 = new DefaultClient("João", EState.GO, EAddressPlace.Capital);
        client3.AddToCart(p1, p2, p3);
        PrimeClient client4 = new PrimeClient("João", EState.SC, EAddressPlace.Inside);
        client4.AddToCart(p1, p2);
        PrimeClient client5 = new PrimeClient("João", EState.SP, EAddressPlace.Capital);
        client5.AddToCart(p1, p2, p3);
        PrimeClient client6 = new PrimeClient("João", EState.DF, EAddressPlace.Capital);
        client6.AddToCart(p1, p2, p3);
        // SpecialClient client7 = new SpecialClient("Antônio Carlos", EState.RS, EAddressPlace.Capital));
        // client7.AddToCart(p1);

        Object[][] parameters = new Object[][] {
                { client1, EPaymentMethod.CashBack, 1, 25.0, 0.16 * 2 },
                { client2, EPaymentMethod.CreditCard, 1, 15.0, 0.48 },
                { client3, EPaymentMethod.Pix, 1, 10.0, 0.64 },
                { client4, EPaymentMethod.CashBack, 1, 13.0, 0.48 },
                { client5, EPaymentMethod.CreditCard, 1, 10.0, 0.64 },
                { client6, EPaymentMethod.Pix, 1, 5.0, 0.72 },
                // { client7, EPaymentMethod.CashBack, 7, 0.0, 0.0 },
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
        db.addSale(client, paymentMethod);
        assertTrue(db.getSales().size() == result);
    }

    @Test
    public void calculateFreight() {
        Sale sale = new Sale(client, paymentMethod);
        assertEquals(sale.calculateFreigth(), expectedFreight, 0.001);
    }
}
