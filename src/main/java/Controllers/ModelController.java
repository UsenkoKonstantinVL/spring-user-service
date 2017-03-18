package Controllers;

import Model.Model;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by toshiba on 17.03.2017.
 */
public class ModelController {
    @RequestMapping("/addperson")
    public void addPerson(@RequestParam(value="name", required=true) String name, @RequestParam(value="surname", required=true) String surname,
                          @RequestParam(value="birthdate", required=true)String birthDate,
                          @RequestParam(value="email", required=true)String email,
                          @RequestParam(value="password", required=true)String password) {

        Model model = new Model(0, name, surname, birthDate, email, password);

    }
    @RequestMapping("/searchpersons")
    public Model searchPersons(@RequestParam(value="searchemail", required = false, defaultValue = "") String searchEmail) {


        Model model = new Model(0, "name", "surname", "11.11.2011",
                "mail@mail.ru", "pass");

        return model;

    }
    @RequestMapping("/deleteperson")
    public void deletePersons(@RequestParam(value="id", required=true) int id) {
        System.out.println("Delete person: id = " + id);
    }
}
