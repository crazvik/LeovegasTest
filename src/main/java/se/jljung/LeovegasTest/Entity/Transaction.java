package se.jljung.LeovegasTest.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private String transactions;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    public Transaction() {
    }

    public Transaction(String transactions, Player player) {
        this.transactions = transactions;
        this.player = player;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long id) {
        this.transactionId = id;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(transactions, that.transactions) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactions, player);
    }

    @Override
    public String toString() {
        return "Date of transaction: " + transactions + '\n';
    }
}
