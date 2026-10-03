import deque.ArrayDeque61B;
import edu.princeton.cs.algs4.In;
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
        assertWithMessage("Check size whether equal to 0.").that(arrayDeque2.size()).isEqualTo(0);
    }

    @Test
    public void addFirstTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        arrayDeque1.addFirst(5);
        assertWithMessage("First element is 5.").that(arrayDeque1.get(arrayDeque1.getHead())).isEqualTo(5);
        assertWithMessage("Size should equal to 1").that(arrayDeque1.size()).isEqualTo(1);
        arrayDeque1.addFirst(8);
        arrayDeque1.addFirst(9);
        assertThat(arrayDeque1.toList()).containsExactly(9, 8, 5).inOrder();
        assertThat(arrayDeque1.size()).isEqualTo(3);

        /* Another element type test */
        ArrayDeque61B<String> arrayDeque2 = new ArrayDeque61B<>();
        arrayDeque2.addFirst("Java");
        arrayDeque2.addFirst("is");
        arrayDeque2.addFirst("This");
        int tail = arrayDeque2.getTail();
        String temp = arrayDeque2.get(arrayDeque2.getTail());
        assertWithMessage("Check last element whether is equal to Java.")
                .that(arrayDeque2.get(arrayDeque2.getTail())).isEqualTo("Java");
        assertThat(arrayDeque2.toList()).containsExactly("This", "is", "Java").inOrder();
        assertThat(arrayDeque2.size()).isEqualTo(3);

    }

    @Test
    public void addLastTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        arrayDeque1.addLast(1);
        arrayDeque1.addLast(3);
        assertWithMessage("Convert to list should contain 1, 3.")
                .that(arrayDeque1.toList()).containsExactly(1, 3).inOrder();
        assertThat(arrayDeque1.size()).isEqualTo(2);
        arrayDeque1.addLast(7);
        assertThat(arrayDeque1.get(arrayDeque1.getTail())).isEqualTo(7);

        arrayDeque1.addFirst(6);
        assertThat(arrayDeque1.get(arrayDeque1.getHead())).isEqualTo(6);
        assertThat(arrayDeque1.size()).isEqualTo(4);
    }

    @Test
    public void isEmptyTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        assertThat(arrayDeque1.isEmpty()).isTrue();

        arrayDeque1.addFirst(3);
        assertThat(arrayDeque1.isEmpty()).isFalse();

        arrayDeque1.addLast(1);
        arrayDeque1.addLast(8);
        assertThat(arrayDeque1.isEmpty()).isFalse();
    }
}
