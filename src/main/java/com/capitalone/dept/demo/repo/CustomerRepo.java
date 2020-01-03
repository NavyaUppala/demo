package com.capitalone.dept.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitalone.dept.demo.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
