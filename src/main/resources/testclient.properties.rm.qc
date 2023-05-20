# @(#)testclient.properties	1.0 00/05/17

#
# Logger-specific properties follow
#
CLASSNAME=com.sbc.eia.bis.facades.testing.rm
LOGFILE_PATH=RMClient.qc.log

#BIS Provider URL
BIS_PROVIDER_URL=corbaloc::bisqc1.sbc.com:11000

BIS_HOME=cell/clusters/RM21.60Cluster/RMHome-21.60

# Test Type
#TARGET_TYPE=OBJECT
TARGET_TYPE=EJB
#TARGET_TYPE=PROXY
#TARGET_TYPE=SOAP
#TARGET_TYPE=SOAPHTTP
#TARGET_TYPE=MDB
#TARGET_TYPE=JMX

THREAD_COUNT=1
CYCLE_COUNT=1
SLEEP_TIME=0

# for OBJECT
BIS_PROPERTIES_PATH=c:\\appl\\eia\\rm\\properties\\rm.properties

# for Vicuna
# Vicuna Home
VICUNA_SERVICE_CONFIG_DIR=c:\\appl\\eia\\rm\\properties
VICUNA_XML_FILE=c:\\vicuna\\etc\\vicunalite.xml
# Vicuna services APPLDATA
APPLDATA=
GWSVCS_CLNTUUID=RMBIS

#RM PROXY APPLDATA
RMPROXY_APPLDATA=STAGING

#MDB Test client properties
RM_MDB_TEST_CLIENT_CONFIG_FILE_NAME=MDBTestClient.properties

#
#RMSoacQueue embus service wrapper properties
#
RM_TEST_CLIENT_CONFIG_FILE_NAME=test-client-config.xml
RM_TEST_ENVIRONMENT_NAME=RM_TEST_SOAC
OSS_PUBLIC_KEY=bossApplData1

# SOAFI PROXY properties
SOAFI_LDAP=ldap://emlqct01.sbc.com:4299/t=jms,c=us
USER_NAME=M18846t
PASSWORD=VVADt>8\u0008/\u0002E+\u007F/\u0005"N
CONNECTION_FACTORY=cn=rmqctQCF,ou=21_60,ou=rm,o=bis
REQUESET_QUEUE=cn=resourceManagement,ou=21_60,ou=rm,o=bis
#REPLY_TO_QUEUE=temp
#WAIT_TIME=120000

# SOAP/HTTP
LOGGING_KEY=SM-NONE
SERVICE_LOCATOR=http://cidc211.sldc.sbc.com/Rm21.60SoapHttpWeb/servlet/SoapAdapterServlet

#JMX
#LOOKUP_TYPE= "cluster" or "server" or "direct"
#PORT=the value of SOAP_CONNECTOR_ADDRESS
LOOKUP_TYPE=server
PORT=8882
MBEAN_IDENTIFIER=WebSphere:mbeanIdentifier=BISTestMBean,*
JMX_TIMEOUT=60
SERVER_NAME=server1

#DTD_URL=http://cscim1.sbc.com/eia/BISFacade/DTD/v1.4/

# BIS_CONTEXT fields
BIS_CONTEXT_Application=LSPWEST
BIS_CONTEXT_CustomerName=ccma
BIS_CONTEXT_UserID=sl2917
BIS_CONTEXT_Password=none_ya
BIS_CONTEXT_LoggingInformation=huh
BIS_CONTEXT_ServiceCenter=TX

#
# TestCase Information
# Each test case is specified in the format:
#	TESTCASEn=TEST_CASE_CLASS_NAME|test-case-specific-pipe-delimited-parameter-list
# TEST_CASE_COUNT should be the same number as 'n' in TESTCASEn.
#
TEST_CASE_COUNT=90

# Value for "Location" tag can be
# 1. null
# 2. a FieldedAddress: FieldedAddress|aRoute|aBox|aHouseNumberPrefix|aHouseNumber|aAssignedHouseNumber|aHouseNumberSuffix|aStreetDirection|aStreetName|aStreetThoroughfare|aStreetNameSuffix|aCity|aState|aPostalCode|aCounty|aCountry|aUnitCategory|aUnitType|aUnitValue|repeating unit category/type/value ...|ENDUNIT|aAdditionalAddressInformation|PROP|aTag0|aValue0|...|end|
# 3. a UnfieldedAddress: UnfieldedAddress|aAddressLine|aCity|aState|aPostalCode|aCounty|aCountry|aUnitCategory|aUnitType|aUnitValue|repeating unit category/type/value ...|ENDUNIT|aAdditionalAddressInformation|PROP|aTag0|aValue0|...|end|

