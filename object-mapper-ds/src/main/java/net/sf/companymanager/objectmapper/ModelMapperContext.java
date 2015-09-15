package net.sf.companymanager.objectmapper;

import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

public interface ModelMapperContext {

    <D> D map(Object source, Class<D> destinationType);

    <S, D> TypeMap<S, D> addMappings(final PropertyMap<S, D> propertyMap);
}
