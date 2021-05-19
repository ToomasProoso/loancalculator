package ee.valiit.loancalculator.dto;


import javax.persistence.*;

@Table(name="account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String personalCode;
    private String name;
    private String address;
    private int creditModifier;

    public int getCreditModifier() {
        return creditModifier;
    }

    public void setCreditModifier(int creditModifier) {
        this.creditModifier = creditModifier;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
