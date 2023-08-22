package br.com.isertech.myinvoice.myinvoiceback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    //    @Id
//    @GeneratedValue(generator = "iser-uuid-generator")
//    @GenericGenerator(
//            name = "iser-uuid-generator",
//            strategy = "br.com.isertech.myinvoice.myinvoiceback.util.IserUUIDGenerator",
//            parameters = @Parameter(name = "prefix", value = "Client")
//    )
//    private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    private Long number;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String contact;
    private String exchange;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Invoice> invoices;

    @Override
    public String toString() {
        List<Long> invoicesToString = new ArrayList<>();
        if (this.invoices != null) {
            invoicesToString = invoices.stream().map(Invoice::getId).toList();
        }

        return "Client{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", contact='" + contact + '\'' +
                ", exchange='" + exchange + '\'' +
                ", invoices=" + invoicesToString +
                '}';
    }
}
