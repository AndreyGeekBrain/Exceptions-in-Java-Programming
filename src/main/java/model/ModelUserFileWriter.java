package model;

import view.Answer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Данный класс создан в соответствии с паттерном проектирования MVC
 * и является основной логикой работы приложения. Модель реагирует на команды из контроллера и выдает
 * информацию и/или изменяет свое состояние. Она передает данные в представление.
 */
public class ModelUserFileWriter {
    private Answer answer = null;

    /**
     * Проводим Dependence Injection класса Answer, слоя view (паттерн MVC).
     */
    public ModelUserFileWriter () {
        this.answer = new Answer ();
    }

    /**
     * Метод должен создать файл расширения .txt с именем @param lastname,
     * a также записать в него строку составленных из параметров ниже согласно шаблону:
     * <Фамилия> <Имя> <Отчество> <дата рождения> <номер телефона>      *
     *
     * @param lastname    - фамилия введенная пользователем.
     * @param firstname   - имя введенная пользователем.
     * @param surname     - отчество введенная пользователем.
     * @param date        - дата введенная пользователем.
     * @param phoneNumber - телефонный номер введенная пользователем.
     * @param male        - пол введенная пользователем.
     */
    public void writerFileUserLastname (String lastname, String firstname, String surname, String date, int phoneNumber, String male) {
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append ("<" + lastname + "> " );
        stringBuilder.append ("<" + firstname + "> " );
        stringBuilder.append ("<" + surname + "> " );
        stringBuilder.append ("<" + date + "> " );
        stringBuilder.append ("<" + phoneNumber + "> " );
        stringBuilder.append ("<" + male + ">" );
        try (FileWriter fileWriter = new FileWriter (lastname + ".txt", true)) {
            fileWriter.write (stringBuilder + "\n" );
            fileWriter.flush ();
            answer.getMessegeOK ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
