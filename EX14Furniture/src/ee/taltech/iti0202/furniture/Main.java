package ee.taltech.iti0202.furniture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.furniture.catalogue.Catalogue;
import ee.taltech.iti0202.furniture.furniture.Chair;
import ee.taltech.iti0202.furniture.furniture.Furniture;
import ee.taltech.iti0202.furniture.furniture.Throne;
import ee.taltech.iti0202.furniture.furniture.builder.ChairBuilder;
import ee.taltech.iti0202.furniture.furniture.builder.ThroneBuilder;
import ee.taltech.iti0202.furniture.material.Material;
import ee.taltech.iti0202.furniture.material.MaterialMapBuilder;
import ee.taltech.iti0202.furniture.warehouse.Warehouse;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Warehouse warehouse = new Warehouse();
        Chair chair = new ChairBuilder()
                .setModel("2034")
                .setComfiness(22)
                .setHeight(11)
                .setLegs(3312)
                .setLength(12314214)
                .setWidth(3)
                .setPrice(2313)
                .setRequiredMaterials(new MaterialMapBuilder()
                        .addMaterial(Material.ALUMINIUM_SHEET, 22f)
                        .addMaterial(Material.CAR_TYRE, 3f)
                        .buildMaterialMap())
                .createChair();
        Throne throne = new ThroneBuilder()
                .setBadassness(2000)
                .setModel("133337")
                .setHeight(52.3f)
                .setLength(333)
                .setWidth(32.42f)
                .setPrice(123123123)
                .setRequiredMaterials(new MaterialMapBuilder()
                        .addMaterial(Material.IRON_ROD, 3234f)
                        .addMaterial(Material.GLASS, 3144f)
                        .buildMaterialMap())
                .createThrone();
        warehouse.addFurnitureStock(chair, 0);
        warehouse.addFurnitureStock(throne, 1);
        System.out.println(warehouse.getFurnitureObjectsJSON(false));
        System.out.println(warehouse.build(throne, 12));
        System.out.println(warehouse.getMaterialsRequiredForFurniture(chair, 12));

    }
}
