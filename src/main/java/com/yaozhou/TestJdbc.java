package com.yaozhou;


import java.sql.*;

/**
 * Created by WXHang on HANG at 2021/6/19 12:55
 * @author HANG
 */
public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/ssmbuild?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //向数据库发送sql的对象statment，执行：CRUD
        Statement statement = connection.createStatement();

        //编写sql语句
        String sql = "select * from books";
        //执行sql语句，返回一个ResultSet：结果集
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println("bookID:"+resultSet.getObject("bookID"));
            System.out.println("bookName:"+resultSet.getObject("bookName"));
            System.out.println("bookCounts:"+resultSet.getObject("bookCounts"));
            System.out.println("detail:"+resultSet.getObject("detail"));

            System.out.println("=======================================================");
        }

        //关闭数据库连接，先开后关
        resultSet.close();
        statement.close();
        connection.close();


    }
}