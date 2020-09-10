package com.bhsoftware.projectserver.controller;

import com.bhsoftware.projectserver.dao.BookDAO;
import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.result.Result;
import com.bhsoftware.projectserver.service.BookService;
import com.bhsoftware.projectserver.service.UserService;
import com.bhsoftware.projectserver.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "用户接口")
@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @ApiOperation(value = "查询用户" ,  notes="查询用户")
    @GetMapping("/api/books")
    public List<Book> list(){
        return bookService.list();
    }
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book){
        bookService.addOrUpdate(book);
        return book;
    }
    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book){
        bookService.deleteById( book.getId());
    }
    //图书分类
    @CrossOrigin
    @ApiOperation(value = "列表切换查询图书" ,  notes="列表切换查询图书")
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid){
        if (cid != 0){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }

    //搜索栏
    @CrossOrigin
    @ApiOperation(value = "搜索图书" ,  notes="搜索图书")
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords) {
        System.out.println(keywords);
        if ("".equals(keywords)){
            return bookService.list();
        }else {
            return bookService.search(keywords);
        }
    }
//    @ApiOperation(value = "返回所有图书" ,  notes="返回所有图书")
//    @GetMapping("/api/book")
//    @ResponseBody
//    public Response<PageInfo<Book>> Bookpaging(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
//                                               @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
//                                               @RequestParam(value = "title", required = false) String title,
//                                               @RequestParam(value = "cid", required = false) Integer cid) {
//
//        PageHelper.startPage(pageNo, pageSize);
//        List<Book> list=bookService.getListBook(title,cid);
//        PageInfo<Book> pageInfo = new PageInfo<>(list);
//        System.out.println(Response.yes(pageInfo));
//        return Response.yes(pageInfo);
//    }

    @ApiOperation(value = "分页返回所有图书" ,  notes="分页返回所有图书")
    @GetMapping("/api/book")
    @ResponseBody
    public Map<String,Object> list(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pagesize", required = false, defaultValue = "8") Integer pagesize,
                                   @RequestParam(value = "cid", required = false) Integer cid,
                                   @RequestParam(value = "keywords", required = false) String keywords
                                   ){
        //return bookService.list();
        PageRequest pageRequest=PageRequest.of(currentPage-1,pagesize);
        Page<Book> pagination=bookDAO.findAll(pageRequest);
        Map<String,Object> map=new HashMap<>();
        map.put("pagination",pagination);
        map.put("currentPage",currentPage);
        map.put("pagesize",pagesize);
        return map;
    }

    //上传
//    @CrossOrigin
    @PostMapping("/api/covers")
    public String coversUpload(MultipartFile file){
        String folder = "d:/vue/img1";
        File imageFolder = new File(folder);
        File f = new File(imageFolder,StringUtils.getRandomString(6) +
                file.getOriginalFilename().substring(file.getOriginalFilename().length() -4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8090/api/file/" + f.getName();
            return imgURL;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

//    @ApiOperation(value = "增加用户" ,  notes="增加用户")
//    @PostMapping("/api/register")
//    public Result addUser(@RequestBody User user){
//        userService.register(user);
//        return new Result(200);
//    }
}
