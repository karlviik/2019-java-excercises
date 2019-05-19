package ee.taltech.iti0202.furniture.warehouse.filter;

public class FurnitureFilterBuilder {
  private Boolean inStock;
  private String type;
  private String model;
  private Float minPrice;
  private Float maxPrice;

  public FurnitureFilterBuilder setInStock(Boolean inStock) {
    this.inStock = inStock;
    return this;
  }

  public FurnitureFilterBuilder setType(String type) {
    this.type = type;
    return this;
  }

  public FurnitureFilterBuilder setModel(String model) {
    this.model = model;
    return this;
  }

  public FurnitureFilterBuilder setMinPrice(Float minPrice) {
    this.minPrice = minPrice;
    return this;
  }

  public FurnitureFilterBuilder setMaxPrice(Float maxPrice) {
    this.maxPrice = maxPrice;
    return this;
  }

  public FurnitureFilter createFurnitureFilter() {
    return new FurnitureFilter(inStock, type, model, minPrice, maxPrice);
  }
}