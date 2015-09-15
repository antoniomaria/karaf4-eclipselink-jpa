package net.sf.companymanager.objectmapper;

import java.util.Map;

import org.osgi.framework.BundleContext;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(immediate = true)
public class MappingHello {

    private Mapper mapper;

    @Reference
    public void setMapper(final Mapper mapper) {
        this.mapper = mapper;
    }

    @Activate
    public final void activate(final BundleContext bundleContext, final Map<String, ?> properties) {
        final SourceBean incoming = new SourceBean();

        incoming.setAge(35);
        incoming.setFirstName("Antonio Maria");
        incoming.setLastName("Sanchez");
        final TargetBean result = mapper.map(incoming, TargetBean.class);

        System.out.println("and the winner is: " + result);

    }
}
