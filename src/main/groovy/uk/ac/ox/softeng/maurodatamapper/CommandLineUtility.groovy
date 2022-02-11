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
package uk.ac.ox.softeng.maurodatamapper

import uk.ac.ox.softeng.maurodatamapper.utils.commandline.CommandLineHelper
import uk.ac.ox.softeng.maurodatamapper.utils.commandline.MdmConnectionOptions

class CommandLineUtility {

    static void main(String[] args) {
        String[] tempArgs = ['-h', "-v", "-U=http://localhost:8080"/*, "--properties=...",
                             "--properties=..."*/]

        MdmConnectionOptions options = new MdmConnectionOptions()

        CommandLineHelper commandLineHelper = new CommandLineHelper(options)
        commandLineHelper.parseArgs(tempArgs)

        println 'options = ' + options
        println 'options.clientBaseUrl = ' + options.clientBaseUrl
        println 'options.verbose = ' + options.verbose

        System.err.println(options.clientBaseUrl)
        System.err.println(options.clientPassword)
    }


}