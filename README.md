# BTTH_IE303.P22 

Đây là bài tập thực hành số 3 môn IE303 - Công nghệ Java, sử dụng JavaFX để thiết kế giao diện website bán sản phẩm.

## Yêu cầu hệ thống

- Java JDK 17 trở lên
- Maven 3.6.0 trở lên
- IDE (khuyến nghị sử dụng IntelliJ IDEA hoặc VS Code)

## Cấu trúc dự án

```
BTTH_IE303.P22/
├── pom.xml                # File cấu hình Maven
└── src/
    └── main/
        ├── java/          # Mã nguồn Java
        │   ├── MainApp.java
        │   ├── Product.java
        │   └── ProductData.java
        └── resources/     # Tài nguyên 
            ├── img1.png
            ├── img2.png
            └── ...
```

## Cách chạy dự án

### 1. Clone dự án
```bash
# Clone từ nhánh BTTH3
git clone -b BTTH3 https://github.com/vonhatphuongahihi/BTTH_IE303.P22.git
cd BTTH_IE303.P22
```

### 2. Cài đặt dependencies
```bash
mvn clean install
```

### 3. Chạy ứng dụng
```bash
mvn javafx:run
```

## Tính năng chính

1. Hiển thị sản phẩm lớn bên trái với đầy đủ thông tin
2. Hiển thị danh sách sản phẩm nhỏ bên phải dạng grid 4 cột
3. Có hiệu ứng hover và click cho các sản phẩm
4. Có hiệu ứng fade khi chuyển đổi sản phẩm
