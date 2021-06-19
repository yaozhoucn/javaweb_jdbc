package com.yaozhou;

import java.sql.*;

/**
 * Created by WXHang on HANG at 2021/6/19 13:33
 * @author HANG
 */
public class TestJdbc2 {
    /**
     *
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException\
     *
     * doc:预编译sql
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/ssmbuild?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);




        //预编写sql语句
        //String sql = "select * from books";
       String sql =  "insert into books (bookID, bookName, bookCounts, detail) values (?,?,?,?);";

        //预编译，执行：CRUD
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,6);
        preparedStatement.setString(2,"jeecg");
        preparedStatement.setInt(3,6);
        preparedStatement.setString(4,"流程引擎");

        //执行sql语句，返回一个ResultSet：结果集
        int i = preparedStatement.executeUpdate();
        if (i > 0 ){
            System.out.println("执行成功");
        }


        //关闭数据库连接，先开后关
        preparedStatement.close();
        connection.close();

    }
}