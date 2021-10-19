package se.jljung.LeovegasTest.Entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    private String username;
    private String password;
    private double funds;
    private boolean isAdmin;

    @OneToMany(mappedBy = "player")
    private Set<Transaction> transactions;

    public Player() {
    }

    public Player(String username, String password, double funds, boolean isAdmin, Set<Transaction> transactions) {
        this.username = username;
        this.password = password;
        this.funds = funds;
        this.isAdmin = isAdmin;
        this.transactions = transactions;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long id) {
        this.playerId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTransactions(Transaction transactions) {
        this.transactions.add(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Double.compare(player.funds, funds) == 0 && Objects.equals(playerId, player.playerId) && Objects.equals(username, player.username) && Objects.equals(password, player.password) && Objects.equals(transactions, player.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, username, password, funds, isAdmin, transactions);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", funds=" + funds +
                ", isAdmin=" + isAdmin +
                ", transactions=" + transactions +
                '}';
    }
}
