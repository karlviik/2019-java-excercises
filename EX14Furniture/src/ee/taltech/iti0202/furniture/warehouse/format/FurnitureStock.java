package ee.taltech.iti0202.furniture.warehouse.format;

import ee.taltech.iti0202.furniture.furniture.Furniture;

public class FurnitureStock {
  private Furniture furniture;
  private Integer stock;

  public FurnitureStock(Furniture furniture, Integer stock) {
    this.furniture = furniture;
    this.stock = stock;
  }

  public Furniture getFurniture() {
    return furniture;
  }

  public Integer getStock() {
    return stock;
  }

  public void changeStock(Integer change) {
    if (stock + change >= 0) {
      stock += change;
    }
  }
}
