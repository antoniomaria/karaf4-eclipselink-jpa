/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
/*
 * ; * Copyright 2012 JAXIO http://www.jaxio.com Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package net.sf.companymanager.qbe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Used to construct OR predicate for a property value. In other words you can
 * search all entities E having a given
 * property set to one of the selected values.
 */
public class PropertySelector<E, F> {

    /**
     * {@link PropertySelector} builder
     */
    static public <E, F> PropertySelector<E, F> newPropertySelector(Attribute<?, ?>... fields) {
        return new PropertySelector<E, F>(fields);
    }

    private static final long serialVersionUID = 1L;
    private final List<Attribute<?, ?>> attributes;
    private List<F> selected = new ArrayList();
    private SearchMode searchMode; // for string property only.

    public PropertySelector(Attribute<?, ?>... attributes) {
        this.attributes = new ArrayList();
        for (Attribute<?, ?> attribute : attributes) {
            this.attributes.add(attribute);
        }
        // TODO replace by guava
        List<Attribute<?, ?>> attributesDump = new ArrayList<Attribute<?,?>>();
        for (Attribute<?, ?> attribute : attributes) {
            attributesDump.add(attribute);
        }
        verifyPath(attributesDump);
    }

    private void verifyPath(List<Attribute<?, ?>> attributes) {
        Class<?> from = attributes.get(0).getJavaType();
        attributes.remove(0);
        for (Attribute<?, ?> attribute : attributes) {
            if (!attribute.getDeclaringType().getJavaType().isAssignableFrom(from)) {
                throw new IllegalStateException("Wrong path.");
            }
            from = attribute.getJavaType();
        }
    }

    public List<Attribute<?, ?>> getAttributes() {
        return attributes;
    }

    /**
     * Get the possible candidates for property.
     */
    public List<F> getSelected() {
        return selected;
    }

    @SuppressWarnings("unchecked")
    public void setSelected(F selected) {
        this.selected = new ArrayList();
        this.selected.add(selected);
    }

    /**
     * Set the possible candidates for property.
     */
    public void setSelected(List<F> selected) {
        this.selected = selected;
    }

    public boolean isNotEmpty() {
        return (selected != null) && !selected.isEmpty();
    }

    public void clearSelected() {
        if (selected != null) {
            selected.clear();
        }
    }

    public boolean isBoolean() {
        return attributes.get(attributes.size() - 1).getJavaType().isAssignableFrom(Boolean.class);
    }

    public SearchMode getSearchMode() {
        return searchMode;
    }

    /**
     * In case, the field's type is a String, you can set a searchMode to use.
     * It is null by default.
     */
    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public void add(F object) {
        this.selected.add(object);
    }

    public String getPath() {
        return JpaUtil.getPath(getAttributes());
    }
}
