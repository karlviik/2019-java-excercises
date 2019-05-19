package ee.taltech.iti0202.furniture.warehouse.format;

import ee.taltech.iti0202.furniture.material.Material;

public class MaterialStock {
  private Material material;
  private Float stock;

  public MaterialStock(Material material, Float stock) {
    this.material = material;
    this.stock = stock;
  }

  public Material getMaterial() {
    return material;
  }

  public Float getStock() {
    return stock;
  }

  public void changeStock(Float amount) {
    if (stock + amount >= 0) {
      stock += amount;
    }
  }
}
