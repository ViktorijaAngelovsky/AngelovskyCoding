import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

public class JavaBasics {

    @Test
    public void javaBasic () {
        int a = 13;
        int b = 10;
        //задать переменные (var)


        int c = sumDigits(13, 10);
        //вызов функции и запись в var c. передача любых значений
        int d = sumDigits(a, b);
        //вызов функции и передача уже заданных var
        int e = a + b;
        //сложение без функции

        System.out.println("Sum is: " + c);
        System.out.println("Sum is: " + d);
        System.out.println("Sum is: " + e);

        Assertions.assertEquals(23, c, "Sum is not correct");
    }
    //проверяет условие. если оно не выполняется, то заваливает программу



    private int sumDigits (int x, int y) {
        return x + y;
    }
}
