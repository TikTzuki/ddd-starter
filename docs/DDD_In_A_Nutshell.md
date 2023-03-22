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

2. [Refactoring Toward Deeper Insight](#fourth-examplehttpwwwfourthexamplecom)
3. [Preserving Model Integrity](#fourth-examplehttpwwwfourthexamplecom)

## 1. Model-Driven Design

![model-drven-design-patterns.png](model-drven-design-patterns.png)

### i. Layered Architecture

![ddd-layers.png](ddd-layers.png)

### ii. Entities

### iii. Value Objects

![Entity_And_Value_Object.png](Entity_And_Value_Object.png)

### iv. Services

### v. Modules

### vi. Aggregates

![Aggregate.png](Aggregate.png)

### vii. Factories

![FactoryMethod.png](FactoryMethod.png)

KHÔNG CẦN đến Factory khi chỉ cần một constructor đơn giản là đủ và NGƯỢC LẠI. Sử dụng constructor khi:

- Constructor đơn giản.
- Việc tạo một object không liên quan đến việc tạo các object khác và tất cả các attributes cần thiết đều được truyền
  qua constructor.

### viii. Repositories

Sử dụng kết hợp Factory và Repository

![FactoryAndRepository.png](FactoryAndRepository.png)