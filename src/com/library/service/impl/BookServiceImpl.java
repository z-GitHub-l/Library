package com.library.service.impl;

import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.impl.BookRepositoryImpl;
import com.library.repository.impl.BorrowRepositoryImpl;
import com.library.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private final int LIMIT=6;
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    @Override
    public List<Book> findAll(int page) {
        int index = (page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        int page = 0;;
        if(count%LIMIT==0){
            page=count/LIMIT;
        }else {
            page=(count/LIMIT)+1;
        }
        return page;
    }

    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        //还书时间，借书时间+14天(工具类实现)
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        //
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);
    }

    @Override
    public List<Borrow> findAllBorrowById(Integer id,Integer page) {
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByReaderid(id,index,LIMIT);
    }

    @Override
    public int getPagesOfBorrow(Integer id) {
        int count = borrowRepository.count(id);
        int page = 0;;
        if(count%LIMIT==0){
            page=count/LIMIT;
        }else {
            page=(count/LIMIT)+1;
        }
        return page;
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state, Integer page) {
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByState(state,index,LIMIT);
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        int count = borrowRepository.BorrowCount(state);
        int page = 0;;
        if(count%LIMIT==0){
            page=count/LIMIT;
        }else {
            page=(count/LIMIT)+1;
        }
        return page;
    }

    @Override
    public void handlleBorrow(Integer readerid, Integer state, Integer adminid) {
        borrowRepository.handle(readerid,state,adminid);
    }
}
