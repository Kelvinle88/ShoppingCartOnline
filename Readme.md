Shopping Cart Online
Backend developer: Viet Tri, Le

Tools and Technologies
Java 17
Spring Boot - version 2.7.5
Spring Cloud - Open Feign - version 2021.0.5
Netflix Eureka Client/Server - version 2.1.1
Jwt - version 0.9.1
Redis Client : JEDIS - version 3.8.0
Spring Data REDIS - version 2.7.0
Spring Data JPA - version 3.1.4
ModelMapper - version 3.1.0
MySQL Database engine : 
Cached Database engine : Redis 3.8.0
Maven

Services :
Products service 
Orders service
Carts service
Discovery service: localhost:8761
API Gateway: localhost:8889

Token generating for testing:
Order-service -> security -> JwtHelper 

Products Service :
- Get all products: {{api-gateway}}{{product-service}}/products
- Get product by VendorId: {{api-gateway}}{{product-service}}/products/vendor/001
- Get product by productId: {{api-gateway}}{{product-service}}/products/1
- Create product: {{api-gateway}}{{product-service}}/products
- Ship in product by Vendor: {{api-gateway}}{{product-service}}/products/1/shipin?quantity=20
- Update product: {{api-gateway}}{{product-service}}/products/1
- Delete product: {{api-gateway}}{{product-service}}/products/1

Cart Service :
- Add items to cart: {{api-gateway}}{{order-service}}/cart?productId=1&quantity=10
- Get cart: {{api-gateway}}{{order-service}}/cart
- Delete cart: {{api-gateway}}{{order-service}}/cart?productId=1

Order Service:
- Create Order: {{api-gateway}}{{order-service}}/orders
