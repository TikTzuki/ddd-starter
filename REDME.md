![ddd-patterns.png](docs%2Fddd-patterns.png)

![ddd-layers.png](docs%2Fddd-layers.png)

Application layer: Đây là một lớp mỏng điều phối hoạt động của ứng dụng. Nó không chứa logic nghiệp vụ. Nó không giữ

trạng thái của các business object, nhưng nó có thể giữ trạng thái của tiến trình tác vụ ứng dụng.

Domain layer: Lớp này chứa thông tin về domain. Là trái tim của chương trình. Tình trạng của các business object được

tổ chức tại đây. Còn chức năng lưu trữ của các business object và trạng thái của chúng được xử lý ở lớp infrastructure.

Infrastructure layer: Lớp này hoạt động như một thư viện hỗ trợ cho tất cả các lớp khác lớp. Nó cung cấp thông tin liên

lạc giữa các lớp, thực hiện lưu trữ cho các đối tượng kinh doanh, chứa các thư viện hỗ trợ cho lớp giao diện người

dùng, v.v.

## Domain object class diagram

![DDD_Core_Model.png](docs%2FDDD_Core_Model.png)

## Value object

Nguyên tắc:

- Immutable, shareable, thin & simple
- Dù DTO bề ngoài trông giống như value object nhưng chúng chỉ là một giải pháp kỹ thuật được sử dụng chủ yếu để truyền
  dữ
  liệu. Còn các đối tượng giá trị đại diện cho các khái niệm trong business.

Lý do:

- Thread-safe, xử lý concurrency
- Cho phép entities tập trung vào định danh, chuyển trách nhiệm thực hiện hành vi cho value object.

## Entities

Identity

## Aggregates

Nguyên tắc:

- Chọn một Entity làm gốc của mỗi Aggregate và kiểm soát tất cả quyền truy cập vào các đối tượng bên trong boundary

  thông qua gốc.

- Chỉ cho phép các đối tượng bên ngoài tham chiếu đến root.

- Các tham chiếu tạm thời đến các entity nội bộ có thể được sử dụng chỉ trong một thao tác.

- Bởi vì root kiểm soát quyền truy cập, nó không thể bị dấu đi bởi những thay đổi đến phần tử bên trong. Sự xắp xếp làm

  cho các đối tượng trong Aggregate bất biến.

Lý do:

- Aggregate làm tăng performance: mặc dù sử dụng lazy fetch có thể tốt hơn eager fetch, nhưng context of data access

  luôn được duy trì cũng ảnh hưởng đến performance. Vì vậy một aggregate nhỏ khi bắt đầu load relationship sẽ không bao

  giờ phình quá lớn.

- Aggregate root đảm bảo tính toàn vẹn của business rule and data

![Aggregate.png](docs%2FAggregate.png)

![aggregate_rel.png](docs%2Faggregate_rel.png)

Nên trỏ đến các aggregate khác theo ID và ID này là một value object, hướng dẫn:

![Aggregate_Id_Aggreate.png](docs%2FAggregate_Id_Aggreate.png)

1. Chọn kiểu dữ liệu được bọc lại làm ID: UUID, String hoặc Long
2. Tạo một descriptor, descriptor biết biết cách chuyển đổi qua lại giữa kiểu raw và và kiểu bọc ngoài.
3. Đăng ký kiểu dữ liệu mới này với hibernate

## Factories

Các aggregates thường được tạo ra với độ phức tạp cao nếu dùng constructor trong khi cần vừa đảm bảo tính encapsulation.

Có thể sử dụng Factory Method, Abstract Factory.

![FactoryMethod.png](docs%2FFactoryMethod.png)

## Repositories

Khi lớp application liên tục gọi infrastructure để lấy dữ liệu từ database nhanh chóng lấn át, điều

này khiến các nhà phát triển bỏ qua lớp miền, khiến mô hình trở nên không phù hợp. Hiệu quả tổng thể là tiêu

điểm miền bị mất và thiết kế bị xâm phạm.

## Service

Thuộc về domain, có hành vi tham chiếu đến các đối tượng khác trong miền, bản thân không có trạng thái

Để quản lý life cycle của domain object ta sử dụng 3 pattern:

- Aggregates định nghĩa ownership và boundaries của đối tượng.

- Factories, repositories tạo và lưu trữ các object.

## Application layer

Controller, Listener, Consumer

## Domain layer

Service, Workflow, Repository, ValueObjectFactory, FunctionalFactory, DomainObject, Specification, Constraint, Validator

## Infrastructure

Configuration, Bean, Datasource, Client, I/O Mapper, RepositoryImpl

# Các feature tích hợp với DDD

## Distributing Domain Events

![DistributingDomainEvent.png](docs%2FDistributingDomainEvent.png)