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
public class Company extends RepresentationModel<Company> implements Serializable {

    @Id
    @GeneratedValue(generator = "company-uuid-generator")
    @GenericGenerator(
            name = "company-uuid-generator",
            strategy = "br.com.isertech.myinvoice.myinvoiceback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "Company")
    )
    private String id;
    private String userId;
    private String name;
    private String fantasyName;
    private Long number;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Invoice> invoices;

    @Override
    public String toString() {
        List<String> invoicesToString = new ArrayList<>();
        if (this.invoices != null) {
            invoicesToString = invoices.stream().map(Invoice::getId).toList();
        }

        return "Company{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", fantasyName='" + fantasyName + '\'' +
                ", number=" + number +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", invoices=" + invoicesToString +
                '}';
    }
}
