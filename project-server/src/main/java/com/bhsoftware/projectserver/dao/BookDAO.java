package com.bhsoftware.projectserver.dao;

import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book,Integer> ,JpaSpecificationExecutor<Book> {

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByTitleLikeOrAuthorLike(String title,String author);
}
