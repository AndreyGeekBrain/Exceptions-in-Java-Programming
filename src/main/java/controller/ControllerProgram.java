package controller;

import erors.CountExceptionOptionsUser;
import model.ModelUserFileWriter;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/**
 * Данный класс создан в соответствии с паттерном проектирования MVC
 * и является ручкой (endpoint) для UI пользователя.
 * Обеспечивает взаимодействие с системой:
 * обрабатывает действия пользователя, проверяет полученную
 * информацию и передает ее модели.
 */

public class ControllerProgram {
    private String date = null;
    private String lastname = null;
    private String firstname = null;
    private String surname = null;
    private String male = null;
    private int phoneNumber = 0;
    private ModelUserFileWriter modelUserFileWriter = null;

    /**
     * Проводим Dependence Injection класса ModelUserFileWriter, слоя view (паттерн MVC).
     */
    public ControllerProgram () {
        this.modelUserFileWriter = new ModelUserFileWriter ();
    }

    /**
     * Метод проверяет введенные данные по количеству.
     * Если количество не совпадает с требуемым,
     * вернуть код ошибки, обработать его и показать.
     * пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
     *
     * @param - в качестве параметра str выступает строка полученная от пользователя.
     * @return - метод возвращает распарсенную на токены строку от пользователя.
     * @throws - в случае если пользователь ввел количество параметров не кратное 4,
     *           то будет выброшено исключение CountExceptionOptionsUser.
     */
    public void checkingDataReceivedFromTheUser (String str) throws CountExceptionOptionsUser {
        String[] arrayStr = str.split (" " );
        if (arrayStr.length < 4) {
            throw new CountExceptionOptionsUser ("Вы ввели меньше параметров, " +
                    "чем требовалось по условию программы!" );
        }
        if (arrayStr.length > 4) {
            throw new CountExceptionOptionsUser ("Вы ввели больше параметров, " +
                    "чем требовалось по условию программы!" );
        }
        parsingOptionFromArrayStringUser (arrayStr);
    }

    /**
     * Приложение должно попытаться распарсить полученные значения
     * и выделить из них требуемые параметры.
     * Если форматы данные не совпадают,
     * нужно бросить исключение, соответствующее типу проблемы.
     * Можно использовать встроенные типы java и создать свои.
     * Исключение должно быть корректно обработано, пользователю выведено сообщение
     * с информацией, что именно неверно.
     *
     * @param array - массив токенов отпарсенных из строки пользователя.
     * @throws CountExceptionOptionsUser - в случае если пользователь ввел количество параметров
     *                                   не соответствующее формату данных,то будет выброшено исключение CountExceptionOptionsUser
     */
    private void parsingOptionFromArrayStringUser (String[] array) throws CountExceptionOptionsUser {
        for (int i = 0; i < array.length; i++) {
            if (array[i].contains ("," )) {
                String[] arrayFIO = array[i].split ("," );
                if (arrayFIO.length != 3) {
                    throw new CountExceptionOptionsUser ("Вы ввели не полные " +
                            "данные ФИО" + array[i]);
                } else {
                    lastname = arrayFIO[0];
                    firstname = arrayFIO[1];
                    surname = arrayFIO[2];
                }
            }
            if (array[i].equals ("f" ) || array[i].equals ("m" )) {
                male = array[i];
            }
            if (array[i].contains ("." )) {
                if (array[i].split ("\\." ).length != 3) {
                    throw new CountExceptionOptionsUser ("Вы ввели не полные " +
                            "данные по параметру дата " + array[i]);
                }
                date = array[i];
            }
            if (isCreatable (array[i])) {
                phoneNumber = Integer.parseInt (array[i]);
            }
        }
        modelUserFileWriter.writerFileUserLastname (lastname, firstname, surname, date, phoneNumber, male);
    }
}
