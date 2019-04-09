package ee.taltech.iti0202.computershop;

import ee.taltech.iti0202.computershop.component.*;
import ee.taltech.iti0202.computershop.computershop.ComputerShop;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Component mobo1 = new MoBo(50, "mobo1", "bestmoboever", 100, 50, CPU.CPUConnector.LGA1150, RAM.RAMType.DDR4, GPU.GPUConnector.PCIe, Storage.StorageConnector.M2);
        Component mobo2 = new MoBo(60, "mobo2", "midmobo", 50, 40, CPU.CPUConnector.LGA1151, RAM.RAMType.DDR3, GPU.GPUConnector.PCIe, Storage.StorageConnector.SATA);
        Component mobo3 = new MoBo(1000, "mobo3", "alsobestmobo but free and super performance", 0, 99999, CPU.CPUConnector.LGA1150, RAM.RAMType.DDR4, GPU.GPUConnector.PCIe, Storage.StorageConnector.M2);

        Component psu1 = new PSU(-700, "psu1", "decent psu", 40.33, 100);
        Component psu2 = new PSU(-1500, "psu2", "op psu", 300, 300);

        Component gpu1 = new GPU(200, "gpu1", "gtx 3080TI with 2000 GB vram", 998, 1001, GPU.GPUConnector.PCIe);
        Component gpu3 = new GPU(200, "gpu1", "gtx 3080TI with 2000 GB vram", 999, 1000, GPU.GPUConnector.PCIe);
        Component gpu2 = new GPU(160, "gpu2", "gtx 970", 250, 250, GPU.GPUConnector.PCIe);

        Component storage1 = new Storage(20, "storage1", "ssd", 100, 100, Storage.StorageConnector.SATA);
        Component storage2 = new Storage(30, "storage2", "m2", 100, 200, Storage.StorageConnector.M2);

        Component ram1 = new RAM(4, "ram1", "now with 5x more leds", 200, 200, RAM.RAMType.DDR4);
        Component ram2 = new RAM(4, "ram2", "now with 50x more leds", 300, 300, RAM.RAMType.DDR4);
        Component ram3 = new RAM(4, "ram3", "snail", 100, 50, RAM.RAMType.DDR3);

        Component cpu1 = new CPU(70, "cpu1", "not ayymd", 200, 200, CPU.CPUConnector.LGA1150);
        Component cpu2 = new CPU(71, "cpu2", "not ayymd", 100, 100, CPU.CPUConnector.LGA1151);

        ComputerShop shop = new ComputerShop("shop");
        {
            List.of(mobo1, mobo2, mobo3, gpu3, psu1, psu2, gpu1, gpu2, storage1, storage2, ram1, ram2, ram3, cpu1, cpu2).forEach(shop::addComponent);
        }
        shop.buildComputer(10, ComputerShop.UseCase.GAMING);
        System.out.println();
        shop.buildComputer(949, ComputerShop.UseCase.GAMING);
        System.out.println();
        shop.buildComputer(1500, ComputerShop.UseCase.WORKSTATION);

        shop.removeComponent(storage2);

        System.out.println();
        shop.buildComputer(949, ComputerShop.UseCase.GAMING);
        System.out.println();
        shop.buildComputer(1500, ComputerShop.UseCase.WORKSTATION);
    }
}
