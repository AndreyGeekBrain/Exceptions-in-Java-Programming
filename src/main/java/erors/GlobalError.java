package erors;

/**
 * Данный абстрактный класс создан как базовый для описания ошибок пользователей.
 */
public abstract class GlobalError extends RuntimeException {
    /**
     * Данный конструктор принимает на вход параметр String, содержащий
     * текст ошибки.
     *
     * @param - содержит сообщение об ошибке пользователя.
     * @method - Вызов сохраненного текста ошибки происходит благодаря методу getMessage()
     */
    public GlobalError (String message) {
        super (message);
    }
}
