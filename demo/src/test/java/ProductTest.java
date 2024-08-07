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

@RunWith(Parameterized.class)
public class ProductTest {
    private Database db;
    private int code;
    private String name;
    private String description;
    private Double price;
    private String unit;
    private int amount;
    private int expectedResult;

    public ProductTest(int code, String name, String description, Double price, String unit, int amount, int expectedResult) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.amount = amount;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
            {12345, "Teclado Gamer", "Um teclado gamer", 140.0, "item", 1, 1},
            {314159, "Pizza", "Pizza Marguerita", 34.99, "unidade", 1, 1},
        };
        return Arrays.asList(parameters);
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
        db.getProducts().clear();
        db.getSales().clear();
    }

    @After
    public void cleanDb() {
        db.getProducts().clear();
        db.getSales().clear();
    }

    @Test
    public void registerAProduct() {
        db.addProduct(code, name, description, price, unit, amount);
        assertEquals(expectedResult, db.getProducts().size());
    }
}
