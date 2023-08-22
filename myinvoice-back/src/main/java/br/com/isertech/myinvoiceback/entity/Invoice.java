package br.com.isertech.myinvoiceback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

    //    @Id
//    @GeneratedValue(generator = "iser-uuid-generator")
//    @GenericGenerator(
//            name = "iser-uuid-generator",
//            strategy = "br.com.isertech.myinvoiceback.util.IserUUIDGenerator",
//            parameters = @Parameter(name = "prefix", value = "Invoice")
//    )
//    private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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