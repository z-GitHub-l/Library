package com.library.service;

import com.library.entity.Book;
import com.library.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);    //查询所有图书并返回一个book的集合

    public int getPages();  //  计算图书数据一共需要多少页

    public void addBorrow(Integer bookid,Integer readerid);

    public List<Borrow> findAllBorrowById(Integer id,Integer page);

    public int getPagesOfBorrow(Integer id);   //  计算借书数据一共需要多少页

    public List<Borrow> findAllBorrowByState(Integer state,Integer page);

    public int getBorrowPagesByState(Integer state);

    public void handlleBorrow(Integer readerid,Integer state,Integer adminid);
}
