package ee.taltech.iti0202.computershop.component;

public class PSU extends Component {
    public PSU(int powerConsumption, String name, String description, double price, int performacePoints) {
        super(powerConsumption, name, description, price, performacePoints);
        componentType = ComponentType.PSU;
    }
}
