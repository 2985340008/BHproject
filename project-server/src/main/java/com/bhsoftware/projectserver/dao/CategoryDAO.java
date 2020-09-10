package com.bhsoftware.projectserver.dao;

import com.bhsoftware.projectserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
