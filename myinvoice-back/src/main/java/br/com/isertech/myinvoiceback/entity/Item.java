package br.com.isertech.myinvoiceback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Item {

    //    @Id
//    @GeneratedValue(generator = "iser-uuid-generator")
//    @GenericGenerator(
//            name = "iser-uuid-generator",
//            strategy = "br.com.isertech.myinvoiceback.util.IserUUIDGenerator",
//            parameters = @Parameter(name = "prefix", value = "Item")
//    )
//    private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long hours;
    private Long hourRate;
    private Long total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id")
    @JsonBackReference
    private Invoice invoice;
}
