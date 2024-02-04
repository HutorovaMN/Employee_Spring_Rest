package com.spring.rest.exception_handling;

//Класс исключений при невозможности получить работника из базы
public class NoSuchEmployeeException extends RuntimeException {

  public NoSuchEmployeeException(String message) {
      super(message);
  }
}