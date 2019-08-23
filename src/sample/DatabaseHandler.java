package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet; // сохранение пользователя в базе данных
public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        //String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
       String connectionString = ("jdbc:mysql://localhost:3306/ilya?useSSL=false&serverTimezone=UTC");
        Class<?> dbDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }

    // будет записывать пользователя в базу данных, создавать запрос
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLES + "(" +
                Const.USER_FIRSTNAME + ", " + Const.USER_LASTNAME + "," +//будет создавать запрос СКЛ, поместить в USER_TABLES
                Const.USER_USERNAME + ", " + Const.USER_PASSWORD + "," +
                Const.USER_LOCATION + ", " + Const.USER_GENDER + ") " +
                //далее указываем данные которые будем помещать
                "VALUES(?,?,?,?,?,?)";
        //потом в эти вопросы мы будем вставлять данные

        try { PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            //getDbConnection() подключение к базе данных, передаем СКЛ запрос

            // далее выполниить эту команду
            prSt.setString(1,user.getFirstName());//акой запрос мы передаем
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.executeUpdate(); // метод закидывает нам в БД
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet=null;
        //создаем строку select, выбрать все из БД, где Юзернейм равно чему-либо
        String select = "SELECT * FROM " + Const.USER_TABLES + " WHERE " +
                Const.USER_USERNAME + "=? AND " + //будет создавать запрос СКЛ, поместить в USER_TABLES
                Const.USER_PASSWORD + "=?";
        try { PreparedStatement prSt = getDbConnection().prepareStatement(select);
            //getDbConnection() подключение к базе данных, передаем СКЛ запрос

            // далее выполниить эту команду
            prSt.setString(1,user.getUserName());//акой запрос мы передаем
            prSt.setString(2, user.getPassword());

             resSet =prSt.executeQuery();// получить данные из БД
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
