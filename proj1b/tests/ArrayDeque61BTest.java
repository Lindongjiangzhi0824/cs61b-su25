import deque.ArrayDeque61B;
import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Array;
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

    @Test
    public void removeFirstTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        arrayDeque1.addFirst(3);  // 3
        arrayDeque1.addFirst(4);  // 4 -> 3
        arrayDeque1.addLast(6);  // 4 -> 3 -> 6
        arrayDeque1.addLast(8);  // 4 -> 3 -> 6 -> 8
        /* Check return value whether is equal to 4 */
        assertThat(arrayDeque1.removeFirst()).isEqualTo(4); // 3 -> 6 -> 8
        assertThat(arrayDeque1.toList()).containsExactly(3, 6, 8).inOrder();
        assertThat(arrayDeque1.size()).isEqualTo(3);
        arrayDeque1.addFirst(10); // 10 -> 3 -> 6 -> 8
        arrayDeque1.addFirst(9); // 9 -> 10 -> 3 -> 6 -> 8
        assertThat(arrayDeque1.toList()).containsExactly(9, 10, 3, 6, 8).inOrder();
    }

    @Test
    public void removeLastTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        arrayDeque1.addFirst(3);  // 3
        arrayDeque1.addFirst(4);  // 4 -> 3
        arrayDeque1.addLast(6);  // 4 -> 3 -> 6
        arrayDeque1.addLast(8);  // 4 -> 3 -> 6 -> 8
        /* Check return value whether is equal to 4 */
        assertThat(arrayDeque1.removeLast()).isEqualTo(8); // 4 -> 3 -> 6
        assertThat(arrayDeque1.toList()).containsExactly(4, 3, 6).inOrder();
        assertThat(arrayDeque1.size()).isEqualTo(3);
        arrayDeque1.addFirst(10); // 10 -> 4 -> 3 -> 6
        arrayDeque1.addFirst(9); // 9 -> 10 -> 4 -> 3 -> 6
        assertThat(arrayDeque1.toList()).containsExactly(9, 10, 4, 3, 6).inOrder();
    }

    @Test
    public void resizeUpTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>(16);

        for (int i = 0; i < 12; i++) {
            arrayDeque1.addLast(i);
        }
        /* Check the capacity whether trigger resizeUp. */
        assertThat(arrayDeque1.capacity).isEqualTo(32);
        /* Check whether the order of elements has changed normally. */
        /* 1(0) -> 2 -> 3 -> ..... -> null(15) */
        /* 1(0) -> 2 -> 3 -> ..... -> null(31) */
        /* Another case */
        /* Before: 3 -> 1 -> ... -> 0(14) -> 4(15)*/
        /* After: 3 -> 1 -> ... -> 0(30) -> 4(31)*/

        /* Verify whether the order of elements after scaling up matches expectations. */
        ArrayDeque61B<Integer> arrayDeque2 = new ArrayDeque61B<>(16);
        for (int i = 0; i < 10; i++) {
            arrayDeque2.addLast(i);
        }
        // Current arrayDeque2 : 1(0) -> 2(1) -> .... -> 9(8) -> ... -> null(15);
        for (int i = 0; i < 4; i++) {
            arrayDeque2.addFirst(i);
        }
        // Current arrayDeque2(Not Expansion) : 1(0) -> 2(1) -> .... -> 9(8 tail) -> ... -> 3(12 head) -> 2(13) -> 1(14) -> 0(15);
        // Current arrayDeque2(After Expansion) : 1(0) -> 2(1) -> .... -> 9(8 tail) -> ... -> 3(28 head) -> 2(29) -> 1(30) -> 0(31);
        assertThat(arrayDeque2.get(28)).isEqualTo(3);
        assertThat(arrayDeque2.get(31)).isEqualTo(0);
        assertThat(arrayDeque2.get(1)).isEqualTo(1);
        assertThat(arrayDeque2.get(4)).isEqualTo(4);




        ArrayDeque61B<Integer> arrayDeque3 = new ArrayDeque61B<>(16);
        for (int i = 0; i < 10; i++) {
            arrayDeque3.addLast(i);
        }
        /* Capacity not change */
        assertThat(arrayDeque3.capacity).isEqualTo(16);
    }
}
