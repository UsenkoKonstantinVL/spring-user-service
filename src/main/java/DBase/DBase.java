package DBase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Model;
import com.sun.javafx.sg.prism.NGShape;

/**
 * Класс управления базой данных
 */
public class DBase {

    private static Connection conn;
    private static Statement stat;
    private static ResultSet res;

    public static String dBase = "";

    /***
     * Соединение с базой данных
     * @throws Exception
     */
    public synchronized static void Connect() throws Exception{
        File file = new File(System.getProperty("user.dir") + "//" + dBase);

        boolean isFileExist = file.exists();

        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dBase);
        stat = conn.createStatement();

        if(!isFileExist)
        {
            stat.execute("CREATE TABLE if not exists 'persons' ('id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'name' STRING, 'surname' STRING, 'birthdate' STRING, 'email' STRING, 'password' STRING);");

        }
    }

    /***
     * Добавление пользователя в базу данных
     * @param model данные о пользователя
     * @throws Exception
     */
    public static void AddModel(Model model) throws Exception{
        Connect();
        String queryString = "INSERT INTO 'persons' ('name' , 'surname' , 'birthdate' , 'email' , 'password' )" +
                " VALUES ('%s', '%s', '%s', '%s', '%s');";
        queryString = String.format(queryString, model.getName(), model.getSurName(),
                model.getBirthDate(), model.getEmail(), model.getPassword());
        stat.execute(queryString);
        Disconnect();
    }

    /***
     * Удаление пользователя
     * @param id идентификатор удаляемого пользователя
     * @throws Exception
     */
    public static void DeleteModel(int id)throws  Exception{
        Connect();
        String queryString = "DELETE FROM persons WHERE id LIKE %s";
        queryString = String.format(queryString, id);
        stat.execute(queryString);
        Disconnect();
    }

    /***
     * Поиск пользователей
     * @param search фильтр
     * @return список найденных пользователей
     * @throws Exception
     */
    public static List<Model> SearchModel(String search) throws Exception{
        Connect();
        String query = "SELECT * FROM persons WHERE email LIKE '%s'";
        query = String.format(query, search);
        res = stat.executeQuery(query);

        List<Model> list = new ArrayList<>();
        while(res.next())
        {
            Model model = new Model(res.getInt("id"), res.getString("name"),
                    res.getString("surname"), res.getString("birthdate"),
                    res.getString("email"), res.getString("password"));
        }

        Disconnect();
        return list;
    }

    /***
     * Разъединение с базой данных
     * @throws Exception
     */
    public static void Disconnect() throws Exception{
        conn.close();
        stat.close();
        if(res != null) {
            res.close();
        }
    }
}
