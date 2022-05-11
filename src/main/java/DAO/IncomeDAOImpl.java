package DAO;

import Model.Income;
import Model.IncomeType;
import Model.Vendor;
import Util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncomeDAOImpl implements IncomeDAO{
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    List<Income> list = null;
    Income income = null;
    Vendor vendor = null;
    PreparedStatement preparedStmt = null;

    @Override
    public List<Income> get() throws SQLException {

        try {
            list = new ArrayList<Income>();
            String sql = "select * from income ";
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                income = new Income();
                //  income.setRole(resultSet.getString("role"));
income.setId(resultSet.getInt("id"));
income.setIncomeType(IncomeType.valueOf(resultSet.getString("incomeType")));
income.setAmount(resultSet.getDouble("amount"));
income.setReceivedFrom(resultSet.getString("receivedFrom"));
income.setLogger(new UserDAOImpl().get(resultSet.getInt("logger")));
income.setDate(resultSet.getDate("date"));






                list.add(income);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }

        return list;

    }



    @Override
    public boolean saveIncome(Income income) {
        boolean flag = false;
        try {




            String sql = "insert into income(incomeType, amount, receivedFrom, logger, date) "
                    + "values('" + income.getIncomeType()+ "', " +income.getAmount() + ",'" + income.getReceivedFrom() + "'," + income.getLogger().getId()+ ", '"+ income.getDate()+"')";
            try {
                connection = DBConnectionUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(IncomeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Income get(int id) {
        Income income = null;
        try {
            income = new Income();
            String sql = "SELECT * FROM income  WHERE id=" + id;
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                income.setId(resultSet.getInt("id"));
                income.setIncomeType(IncomeType.valueOf(resultSet.getString("incomeType")));
                income.setAmount(resultSet.getDouble("amount"));
                income.setReceivedFrom(resultSet.getString("receivedFrom"));
                income.setLogger(new UserDAOImpl().get(resultSet.getInt("logger")));
                income.setDate(resultSet.getDate("date"));



            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return income;
    }    @Override
    public Income get(String name) {
        Income income = null;
        try {
            income = new Income();
            String sql = "SELECT * FROM income  WHERE incomeName='" + name +"'";
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                income.setId(resultSet.getInt("id"));
                income.setIncomeType(IncomeType.valueOf(resultSet.getString("incomeType")));
                income.setAmount(resultSet.getDouble("amount"));
                income.setReceivedFrom(resultSet.getString("receivedFrom"));
                income.setLogger(new UserDAOImpl().get(resultSet.getInt("logger")));
                income.setDate(resultSet.getDate("date"));



            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return income;
    }

    @Override
    public boolean updateIncome(Income income) {

        boolean flag = false;

        try {
            String sql = "update income set incomeType='"+ income.getIncomeType()+"',amount=" + income.getAmount() + ",receivedFrom='" + income.getReceivedFrom() + "',logger =" + income.getLogger().getId()+", date='"+ income.getDate()  +"' where id=" + income.getId() ;
            connection = DBConnectionUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;

    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM income WHERE id=" + id;
            connection = DBConnectionUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncomeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }






}