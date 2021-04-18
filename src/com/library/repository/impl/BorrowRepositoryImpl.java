package com.library.repository.impl;

import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.entity.Reader;
import com.library.repository.BorrowRepository;
import com.library.utils.JDBCTools;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {

    @Override
    public void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values (?,?,?,?,0)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookid);
            preparedStatement.setInt(2,readerid);
            preparedStatement.setString(3,borrowtime);
            preparedStatement.setString(4,returntime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public List<Borrow> findAllByReaderid(Integer id,Integer index,Integer limit) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        String sql =  "select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.username,r.tel,r.cardid,br.state from borrow br,book b,reader r where readerid = ? and b.id=br.bookid and r.id=br.readerid limit ?,?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer borrowId = resultSet.getInt(1);
                String bookName = resultSet.getString(2);
                String author = resultSet.getString(3);
                String publish = resultSet.getString(4);
                String borrowTime = resultSet.getString(5);
                String returnTime = resultSet.getString(6);
                String username = resultSet.getString(7);
                String tel = resultSet.getString(8);
                String cardId = resultSet.getString(9);
                Integer state = resultSet.getInt(10);
                Book book = new Book(bookName,author,publish);
                Reader reader = new Reader(username,tel,cardId);
                Borrow borrow = new Borrow(borrowId,book,reader,borrowTime,returnTime,state);
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql =  "select count(*) from borrow br,book b,reader r where readerid = ? and b.id=br.bookid and r.id=br.readerid ";
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public List<Borrow> findAllByState(Integer state, Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        String sql =  "select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.username,r.tel,r.cardid,br.state from borrow br,book b,reader r where state = ? and b.id=br.bookid and r.id=br.readerid limit ?,?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //取出所有的素材
                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int BorrowCount(Integer state) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql =  "select count(*) from borrow br,book b,reader r where state = ? and b.id=br.bookid and r.id=br.readerid ";
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void handle(Integer readerid, Integer state, Integer adminid) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update borrow set state = ?,adminid = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,adminid);
            preparedStatement.setInt(3,readerid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }
}