# An optional BisContext can be specified as the first object for each trans
# BisContext: BisContext|tag|value|...|end|
# Valid tags are: Application,
#                 CustomerName,
#                 UserID,
#                 Password,
#                 LoggingInformation,
#                 ServiceCenter


# RetrieveResourcesForService
#
#	parm list = RetrieveResourcesForService|ServiceHandle|null|ResourceRoleHandles|...|end|
#
TESTCASE6=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|end|ServiceHandle|9252000001|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE7=RetrieveResourcesForService|BisContext|ServiceCenter|IL|Application|RMDEV|end|ServiceHandle|2173593914|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE8=RetrieveResourcesForService|BisContext|ServiceCenter|CT|Application|RMDEV|end|ServiceHandle|8606492637|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE9=RetrieveResourcesForService|BisContext|ServiceCenter|IN|Application|RMDEV|CustomerName|2917|end|ServiceHandle|3178531011|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE10=RetrieveResourcesForService|BisContext|ServiceCenter|IL|Application|RMDEV|CustomerName|2917|end|ServiceHandle|6305346254|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE11=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|CustomerName|2917|end|ServiceHandle|6616750001|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE12=RetrieveResourcesForService|BisContext|ServiceCenter|KS|Application|RMDEV|CustomerName|2917|end|ServiceHandle|3167840001|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE13=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|CustomerName|2917|end|ServiceHandle|2092451000|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE14=RetrieveResourcesForService|BisContext|ServiceCenter|OK|Application|RMDEV|CustomerName|2917|end|ServiceHandle|4053218458|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE15=RetrieveResourcesForService|BisContext|ServiceCenter|IL|Application|RMDEV|CustomerName|2917|end|ServiceHandle|8472230010|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE16=RetrieveResourcesForService|BisContext|ServiceCenter|TX|Application|RMDEV|CustomerName|2917|end|ServiceHandle|4097628608|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE17=RetrieveResourcesForService|BisContext|ServiceCenter|IN|Application|RMDEV|CustomerName|2917|end|ServiceHandle|317853|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|

# CFA type circuit
TESTCASE18=RetrieveResourcesForService|BisContext|ServiceCenter|MO|Application|RMDEV|end|ServiceHandle|121.T1ZF.BLVLILAD.STLSMO01|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|

#Format:.NN.AAAA.NNNNNN..AA  
TESTCASE19=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|IL|end|ServiceHandle|.11.HCGS.111111..AM|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|
TESTCASE20=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|CA|end|ServiceHandle|.11.HCGS.111111..PT|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|

#Format:.AAAA.NNNNNN.NNN.AA
TESTCASE21=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|TX|end|ServiceHandle|.XGGE.414915.001.SW|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|
TESTCASE22=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|IL|end|ServiceHandle|.HCGS.111111.001.AM|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|
TESTCASE23=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|CA|end|ServiceHandle|.HCGS.111111.001.PT|com.sbc.eia.bis.CircuitId|ResourceRoleNames|ALocationTermination|ZLocationTermination|end|

#Format:.AAAA.NNN.NNN.NNNN.ANNNN.
TESTCASE24=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|TX|end|ServiceHandle|.FXNA.210.886.4617.T0001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|
TESTCASE25=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|IL|end|ServiceHandle|.FXNA.214.745.4808.T0001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|
TESTCASE26=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|CA|end|ServiceHandle|.FXNA.214.745.4808.T0001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|

#Format:.AAAA.NNN.NNN.NNNN.ANNN.
TESTCASE27=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|TX|end|ServiceHandle|.FXNA.210.886.4617.T001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|
TESTCASE28=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|IL|end|ServiceHandle|.FXNA.214.745.4808.T001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|
TESTCASE29=RetrieveResourcesForService|BisContext|Application|RMDEV|CustomerName|2917|ServiceCenter|CA|end|ServiceHandle|.FXNA.214.745.4808.T001.|com.sbc.eia.bis.CircuitId|ResourceRoleNames||ALocationTermination|ZLocationTermination|end|

