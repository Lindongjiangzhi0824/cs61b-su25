import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    @Test
    public void testMissingNumber() {
        int arr1[] = {1,2,4,5,0};
	    assertWithMessage("Array [1,2,4,5,0] should get 3.").that(CodingChallenges.missingNumber(arr1)).isEqualTo(3);

        int arr2[] = {1,2,3,4};
        assertWithMessage("The array [1,2,3,4] should be missing 0.").that(CodingChallenges.missingNumber(arr2)).isEqualTo(0);

        int arr3[] = {};
        assertWithMessage("Empty array should be missing 0.").that(CodingChallenges.missingNumber(arr3)).isEqualTo(-1);

    }

    @Test
    public void testIsPermutation() {
	    String s1 = "abc", s2 = "cba";
        assertWithMessage("They are all composed of one element from each of a, b, and c.").that(CodingChallenges.isPermutation(s1, s2)).isTrue();

        String s3 = "silent", s4 = "listen";
        assertWithMessage("Both are consist of s i l e n t only once.").that(CodingChallenges.isPermutation(s3, s4)).isTrue();

        String s5 = "hello", s6 = "apple";
        assertWithMessage("Not match.").that(CodingChallenges.isPermutation(s5, s6)).isFalse();
    }
}
