package tags;

import model.Device;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;

/**
 * Created by Freemind on 2016-10-30.
 */
public class Catalog extends TagSupport {
    private Collection<Device> devicesInCatalog;

    public void setDevicesInCatalog(Collection<Device> devicesInCatalog) {
        this.devicesInCatalog = devicesInCatalog;
    }


}
