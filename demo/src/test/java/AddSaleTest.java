import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
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
import com.example.Models.Entities.Product.Product;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.Enums.EState;
import com.example.Models.ValueObject.Address;
import com.example.Models.ValueObject.ProductInfo;

public class AddSaleTest {
    private Database db;
    private Client client;
    private EPaymentMethod paymentMethod;
    private int result;

    public AddSaleTest(Client client, EPaymentMethod paymentMethod, int result) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.result = result;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Product p1 = new Product(new ProductInfo(1, "Caderno", "Caderno escolar"), 19.99, "item", 1);
        Product p2 = new Product(new ProductInfo(1, "Coca Cola", "Coca zero"), 8.99, "litro", 1);
        Product p3 = new Product(new ProductInfo(1, "Tomate", "Tomate para salada"), 23.55, "kg", 1);
        DefaultClient client1 = new DefaultClient("João", new Address(EState.SP, EAddressPlace.Capital), new Cart(new ArrayList<Product>(Arrays.asList(p1))));
        DefaultClient client2 = new DefaultClient("João", new Address(EState.SP, EAddressPlace.Capital), new Cart(new ArrayList<Product>(Arrays.asList(p1, p2))));
        DefaultClient client3 = new DefaultClient("João", new Address(EState.SP, EAddressPlace.Capital), new Cart(new ArrayList<Product>(Arrays.asList(p1, p2, p3))));
        
        Object[][] parameters = new Object[][] {
            {client1, EPaymentMethod.CashBack, 1},
            {client2, EPaymentMethod.CreditCard, 1},
            {client3, EPaymentMethod.Pix, 1}
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
}
