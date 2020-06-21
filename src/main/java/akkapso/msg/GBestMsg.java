package akkapso.msg;

/**
 * Created by ua28 on 6/21/20.
 */
public final class GBestMsg {
    final PsoValue value;

    public GBestMsg(PsoValue v) {
        value = v;
    }

    public PsoValue getValue() {
        return value;
    }
}
