package com.ice.brother.house.app;

/**
 * @author:ice
 * @Date: 2018/6/29 21:20
 */
public class SpringTest {

  public static void main(String[] args) {
    Test1 test1 = new Test1();
    test1.setAge(null);
    System.out.println(test1);
  }

  public static class Test1 {

    private String id;

    private String name;

    private Integer age;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "Test1{" +
          "id='" + id + '\'' +
          ", name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
