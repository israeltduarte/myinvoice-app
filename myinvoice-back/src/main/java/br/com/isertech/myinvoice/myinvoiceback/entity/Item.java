package br.com.isertech.myinvoice.myinvoiceback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Item extends RepresentationModel<Item> implements Serializable {

    @Id
    @GeneratedValue(generator = "item-uuid-generator")
    @GenericGenerator(
            name = "item-uuid-generator",
            strategy = "br.com.isertech.myinvoice.myinvoiceback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "Item")
    )
    private String id;
    private String description;
    private Long hours;
    private Long hourRate;
    private Long total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;
}
