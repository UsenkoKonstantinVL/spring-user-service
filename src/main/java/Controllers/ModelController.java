package Controllers;

import DBase.DBase;
import Model.Model;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс, предоставляющий ресурс
 */
public class ModelController {
    /***
     * Добавление данных
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @param birthDate дата рождения
     * @param email адресс электронной почты
     * @param password пароль
     */
    @RequestMapping("/addperson")
    public void addPerson(@RequestParam(value="name", required=true) String name, @RequestParam(value="surname", required=true) String surname,
                          @RequestParam(value="birthdate", required=true)String birthDate,
                          @RequestParam(value="email", required=true)String email,
                          @RequestParam(value="password", required=true)String password) {

        Model model = new Model(name, surname, birthDate, email,  MD5Coder.md5Custom(password));
        try {
            DBase.AddModel(model);
        }
        catch (Exception ex){

        }

    }

    /***
     * Поиск пользователей
     * @param searchEmail фильтр поиска
     * @return список найденных пользователей
     */
    @RequestMapping("/searchpersons")
    public List<Model> searchPersons(@RequestParam(value="searchemail", required = false, defaultValue = "") String searchEmail) {
        List<Model> list = new ArrayList<>();

        try {
            list = DBase.SearchModel(searchEmail);
        }
        catch (Exception ex){

        }
        /*Model model = new Model(0, "name", "surname", "11.11.2011",
                "mail@mail.ru", "pass");*/
        return list;

    }

    /***
     * Удаление пользователя
     * @param id идентификатор пользователя
     */
    @RequestMapping("/deleteperson")
    public void deletePersons(@RequestParam(value="id", required=true) int id) {
        try {
            DBase.DeleteModel(id);
        }
        catch (Exception ex)
        {

        }
    }
}
