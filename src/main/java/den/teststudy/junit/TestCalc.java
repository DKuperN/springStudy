package den.teststudy.junit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Dzianis_Kupryianchyk on 23-Mar-16.
 */
@RunWith(Parameterized.class)
public class TestCalc {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {5, 3, 8, 2},
                {15, 10, 25, 5},
                {5, 10, 15, -5}
        });
    }
    private int x1, x2, sum, sub;

    public TestCalc(int x1, int x2, int sum, int sub) {
        this.x1 = x1;
        this.x2 = x2;
        this.sum = sum;
        this.sub = sub;
    }

    @Test
    public void getSumTest(){
        Calc c = new Calc();
        assertEquals(c.getSum(x1, x2), sum);
        System.out.println("Sum "+ x1 +" + " + x2 + " OK");
    }
    @Test
    public void getSubtractionTest(){
        Calc c = new Calc();
        assertEquals(sub, c.getSubtraction(x1, x2));
        System.out.println("Sub "+ x1 +" - " + x2 + " OK");
    }

    @Before
    public void startTests(){
        System.out.println("Test is start: ");
    }
    @After
    public void finishTests(){
        System.out.println("Test is finished.");
    }

}
