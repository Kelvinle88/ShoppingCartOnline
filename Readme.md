# CS490 - Project Management - Shopping Cart Online System

**Professor** - Somesh Rao Pullapantula

**Team Members**
1.  Viet Tri, Le
2.  Gad
2.  Dennis

## Scope
Our team will create an Online Shopping Cart System.
### Domain Driven Design (Essential Entities)
- Product
- User
- Order
- Cart
- Payment
- Report
###  Functional  Requirements

#### Product Service
- Add Product
- Remove Product
- Update Product along with status (AVAILABLE,UNAVAILABLE)
- Update quantity status upon order transactional (consume Kafka message from order service).
- Search Product
- Ship in product quantity based on productId by Vendor
- Ship out product quantity based on productId by Customer ordered
- Ship out product based on range time reports.
- Ship in product based on range time reports.

#### Order - Cart Service
- Get items from Cart(send cookies by RequestHeader)
- Add items to Cart (send cookies by RequestHeader, params Quantity and productId)
- Delete items from Cart(send cookies by RequestHeader, params productId)

- Search Order (A filter search for orders based on customerId)
- Create Order (Send Kafka message request payment to payment service).
- Cancel Order (Send Kafka message update quantity to product service,Send Kafka message update to payment service, Send Kafka message update status to SMTP service).
- Confirmed Order (Send Kafka message update quantity to product service,Send Kafka message update order status to SMTP service).
- Process Payment using Stripe (recorded Stripe Dashboard Successful or Cancel)
- Fetch/View order history

#### Payment Service
- Create payment and record on Stripe Dashboard(consume Kafka message from order service, send Kafka message update Payment status to order service )
- Cancel payment and record on Stripe Dashboard(consume Kafka message from order service, send Kafka message update Payment status to order service )

#### SMTP Service
- Send ordered confirmation email to customer(consume Kafka message from order service)
- Send order canceled to customer (consume Kafka message from order service)

#### User Service
- Add manager (Admin).

#### Report Service
- Add 


### Tools & Technologies used:
---
* Java
* HTML and CSS, JavaScript, TypeScript,  Angular
* MySQL - MySQL Workbench
* Redis
* RESTFul API
* Maven
* Spring Initializer
* Spring Security / JWT
* Spring Boot / Spring Data / Spring Cloud
* Microservices Stack
* Kafka
* Iterative Software Development Life Cycle
* Agile - Scrum
* StarUML
* Stripe API
* JavaMail
* GitHub / Docker / Postman
* IntelliJ / Visual Studio Code
* MaterialUI / Angular Bootstrap
