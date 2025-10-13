# 🖥️ LaptopShop

## 🧩 Giới thiệu
**LaptopShop** là website thương mại điện tử chuyên bán các sản phẩm **laptop**.  
Hệ thống hỗ trợ hai vai trò chính:

- **Người mua (User)**: Đăng ký, đăng nhập, xem sản phẩm, thêm vào giỏ hàng, đặt hàng, xác nhận nhận hàng, xem lịch sử đơn hàng.  
- **Quản trị viên (Admin)**: Đăng nhập bằng tài khoản mặc định, quản lý sản phẩm, đơn hàng, và xem thống kê doanh thu theo thời gian.

---

## 🚀 Công nghệ sử dụng
| Thành phần | Công nghệ |
|-------------|------------|
| Backend | Spring Boot 3.x, Spring Data JPA, Spring Security |
| Frontend | Thymeleaf, Bootstrap 5 |
| Database | MySQL |
| Build Tool | Maven |
| Ngôn ngữ | Java 17 |
| Template Engine | Thymeleaf |
| IDE đề xuất | IntelliJ IDEA / Eclipse |

---

## ⚙️ Cấu trúc dự án

```
laptopshop/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/laptopshop/
│   │   │   ├── LaptopShopApplication.java       # Main class
│   │   │
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java          # Cấu hình Spring Security
│   │   │   │   └── WebConfig.java               # Cấu hình CORS, static resource,...
│   │   │
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Laptop.java
│   │   │   │   ├── CartItem.java
│   │   │   │   ├── Order.java
│   │   │   │   └── OrderItem.java
│   │   │
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── LaptopRepository.java
│   │   │   │   ├── CartItemRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── OrderItemRepository.java
│   │   │
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── ProductService.java
│   │   │   │   ├── CartService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   └── ReportService.java           # Thống kê doanh thu
│   │   │
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java          # Đăng nhập/đăng ký
│   │   │   │   ├── ProductController.java       # Xem sản phẩm
│   │   │   │   ├── CartController.java          # Giỏ hàng
│   │   │   │   ├── OrderController.java         # Đặt hàng + lịch sử đơn
│   │   │   │   └── AdminController.java         # Quản lý + thống kê
│   │   │
│   │   │   └── util/
│   │   │       └── SecurityUtils.java           # Lấy user hiện tại, check role,...
│   │   │
│   │   ├── resources/
│   │   │   ├── static/                          # CSS, JS, ảnh tĩnh
│   │   │   │   ├── css/
│   │   │   │   ├── js/
│   │   │   │   └── images/
│   │   │   ├── templates/                       # Thymeleaf templates
│   │   │   │   ├── user/
│   │   │   │   │   ├── product-list.html
│   │   │   │   │   ├── product-detail.html
│   │   │   │   │   ├── cart.html
│   │   │   │   │   ├── checkout.html
│   │   │   │   │   └── order-history.html
│   │   │   │   ├── admin/
│   │   │   │   │   ├── dashboard.html
│   │   │   │   │   ├── product-management.html
│   │   │   │   │   ├── order-management.html
│   │   │   │   │   └── report.html
│   │   │   │   └── auth/
│   │   │   │       ├── login.html
│   │   │   │       └── register.html
│   │   │   ├── application.properties
│   │   │   └── data.sql                         # Dữ liệu mẫu ban đầu (nếu có)
│   │
│   └── test/java/com/example/laptopshop/
│       └── LaptopShopApplicationTests.java
│
├── pom.xml
└── README.md
```

---

## 🧠 Phân công nhóm

| STT | Thành viên | Vai trò / Công việc chính |
|-----|-------------|-----------------------------|
| **1** | **G.Anh** | Thiết kế **Entity**, **Repository**, **Database** (User, Product, Order, CartItem, OrderItem) |
| **2** | **Hạnh** | **Controller + Service** cho **Sản phẩm & Giỏ hàng** |
| **3** | **Thành** | **Controller + Service** cho **Đặt hàng & Quản lý đơn hàng** (bao gồm thống kê, doanh thu) |
| **4** | **Mạnh Đức** | **Đăng ký, đăng nhập, phân quyền (Spring Security)** |
| **5** | **Huy** | Thiết kế giao diện người dùng & admin |

---

## 🧾 Business Rules
- Người dùng phải đăng nhập để đặt hàng hoặc xem lịch sử.
- Sản phẩm hết hàng không thể thêm vào giỏ.
- Sau khi đặt hàng, **stock** giảm tương ứng.
- Chỉ đơn hàng **CONFIRMED** mới được tính vào doanh thu.
- Trạng thái đơn hàng:  
  `PENDING → SHIPPED → CONFIRMED`
- **Doanh thu = Σ (số lượng × đơn giá)** của các OrderItem trong đơn hàng **CONFIRMED**.

---

## 📊 Các chức năng chính

### 🧍 Người dùng (USER)
- Đăng ký / Đăng nhập / Đăng xuất  
- Xem danh sách laptop  
- Xem chi tiết sản phẩm  
- Thêm vào giỏ hàng, chỉnh sửa giỏ  
- Đặt hàng  
- Xác nhận nhận hàng  
- Xem lịch sử đơn hàng  

### 🛠️ Quản trị viên (ADMIN)
- Đăng nhập bằng tài khoản mặc định  
- Quản lý sản phẩm (CRUD)  
- Quản lý đơn hàng (cập nhật trạng thái, xem chi tiết)  
- Xem thống kê doanh thu:  
  - Doanh thu theo ngày, tháng, tổng  
  - Số lượng đơn hàng  
  - Top sản phẩm bán chạy  

---

## 🔐 Phân quyền (Spring Security)
| Vai trò | Quyền truy cập |
|----------|----------------|
| `USER` | Truy cập các trang người dùng (sản phẩm, giỏ hàng, đặt hàng, lịch sử đơn) |
| `ADMIN` | Quản lý sản phẩm, đơn hàng, báo cáo doanh thu |
| `PUBLIC` | Đăng nhập / đăng ký / xem danh sách sản phẩm |

---

## 🧰 Cấu hình cơ bản (application.properties)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/laptopshop
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
server.port=8080
```

---

## 🧪 Tài khoản mẫu (demo)
| Role | Username | Password |
|------|-----------|----------|
| ADMIN | admin | admin123 |
| USER | user | user123 |

---

## 📈 Hướng phát triển
- Thêm chức năng tìm kiếm & lọc laptop theo thương hiệu, giá  
- Cho phép thanh toán online (tích hợp VNPay / Momo)  
- Dashboard trực quan hơn với biểu đồ doanh thu  

---

## 🧑‍💻 Cách chạy dự án
```bash
# 1. Clone project
git clone https://github.com/<your-team>/laptopshop.git
cd laptopshop

# 2. Cấu hình database trong application.properties

# 3. Chạy ứng dụng
mvn spring-boot:run

# 4. Truy cập web
http://localhost:8080
```

---

## 💡 Gợi ý tên repository GitHub
```
fpt-laptopshop
```
Hoặc  
```
springboot-laptopshop-ecommerce
```
