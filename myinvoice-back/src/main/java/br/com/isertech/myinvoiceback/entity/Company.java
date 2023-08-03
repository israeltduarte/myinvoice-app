package br.com.isertech.myinvoiceback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    //    @Id
//    @GeneratedValue(generator = "iser-uuid-generator")
//    @GenericGenerator(
//            name = "iser-uuid-generator",
//            strategy = "br.com.isertech.myinvoiceback.util.IserUUIDGenerator",
//            parameters = @Parameter(name = "prefix", value = "Company")
//    )
//    private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Invoice> invoices;

    @Override
    public String toString() {
        List<Long> invoicesToString = new ArrayList<>();
        if (this.invoices != null) {
            invoicesToString = invoices.stream().map(Invoice::getId).toList();
        }

        return "Company{" +
                "id=" + id +
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
