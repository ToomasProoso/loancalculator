package ee.valiit.loancalculator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoanRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void insertAccount(String code, String name, String address, int creditModifier) {
        String sql = "INSERT INTO account(personal_code, name, address, credit_modifier)" +
                "VALUES(:code, :name, :address, :creditModifier)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", code);
        paramMap.put("name", name);
        paramMap.put("address", address);
        paramMap.put("creditModifier", creditModifier);
        jdbcTemplate.update(sql, paramMap);
    }

    public void insertLoan(Double loanAmount, Double loanPeriod) {
        String sql = "INSERT INTO loan(loan_amount, loan_period)" +
                "VALUES(:loanAmount, :loanPeriod)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanAmount", loanAmount);
        paramMap.put("loanPeriod", loanPeriod);
        jdbcTemplate.update(sql, paramMap);
    }

}

