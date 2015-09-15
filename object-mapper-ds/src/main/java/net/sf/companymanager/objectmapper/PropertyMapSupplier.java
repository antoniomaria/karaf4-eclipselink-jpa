package net.sf.companymanager.objectmapper;

import org.modelmapper.PropertyMap;

// https://cleantestcode.wordpress.com/2014/05/04/bean-mapping-with-modelmapper/
public interface PropertyMapSupplier<S, D> {

    PropertyMap<S, D> get();

}
