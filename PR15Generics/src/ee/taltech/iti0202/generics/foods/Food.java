package ee.taltech.iti0202.generics.foods;

public abstract class Food {
  protected String name;

  public Food(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
