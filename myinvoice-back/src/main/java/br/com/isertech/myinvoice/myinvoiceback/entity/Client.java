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
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client extends RepresentationModel<Client> implements Serializable {

    @Id
    @GeneratedValue(generator = "client-uuid-generator")
    @GenericGenerator(
            name = "client-uuid-generator",
            strategy = "br.com.isertech.myinvoice.myinvoiceback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "Client")
    )
    private String id;
    @ManyToOne
    @JsonBackReference
    private MIUser user;
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
        List<String> invoicesToString = new ArrayList<>();
        if (this.invoices != null) {
            invoicesToString = invoices.stream().map(Invoice::getId).toList();
        }

        return "Client{" +
                "id=" + id +
                ", userId='" + user.getId() + '\'' +
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
