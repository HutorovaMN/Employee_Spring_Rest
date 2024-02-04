package com.spring.rest.exception_handling;

//Класс, конвертирубщийся в json при выбросе исключений NuSuchEmployeeException
public class EmployeeIncorrectData {
  private String info;

  public EmployeeIncorrectData() {
  }

  public String getInfo() {
      return info;
  }

  public void setInfo(String info) {
      this.info = info;
  }
}