# IDLC inquiry
TESTCASE30=RetrieveResourcesForService|BisContext|ServiceCenter|MO|Application|RMDEV|end|ServiceHandle|3146280180|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|IntegratedDigitalLoopCarrier|end|
TESTCASE31=RetrieveResourcesForService|BisContext|ServiceCenter|CT|Application|RMDEV|end|ServiceHandle|2034599442|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|IntegratedDigitalLoopCarrier|end|
TESTCASE32=RetrieveResourcesForService|BisContext|ServiceCenter|IL|Application|RMDEV|end|ServiceHandle|6303578811|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|IntegratedDigitalLoopCarrier|end|
TESTCASE33=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|end|ServiceHandle|9165578084|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|IntegratedDigitalLoopCarrier|end| 
TESTCASE34=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|end|ServiceHandle|4086551414|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|IntegratedDigitalLoopCarrier|end|

# NPA NNX  X selection 
TESTCASE35=RetrieveResourcesForService|BisContext|ServiceCenter|MO|Application|RMDEV|end|ServiceHandle|3146280|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE36=RetrieveResourcesForService|BisContext|ServiceCenter|CT|Application|RMDEV|end|ServiceHandle|2034599|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE37=RetrieveResourcesForService|BisContext|ServiceCenter|IL|Application|RMDEV|end|ServiceHandle|6303578|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE38=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|end|ServiceHandle|9165578|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end| 
TESTCASE39=RetrieveResourcesForService|BisContext|ServiceCenter|CA|Application|RMDEV|end|ServiceHandle|4086551|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE40=RetrieveResourcesForService|BisContext|ServiceCenter|OK|Application|RMDEV|CustomerName|2917|end|ServiceHandle|4053215|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|

# The following two should fail.
TESTCASE41=RetrieveResourcesForService|BisContext|ServiceCenter|OK|Application|RMDEV|CustomerName|2917|end|ServiceHandle|40532|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|
TESTCASE42=RetrieveResourcesForService|BisContext|ServiceCenter|OK|Application|RMDEV|CustomerName|2917|end|ServiceHandle|40532156|com.sbc.eia.bis.TelephoneNumber|ResourceRoleNames|ServingSwitch|end|



# RetrieveServiceProvidersForResource
#
#	parm list = RetrieveServiceProvidersForResource|ResourceHandle|val|kind|ServiceTypeHandles|val|kind|...|end|
#
TESTCASE57=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|CA|CustomerName|sl2917|Application|RMDEV|end|ResourceHandle|ELSBCA11DS0|CLLI|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
TESTCASE58=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|MO|CustomerName|sslok|Application|RMDEV|end|ResourceHandle|BUFTMOHURS3|CLLI|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
TESTCASE59=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|CT|CustomerName|sslok|Application|RMDEV|end|ResourceHandle|MDTWCT00DS0|CLLI|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
#ocn inquiry
TESTCASE60=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|CA|CustomerName|sl2917|Application|RMDEV|end|ResourceHandle|ELSBCA11DS0|CLLI|ServiceTypeHandles|localvoice|carriertype|end|
TESTCASE61=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|MO|CustomerName|sslok|Application|RMDEV|end|ResourceHandle|BUFTMOHURS3|CLLI|ServiceTypeHandles|localvoice|carriertype|end|
TESTCASE62=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|CT|CustomerName|sslok|Application|RMDEV|end|ResourceHandle|MDTWCT00DS0|CLLI|ServiceTypeHandles|localvoice|carriertype|end|
TESTCASE63=RetrieveServiceProvidersForResource|BisContext|ServiceCenter|IL|CustomerName|sslok|Application|RMDEV|end|ResourceHandle|MDTWCT00DS0|CLLI|ServiceTypeHandles|localvoice|carriertype|end|



