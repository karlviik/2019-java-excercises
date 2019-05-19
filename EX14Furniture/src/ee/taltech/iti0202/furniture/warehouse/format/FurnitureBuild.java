package ee.taltech.iti0202.furniture.warehouse.format;

import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.furniture.furniture.Furniture;

public class FurnitureBuild {
  private Furniture furniture;
  @SerializedName("build_amount")
  private Integer buildAmount;

  public FurnitureBuild(Furniture furniture, Integer buildAmount) {
    this.furniture = furniture;
    this.buildAmount = buildAmount;
  }

  public Furniture getFurniture() {
    return furniture;
  }

  public Integer getBuildAmount() {
    return buildAmount;
  }
}
