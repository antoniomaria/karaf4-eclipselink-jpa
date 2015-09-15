package net.sf.companymanager.objectmapper;

import java.util.Map;

import org.modelmapper.PropertyMap;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.google.common.base.Supplier;

@Component(immediate = true)
public class MapperImpl implements Mapper {

    public MapperImpl() {
        System.out.println("MapperImpl instance created");
    }

    private ModelMapperContext modelMapperContext;

    @Reference
    public void setModelMapperContext(final ModelMapperContext modelMapperContext) {
        this.modelMapperContext = modelMapperContext;
    }

    @Reference(dynamic = true, multiple = true, optional = false)
    public final void addMappings(final PropertyMapSupplier<?, ?> supplier, final Map<String, ?> properties) {

        final PropertyMap<?, ?> propertyMap = supplier.get();

        System.out.println("Adding : " + propertyMap + " mapping");
        modelMapperContext.addMappings(propertyMap);
    }

    public final void removeMappings(final Supplier<PropertyMap<?, ?>> supplier, final Map<String, ?> properties) {
        System.out.println("removing mapping");
    }

    @Override
    public <D> D map(final Object source, final Class<D> destinationType) {
        return modelMapperContext.map(source, destinationType);
    }

}
