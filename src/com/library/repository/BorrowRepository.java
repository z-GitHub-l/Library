package com.library.repository;

import com.library.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerid,String borrowtime,String returntime,Integer adminid,Integer state);

    public List<Borrow> findAllByReaderid(Integer id,Integer index,Integer limit);  //通过读者ID查询读者所以借阅操作

    public int count(Integer readerid);

    public List<Borrow> findAllByState(Integer state,Integer index,Integer limit);

    public int BorrowCount(Integer state);

    public void handle(Integer readerid,Integer state,Integer adminid);
}
