package pl.budget.budgetManager.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budget")
public class Amount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "amount")
    private BigDecimal value;
    @Column(name = "category")
    private String category;
    @Column(name = "in_out")
    private String inOut;

    @Override
    public String toString() {
        return "Amount{" +
                "id=" + id +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", inOut='" + inOut + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }
}
