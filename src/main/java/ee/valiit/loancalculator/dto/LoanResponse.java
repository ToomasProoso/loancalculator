package ee.valiit.loancalculator.dto;

public class LoanResponse {
    private double loanAmount;
    private int loanPeriod;
    private  double approvedAmount;
    private int approvedPeriod;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public int getApprovedPeriod() {
        return approvedPeriod;
    }

    public void setApprovedPeriod(int approvedPeriod) {
        this.approvedPeriod = approvedPeriod;
    }
}