# RetrieveServiceProvidersForService
#
#	parm list = RetrieveServiceProvidersForService|ServiceHandle|value|kind|ServiceTypeHandles|val|kind|...|end|
#
#TESTCASE1=RetrieveServiceProvidersForService|ServiceHandle|something|kind|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
TESTCASE64=RetrieveServiceProvidersForService|BisContext|ServiceCenter|CA|CustomerName|sslok|Application|RMDEV|end|ServiceHandle|4156410000|com.sbc.eia.bis.TelephoneNumber|end|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
TESTCASE65=RetrieveServiceProvidersForService|BisContext|ServiceCenter|CT|CustomerName|sslok|Application|RMDEV|end|ServiceHandle|2037711061|com.sbc.eia.bis.TelephoneNumber|end|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|
TESTCASE66=RetrieveServiceProvidersForService|BisContext|ServiceCenter|TX|CustomerName|sr1284|Application|RMDEV|end|ServiceHandle|2142424000|com.sbc.eia.bis.TelephoneNumber|end|ServiceTypeHandles|pic|carriertype|lpic|carriertype|end|


# Used by Lead Free NetP group ActivateResourcesForDSLService
#
#	parm list = ActivateResourcesForDSLService|ActivationType|value|ProvisioningType|value|ServiceLocation|value|ServiceProviderId|value|CircuitId|value|ProviderTrunk|value|end| 
#
TESTCASE79=ActivateResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|ActivationType|NewVC|end|ProvisioningType|RT|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|WNDSCT00|end|end|ServiceProviderId|aolneisp|end|CircuitId|203-302-2000|end|ProviderTrunk||||end|
TESTCASE80=ActivateResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|ActivationType|ManualNewVC|end|ProvisioningType|RT|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|92|CLLI|WHFRCT01|end|end|ServiceProviderId|aolneisp|end|CircuitId|203-302-2001|end|ProviderTrunk|9|55|31/HFGJ/000086|end|

# ModifyResourcesForDSLService
#	parm list = ModifyResourcesForDSLService|ModificationType|value|RequestType|value|ProvisioningType|value|ServiceLocation|value|ServiceProviderId|value|CircuitId|value|NewCircuitId|value|ProviderTrunk|value|end|
#
TESTCASE81=ModifyResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|ModificationType|IspChange|end|RequestType|null|end|ProvisioningType|CO|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|NWBRCT00|end|end|ServiceProviderId|aol_isp|end|CircuitId|860-826-1814|end|NewCircuitId||end|ProviderTrunk|2|44|31/HCGJ/000086|end|
TESTCASE82=ModifyResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|ModificationType|InitiateMove|end|RequestType|Manual|end|ProvisioningType|CO|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|WHFRCT02|end|end|ServiceProviderId|moegacommisp|end|CircuitId|860-276-3875|end|NewCircuitId|801-822-0000|end|ProviderTrunk|2|104|31/HFFJ/000044|end|

# RetrieveResourcesForDSLService
#
# Parm list = RetrieveResourcesForDSLService|value|aQueryType|value|aServiceLocation|value|aCircuitId|value|end|
#
TESTCASE83=RetrieveResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|QueryType|queryDslCircuit|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|WNDSCT00|end|end|CircuitId|860-688-1294|end|
TESTCASE84=RetrieveResourcesForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|QueryType|queryDslCircuit|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|DNBRCT00|end|end|CircuitId|203-205-0067|end|

# RetrieveResourceStatusForDSLService
#
#	Parm list = RetrieveResourceStatusForDSLService|aQueryType|value|aProvisioningType|value|aServiceLocation|value|aServiceProviderId|value|aCircuitId|value|aNewCircuitId|value|aProviderTrunk|value|end|
#
TESTCASE85=RetrieveResourceStatusForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|QueryType|DisconnectVc|end|ProvisioningType|co|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|WHFRCT01|end|end|ServiceProviderId|aolneisp|end|CircuitId|203-205-0155|end|NewCircuitId| |end|ProviderTrunk|9|70|31/HFGJ/000086|end|
TESTCASE86=RetrieveResourceStatusForDSLService|BisContext|Application|CPSOS|BusinessUnit|SbcDataServices|end|QueryType|DisconnectVc|end|ProvisioningType|co|end|ServiceLocation|FieldedAddress|null|null|null|null|null|null|null|null|null|null|null|CT|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|LATA|920|CLLI|WHFRCT01|end|end|ServiceProviderId|aolneisp|end|CircuitId|203-205-0112|end|NewCircuitId| |end|ProviderTrunk|9|70|31/HFGJ/000086|end|


#CreateFacilityAssignment

