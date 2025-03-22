import java.util.Random;

public class Bai2 {
    public static double estimatePi(int iterations) {
        Random random = new Random();
        int insideCircle = 0;
        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }

        return 4.0 * insideCircle / iterations;
    }

    public static void main(String[] args) {
        int iterations = 1000000;
        double estimatedPi = estimatePi(iterations);
        System.out.println("Giá trị ước lượng của số Pi là: " + estimatedPi);
    }

}