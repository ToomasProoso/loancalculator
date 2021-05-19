package ee.valiit.loancalculator.repository;

import ee.valiit.loancalculator.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepositoryHibernate extends JpaRepository<Account, String> {

}
