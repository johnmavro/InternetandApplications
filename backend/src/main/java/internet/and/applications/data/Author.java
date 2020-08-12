package internet.and.applications.data;

import java.util.Objects;

public class Author {
  private String name;
  private int number;

  public Author(){

  }

  public String getname(){
    return name;
  }

  public void setname(String name){
    this.name =name;
  }

  public int getnumber(){
    return number;
  }

  public void setnumber(int number){
    this.number=number;
  }
}
