/*
 * Copyright 2020 University of Oxford
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
package uk.ac.ox.softeng.maurodatamapper.api.restful.client


import groovy.util.logging.Slf4j

@Slf4j
class TestClient {

    static void main(String[] args) throws Exception {
        MauroDataMapperClient client
        try {
            client = new BindingMauroDataMapperClient('http://localhost:8080', 'admin@maurodatamapper.com', 'password')
            // client = new MauroDataMapperClient('https://modelcatalogue.cs.ox.ac.uk/continuous-deployment', UUID.fromString('a50befd8-6c4c-40e5-aef4-daf0494a4848'))

            //def terminology = client.exportTerminology(UUID.fromString('bc6011c6-0f35-4c2b-9ddb-346db891776b'))
            //def dataModel = client.exportDataModel(UUID.fromString('2d58e58b-ac23-484f-902a-12912a2e5343'))
            //def dataModel = client.exportAndBindDataModelById(UUID.fromString('427d1243-4f89-46e8-8f8f-8424890b5083'))
            def dataModel = client.exportAndBindDataModelById(UUID.fromString('2d58e58b-ac23-484f-902a-12912a2e5343'))
            System.err.println(dataModel)



            //            client.openConnection('readerConnection', 'http://localhost:8080', 'reader@mdm.com', 'password')
            //            client.openConnection('editorConnection', 'http://localhost:8080', 'ecitor@mdm.com', 'password')
            //            client.openConnection('nhsd', 'http://nhsd/mdm', 'reader@nhs.uk', 'password')
            //
            //
            //            MauroDataMapperConnection nhsdConnection = client.getConnection('nhsd')
            //            MauroDataMapperConnection connection = client.getConnection(MauroDataMapperClient.DEFAULT_CONNECTION_NAME)
            //
            //            nhsdConnection.currentCookie != connection.currentCookie
            //
            //            connection.GET('admin/status')
            //
            //            client.createFolder('new folder', null) // works as admin user
            //            client.createFolder('new folder', null, 'editorConnection') // works as editor user
            //            client.createFolder('new folder', null, 'readerConnection') // does not work
            //
            //            client.copyDataModelToTarget('asdifgwieytr3iyu2', MauroDataMapperClient.DEFAULT_CONNECTION_NAME, '832749823y9rg329',
            //            false, 'nhsd')


        } catch (Exception exception) {
            if (client) {
                client.close()
            }
            log.error('Failed', exception)
            System.exit(1)
        }
        if (client) {
            client.close()
        }
        System.exit(0)
    }
}
