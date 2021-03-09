package pl.budget.budgetManager.service;

import pl.budget.budgetManager.model.Amount;

import java.math.BigDecimal;
import java.util.List;

public interface AmountService {
    List<Amount> getAllAmounts();
    List<Amount> getOnlyIn();
    List<Amount> getOnlyOut();
    void saveAmount (Amount amount);
    Amount getAmountById(long id);
    void deleteAmoutById(long id);

    List<Amount> sortByAmount();
    List<Amount> sortByAmountReversed();

    List<Amount> sortOnlyInByAmount();
    List<Amount> sortOnlyInByAmountReserved();
    List<Amount> sortOnlyOutByAmount();
    List<Amount> sortOnlyOutByAmountReserved();

    BigDecimal returnSum();
    BigDecimal returnSumIn();
    BigDecimal returnSumOut();
}
