                               Autopsy 3.0 (Beta)
                          http://www.sleuthkit.org/

                                June 4, 2012


OVERVIEW

Autopsy is a graphical interface to The Sleuth Kit and other open source digital forensics tools. 
Autopsy 3 is a complete rewrite from Autopsy 2 and it is now Java-based.  This version is currently in a beta state.   

The current beta version works only on Windows.  We have run it on XP, Vista, and Windows 7 with no problems. 

Autopsy 3.0 is released under the Apache 2.0 license. 


INSTALLATION

All Autopsy dependencies are bundled with the installer provided.
There is no need for manual installation of additional dependencies if the installer is used.

Refer to the next section for additional info on third-party software requirements to run Autopsy without installer.

Refer to the KNOWN_ISSUES.txt file for known bugs that could cause investigation problems. 


SUPPORT

There is a built-in help system in Autopsy once you get it started.  There is also a QuickStart Guide that came
with the installer. 

Send any bug reports or feature requests to the sleuthkit-users e-mail list.
    http://www.sleuthkit.org/support.php


LICENSE

The Autopsy code is released under the Apache License, Version 2.  See LICENSE-2.0.txt for details.


EMBEDDED SOFTWARE

This section lists the software components and libraries that are used inside of
Autopsy.   These tools are bundled with the installer, unless specified otherwise.

JRE (Java Runtime Environment) 1.6, 32 bit
- Web page: http://www.oracle.com/technetwork/java/index.html
- License: http://www.oracle.com/technetwork/java/javase/terms/license/index.html

Netbeans 7.0.1 RCP platform and .jar files bundled with the platform
- Web page: http://netbeans.org/features/platform/
- License: 
http://services.netbeans.org/downloads/licence/nb-7.0-final-2011-04-20-license.txt

Sleuth Kit for analyzing disk images.
- Web page: http://www.sleuthkit.org/sleuthkit/
- License: http://sleuthkit.org/sleuthkit/licenses.php

Libewf for opening E01 files
- Web page: http://sourceforge.net/projects/libewf/
- License: http://www.gnu.org/licenses/lgpl.html

zlib for opening E01 files
- Web page: http://zlib.net/
- License: http://zlib.net/zlib_license.html

Solr (including Lucene and TIKA) for keyword search
- Web page: http://projects.apache.org/projects/solr.html
- License: http://www.apache.org/licenses/LICENSE-2.0

GStreamer for viewing video files
- Web page: http://gstreamer.freedesktop.org/
- License: http://www.gnu.org/licenses/lgpl.html

GStreamer-java for viewing video files
- Web page: http://code.google.com/p/gstreamer-java/
- License: http://www.gnu.org/licenses/lgpl.html

Regripper for pulling recently activity
(Including custom plugins)
- Web page: http://regripper.wordpress.com/
- License: http://www.gnu.org/licenses/gpl.html

Pasco2 for pulling Internet Explorer activity
- Web page: http://sourceforge.net/projects/pasco2/
- License: http://www.gnu.org/licenses/gpl.html

Advanced installer 9.0 (Freeware)
(not embedded in Autopsy, but used to generate Autopsy installer.)
- Web page: http://www.advancedinstaller.com/

