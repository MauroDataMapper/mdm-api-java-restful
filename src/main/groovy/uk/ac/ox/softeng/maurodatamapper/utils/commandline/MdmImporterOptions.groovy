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
package uk.ac.ox.softeng.maurodatamapper.utils.commandline;

import picocli.CommandLine;

@CommandLine.Command
class MdmImporterOptions extends MdmConnectionOptions {

    @CommandLine.Option(
        names = ["-e", "--export"],
        description = ["Export Data Model to JSON file."]
    )
    String exportJsonFile

    @CommandLine.Option(
        names = ["-f", "--folder-id"],
        description = ["An ID specifying the folder that the model should be imported into."]
    )
    UUID folderId

}
