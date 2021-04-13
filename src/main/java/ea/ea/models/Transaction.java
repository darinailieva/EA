package ea.ea.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;

/**
 * Transaction model - POJO class.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions", schema = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @CreatedDate
    @Column(name = "date_of_creation")
    private Date date;

    @Column(name = "source_amount")
    private double sourceAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchange_rate_id")
    private ExchangeRate exchangeRate;

    @Column(name = "target_amount")
    private double targetAmount;

    public Transaction() {

    }

    public Transaction(double sourceAmount, ExchangeRate exchangeRate) {
        this.sourceAmount = sourceAmount;
        this.exchangeRate = exchangeRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void calculateTargetAmount() {
        targetAmount = sourceAmount * exchangeRate.getConversionRate();
    }
}
