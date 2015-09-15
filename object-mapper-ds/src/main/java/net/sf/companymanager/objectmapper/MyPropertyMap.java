package net.sf.companymanager.objectmapper;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import aQute.bnd.annotation.component.Component;

@Component
public class MyPropertyMap implements PropertyMapSupplier<SourceBean, TargetBean> {

    public MyPropertyMap() {
        System.out.println("MyPropertyMap created");
    }

    @Override
    public PropertyMap<SourceBean, TargetBean> get() {
        return new PropertyMap<SourceBean, TargetBean>() {

            Converter<SourceBean, String> concatWithSpace = new Converter<SourceBean, String>() {
                @Override
                public String convert(final MappingContext<SourceBean, String> context) {
                    final SourceBean src = context.getSource();
                    return src.getFirstName() + " " + src.getLastName();
                }
            };

            Converter<Integer, Boolean> over18 = new Converter<Integer, Boolean>() {
                @Override
                public Boolean convert(final MappingContext<Integer, Boolean> context) {
                    return context.getSource() >= 18;
                }
            };

            @Override
            protected void configure() {
                System.out.println("Mapping is called");
                using(over18).map(source.getAge()).setOldEnoughToVote(null);
                using(concatWithSpace).map(source).setFullName(null);
            }
        };
    }

}
