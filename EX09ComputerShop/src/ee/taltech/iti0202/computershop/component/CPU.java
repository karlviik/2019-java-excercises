package ee.taltech.iti0202.computershop.component;

public class CPU extends Component {

    public enum CPUConnector {
        LGA2066,
        TR4,
        SP3,
        AM4,
        LGA3647,
        LGA1151,
        AM1,
        FM2,
        LGA1150,
        LGA1356
    }
    private CPUConnector connector;

    public CPU(
            int powerConsumption,
            String name,
            String description,
            double price,
            int performacePoints,
            CPUConnector connector
    ) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.CPU;
        this.connector = connector;
    }

    public CPUConnector getConnector() {
        return connector;
    }
}
