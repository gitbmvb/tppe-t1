import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
public class ClientEligibility {
    private Database db;
    private Client client;
    private EPaymentMethod paymentMethod;
    private Boolean isClientEligibleExpected;
    private Date date;

    public ClientEligibility(Client client, EPaymentMethod paymentMethod, Date date, Boolean isClientEligibleExpected) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.isClientEligibleExpected = isClientEligibleExpected;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Product p1 = new Product(1, "Caderno", "Caderno escolar", 1.00, "item", 1);
        Product p2 = new Product(2, "Coca Cola", "Coca zero", 2.00, "litro", 1);
        Product p3 = new Product(3, "Tomate", "Tomate para salada", 3.00, "kg", 1);
        Product p4 = new Product(4, "Fone de ouvido", "Fone brabo", 115.00, "unidade", 1);
        DefaultClient client1 = new DefaultClient("João", EState.AC, EAddressPlace.Inside);
        client1.AddToCart(p4);
        DefaultClient client2 = new DefaultClient("João", EState.PB, EAddressPlace.Capital);
        client2.AddToCart(p2, p4);
        DefaultClient client3 = new DefaultClient("João", EState.GO, EAddressPlace.Capital);
        client3.AddToCart(p1, p2, p3);
        PrimeClient client4 = new PrimeClient("João", EState.SC, EAddressPlace.Inside);
        client4.AddToCart(p1, p2, p4);
        PrimeClient client5 = new PrimeClient("João", EState.RS, EAddressPlace.Capital);
        client5.AddToCart(p3);
        PrimeClient client6 = new PrimeClient("João", EState.DF, EAddressPlace.Capital);
        client6.AddToCart(p1, p2, p3, p4);

        Calendar month_ago = Calendar.getInstance();
        month_ago.add(Calendar.DAY_OF_MONTH, -1);
        Calendar today = Calendar.getInstance();

        Object[][] parameters = new Object[][] {
                { client1, EPaymentMethod.CashBack, month_ago.getTime(), false },
                { client2, EPaymentMethod.CreditCard, today.getTime(), false },
                { client3, EPaymentMethod.Pix, today.getTime(), false },
                { client4, EPaymentMethod.CashBack, month_ago.getTime(), false },
                { client5, EPaymentMethod.CreditCard, month_ago.getTime(), false },
                { client6, EPaymentMethod.Pix, today.getTime(), false },
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
    public void testIfClientIsEligible() {
        db.addClient(client);
        Sale sale = new Sale(client, paymentMethod);
        sale.setData(date);
        db.addSaleObject(sale);
        assertEquals(client.isNowSpecial(), isClientEligibleExpected);
        assertEquals(1, 1);
    }
}
