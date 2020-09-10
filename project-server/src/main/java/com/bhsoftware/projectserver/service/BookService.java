package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.dao.BookDAO;
import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryService categoryService;

    public List<Book> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bookDAO.findAll(sort);
    }
    public void addOrUpdate(Book book){

        bookDAO.save(book);
    }
    public void deleteById(int id){

        bookDAO.deleteById(id);
    }
    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }
    public List<Book> search(String keywords){
        return bookDAO.findAllByTitleLikeOrAuthorLike('%'+keywords+'%','%'+keywords+'%');
    }


}
