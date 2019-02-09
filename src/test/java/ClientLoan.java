import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ClientLoan {
    @Test
    public void clientLoan () {
        sumToPay (100, 124);
    }


    public void sumToPay (double a, double b) {
        double r = a + 0.24 * a;
        Assertions.assertEquals(b, r, "The sum is incorrect!");
    }
}

//а- необходимая клиенту сумма
//b- сколько клиент думает он должен вернуть
//r- сколько клиент по факту должен вернуть