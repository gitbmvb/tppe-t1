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

@RunWith(Parameterized.class)
public class AddProduct {

    private Database db;
    private int code;
    private String name;
    private String description;
    private Double price;
    private String unit;
    private int amount;

    public AddProduct(int code, String name, String description, Double price, String unit, int amount, int result) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.amount = amount;
        this.result = result;
    }

    private int result;

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                {10, "Teclado Gamer", "Um teclado gamer", 140, "item", 1 }
        };

        return Arrays.asList(parameters);
    }

    @Before
    public void setupDb() {
        this.db = Database.getInstance();
    }

    @After
    public void cleanDb() {
        this.db.getProducts().clear();
    }

    @Test
    public void registerAProduct() {
        db.addProduct(code, name, description, price, unit, amount);
        assertTrue(db.getProducts().size() == result);
    }
}