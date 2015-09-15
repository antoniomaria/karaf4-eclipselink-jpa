package net.sf.companymanager.objectmapper;

import org.modelmapper.ModelMapper;

public class Test {

    public static void main(final String args[]) {
        final ModelMapper mapper = new ModelMapper();

        mapper.addMappings(new MyPropertyMap().get());

        final SourceBean incoming = new SourceBean();

        incoming.setAge(35);
        incoming.setFirstName("Antonio Maria");
        incoming.setLastName("Sanchez");
        final TargetBean result = mapper.map(incoming, TargetBean.class);

        System.out.println("and the winner is: " + result);
    }
}
