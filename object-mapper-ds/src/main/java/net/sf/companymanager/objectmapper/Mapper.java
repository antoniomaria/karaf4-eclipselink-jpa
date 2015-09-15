package net.sf.companymanager.objectmapper;

public interface Mapper {
    <D> D map(Object source, Class<D> destinationType);
}
