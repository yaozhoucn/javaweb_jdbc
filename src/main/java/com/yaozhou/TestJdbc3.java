package com.yaozhou;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by WXHang on HANG at 2021/6/19 14:22
 * @author HANG
 */

public class TestJdbc3 {
    @Test
    public void Test_AutoCommit() throws ClassNotFoundException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/ssmbuild?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        //加载驱动
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        //连接数据库
        try {
            connection = DriverManager.getConnection(url, username, password);

            //通知数据库开启事务
            connection.setAutoCommit(false);

            /**
             * 数据库开启事务SQL语句：start transaction
             */

            String sql1 = "update books set bookCounts = bookCounts -1 where bookName = 'MySQL'";
            //执行sql语句
            connection.prepareStatement(sql1).executeUpdate();

            //制造错误
            //int i = 1/0;

            String sql2 = "update books set bookCounts = bookCounts + 1 where bookName = 'Java'";
            connection.prepareStatement(sql2).executeUpdate();
            //以上两条sql语句执行成功就提交事务
            connection.commit();
            System.out.println("success");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }


}