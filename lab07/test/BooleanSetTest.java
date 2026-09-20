import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class BooleanSetTest {

    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        assertWithMessage("Size is not zero upon instantiation").that(aSet.size()).isEqualTo(0);
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertWithMessage("aSet should contain " + i).that(aSet.contains(i));
        }

        assertWithMessage("Size is not 50 after 50 calls to add").that(aSet.size()).isEqualTo(50);
        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertWithMessage("aSet should not contain " + i).that(!aSet.contains(i));;
        }

        assertWithMessage("aSet is not empty after removing all elements").that(aSet.isEmpty());
        assertWithMessage("Size is not zero after removing all elements").that(aSet.size()).isEqualTo(0);
    }

    /** add 重复：Set 不允许重复 */
    @Test
    public void testAddDuplicate() {
        BooleanSet s = new BooleanSet(10);
        s.add(3);
        s.add(3);
        s.add(3);
        assertWithMessage("Adding same element 3 times should keep size 1.").that(s.size()).isEqualTo(1);
        assertWithMessage("Set should still contains 3.").that(s.contains(3)).isTrue();
    }

    /** remove 不存在的元素：size 不应变化 */
    @Test
    public void testRemoveNonexistent() {
        BooleanSet s = new BooleanSet(10);
        s.add(5);
        s.remove(7);   // 7 不在集合里
        assertWithMessage("Removing non-existent element should not change size")
                .that(s.size()).isEqualTo(1);
        assertWithMessage("Set should still contain 5")
                .that(s.contains(5)).isTrue();
    }

    /** 边界：0 和 maxElement 都能用（inclusive） */
    @Test
    public void testBoundaries() {
        BooleanSet s = new BooleanSet(10);

        s.add(0);
        s.add(10);   // 上边界，maxElement 本身
        assertWithMessage("Should contain lower bound 0").that(s.contains(0)).isTrue();
        assertWithMessage("Should contain upper bound 10").that(s.contains(10)).isTrue();
        assertWithMessage("Size should be 2").that(s.size()).isEqualTo(2);

        s.remove(0);
        s.remove(10);
        assertWithMessage("Should be empty after removing boundaries").that(s.isEmpty()).isTrue();
    }

    /** contains 对不在集合里的元素返回 false */
    @Test
    public void testContainsFalse() {
        BooleanSet s = new BooleanSet(10);
        s.add(1);
        assertWithMessage("Should not contain 2").that(s.contains(2)).isFalse();
        assertWithMessage("Should not contain 9").that(s.contains(9)).isFalse();
    }

    /** toIntArray：长度、内容、顺序 */
    @Test
    public void testToIntArray() {
        BooleanSet s = new BooleanSet(10);
        s.add(3);
        s.add(7);
        s.add(1);

        int[] arr = s.toIntArray();

        assertWithMessage("toIntArray length should equal size").that(arr.length).isEqualTo(3);
        // 顺序按数字从小到大（因为按下标遍历）
        assertWithMessage("toIntArray contents/order wrong").that(arr).asList().containsExactly(1, 3, 7).inOrder();
    }

}
