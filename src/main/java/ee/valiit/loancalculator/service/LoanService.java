package ee.valiit.loancalculator.service;

import ee.valiit.loancalculator.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;


    public void account(String code, String name, String address, int creditModifier) {
        loanRepository.insertAccount(code, name, address, creditModifier);
    }

    public void loan(double loanAmount, double loanPeriod) {
        loanRepository.insertLoan(loanAmount,loanPeriod);
    }

}