
import java.util.ArrayList;
import java.util.List;

public class Producers {

    private List<Producer> producerList;

    public Producers() {
        this.producerList = new ArrayList<>();
    }

    public void addProducer(Producer producer) {
        producerList.add(producer);
    }

    public Producer getProducer(String username) {
        for (Producer producer : producerList) {
            if (producer.getUserName().equalsIgnoreCase(username)) {
                return producer;
            }
        }
        return null;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

}
