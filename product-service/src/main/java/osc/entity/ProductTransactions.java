//package osc.productservice.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class ProductTransactions {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String productTransId;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;
//    private int quantityIn;
//    private int quantityOut;
//    private String narration;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date transactionDate;
//}
