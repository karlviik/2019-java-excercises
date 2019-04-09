package ee.taltech.iti0202.computershop.component;

public class MoBo extends Component {
    private CPU.CPUConnector cpuConnector;
    private RAM.RAMType ramType;
    private GPU.GPUConnector gpuConnector;
    private Storage.StorageConnector storageConnector;

    public MoBo(
            int powerConsumption,
            String name,
            String description,
            double price,
            int performacePoints,
            CPU.CPUConnector cpuConnector,
            RAM.RAMType ramType,
            GPU.GPUConnector gpuConnector,
            Storage.StorageConnector storageConnector
    ) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.MoBo;
        this.cpuConnector = cpuConnector;
        this.ramType = ramType;
        this.gpuConnector = gpuConnector;
        this.storageConnector = storageConnector;
    }

    public CPU.CPUConnector getCpuConnector() {
        return cpuConnector;
    }

    public RAM.RAMType getRamType() {
        return ramType;
    }

    public GPU.GPUConnector getGpuConnector() {
        return gpuConnector;
    }

    public Storage.StorageConnector getStorageConnector() {
        return storageConnector;
    }
}
