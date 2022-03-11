package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.exceptions.EtAuthException;
import com.pairlearning.expensetracker.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT COUNT(*) FROM User WHERE email = :email")
    int getCountByEmail(String email);

    @Query("SELECT userId FROM User WHERE email= :email")
    int findByEmail(String email);
}
