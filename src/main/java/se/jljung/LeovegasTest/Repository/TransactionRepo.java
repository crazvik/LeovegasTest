package se.jljung.LeovegasTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jljung.LeovegasTest.Entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
