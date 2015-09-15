package net.sf.companymanager.objectmapper;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.osgi.framework.BundleContext;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;

@Component
public class ModelMapperContextImpl implements ModelMapperContext {

    private ModelMapper modelMapper;

    public ModelMapperContextImpl() {
        System.out.println("ModelMapperContextImpl instance created");
    }

    @Activate
    public final void activate(final BundleContext bundleContext, final Map<String, ?> properties) {
        modelMapper = new ModelMapper();
    }

    @Deactivate
    public final void deactivate() {
        modelMapper = null;
    }

    @Override
    public <D> D map(final Object source, final Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <S, D> TypeMap<S, D> addMappings(final PropertyMap<S, D> propertyMap) {
        return modelMapper.addMappings(propertyMap);
    }

}
