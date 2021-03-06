 /*
 *
 * Autopsy Forensic Browser
 * 
 * Copyright 2012 42six Solutions.
 * Contact: aebadirad <at> 42six <dot> com
 * Project Contact/Architect: carrier <at> sleuthkit <dot> org
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
package org.sleuthkit.autopsy.report;

//interface every reporting module should implement
public interface ReportModule {

    /**
     * Generates a report on the current case Reporting module should traverse
     * the blackboard, extract needed information as specified in the config and
     * generate a report file
     *
     * @param config specifiying parts that should be generated
     * @return absolute file path to the report generated
     * @throws ReportModuleException if report generation failed
     */
    public String generateReport(ReportConfiguration config) throws ReportModuleException;

    /**
     * This saves a copy of the report (current one) to another place specified
     * by the user. Takes the input of where the path needs to be saved, include
     * filename and extention.
     */
    public void save(String Path) throws ReportModuleException;

    /**
     * Returns a short description of report type/file format this module
     * generates for instance, "XML", "Excel"
     *
     * @return
     */
    public String getReportType();

    /**
     * Returns a basic string name for the report. What is 'officially' titled.
     *
     * @return
     */
    public String getName();

    /**
     * Returns the reportconfiguration object that was created
     *
     * @return
     */
    public ReportConfiguration GetReportConfiguration();

    /**
     * Returns a one line human readable description of the type of report this
     * module generates
     */
    public String getReportTypeDescription();

    /**
     * Calls to the report module to execute a method to display the report that
     * was generated.
     *
     * @param String the path to the file
     *
     */
    public void getPreview(String path);
    
        /**
     * Calls to the report module to execute a method to get the extension
     * that is used for the report
     *
     * @return String the extension the file will be saved as
     *
     */
    public String getExtension();
}