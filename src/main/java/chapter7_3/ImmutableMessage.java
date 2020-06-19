package chapter7_3;

import java.util.List;

/**
 * Created by ua28 on 6/19/20.
 */
public final class ImmutableMessage {

    private final int sequenceNumber;

    private final List<String> values;


    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = values;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}
