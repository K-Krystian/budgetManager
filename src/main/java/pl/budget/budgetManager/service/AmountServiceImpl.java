package pl.budget.budgetManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.budget.budgetManager.model.Amount;
import pl.budget.budgetManager.repository.AmountRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmountServiceImpl implements AmountService{

    @Autowired
    private AmountRepository amountRepository;

    @Override
    public List<Amount> getAllAmounts() {
        return amountRepository.findAll();
    }

    @Override
    public List<Amount> getOnlyIn() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountIn = c.stream()
                .filter(amount -> amount.getInOut().equals("Przychód")).collect(Collectors.toList());
        return amountIn;
    }

    @Override
    public List<Amount> getOnlyOut() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountOut = c.stream()
                .filter(amount -> amount.getInOut().equals("Wydatek")).collect(Collectors.toList());
        return amountOut;
    }

    @Override
    public void saveAmount(Amount amount) {
        if (amount.getInOut().equals("Wydatek")) {
            BigDecimal f = amount.getValue().negate();
            amount.setValue(f);
            amountRepository.save(amount);
        } else {
            this.amountRepository.save(amount);
        }
    }

    @Override
    public Amount getAmountById(long id) {
        Optional<Amount> optional = amountRepository.findById(id);
        Amount amount = null;
        if (optional.isPresent()) {
            amount = optional.get();
        } else {
            throw new RuntimeException("Nie znaleziono kwoty o id: " + id);
        }
        return amount;
    }

    @Override
    public void deleteAmoutById(long id) {
        this.amountRepository.deleteById(id);

    }

    @Override
    public List<Amount> sortByAmount() {
        List<Amount> a = amountRepository.findAll();
        a.sort(Comparator.comparing(Amount::getValue));
        return a;
    }

    @Override
    public List<Amount> sortByAmountReversed() {
        List<Amount> a = amountRepository.findAll();
        a.sort(Comparator.comparing(Amount::getValue).reversed());
        return a;
    }

    @Override
    public List<Amount> sortOnlyInByAmount() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountIn = c.stream()
                .filter(amount -> amount.getInOut().equals("Przychód")).collect(Collectors.toList());
        amountIn.sort(Comparator.comparing(Amount::getValue));
        return amountIn;
    }

    @Override
    public List<Amount> sortOnlyInByAmountReserved() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountIn = c.stream()
                .filter(amount -> amount.getInOut().equals("Przychód")).collect(Collectors.toList());
        amountIn.sort(Comparator.comparing(Amount::getValue).reversed());
        return amountIn;
    }

    @Override
    public List<Amount> sortOnlyOutByAmount() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountOut = c.stream()
                .filter(amount -> amount.getInOut().equals("Wydatek")).collect(Collectors.toList());
        amountOut.sort(Comparator.comparing(Amount::getValue));
        return amountOut;
    }

    @Override
    public List<Amount> sortOnlyOutByAmountReserved() {
        List<Amount> c = getAllAmounts();
        List<Amount> amountOut = c.stream()
                .filter(amount -> amount.getInOut().equals("Wydatek")).collect(Collectors.toList());
        amountOut.sort(Comparator.comparing(Amount::getValue).reversed());
        return amountOut;
    }

    @Override
    public BigDecimal returnSum() {
        List<Amount> retSumList = getAllAmounts();
        BigDecimal retSum = retSumList.stream().map(Amount::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        return retSum;
    }

    @Override
    public BigDecimal returnSumIn() {
        List<Amount> retSumList = getAllAmounts();
        List<Amount> retSumInList = retSumList.stream().filter(amount -> amount.getInOut().equals("Przychód")).collect(Collectors.toList());
        BigDecimal retSumIn = retSumInList.stream().map(Amount::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        return retSumIn;
    }

    @Override
    public BigDecimal returnSumOut() {
        List<Amount> retSumList = getAllAmounts();
        List<Amount> retSumOutList = retSumList.stream().filter(amount -> amount.getInOut().equals("Wydatek")).collect(Collectors.toList());
        BigDecimal retSumOut = retSumOutList.stream().map(Amount::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        return retSumOut;
    }
}
