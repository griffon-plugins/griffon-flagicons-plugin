/*
 * Copyright 2014-2015 the original author or authors.
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
package griffon.swing.support.flagicons;

import griffon.plugins.flagicons.Country;

import javax.annotation.Nonnull;
import javax.swing.ImageIcon;
import java.net.URL;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class FlagIcon extends ImageIcon {
    private Country country;

    public FlagIcon(@Nonnull Country country) {
        super(toURL(country));
        this.country = country;
    }

    public FlagIcon(@Nonnull String code) {
        this(Country.findByCode(code));
    }

    @Nonnull
    private static URL toURL(@Nonnull Country country) {
        requireNonNull(country, "Argument 'country' must not be null.");
        String resource = country.asResource();
        return Thread.currentThread().getContextClassLoader().getResource(resource);
    }

    @Nonnull
    public Country getCountry() {
        return country;
    }
}
