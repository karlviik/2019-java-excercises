package ee.taltech.iti0202.computershop.component;

public class RAM extends Component {
    public enum RAMType {
        DDR,
        DDR2,
        DDR3,
        DDR4
    }

    private RAMType type;

    public RAM(int powerConsumption, String name, String description, double price, int performacePoints, RAMType type) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.RAM;
        this.type = type;
    }

    public RAMType getType() {
        return type;
    }
}
