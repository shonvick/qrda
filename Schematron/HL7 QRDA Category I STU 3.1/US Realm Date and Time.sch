<?xml version="1.0" encoding="UTF-8"?>

<sch:schema xmlns:voc="http://www.lantanagroup.com/voc" xmlns:svs="urn:ihe:iti:svs:2008" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:sdtc="urn:hl7-org:sdtc" xmlns="urn:hl7-org:v3" xmlns:cda="urn:hl7-org:v3" xmlns:sch="http://purl.oclc.org/dsdl/schematron">
    <sch:ns prefix="voc" uri="http://www.lantanagroup.com/voc" />
    <sch:ns prefix="svs" uri="urn:ihe:iti:svs:2008" />
    <sch:ns prefix="xsi" uri="http://www.w3.org/2001/XMLSchema-instance" />
    <sch:ns prefix="sdtc" uri="urn:hl7-org:sdtc" />
    <sch:ns prefix="cda" uri="urn:hl7-org:v3" />
    
    <sch:phase id="errors">
        <sch:active pattern="US-Realm-Date-and-Time-pattern-errors" />
    </sch:phase>
    
    <sch:phase id="warnings">
        <sch:active pattern="US-Realm-Date-and-Time-pattern-warnings" />
    </sch:phase>
    
    <sch:pattern id="US-Realm-Date-and-Time-pattern-errors">
        <sch:rule id="US-Realm-Date-and-Time-errors" context="cda:effectiveTime[cda:templateId[@root='2.16.840.1.113883.10.20.22.5.4']]">
            <sch:assert id="a-81-10127-c-error" test="string-length(@value)&gt;=8">*SHALL* be precise to the day (CONF:81-10127).</sch:assert>
        </sch:rule>
    </sch:pattern>
    
    <sch:pattern id="US-Realm-Date-and-Time-pattern-warnings">
        <sch:rule id="US-Realm-Date-and-Time-warnings" context="cda:effectiveTime[cda:templateId[@root='2.16.840.1.113883.10.20.22.5.4']]">
            <sch:assert id="a-81-10128-c-warning" test="string-length(@value)&gt;=12">*SHOULD* be precise to the minute (CONF:81-10128).</sch:assert>
            <sch:assert id="a-81-10130-c-warning" test="string-length(@value)&lt;10 or ( string-length(@value)&gt;=10 and (contains(@value,'+') or contains(@value,'-')))">If more precise than day, *SHOULD* include time-zone offset (CONF:81-10130).</sch:assert>
        </sch:rule>
    </sch:pattern>
    
</sch:schema>