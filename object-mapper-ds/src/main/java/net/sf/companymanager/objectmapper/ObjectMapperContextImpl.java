package net.sf.companymanager.objectmapper;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.osgi.framework.BundleContext;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;

@Component
public class ObjectMapperContextImpl {

    private ModelMapper modelMapper;

    @Activate
    public final void activate(final BundleContext bundleContext, final Map<String, ?> properties) {
        modelMapper = new ModelMapper();
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);

    }

    public <S, D> TypeMap<S, D> addMappings(PropertyMap<S, D> propertyMap) {

        return modelMapper.addMappings(propertyMap);
    }
}
