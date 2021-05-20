package ee.valiit.loancalculator.repository;

import ee.valiit.loancalculator.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

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

    public Integer getCreditModifier(String code) {
        String sql = "SELECT credit_modifier FROM account where personal_code = :dbPersonalCode ";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("dbPersonalCode", code);
        return jdbcTemplate.queryForObject(sql, paramMap1, Integer.class);


    }


}

