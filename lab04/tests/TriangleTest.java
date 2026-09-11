import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;
public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    // FIXME: Add additional tests for Triangle.java here that pass on a
    //  correct Triangle implementation and fail on buggy Triangle implementations.

    @Test
    public void testSidesFormTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("3,4,5 可以成为三角形").that(t.sidesFormTriangle(3,4,5)).isTrue();
        assertWithMessage("1,2,3 不可以成为三角形").that(t.sidesFormTriangle(1,2,3)).isFalse();
        assertWithMessage("1,1,2 不可以成为三角形").that(t.sidesFormTriangle(1,1,2)).isFalse();
        assertWithMessage("3,3,3 可以成为三角形").that(t.sidesFormTriangle(3,3,3)).isTrue();
    }

    @Test
    public void testPointsFormTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("(0 0), (0 1), (1 0) 可以成为三角形").that(t.pointsFormTriangle(0,0,0,1,1,0)).isTrue();
        assertWithMessage("(0 0), (0 1), (0 2) 共线").that(t.pointsFormTriangle(0,0,0,1,0,2)).isFalse();
        assertWithMessage("(0 0), (1 0), (2 0) 共线").that(t.pointsFormTriangle(0,0,1,0,2,0)).isFalse();
        assertWithMessage("(0 0), (1 1), (2 2) 共线").that(t.pointsFormTriangle(0,0,1,1,2,2)).isFalse();
        assertWithMessage("(0 0), (0 0), (0 0) 三个点共点").that(t.pointsFormTriangle(0,0,0,0,0,0)).isFalse();
        assertWithMessage("(0 0), (0 0), (0 1) 两个点共点").that(t.pointsFormTriangle(0,0,0,0,0,1)).isFalse();
    }

    @Test
    public void testTriangleType() {
        Triangle t = getNewTriangle();
        assertWithMessage("3,3,3 应为 Equilateral").that(t.triangleType(3,3,3)).isEqualTo("Equilateral");
        assertWithMessage("3,3,5 应为 Isosceles").that(t.triangleType(3,3,5)).isEqualTo("Isosceles");
        assertWithMessage("5,3,3 应为 Isosceles").that(t.triangleType(5,3,3)).isEqualTo("Isosceles");
        assertWithMessage("3,4,5 应为 Scalene").that(t.triangleType(3,4,5)).isEqualTo("Scalene");
    }

    @Test
    public void testSquaredHypotenuse() {
        Triangle t = getNewTriangle();
        assertWithMessage("3, 4 的平方斜边为 25").that(t.squaredHypotenuse(3,4)).isEqualTo(25);
        assertWithMessage("0, 5 的平方斜边为 25").that(t.squaredHypotenuse(0,5)).isEqualTo(25);
        assertWithMessage("3, 3 的平方斜边为 18").that(t.squaredHypotenuse(3,3)).isEqualTo(18);
    }

}
