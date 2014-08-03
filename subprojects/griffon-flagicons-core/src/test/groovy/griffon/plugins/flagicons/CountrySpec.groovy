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
package griffon.plugins.flagicons

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Andres Almiray
 */
class CountrySpec extends Specification {
    @Unroll("Code #code is related to country #name")
    def "Country code and name are found"() {
        when:
        Country actual = Country.findByCode(code)

        then:
        actual == expected
        actual.name == name
        actual.code == code
        actual.toString() == "$name [$code]"

        where:
        [code, name, expected] << countryLoader()
    }

    @Unroll("Code '#code' is invalid")
    def "Invalid country codes"() {
        when:
        Country.findByCode(code)

        then:
        thrown(IllegalArgumentException)

        where:
        code  | _
        null  | _
        ''    | _
        ' '   | _
        'zz'  | _
        'foo' | _
    }

    private static Iterable countryLoader() {
        String resource = 'griffon/plugins/flagicons/countrynames.properties'
        InputStream is = Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
        Properties props = new Properties()
        props.load(is)
        props.collect([]) { entry ->
            String code = entry.key.toUpperCase()
            [code, entry.value, Country."${code}"]
        }
    }
}
