/*
 * Copyright 2020-2024 University of Oxford and NHS England
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
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package uk.ac.ox.softeng.maurodatamapper

import groovy.util.logging.Slf4j
import uk.ac.ox.softeng.maurodatamapper.api.restful.client.BindingMauroDataMapperClient
import uk.ac.ox.softeng.maurodatamapper.terminology.Terminology

@Slf4j
class TestConnection {

    static void main(String[] args) {
        try {
            //client = new BindingMauroDataMapperClient('http://localhost:8080', 'admin@maurodatamapper.com', 'password')
            new BindingMauroDataMapperClient('http://localhost:8080', UUID.fromString("258d9734-8839-4206-acb1-d049aa9dd409"))
                .withCloseable {client ->
                    UUID folderId = client.findFolderByName("Development Folder")

                    Terminology terminology = new Terminology(label: "My terminology 3")

                    UUID id = client.importTerminology(terminology, folderId, terminology.label, false, false)
                    System.err.println(id)
            }

        } catch(Exception e) {
            e.printStackTrace()
        }
    }

}
