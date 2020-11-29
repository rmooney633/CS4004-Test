import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Class_CompiledTest {

	@Test
	public void test() {
		Class_Compiled test = new Class_Compiled();

		double result1 = test.getSquare(1);
		assertEquals(1, result1, 0.001);

		double result2 = test.getCircle(2);
		assertEquals(12.56637, result2, 0.001);

		double result3 = test.getTriangle(4, 4, 2);
		assertEquals(3.873, result3, 0.001);

		String result4 = test.getShape("Square");
		assertEquals("square", result4);

		String result5 = test.getShape("cIRCle");
		assertEquals("circle", result5);

		String result6 = test.getShape("Rhombus");
		assertEquals("Shape was not a square, circle, or triangle", result6);
	}

}
