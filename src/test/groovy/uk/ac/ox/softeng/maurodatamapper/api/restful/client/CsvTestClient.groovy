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

import uk.ac.ox.softeng.maurodatamapper.api.restful.render.json.JsonViewRenderer
import uk.ac.ox.softeng.maurodatamapper.core.authority.AuthorityService
import uk.ac.ox.softeng.maurodatamapper.core.container.ClassifierService
import uk.ac.ox.softeng.maurodatamapper.core.provider.importer.parameter.FileParameter
import uk.ac.ox.softeng.maurodatamapper.datamodel.DataModel
import uk.ac.ox.softeng.maurodatamapper.datamodel.DataModelService
import uk.ac.ox.softeng.maurodatamapper.datamodel.DataModelType
import uk.ac.ox.softeng.maurodatamapper.datamodel.item.DataClassService
import uk.ac.ox.softeng.maurodatamapper.datamodel.item.DataElementService
import uk.ac.ox.softeng.maurodatamapper.datamodel.item.datatype.DataTypeService
import uk.ac.ox.softeng.maurodatamapper.datamodel.item.datatype.EnumerationTypeService
import uk.ac.ox.softeng.maurodatamapper.datamodel.item.datatype.enumeration.EnumerationValueService
import uk.ac.ox.softeng.maurodatamapper.datamodel.provider.exporter.DataModelJsonExporterService
import uk.ac.ox.softeng.maurodatamapper.plugins.csv.datamodel.provider.importer.parameter.CsvDataModelImporterProviderServiceParameters
import uk.ac.ox.softeng.maurodatamapper.util.Utils
import uk.ac.ox.softeng.maurodatamapper.plugins.csv.datamodel.provider.importer.CsvDataModelImporterProviderService

import groovy.util.logging.Slf4j

@Slf4j
class CsvTestClient {

    static void main(String[] args) throws Exception {
        log.debug('Starting TestClient...')
        MauroDataMapperClient client
        try {
            //            client = new BindingMauroDataMapperClient('http://localhost:8080', 'admin@maurodatamapper.com', 'password')
            BindingMauroDataMapperClient secondaryClient = new BindingMauroDataMapperClient('secondary', 'http://secondary.localhost:8090', 'admin@maurodatamapper.com',
                                                                                            'password')

            AuthorityService authorityService = new AuthorityService()
            DataTypeService dataTypeService = new DataTypeService(enumerationTypeService: new EnumerationTypeService(enumerationValueService: new EnumerationValueService()))
            DataModelService dataModelService = new DataModelService(dataTypeService: dataTypeService,
                                                                     dataClassService: new DataClassService(dataElementService: new DataElementService()),
                                                                     authorityService: authorityService)

            CsvDataModelImporterProviderService csvDataModelImporterProviderService = new CsvDataModelImporterProviderService(authorityService: authorityService,
                                                                                                                              dataTypeService: dataTypeService,
                                                                                                                              dataModelService: dataModelService,
                                                                                                                              classifierService: new ClassifierService())

            File csvFile = new File('/Users/josephcrawford/data/recovery-metadata/target/0041/6_standardised/INT05_SGSS/INT05_SGSS_0041.csv')
            byte[] csvBytes = csvFile.bytes

            CsvDataModelImporterProviderServiceParameters parameters = new CsvDataModelImporterProviderServiceParameters(
                importFile: new FileParameter(
                    fileName: 'temporary',
                    fileType: 'text/csv',
                    fileContents: csvBytes
                ),
                modelName: 'API Client CSV Import Test'
            )

            DataModel csvDataModel = csvDataModelImporterProviderService.importDomain(secondaryClient.getConnection('secondary').clientUser, parameters)
            csvDataModel.type = DataModelType.DATA_ASSET

            DataModelJsonExporterService dataModelJsonExporterService = new DataModelJsonExporterService(templateEngine: JsonViewRenderer.instance.templateEngine)

            String dataModelExportModelJson = dataModelJsonExporterService.exportDataModel(secondaryClient.getConnection('secondary').clientUser, csvDataModel, [:])

            log.debug('CSV -> JSON: {}', dataModelExportModelJson)

            secondaryClient.importDataModel(csvDataModel, Utils.toUuid('f2e12290-3dd9-4a4e-b619-98dd70291fe6'), 'Client CSV import', false, false, 'secondary')

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
