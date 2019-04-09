package ee.taltech.iti0202.computershop.component;

public class Storage extends Component {
    public enum StorageConnector {
        SATA,
        mSATA,
        SATAe,
        PCIE,
        M2
    }
    private StorageConnector connector;

    public Storage(
        int powerConsumption,
        String name,
        String description,
        double price,
        int performacePoints,
        StorageConnector connector
    ) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.Storage;
        this.connector = connector;
    }

    public StorageConnector getConnector() {
        return connector;
    }
}
