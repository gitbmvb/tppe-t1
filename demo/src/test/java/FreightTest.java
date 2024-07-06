import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Client.DefaultClient;
import com.example.Models.Entities.Client.PrimeClient;
import com.example.Models.Entities.Product.Product;
import com.example.Models.Entities.Sale.Sale;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;

@RunWith(Parameterized.class)
public class FreightTest {
    private Client client;
    private EPaymentMethod paymentMethod;
    private Double expectedFreight;
   
    public FreightTest(Client client, EPaymentMethod paymentMethod, Double expectedFreight) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.expectedFreight = expectedFreight;
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

        Object[][] parameters = new Object[][] {
                { client1, EPaymentMethod.CashBack, 25.0 },
                { client2, EPaymentMethod.CreditCard, 15.0 },
                { client3, EPaymentMethod.Pix, 10.0},
                { client4, EPaymentMethod.CashBack, 13.0 },
                { client5, EPaymentMethod.CreditCard, 10.0 },
                { client6, EPaymentMethod.Pix, 5.0 },
        };
        return Arrays.asList(parameters);
    }

    @Test
    public void calculateFreight() {
        Sale sale = new Sale(client, paymentMethod);
        assertEquals(sale.calculateFreigth(), expectedFreight, 0.001);
    }
}
