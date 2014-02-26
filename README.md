# 0. Introduction

This file describes small tools designed and used for the SMART
Infrastructure Dashboard (SID) project funded by the Australian
National Data Services (ANDS).

The SID project needs to extract information from MS Excel
files. These files are collected from the collaborators. Most of these
files are in MS Excel 2003 or previous version format.

These files include: - Australian Bureau of Statistics Community Basic
Profiles - Australian Bureau of Meteorology Weather Records -
Infrastructure utility consumptions, assets, and technical reports

This project creates small tools for the SID project use only,
although these tools can be further developed for general-purpose
usage. Hence, these tools are designed for completing the on-demand
SID data processing tasks as simple as possible. It is NOT developed
following the design and development rules of a productive
software. Modification may be needed for other usage.

Due to no guarantee for any data demage, please make a copy of your
data before using them.

# 1. Directory Tree Structure

The *dashboard* folder contains source codes of small tools for
extracting data from the Australian Bureau of Statistics (ABS) census
statistics files.

    +-- dashboard
           |-- ExcelCellIndexReader.java
           |-- ExcelReader.java
           |-- ExcelWriter.java
           |-- SMARTFileReader.java
           |-- SMARTFileWriter.java
           |-- TestExcelReader.java
           +-- TestExcelWriter.java

NOTE: The class "TestExcelReader.java" contains the main method. You
need to modify it to fit your work requirements. For example, you can
change the default output file name and location.

The *sample* folder contains a packed executable file and some testing
files.

    +-- sample
           |-- Region_107011130.xls	// sample region profile from ABS
           |-- Region_107011131.xls	// sample region profile from ABS
           |-- abs-region-Table4.csv	// output of the "sid-tool-2.jar"
           |-- filelist.txt		// file list of region profiles
           |-- sheet-cells.txt		// extracted sheet and cell indexes
           +-- stn-rainfall.txt		// weather station list from BOM

NOTE: 
    . Except the "stn-rainfall.txt" file, other files are used for the
    dashboard tool.
    . Example of using the dashboard tool:
          java -jar sid-tool.jar ./
    . The "filelist.txt" cannot include empty line for simplicity
    reason.

The *scripts* folder contains a Linux Bash script to download weather
records from the Australian Bureau of Meteorology (BoM).

    +-- scripts
           |__ get-bom-data.sh	        

NOTE: This is a Linux Bash script to extract BOM history weather
records (rainfall and temperature). Please refer the script for usage.

The *Readme.txt* is this file.

2. JDK and External Libraries
-----------------------------

These tools are developed in Java SE version 6. It uses the Apache POI
library to handle Excel files.

3. Copyright and License Issues
-------------------------------

Copyright &copy; 2013-2014, SMART Infrastructure Facility, University of Wollongong.

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the organization nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE UNIVERSITY OF WOLLONGONG BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

# 4. Feedback

We sincerely appreciate your valuable feedback. Any comments and
suggestions please contact:
    
    Professor Pascal Perez <pascal@uow.edu.au> 
    Research Director
    SMART Infrastructure Facility
    University of Wollongong 
