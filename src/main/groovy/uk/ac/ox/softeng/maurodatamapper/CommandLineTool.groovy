package uk.ac.ox.softeng.maurodatamapper

import uk.ac.ox.softeng.maurodatamapper.utils.commandline.MdmCommandLineTool
import uk.ac.ox.softeng.maurodatamapper.utils.commandline.MdmConnectionOptions

class CommandLineTool extends MdmCommandLineTool<MdmConnectionOptions> {
    static void main(String[] args) {
        CommandLineTool commandLineTool = new CommandLineTool(args)
        commandLineTool.run()
    }

    CommandLineTool(String[] args) {
        super(args, MdmConnectionOptions)
    }

    void run() {
        println 'CommandLineTool::run'
        println 'options.clientBaseUrl = ' + options.clientBaseUrl
    }
}
