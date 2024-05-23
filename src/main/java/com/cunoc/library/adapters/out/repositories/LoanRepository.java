package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.domain.models.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    @Query("SELECT l FROM LoanEntity l WHERE l.user.username = :username AND l.status = 'lent'")
    List<LoanEntity> findActiveLoansByUsername(@Param("username") String username);

    @Query("SELECT l FROM LoanEntity l WHERE l.status = :status")
    List<LoanEntity> findAllByStatus(@Param("status") LoanStatus status);

    @Query("SELECT l FROM LoanEntity l WHERE l.user.username = :username")
    List<LoanEntity> findLoansByUsername(@Param("username") String username);

    boolean existsByUserUsernameAndStatus(String username, LoanStatus status);
}

