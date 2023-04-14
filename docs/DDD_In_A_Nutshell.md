# Model-Driven Design In A Nutshell

# Table of Contents

___

1. [Model-Driven Design](#model-driven-design)
    1. [Layered Architecture](#layered-architecture)
    2. [Entities](#entities)
    3. [Value Objects](#value-objects)
    4. [Services](#services)
    5. [Modules](#modules)
    6. [Aggregates](#aggregates)
    7. [Factories](#factories)
    8. [Repositories](#repositories)
    9. [Exception handling](#exception-handling)
2. [Event-Driven Design](#2-event-drivent-design)

## 1. Model-Driven Design

![model-drven-design-patterns.png](https://bnz07pap001files.storage.live.com/y4mQMB1ud0alGqML9o1tg8b1LUF5VTRrQ3wJZ6aUZr6p7s44yYdIdsxlkXWoc8GfJq53X64hg1eyR4XZpAy0zMu-BVE5wRhvsS6msH087k0nCXwlijkDA7hXAC7-PrGZSI87LbVO3tQOQxoqxBmGw-MYwqw5sEne2twMhyWr11GVCUCSe2klh3-cQBOBb-UmMp0?width=1075&height=712&cropmode=none)

### i. Layered Architecture

![ddd-layers.png](https://bnz07pap001files.storage.live.com/y4mt97Jygv9Fg9U3ovs3ShCxoah6FtsSHCEJftQjFQ3nhS9Po7UZjElyGP-rIiHgWaJII55kb7UCKpr1E-q_OHfkUgyGIcdvrmdHfIOjgLPcUtzxHSFyPtmaCMcIl29l1BEieW1NMofe0p7iLyK5HPmYtK4xYAriZfWs8tZLgykPKFNNGetMGCg6kEffxMD7mue?width=884&height=511&cropmode=none)

### ii. Entities

Entity là mutable \
Mỗi entity có một ID duy nhất được chỉ định khi được tạo ra và không thay đổi trong suốt vòng đời của nó. \
Mô hình hóa tất cả các method thay đổi state của entity dưới dạng các động từ thể hiện bussiness logic.

```java
/// Hợp đồng lao động có thể kết thúc vì ban đầu chúng chỉ là tạm thời,
// do chuyển nội bộ từ chi nhánh công ty này sang chi nhánh công ty khác,
// do nhân viên nghỉ việc hoặc do người sử dụng lao động sa thải nhân viên
class Employee {
    String endDate;
   
   ❌ setEndDate(finalDay);
   
   ✅ terminateContract(reason, finalDay);
}
```

Một Entity tiêu chuẩn:

```java
public class Person {

    private final PersonId personId;
    private final EventLog changeLog;

    private PersonName name;
    private LocalDate birthDate;
    private StreetAddress address;
    private EmailAddress email;
    private PhoneNumber phoneNumber;

    public Person(PersonId personId, PersonName name) {
        this.personId = Objects.requireNonNull(personId);
        this.changeLog = new EventLog();
        changeName(name, "initial name");
    }

    public void changeName(PersonName name, String reason) {
        Objects.requireNonNull(name);
        this.name = name;
        this.changeLog.register(new NameChangeEvent(name), reason);
    }

    public Stream<PersonName> getNameHistory() {
        return this.changeLog.eventsOfType(NameChangeEvent.class).map(NameChangeEvent::getNewName);
    }

    // Other getters omitted

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        return personId.equals(((Person) o).personId);
    }

    public int hashCode() {
        return personId.hashCode();
    }
}
```

- PersonId là entity ID. Có thể sử dụng UUID, String hoặc Long nhưng sử dụng value object sẽ nói lên bản thân nó là một
  ID định danh cho một Person nhất định.
- Sử dụng nhiều value objects nhất có thể: *PersonName*, *LocalDate* (standard Java
  API), *StreetAddress*, *EmailAddress* and *PhoneNumber*.
- Thể hiện bussiness logic bằng cách sử dụng method lưu lại thay đổi property *name* trong *event log* cùng với lý
  do *name* bị đổi.
- Getter lấy ra lịch sử thay đổi tên.
- equals and hashCode chỉ check entity ID.

### iii. Value Objects

Một value object là một đối tượng chứa value. Nếu hai value object có cùng giá trị các properties được coi là một.
. Đối với các value object phức tạp, hãy sử dụng `Builder Pattern`.

- Immutable, shareable, thin & simple.
- Thread safe, xử lý concurrency
- Cho phép entity tập trung trung vào định danh, chuyển trách nhiệm thực hiện business logic cho value object.

- Tên của value object đại diện cho các business logic.
- Triển khai Value Objects đơn giản bằng `Hibernate's UserType, @TypeDef` hoặc `Attribute Converter`
- Triển khai Value Objects phức tạp bằng `@Embeddables`

Không nên
- Value object không phải DTO , DTO là kỹ thuật truyền dữ liệu,
- Không thay đổi trạng thái của value object mà nên tạo ra 1 instance mới.

![Entity_And_Value_Object.png](https://bnz07pap001files.storage.live.com/y4mMHgGULiFvy2nCBNUy32wunOvxnTW-Lf85k6gJxrHhdKAhIpjToeI-zKFVhILWvfNb0Oar1SMkCYPT-Cj3_8NH9hQA-chg2Co7umsuE__dW7CbY6KZB180O9n89qfUtRYHzuvXKmF-Jw8RntxuY9zQitFvJK38QtknFDHuiIxk8rx_KKFHhqkSTrI9VaDc4Lv?width=1056&height=583&cropmode=none)

### iv. Aggregates

❓ Aggreate là một nhóm các entities và value objects có liên quan chặt chẽ với nhau

![Aggregate.png](https://bnz07pap001files.storage.live.com/y4mJ3FmzDqSDcAOZYfaQl0P30011McoVLPpp26vXJp8ch6hRKJCjuGZOXs-wahv1iEZ8YAAoBen_59YCtIpnGfLuAqMQlXRrSbDnVgcMEWzWsOyCFEZLO7LaIlJzBk1o5Jw6GphC1pFP1hI6DJDRhYQUR0TOh_ks-9kjrpUVxitZbXv5QBBfn1k1NBNTZpoVWu5?width=1118&height=652&cropmode=none)

- Aggreate luôn luôn có trạng thái nhất quán
- Đảm bảo tính toàn vẹn của business rule và dữ liệu.
- Tăng performance: mặc dù sử dụng lazy fetch có thể tốt hơn eager fetch, nhưng context of data access luôn được duy trì
  cũng ảnh hưởng đến performance. Vì vậy một aggregate NHỎ khi tiếp tục load relationship sẽ không bị phình to.

✅

- Giữ cho aggregate nhỏ
- Chọn một entity làm gốc của aggregate và kiểm soát tất cả quyền truy cập vào các đối tượng bên trong boundary
  thông qua root.
- Chỉ cho phép các đối tượng bên ngoài tham chiếu đến root.
- Các tham chiếu tạm thời đến các entity nội bộ có thể được sử dụng chỉ trong một thao tác.
- Aggreate root chịu trách nhiệm thực thi các thay đổi trạng thái lên các entity, object value bên trong.

![aggregate_rel.png](https://bnz07pap001files.storage.live.com/y4mh6NaGKRvmtcwpVl5T00ECySRSd78feRmb371bQttZNGwor4rM1NAk1YRnyLy6RixgASoGBQctanug1LR-bfHhBOpPWC9Exeoxc-i6oi0xyJx1HSFBjMIqLzaRVg6Qc_trztCAdR6LnIxrRI_yx9rq9Fy0hBX13jXAlbfF8F_CEKykb6DWpbPL7YnsW2EYIyh?width=574&height=483&cropmode=none)

![Aggregate_Id_Aggreate.png](https://bnz07pap001files.storage.live.com/y4mKaJMcbeO9md8jTLLKwVhULnKsNldzPFpocUgDfwNRHeuQ-sNAKEogg2G7NYlNnSdXuqazs0P1x9TwRq2LnVzGJ3X6noK14uCisq1Jl80x5AkoiNN-_oXdTTMGDiy-r9Z-ZcwQybU9oIRWnP8Wx3iY_LMwTzMPjDP5w1lkuCMTQJ8MCyaJvCNTA47TRzaMi9D?width=568&height=176&cropmode=none)

```java

@Entity
public class Invoice extends BaseAggregateRoot<InvoiceId> { // <1>

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceItem> items; // <2>

    // The rest of the methods and fields are omitted
}
```

### v. Services

Thuộc về domain, có hành vi tham chiếu đến các đối tượng khác trong miền, bản thân không có trạng thái.

Thể hiện nghiệp vụ chứa tương tác giữa các aggregate với nhau.

### vi. Modules

```
foo.bar.domain.model.authentication
    AuthenticationService
foo.bar.domain.model.user
    User
    UserRepository
    UserId
    UserName
    PasswordEncoder
foo.bar.domain.model.role
    Role
    RoleRepository
    RoleId
```


### vii. Factories

![FactoryMethod.png](https://bnz07pap001files.storage.live.com/y4mKrAwwzhF9O_cm6Ho93zg9RYqgNIULyY-ih3nGpBBDmIeAZn23MutQY0RgXjzppE-S7YHDghSAys8Q3s78Xp2EC7nKR2eq7Qv3pzEE9rO_fW8chXErX13mbTgWKe_uhbhUqYdzjvzKJE8LSL2sZSf5qGqO9XV8JZQizD5llkRaW0nd-Mp5BUn1NE6hMEaI-td?width=742&height=421&cropmode=none)

Khi các entity và aggregate quá phức tạo để tạo một instance thông qua constructor thì cần sử dụng factory pattern.

Facory đóng gói logic nghiệp vụ nằm trong lớp domain.

### viii. Repositories

Sử dụng repository nhằm gói gọn tất cả logic cần thiết để lấy các reference đến các domain object.

Các domain object sẽ không phải xử lý logic infrastructure mà sẽ chỉ lấy chúng từ repository và model sẽ đạt được sự rõ
ràng và tập trung vào nghiệp vụ.

Để viết các sql phức tạp, sử dụng `Specifications` hoặc `QueryDSL`

Sử dụng kết hợp Factory và Repository

![FactoryAndRepository.png](https://bnz07pap001files.storage.live.com/y4mr3Sa7FiVwxqCUDarzn-qiCx17I1iU1xHi76jxwyC7_DYb7ClCYG48-tU5qUdoiyKn0z_YOewbhZyKnHpbg8MC-uBDuXlQ-Gv3Jzal60nujWEvwm2M1PwS3PdiJHjZuHokRUFa2zkl0mbgGekWhQ3oWqR-AGSTbPclCzcPU10T7xP8LZ9y8oIIbDzsZwjVnwk?width=1214&height=879&cropmode=none)

### ix. Exception handling
- Không tập trung khai báo exception ở 1 nơi, không kế thừa exception.
- Sử dụng asserts/check để ràng buộc dữ liệu đầu vào.
- Handle exception ở lớp application/adapter.
- Sử dụng RuntimeException cho các ngoại lệ không đoán trước thay vì custom exceptions.
- Trong domain không lưu error code/message trong exception.
- Thêm custom exception vừa đủ để sử dụng và chỉ khi mang lại lợi ích nhiều hơn Java's standard exception.
___

## 2. Event-Drivent Design

### 2.1 Event appling

### 2.2 Event handling

@EventHandler

@EventListener

@TransactionalEventListener

## References

Eric Evans. Domain-Driven Design (08-2003). \
Petter Holmstöm. Domain-Driven Design Series' Articles (04-2021).