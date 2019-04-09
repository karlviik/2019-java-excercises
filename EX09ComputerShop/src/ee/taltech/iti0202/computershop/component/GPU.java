package ee.taltech.iti0202.computershop.component;

public class GPU extends Component {
    public enum GPUConnector {
        PCIe
    }
    private GPUConnector connector;

    public GPU(
            int powerConsumption,
            String name,
            String description,
            double price,
            int performacePoints,
            GPUConnector connector
    ) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.GPU;
        this.connector = connector;
    }

    public GPUConnector getConnector() {
        return connector;
    }
}
