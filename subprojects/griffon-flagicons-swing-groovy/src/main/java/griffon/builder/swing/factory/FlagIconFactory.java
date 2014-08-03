/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.builder.swing.factory;

import griffon.plugins.flagicons.Country;
import griffon.swing.support.flagicons.FlagIcon;
import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

import java.util.Map;

/**
 * @author Andres Almiray
 */
public class FlagIconFactory extends AbstractFactory {
    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        Object country = attributes.remove("country");
        if (country == null) {
            country = value;
        }

        if (country == null) {
            throw new IllegalArgumentException("In " + name + " you must define a node value or country:");
        }
        if (country instanceof CharSequence) {
            return new FlagIcon(country.toString());
        } else if (country instanceof Country) {
            return new FlagIcon((Country) country);
        }

        throw new IllegalArgumentException(name + " cannot parse " + country + " as a FlagIcon description.");
    }
}
