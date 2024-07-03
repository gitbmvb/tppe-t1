import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import com.example.Models.ValueObject.Address;
import com.example.Models.ValueObject.ProductInfo;

@RunWith(Parameterized.class)
public class CashBackTest {

    public String name;
    public Double result;
    public EPaymentMethod paymentMethod;
    public Class<? extends Client> clientClass;
    public Address address = new Address(EState.MG, EAddressPlace.Capital);
    Product p1 = new Product(new ProductInfo(1, "Caderno", "Caderno escolar"), 2.00, "item", 1);
    Product p2 = new Product(new ProductInfo(1, "Coca Cola", "Coca zero"), 3.00, "litro", 1);
    Product p3 = new Product(new ProductInfo(1, "Tomate", "Tomate para salada"), 4.00, "kg", 1);

    public CashBackTest(Class<? extends Client> clientClass, String name, Double result, EPaymentMethod paymentMethod) {
        this.clientClass = clientClass;
        this.name = name;
        this.result = result;
        this.paymentMethod = paymentMethod;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                { PrimeClient.class, "João", 6.3, EPaymentMethod.CreditCard },
                { PrimeClient.class, "João", 7.0, EPaymentMethod.Pix },
                { DefaultClient.class, "Lionel Messi", 0.0, EPaymentMethod.CreditCard }
        };

        return Arrays.asList(parameters);
    }

    @Test
    public void ItShouldCalculateCashbackForEachClient() throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<? extends Client> constructor = clientClass.getConstructor(String.class, Address.class);
        Client c = constructor.newInstance(name, address);
        Sale s = new Sale(c, paymentMethod);
        s.finish();
        assertEquals(c.getCashBack(), result, 0.001);
    }
}
