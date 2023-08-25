package br.com.isertech.myinvoice.myinvoiceback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice extends RepresentationModel<Invoice> implements Serializable {

    @Id
    @GeneratedValue(generator = "iser-uuid-generator")
    @GenericGenerator(
            name = "iser-uuid-generator",
            strategy = "br.com.isertech.myinvoice.myinvoiceback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "Invoice")
    )
    private String id;
    private Long number;
    private LocalDate date;
    private Long total;
    private String currency;
    private boolean isPublished;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    @Override
    public String toString() {

        return "Invoice{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", total=" + total +
                ", currency='" + currency + '\'' +
                ", isPublished=" + isPublished +
                ", company=" + company.getId() +
                ", client=" + client.getId() +
                ", items=" + items.stream().map(Item::getId).toList() +
                '}';
    }

}