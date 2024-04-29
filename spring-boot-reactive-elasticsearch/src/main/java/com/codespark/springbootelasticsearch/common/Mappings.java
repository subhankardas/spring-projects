package com.codespark.springbootelasticsearch.common;

import java.util.Map;

public class Mappings {

        /** Mappings for request field names to index field names. */
        public static final Map<String, String> fieldMapping = Map.of(
                        "name", "name",
                        "ip", "ip_address",
                        "os_name", "os.name",
                        "os_version", "os.version",
                        "os_type", "os.type",
                        "software_name", "installed_softwares.name",
                        "software_version", "installed_softwares.version",
                        "software_install_date", "installed_softwares.install_date");

        /** List of allowed searchable text field names. */
        public static final String[] searchableFieldNames = { "name", "ip_address", "os.name", "os.type",
                        "installed_softwares.name" };

}
