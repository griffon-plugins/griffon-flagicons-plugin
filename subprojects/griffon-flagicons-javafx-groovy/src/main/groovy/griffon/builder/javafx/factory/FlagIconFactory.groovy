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
package griffon.builder.javafx.factory

import griffon.javafx.support.flagicons.FlagIcon
import griffon.plugins.flagicons.Country
import groovyx.javafx.factory.LabeledFactory

/**
 * @author Andres Almiray
 */
public class FlagIconFactory extends LabeledFactory {
    FlagIconFactory() {
        super(FlagIcon)
    }

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        def country = attributes.remove('country') ?: value
        if (country instanceof CharSequence) country = country.toString()

        if (country instanceof Country || country instanceof String) {
            return new FlagIcon(country)
        }

        throw new IllegalArgumentException("In $name you must define a node value or icon:")
    }
}
