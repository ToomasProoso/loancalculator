package ee.valiit.loancalculator.dto;

import javax.persistence.*;

@Table(name = "loan")
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double loanAmount;
    private double loanPeriod;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(double loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
