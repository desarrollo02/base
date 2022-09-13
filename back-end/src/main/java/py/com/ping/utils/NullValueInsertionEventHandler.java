package py.com.ping.utils;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;

/**
 * @author Jose Noguera
 * created on 7/9/21
 * inside the package - py.com.ping.utils
 */
public class NullValueInsertionEventHandler implements ReferenceInsertionEventHandler {

    @Override
    public Object referenceInsert(String string, Object object) {
        if (object == null || object == "null") {
            return "";
        }
        return object;
    }
}
