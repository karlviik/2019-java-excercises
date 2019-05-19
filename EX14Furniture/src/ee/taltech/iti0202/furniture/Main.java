package ee.taltech.iti0202.furniture;

import ee.taltech.iti0202.furniture.catalogue.Catalogue;
import ee.taltech.iti0202.furniture.furniture.Chair;
import ee.taltech.iti0202.furniture.furniture.Furniture;
import ee.taltech.iti0202.furniture.furniture.Throne;
import ee.taltech.iti0202.furniture.furniture.builder.ChairBuilder;
import ee.taltech.iti0202.furniture.furniture.builder.ThroneBuilder;
import ee.taltech.iti0202.furniture.material.Material;
import ee.taltech.iti0202.furniture.material.MaterialMapBuilder;
import ee.taltech.iti0202.furniture.warehouse.Warehouse;
import ee.taltech.iti0202.furniture.warehouse.filter.FurnitureFilter;
import ee.taltech.iti0202.furniture.warehouse.filter.FurnitureFilterBuilder;


public class Main {

    public static void main(String[] args) {
        // create warehouse
        Warehouse warehouse = new Warehouse();

        // create a chair
        Chair chair = new ChairBuilder()
                .setModel("2034")
                .setComfiness(22)
                .setHeight(11)
                .setLegs(3312)
                .setLength(12314214)
                .setWidth(3)
                .setPrice(5)
                .setRequiredMaterials(new MaterialMapBuilder()
                        .addMaterial(Material.ALUMINIUM_SHEET, 5f)
                        .addMaterial(Material.CAR_TYRE, 1f)
                        .buildMaterialMap())
                .createChair();

        // create a throne
        Throne throne = new ThroneBuilder()
                .setBadassness(2000)
                .setModel("133337")
                .setHeight(52.3f)
                .setLength(333)
                .setWidth(32.42f)
                .setPrice(10)
                .setRequiredMaterials(new MaterialMapBuilder()
                        .addMaterial(Material.IRON_ROD, 10f)
                        .addMaterial(Material.GLASS, 10f)
                        .buildMaterialMap())
                .createThrone();

        // add those 2 to there
        warehouse.changeFurnitureStock(chair, 0);
        warehouse.changeFurnitureStock(throne, 1);

        // print out catalogue
        System.out.println("\nPrint out catalogue");
        System.out.println(warehouse.getCatalogue().getAllFurnitureJSON());

        // false because can't build
        System.out.println("\nCan build one throne?");
        System.out.println(warehouse.build(throne, 1));

        // prints required materials required to craft 1 throne
        System.out.println("\nMaterials required for crafting 1 throne");
        System.out.println(warehouse.getMaterialsRequiredForFurniture(throne, 1));

        // add missing materials
        warehouse.changeMaterialStock(Material.IRON_ROD, 10f);
        warehouse.changeMaterialStock(Material.GLASS, 10f);

        // print out again, is empty list
        System.out.println("\nMaterials required after adding missing ones:");
        System.out.println(warehouse.getMaterialsRequiredForFurniture(throne, 1));

        // print out stocks of furniture
        System.out.println("\nCurrent stocks of furniture:");
        System.out.println(warehouse.getFurnitureStocksJSON(new FurnitureFilterBuilder().createFurnitureFilter()));

        // print max amount of thrones can build
        System.out.println("\nHow many thrones can build");
        System.out.println(warehouse.getMaxAmountPossibleToBuild(throne));

        // build 1 throne
        warehouse.build(throne, 1);

        // print out new stocks of furniture
        System.out.println("\nCurrent stocks of furniture after building 1 throne:");
        System.out.println(warehouse.getFurnitureStocksJSON(new FurnitureFilterBuilder().createFurnitureFilter()));

        // show filter
        System.out.println("\nPrint out only those in stock:");
        System.out.println(warehouse.getFurnitureStocksJSON(new FurnitureFilterBuilder().setInStock(true).createFurnitureFilter()));

        // print out current material stocks
        System.out.println("\nCurrent material stocks");
        System.out.println(warehouse.getMaterialStocksJSON());

        // add some new material
        warehouse.changeMaterialStock(Material.PLYWOOD, 2000f);

        // print out current material stocks
        System.out.println("\nCurrent material stocks with new material");
        System.out.println(warehouse.getMaterialStocksJSON());

        // print out specific material stocks
        System.out.println("\nPrint plywood stocks");
        System.out.println(warehouse.getMaterialStocksJSON(Material.PLYWOOD));

    }
}
