package ee.valiit.loancalculator.controller;


import ee.valiit.loancalculator.dto.LoanResponse;
import ee.valiit.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {
    //make yourself database before start
    @Autowired
    private LoanService loanService;
    // http://localhost:9090/loanCalculator.html
    //    http://localhost:9090/account?code=12345&name=johns4&address=paasiku14
    @CrossOrigin
    @PostMapping("account")
    private void account(@RequestParam("personalCode") String code,
                         @RequestParam("name") String name,
                         @RequestParam("address") String address,
                         @RequestParam("creditModifier") int creditModifier) {
        loanService.account(code, name, address, creditModifier);
    }
//http://localhost:9090/getLoan/5000/60/1
    @CrossOrigin
    @GetMapping("loanCalculator/{loanAmount}/{loanPeriod}/{code}")
    public LoanResponse getLoan(@PathVariable("loanAmount") double loanAmount,
                                @PathVariable("loanPeriod") int loanPeriod,
                                @PathVariable("code") String code) {
        return loanService.getAccount(loanAmount, loanPeriod, code);
    }

}
//make yourself database before start