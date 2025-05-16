public class Product {
    private String name;
    private String brand;
    private String description;
    private double price;
    private String imagePath;

    public Product(String name, String brand, String description, double price, String imagePath) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}