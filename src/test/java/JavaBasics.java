import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

public class JavaBasics {

    @Test
    public void javaBasic () {
        int a = 13;
        int b = 10;

        int c = sumDigits(13, 10);
        int d = sumDigits(a, b);
        int e = a + b;

        System.out.println("Sum is: " + c);
        System.out.println("Sum is: " + d);
        System.out.println("Sum is: " + e);

        Assertions.assertEquals(23, c, "Sum is not correct");
    }



    private int sumDigits (int a, int b) {
        return a + b;

    }

}
