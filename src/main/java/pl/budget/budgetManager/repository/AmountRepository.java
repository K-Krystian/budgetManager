package pl.budget.budgetManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.budget.budgetManager.model.Amount;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Long> {
}
