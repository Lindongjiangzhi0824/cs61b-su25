import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

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
    public void bugCheckTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>();
        arrayDeque1.addLast(0);
        arrayDeque1.addFirst(1);
        arrayDeque1.removeLast();
        arrayDeque1.removeLast();
        arrayDeque1.addLast(4);
        assertThat(arrayDeque1.removeFirst()).isEqualTo(4);
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
        assertThat(arrayDeque1.getCapacity()).isEqualTo(32);
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
        assertThat(arrayDeque3.getCapacity()).isEqualTo(16);
    }

    @Test
    public void resizeDownTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>(32);
        for (int i = 0; i < 4; i++) {
            arrayDeque1.addLast(i);
        }
        /*
        * When add first element should trigger resizeDown,
        * because the utilization of capacity is less than 0.25
        * So, after insert operations, what should be obtained is :
        * 0 -> 1 -> .... -> null(15) [samllest capacity is 16]
        * */

        /* Check capacity after insert operations. */
        assertThat(arrayDeque1.getCapacity()).isEqualTo(8);
        /* Check position of element */
        assertThat(arrayDeque1.get(2)).isEqualTo(2);
        assertThat(arrayDeque1.get(3)).isEqualTo(3);
        assertThat(arrayDeque1.get(10)).isEqualTo(null);


        ArrayDeque61B<Integer> arrayDeque2 = new ArrayDeque61B<>(32);
        for (int i = 0; i < 4; i++) {
            arrayDeque2.addFirst(i);
        }
        /* After insert operations get : 0(0 tail) -> null -> ... -> 3 -> 2 -> 1(15 head) */

        assertThat(arrayDeque2.getCapacity()).isEqualTo(8);
        assertThat(arrayDeque2.get(7)).isEqualTo(1);
        assertThat(arrayDeque2.get(6)).isEqualTo(2);
        assertThat(arrayDeque2.get(5)).isEqualTo(3);
        assertThat(arrayDeque2.get(0)).isEqualTo(0);
        assertThat(arrayDeque2.get(2)).isEqualTo(null);

        ArrayDeque61B<Integer> arrayDeque3 = new ArrayDeque61B<>(32);
        for (int i = 0; i < 23; i++) {
            if (i < 12) {
                arrayDeque3.addLast(i);
            } else {
                arrayDeque3.addFirst(i);
            }
        }
        /* After insert operations ,
        should obtain : 0 -> 1 -> .... -> 11 (11 tail) -> .... -> 22(20 head) -> 22 -> .... -> 12(31)*/
        /* First trigger resizeDown , then trigger resizeUp. */
        assertThat(arrayDeque3.getCapacity()).isEqualTo(32);
        assertThat(arrayDeque3.get(arrayDeque3.getHead())).isEqualTo(22);
        assertThat(arrayDeque3.get(arrayDeque3.getTail())).isEqualTo(11);
        assertThat(arrayDeque3.get(11)).isEqualTo(11);
        assertThat(arrayDeque3.get(21)).isEqualTo(22);
        assertThat(arrayDeque3.get(31)).isEqualTo(12);


        ArrayDeque61B<Integer> arrayDeque4 = new ArrayDeque61B<>(32);
        /* null -> .... -> head -> ... -> tail -> null -> ... -> null */
        /*
        * 1. [head,tail] in left half of the interval
        * 2. [head,tail] in right half of the interval
        * 3. [head, mid, tail]
        * */
        for (int i = 0; i < 20; i++) {
            arrayDeque4.addLast(i);
        }
        for (int i = 0; i < 11; i++) {
            arrayDeque4.removeFirst();
        }
        arrayDeque4.removeFirst();
        assertThat(arrayDeque4.size()).isEqualTo(8);
        assertThat(arrayDeque4.getHead()).isEqualTo(12);
        assertThat(arrayDeque4.getTail()).isEqualTo(3);

    }

    @Test
    public void ArrayDequeIteratorTest() {
        ArrayDeque61B<Integer> arrayDeque1 = new ArrayDeque61B<>(8);
        for (int i = 0; i < 5; i++) {
            arrayDeque1.addLast(i % 3);
        }

        Iterator it = arrayDeque1.iterator();
        for (int i = 0; i < 5; i++) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(i % 3);
        }

        /* Catch Error Test.*/
        try {
            it.next();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void LinkedListIteratorTest() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 6; i++) {
            lld1.addLast(i);
        }
        Iterator it = lld1.iterator();
        for (int i = 0; i < 6; i++) {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(i);
        }
        assertThat(it.hasNext()).isFalse();

        /* For-each assess every element*/
        for (int a : lld1) {
            System.out.println(a);
        }
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);

        Deque61B<Integer> lld3 = new ArrayDeque61B<>();
        Deque61B<Integer> lld4 = new ArrayDeque61B<>();
        Deque61B<Integer> lld5 = new ArrayDeque61B<>();

        lld3.addLast(1);
        lld3.addLast(2);
        lld3.addLast(3);

        lld4.addLast(1);
        lld4.addLast(2);
        lld4.addLast(3);

        lld5.addLast(1);
        lld5.addLast(2);

        assertThat(lld3).isEqualTo(lld4);
        assertThat(lld4).isNotEqualTo(lld5);
    }

    @Test
    public void toStringTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");

        Deque61B<String> lld2 = new ArrayDeque61B<>();
        lld2.addLast("A");
        lld2.addLast("B");
        lld2.addLast("C");
        assertThat(lld2.toString()).isEqualTo("[A, B, C]");

    }

}
