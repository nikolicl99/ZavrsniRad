package com.asss.www.ApotekarskaUstanova.Repository;

import com.asss.www.ApotekarskaUstanova.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByHealthCardNumber(String healthCardNumber);
}