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
package org.sleuthkit.autopsy.recentactivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.lang.NullArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.datamodel.FsContent;
import org.sleuthkit.datamodel.SleuthkitCase;

/**
 *
 * @author Alex
 */
public class Util {

    private static Logger logger = Logger.getLogger(Util.class.getName());

    private Util() {
    }

    public static boolean pathexists(String path) {
        File file = new File(path);
        boolean exists = file.exists();
        return exists;
    }

    public static String utcConvert(String utc) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        String tempconvert = formatter.format(new Date(Long.parseLong(utc)));
        return tempconvert;
    }

    public static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /*
             * Instead of using default, pass in a decoder.
             */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    public static boolean imgpathexists(String path) {
        Case currentCase = Case.getCurrentCase(); // get the most updated case
        SleuthkitCase tempDb = currentCase.getSleuthkitCase();
        Boolean rt = false;
        int count = 0;
        try {
            List<FsContent> FFSqlitedb;
            ResultSet rs = tempDb.runQuery("select * from tsk_files where parent_path LIKE '%" + path + "%'");
            FFSqlitedb = tempDb.resultSetToFsContents(rs);
            count = FFSqlitedb.size();
            final Statement s = rs.getStatement();
            rs.close();
            if (s != null) {
                s.close();
            }
            if (count > 0) {
                rt = true;
            } else {
                rt = false;
            }
        } catch (SQLException ex) {
            //logger.log(Level.WARNING, "Error while trying to contact SQLite db.", ex);
        }
        return rt;
    }

    public static String getBaseDomain(String url) {
    String host = url;

    int startIndex = 0;
    int nextIndex = host.indexOf('.');
    int lastIndex = host.lastIndexOf('.');
    while (nextIndex < lastIndex) {
        startIndex = nextIndex + 1;
        nextIndex = host.indexOf('.', startIndex);
    }
    if (startIndex > 0) {
        return host.substring(startIndex);
    } else {
        return host;
    }
}
    
    
    public static String extractDomain(String value) {
        if (value == null) {
            throw new java.lang.NullPointerException("domains to extract");
        }
        String result = "";
        // String domainPattern = "(\\w+)\\.(AC|AD|AE|AERO|AF|AG|AI|AL|AM|AN|AO|AQ|AR|ARPA|AS|ASIA|AT|AU|AW|AX|AZ|BA|BB|BD|BE|BF|BG|BH|BI|BIZ|BJ|BM|BN|BO|BR|BS|BT|BV|BW|BY|BZ|CA|CAT|CC|CD|CF|CG|CH|CI|CK|CL|CM|CN|CO|COM|COOP|CR|CU|CV|CW|CX|CY|CZ|DE|DJ|DK|DM|DO|DZ|EC|EDU|EE|EG|ER|ES|ET|EU|FI|FJ|FK|FM|FO|FR|GA|GB|GD|GE|GF|GG|GH|GI|GL|GM|GN|GOV|GP|GQ|GR|GS|GT|GU|GW|GY|HK|HM|HN|HR|HT|HU|ID|IE|IL|IM|IN|INFO|INT|IO|IQ|IR|IS|IT|JE|JM|JO|JOBS|JP|KE|KG|KH|KI|KM|KN|KP|KR|KW|KY|KZ|LA|LB|LC|LI|LK|LR|LS|LT|LU|LV|LY|MA|MC|MD|ME|MG|MH|MIL|MK|ML|MM|MN|MO|MOBI|MP|MQ|MR|MS|MT|MU|MUSEUM|MV|MW|MX|MY|MZ|NA|NAME|NC|NE|NET|NF|NG|NI|NL|NO|NP|NR|NU|NZ|OM|ORG|PA|PE|PF|PG|PH|PK|PL|PM|PN|PR|PRO|PS|PT|PW|PY|QA|RE|RO|RS|RU|RW|SA|SB|SC|SD|SE|SG|SH|SI|SJ|SK|SL|SM|SN|SO|SR|ST|SU|SV|SX|SY|SZ|TC|TD|TEL|TF|TG|TH|TJ|TK|TL|TM|TN|TO|TP|TR|TRAVEL|TT|TV|TW|TZ|UA|UG|UK|US|UY|UZ|VA|VC|VE|VG|VI|VN|VU|WF|WS|XXX|YE|YT|ZA|ZM|ZW(co\\.[a-z].))";
        //  Pattern p = Pattern.compile(domainPattern,Pattern.CASE_INSENSITIVE);
        //  Matcher m = p.matcher(value);
        //  while (m.find()) {
        //  result = value.substring(m.start(0),m.end(0));
        //  }
        try {
            URL url = new URL(value);
            result = url.getHost();
        } catch (Exception e) 
        {
             logger.log(Level.WARNING, "Error while trying to convert url to domain. " + value, e);
        }

        return result;
    }

    public static String getFileName(String value) {
        String filename = "";
        String filematch = "^([a-zA-Z]\\:)(\\\\[^\\\\/:*?<>\"|]*(?<!\\[ \\]))*(\\.[a-zA-Z]{2,6})$";

        Pattern p = Pattern.compile(filematch, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.COMMENTS);
        Matcher m = p.matcher(value);
        if (m.find()) {
            filename = m.group(1);

        }
        int lastPos = value.lastIndexOf('\\');
        filename = (lastPos < 0) ? value : value.substring(lastPos + 1);
        return filename.toString();
    }

    public static String getPath(String txt) {
        String path = "";

        //String drive ="([a-z]:\\\\(?:[-\\w\\.\\d]+\\\\)*(?:[-\\w\\.\\d]+)?)";	// Windows drive
        String drive = "([a-z]:\\\\\\S.+)";
        Pattern p = Pattern.compile(drive, Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);
        Matcher m = p.matcher(txt);
        if (m.find()) {
            path = m.group(1);

        } else {

            String network = "(\\\\(?:\\\\[^:\\s?*\"<>|]+)+)";	// Windows network

            Pattern p2 = Pattern.compile(network, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m2 = p2.matcher(txt);
            if (m2.find()) {
                path = m2.group(1);
            }
        }
        return path;
    }

    public static long findID(String path) {
        String parent_path = path.replace('\\', '/'); // fix Chrome paths
        if (parent_path.length() > 2 && parent_path.charAt(1) == ':') {
            parent_path = parent_path.substring(2); // remove drive letter (e.g., 'C:')
        }
        int index = parent_path.lastIndexOf('/');
        String name = parent_path.substring(++index);
        parent_path = parent_path.substring(0, index);
        String query = "select * from tsk_files where parent_path like \"" + parent_path + "\" AND name like \"" + name + "\"";
        Case currentCase = Case.getCurrentCase();
        SleuthkitCase tempDb = currentCase.getSleuthkitCase();
        try {
            ResultSet rs = tempDb.runQuery(query);
            List<FsContent> results = tempDb.resultSetToFsContents(rs);
            Statement s = rs.getStatement();
            rs.close();
            if (s != null) {
                s.close();
            }
            if (results.size() > 0) {
                return results.get(0).getId();
            }
        } catch (Exception ex) {
            //    logger.log(Level.WARNING, "Error retrieving content from DB", ex);
        }
        return -1;
    }

    public static boolean checkColumn(String column, String tablename, String connection) {
        String query = "PRAGMA table_info(" + tablename + ")";
        boolean found = false;
        ResultSet temprs;
        try {
            dbconnect tempdbconnect = new dbconnect("org.sqlite.JDBC", "jdbc:sqlite:" + connection);
            temprs = tempdbconnect.executeQry(query);
            while (temprs.next()) {
                if (temprs.getString("name") == null ? column == null : temprs.getString("name").equals(column)) {
                    found = true;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while trying to get columns from sqlite db." + connection, ex);
        }
        return found;
    }

    public static ResultSet runQuery(String query, String connection) {
        ResultSet results = null;
        try {
            dbconnect tempdbconnect = new dbconnect("org.sqlite.JDBC", "jdbc:sqlite:" + connection);
            results = tempdbconnect.executeQry(query);
            tempdbconnect.closeConnection();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while trying to get columns from sqlite db." + connection, ex);
        }
        return results;
    }
}