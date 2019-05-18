package ee.taltech.iti0202.generics;

import ee.taltech.iti0202.generics.foods.Food;

import java.util.Optional;

public class AnimalBox<T extends Animal> {
  private T animal;

  public void put(T animal) {
    this.animal = animal;
  }

  public void sound() {
    if (animal != null) {
      animal.sound();
    }
  }

  public void feed(Food food) {
    if (animal != null) {
      System.out.println(animal.getName() + " was fed with " + food.getName());
    }
  }

  public Optional<T> getAnimal() {
    return Optional.ofNullable(animal);
  }
}
