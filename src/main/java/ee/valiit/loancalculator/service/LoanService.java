package ee.valiit.loancalculator.service;

import ee.valiit.loancalculator.dto.LoanResponse;
import ee.valiit.loancalculator.loanException.LoanCalculatorException;
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


    public LoanResponse getAccount(double loanAmount, int loanPeriod, String code) {
        //create new loan response object to be sent to front
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setLoanAmount(loanAmount);
        loanResponse.setLoanPeriod(loanPeriod);

//  get the credit_modifier from database
        int creditModifier = loanRepository.getCreditModifier(code);

        double creditScore = getCreditScore(creditModifier, loanAmount, loanPeriod);

        if (loanAmount < 2000 || loanAmount > 10000 || loanPeriod < 12 || loanPeriod > 60) {
            throw new LoanCalculatorException("Insert valid amount and/or loan period");
        } else if (creditScore == 0) {
            throw new LoanCalculatorException("Error, customer has dept, no amount of loan allowed");
        } else if (creditScore >= 1) {
            //in case of modifier equal or bigger than 1 - 1)find max loan amount for requested period
            int maxLoan = findMaxLoanAmount(creditModifier, loanPeriod);
            loanResponse.setApprovedAmount(maxLoan);
            //as customer is valid for loan we can propose the same period that the customer has entered
            loanResponse.setApprovedPeriod(loanPeriod);
            return loanResponse;
        } else {
            // 1) find max sum what would be suitable for the customer
            int maxLoan = findMaxLoanAmount(creditModifier, loanPeriod);
            loanResponse.setApprovedAmount(maxLoan);
            // 2)find loan period that would meet the requested sum
            int suggestedLoanPeriod = findLoanPeriod(creditModifier, loanAmount);
            loanResponse.setApprovedPeriod(suggestedLoanPeriod);
            return loanResponse;
        }

    }

    private double getCreditScore(int code, double loanAmount, int loanPeriod) {
        return ((double) code / loanAmount) * (double) loanPeriod;
    }

    private int findMaxLoanAmount(int creditModifier, int loanPeriod) {
        //lowest credit score can be 1 -> Max amount=(creditModifier*months)/1
        int maxLoan = creditModifier * loanPeriod;
        //check if max loan is in bounds:
        if (maxLoan < 2000) {
            return 0; // in front layer, need to use if statement if(response.proposedAmount==0) - maximum loan for the entered period is below 2000EUR
        } else if (maxLoan > 2000 && maxLoan <= 10000) {
            return maxLoan;
        } else {
            return 10000; //we offer maximum allowed loan amount
        }
    }

    private int findLoanPeriod(int creditModifier, double loanAmount) {
        //suitable period= amount * 1(lowest possible credit  score) / credit modifier
        int loanPeriod = (int) (loanAmount) / creditModifier;
        //check if loan period is valid:
        if (loanPeriod > 60) {
            return 0; //in front layer we have to use if(response.proposedPeriod== 0) - Calculated period exceeds the maximum allowed loan period
        } else if (loanPeriod < 12) {
            return 12; //we offer shortest possible loan period
        } else {
            return loanPeriod;
        }
    }
}