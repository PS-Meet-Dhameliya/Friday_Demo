Feature: Verify User can get the legislations all data

    Scenario Outline: get the legislation all data
        Given setup legislation api "/legislations/find"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |legilations_schema.json|
        
    Scenario Outline: get the legislation list all data
        Given setup legislation api "/legislation-lists/find"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |legislation_lists_schema.json|
        
   Scenario Outline: get the legislation list entry all data
        Given setup legislation api "legislation-list-entries/find" with form parama with "legislationListKey" and "EU-ECOLABEL-ART_6_6"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |legislation_lists_entries_schema.json|
        
   Scenario Outline: get the substance all data
        Given setup legislation api "/substances/find"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |substances_schema.json|
        
 		Scenario Outline: get the resources all data
        Given setup legislation api "/find"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |resources_schema.json|
        
     
 		Scenario Outline: get the retrive statistics all data
        Given setup legislation api "/statistics"
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name |
        | 200         |resources_statics_schema.json|
        
 		Scenario Outline: post the legislations with all data
        Given setup post legislation api <endpoint>,<Key1>,<Key2>  
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name | endpoint | Key1 | Key2 | 
        | 200 | legislations_findByKeys_schema.json | /legislations/findByKeys  | EU-DETERGENTS | EU-ECOLABEL|

    Scenario Outline: post the legislations list with all data
        Given setup post legislation api <endpoint>,<Key1>,<Key2>  
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name | endpoint | Key1 | Key2 | 
        | 200 | legilations_list_find_keys.json | /legislation-lists/findByKeys  | EU-WORKER_CARC_MUTA-ANX_I_ART_2 | EU-ECOLABEL-ART_6_6|

    Scenario Outline: post the legislations list enteries with all data
        Given setup post legislation api <endpoint>,<Key1>,<Key2>  
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name | endpoint | Key1 | Key2 | 
        | 200 | legilations_list_entries.json | /legislation-list-entries/findByKeys | VSK-00188D |VSK-00188E|

    Scenario Outline: post the substance with all data
        Given setup post legislation api <endpoint>,<Key1>,<Key2>  
        Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name | endpoint | Key1 | Key2 | 
        | 200 | substances_find_keys.json | /substances/findByKeys | VSK-00000000-0000-0000-0000-000000000003 |VSK-00000000-0000-0000-0000-000000000005|


    Scenario Outline: post the Resources with all data
        Given setup post legislation api for ref <endpoint>
       	Then verify the response code is <status_code>
        And verify the <schema_file_name> json schema
        And verify response time is within configured value
      Examples:
        | status_code | schema_file_name | endpoint |
        | 200 | resource.json | /findByRefs |

