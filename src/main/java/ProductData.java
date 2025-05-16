import java.util.List;
import java.util.Arrays;

public class ProductData {
        public static List<Product> getSampleProducts() {
                return Arrays.asList(
                                new Product("4DFWD PULSE SHOES", "Adidas",
                                                "This product is excluded from all promotional discounts and offers.",
                                                160.0, "img1.png"),
                                new Product("FORUM MID SHOES", "Adidas",
                                                "This product is excluded from all promotional discounts and offers.",
                                                100.0, "img2.png"),
                                new Product("SUPERNOVA SHOES", "Adidas", "NMD City Stock 2", 150.0, "img3.png"),
                                new Product("ADIDAS SHOES", "Adidas", "NMD City Stock 2", 160.0, "img4.png"),
                                new Product("SUBA SHOES", "Adidas", "NMD City Stock 2", 120.0, "img5.png"),
                                new Product("4DFWD PULSE 2 SHOES", "Adidas", "NMD City Stock 2", 160.0, "img6.png"),
                                new Product("OZWEEGO", "Adidas", "Retro-inspired shoes with modern comfort.", 135.0,
                                                "img2.png"),
                                new Product("NMD_R1", "Adidas", "Urban style shoes with Boost cushioning.", 175.0,
                                                "img5.png"));
        }
}