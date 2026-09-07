public class TriangleDrawer {
    public static void drawTriangle() {
        int SIZE = 5;
        int i = 1, j = 1;
        while (i <= SIZE) {
            while (j <= i) {
                System.out.print("*");
                j++;
            }
            i++;
            j = 1;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle();
    }
}
