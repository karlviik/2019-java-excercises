package ee.taltech.iti0202.furniture.warehouse.filter;

import java.util.Optional;

public class FurnitureFilter {
  private Boolean inStock;
  private String type;
  private String model;
  private Float minPrice;
  private Float maxPrice;

  public FurnitureFilter(Boolean inStock, String type, String model, Float minPrice, Float maxPrice) {
    this.inStock = inStock;
    this.type = type;
    this.model = model;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
  }

  public Optional<Boolean> getInStock() {
    return Optional.ofNullable(inStock);
  }

  public Optional<String> getType() {
    return Optional.ofNullable(type);
  }

  public Optional<String> getModel() {
    return Optional.ofNullable(model);
  }

  public Optional<Float> getMinPrice() {
    return Optional.ofNullable(minPrice);
  }

  public Optional<Float> getMaxPrice() {
    return Optional.ofNullable(maxPrice);
  }
}
