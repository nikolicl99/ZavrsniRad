package com.asss.www.ApotekarskaUstanova.Repository;

import com.asss.www.ApotekarskaUstanova.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmail(String email);

    List<Employees> findAllByOrderByIdAsc();

    @Query("SELECT e FROM Employees e LEFT JOIN FETCH e.employeeType")
    List<Employees> findAllWithEmployeeType();

    @Query("SELECT e FROM Employees e JOIN FETCH e.employeeType t WHERE e.id = :id")
    Optional<Employees> findByIdWithEmployeeType(@Param("id") int id);

}
