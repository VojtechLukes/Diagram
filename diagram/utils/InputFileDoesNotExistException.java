package utils;

import java.io.IOException;

/**
 * Výjimka InputFileDoesNotExistException se vyhazuje, když vstupní soubor
 * neexistuje.
 */
public class InputFileDoesNotExistException extends IOException {

    /**
     * Vytváří novou výjimku InputFileDoesNotExistException se specifikovanou
     * chybovou zprávou.
     *
     * @param message chybová zpráva
     */
    public InputFileDoesNotExistException(String message) {
        super(message);
    }
}
