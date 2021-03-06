/*
 * Copyright 2003-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package org.codehaus.groovy.transform.traitx

class Groovy7217Bug extends GroovyTestCase {
    void testNumberInitializationInTrait() {
        assertScript '''
            trait Version {
                Long version = 1
            }

            class HasVersion implements Version {}

            def v = new HasVersion()
            assert v.version == 1'''
    }

    void testAnyInitializerInTrait() {
        assertScript '''
            class SomeA {}
            trait DummyInit {
                SomeA a = init()
            }
            class Dummy implements DummyInit {
                def init() {
                    new SomeA()
                }
            }
            def d = new Dummy()
            assert d.a instanceof SomeA
        '''
    }
}
