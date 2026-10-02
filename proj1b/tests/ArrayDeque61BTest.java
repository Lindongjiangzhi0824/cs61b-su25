import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void ConstructorTest() {
        /* Empty constructor */
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        assertWithMessage("Check default size whether equal to 8.").that(arrayDeque1.getNums().length).isEqualTo(8);

        /* Parameter-constrained constructor */
        ArrayDeque61B<Integer> arrayDeque2 = new ArrayDeque61B<>(10);
        assertWithMessage("Check size whether equal to 10.").that(arrayDeque2.getNums().length).isEqualTo(10);
        assertWithMessage("Check size whether equal to 0.").that(arrayDeque2.getSize()).isEqualTo(0);
    }
}
