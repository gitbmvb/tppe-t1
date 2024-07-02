import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.example.Models.ValueObject.CreditCard;

@RunWith(Parameterized.class)
public class CreditCardTest {

    public String number;
    public boolean expectedResult;

    public CreditCardTest(String number, boolean expectedResult) {
        this.number = number;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                { "4296 1323 8907 8903", true },
                { "4296 124", false },
                { "4256 1241 1234 1543", false },
                { "", false },

        };
        return Arrays.asList(parameters);
    }

    @Test
    public void ItShouldInstanciateTheCorrectCreditCardNumbers() {
        CreditCard cc = new CreditCard(number);
        assertEquals(cc.getNumber() != "", expectedResult);
    }
}
