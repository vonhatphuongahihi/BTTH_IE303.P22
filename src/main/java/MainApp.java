import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.util.List;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane;

public class MainApp extends Application {
    private VBox leftPane;
    private ImageView mainImageView;
    private Label nameLabel, priceLabel, brandLabel, descLabel;
    private Product currentProduct;
    private VBox selectedBox = null;

    // Phương thức start - Khởi tạo và hiển thị giao diện chính
    @Override
    public void start(Stage primaryStage) {
        List<Product> products = ProductData.getSampleProducts();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #fff;");

        // LEFT PANEL: Thiết lập panel bên trái hiển thị sản phẩm chi tiết
        leftPane = new VBox();
        leftPane.setPadding(new Insets(10));
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.setPrefWidth(300);
        leftPane.setStyle(
                "-fx-background-color: #fff; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        leftPane.setFocusTraversable(false);

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setSpacing(8);
        centerBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        centerBox.setPadding(new Insets(0, 0, 0, 0));

        mainImageView = new ImageView();
        mainImageView.setFitWidth(250);
        mainImageView.setFitHeight(1500);
        mainImageView.setPreserveRatio(true);
        mainImageView.setEffect(new DropShadow(10, Color.rgb(180, 180, 180, 0.3)));

        Line line = new Line(0, 0, 300, 0);
        line.setStroke(Color.web("#cccccc"));
        line.setStrokeWidth(1.2);
        line.setTranslateY(0);

        nameLabel = new Label();
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        priceLabel = new Label();
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        brandLabel = new Label();
        brandLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        descLabel = new Label();
        descLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        descLabel.setWrapText(true);
        descLabel.setTextFill(Color.GRAY);

        VBox infoBox = new VBox(8, nameLabel, priceLabel, brandLabel, descLabel);
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setPadding(new Insets(5, 0, 0, 0));

        HBox imgBox = new HBox(mainImageView);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setPadding(new Insets(0, 0, 0, 0));

        centerBox.getChildren().addAll(imgBox, line, infoBox);
        leftPane.getChildren().add(centerBox);
        root.setLeft(leftPane);

        // RIGHT PANEL: Thiết lập panel bên phải hiển thị danh sách sản phẩm
        GridPane grid = new GridPane();
        grid.setHgap(18);
        grid.setVgap(18);
        grid.setPadding(new Insets(70, 24, 24, 24));

        int col = 0, row = 0;
        for (Product p : products) {
            VBox item = createProductItem(p);
            grid.add(item, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(600);
        scrollPane.setStyle(
                "-fx-background: #fff; -fx-background-color: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        scrollPane.setFocusTraversable(false);
        root.setCenter(scrollPane);

        showProduct(products.get(0), false);
        VBox firstBox = (VBox) grid.getChildren().get(0);
        firstBox.setStyle(
                "-fx-background-color: #e0e7ef; -fx-border-color: #3b82f6; -fx-border-radius: 8; -fx-background-radius: 8;");
        selectedBox = firstBox;

        Scene scene = new Scene(root, 1100, 650);
        primaryStage.setTitle("Product Shop JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createProductItem(Product p) {
        VBox box = new VBox(6);
        box.setPadding(new Insets(8));
        box.setAlignment(Pos.TOP_LEFT);
        box.setPrefWidth(180);
        box.setStyle(
                "-fx-background-color: #f3f4f6; -fx-border-color: #e0e0e0; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label name = new Label(p.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        name.setWrapText(false);
        name.setMaxWidth(160);
        name.setEllipsisString("...");
        name.setTooltip(new Tooltip(p.getName()));

        Label desc = new Label(p.getDescription());
        desc.setFont(Font.font("Arial", FontWeight.NORMAL, 11));
        desc.setTextFill(Color.web("#b0b0b0"));
        desc.setWrapText(false);
        desc.setMaxWidth(160);
        desc.setEllipsisString("...");
        desc.setTooltip(new Tooltip(p.getDescription()));

        ImageView img = new ImageView(new Image(p.getImagePath()));
        img.setFitWidth(160);
        img.setFitHeight(120);
        img.setPreserveRatio(true);

        Label brand = new Label(p.getBrand());
        brand.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

        Label price = new Label(String.format("$%.2f", p.getPrice()));
        price.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        price.setTextFill(Color.DIMGRAY);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomBox = new HBox(brand, spacer, price);
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        HBox imgBox = new HBox(img);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setPrefWidth(200);

        box.getChildren().addAll(name, desc, imgBox, bottomBox);

        box.setOnMouseClicked((MouseEvent e) -> {
            showProduct(p, true);
            if (selectedBox != null) {
                selectedBox.setStyle(
                        "-fx-background-color: #f3f4f6; -fx-border-color: #e0e0e0; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
            box.setStyle(
                    "-fx-background-color: #e0e7ef; -fx-border-color: #3b82f6; -fx-border-radius: 8; -fx-background-radius: 8;");
            selectedBox = box;
        });

        box.setOnMouseEntered(e -> {
            if (box != selectedBox) {
                box.setStyle(
                        "-fx-background-color: #e0e7ef; -fx-border-color: #3b82f6; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });

        box.setOnMouseExited(e -> {
            if (box != selectedBox) {
                box.setStyle(
                        "-fx-background-color: #f3f4f6; -fx-border-color: #e0e0e0; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });

        return box;
    }

    // Hiển thị thông tin sản phẩm ở panel bên trái
    private void showProduct(Product p, boolean isAnimated) {
        currentProduct = p;
        mainImageView.setImage(new Image(p.getImagePath()));
        nameLabel.setText(p.getName());
        priceLabel.setText(String.format("$%.2f", p.getPrice()));
        brandLabel.setText(p.getBrand());
        descLabel.setText(p.getDescription());

        // Thêm hiệu ứng fade
        if (isAnimated) {
            FadeTransition ft = new FadeTransition(Duration.millis(350), leftPane);
            ft.setFromValue(0.2);
            ft.setToValue(1.0);
            ft.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}