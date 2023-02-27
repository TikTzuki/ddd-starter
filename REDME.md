![ddd-patterns.png](docs%2Fddd-patterns.png)

![ddd-layers.png](docs%2Fddd-layers.png)

Application layer: Đây là một lớp mỏng điều phối hoạt động của ứng dụng. Nó không chứa logic nghiệp vụ. Nó không giữ
trạng thái của các business object, nhưng nó có thể giữ trạng thái của tiến trình tác vụ ứng dụng.

Domain layer: Lớp này chứa thông tin về domain. Là trái tim của chương trình. Tình trạng của các business object được
tổ chức tại đây. Còn chức năng lưu trữ của các business object và trạng thái của chúng được xử lý ở lớp infrastructure.

Infrastructure layer: Lớp này hoạt động như một thư viện hỗ trợ cho tất cả các lớp khác lớp. Nó cung cấp thông tin liên
lạc giữa các lớp, thực hiện lưu trữ cho các đối tượng kinh doanh, chứa các thư viện hỗ trợ cho lớp giao diện người
dùng, v.v.

## Value object

Immutable, shareable, thin & simple

## Entities

Identity

## Service

Thuộc về domain, có hành vi tham chiếu đến các đối tượng khác trong miền, bản thân không có trạng thái

Để quản lý life cycle của domain object ta sử dụng 3 pattern:

- Aggregates định nghĩa ownership và boundaries của đối tượng.
- Factories, repositories tạo và lưu trữ các object.

## Aggregates

Chọn một Entity làm gốc của mỗi Aggregate và kiểm soát tất cả quyền truy cập vào các đối tượng bên trong boundary thông
qua gốc. Chỉ cho phép các đối tượng bên ngoài tham chiếu đến root.
Các tham chiếu tạm thời đến các entity nội bộ có thể được sử dụng chỉ trong một thao tác.
Bởi vì root kiểm soát quyền truy cập, nó không thể bị dấu đi bởi những thay đổi đến phần tử bên trong.
Sự xắp xếp làm cho các đối tượng trong Aggregate bất biến.

![Aggregate.png](docs%2FAggregate.png)

## Factories

Các aggregates thường được tạo ra với độ phức tạp cao nếu dùng constructor trong khi cần vừa đảm bảo tính encapsulation.
Có thể sử dụng Factory Method, Abstract Factory.
![FactoryMethod.png](docs%2FFactoryMethod.png)

## Repositories

Khi lớp application liên tục gọi infrastructure để lấy dữ liệu từ database nhanh chóng lấn át, điều
này khiến các nhà phát triển bỏ qua lớp miền, khiến mô hình trở nên không phù hợp. Hiệu quả tổng thể là tiêu
điểm miền bị mất và thiết kế bị xâm phạm.