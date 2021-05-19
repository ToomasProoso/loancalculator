package ee.valiit.loancalculator.controller;


import ee.valiit.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    //    http://localhost:9090/account?code=12345&name=johns4&address=paasiku14
    @PostMapping("account")
    private void account(@RequestParam("code") String code,
                         @RequestParam("name") String name,
                         @RequestParam("address") String address,
                         @RequestParam("creditModifier") int creditModifier) {
        loanService.account(code, name, address, creditModifier);
    }
    //    http://localhost:9090/loan?loanAmount=5000&loanPeriod=60
    @PostMapping("loan")
    private void loan(@RequestParam("loanAmount") double loanAmount,
                      @RequestParam("loanPeriod") double loanPeriod) {
        loanService.loan(loanAmount, loanPeriod);

    }
}
