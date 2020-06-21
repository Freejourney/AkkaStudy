package akkapso.msg;

/**
 * Created by ua28 on 6/21/20.
 */
public final class PBestMsg {

    final PsoValue value;

    public PBestMsg(PsoValue value) {
        this.value = value;
    }

    public PsoValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
