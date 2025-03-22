import java.util.Random;
import java.util.Scanner;

public class Bai1 {
    public static double approximateArea(double r, int iterations) {
        Random random = new Random();
        int insideCircle = 0;

        for (int i = 0; i < iterations; i++) {
            double x = (random.nextDouble() * 2 - 1) * r;
            double y = (random.nextDouble() * 2 - 1) * r;

            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }

        return 4.0 * r * r * insideCircle / iterations;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập bán kính hình tròn: ");
        double r = scanner.nextDouble();
        int iterations = 1000000;

        double estimatedArea = approximateArea(r, iterations);
        System.out.println("Diện tích ước lượng của hình tròn là: " + estimatedArea);
    }
}