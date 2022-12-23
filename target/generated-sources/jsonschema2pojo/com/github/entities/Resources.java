
package com.github.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "core",
    "graphql",
    "integration_manifest",
    "search"
})
@Generated("jsonschema2pojo")
public class Resources {

    @JsonProperty("core")
    private Core core;
    @JsonProperty("graphql")
    private Graphql graphql;
    @JsonProperty("integration_manifest")
    private IntegrationManifest integrationManifest;
    @JsonProperty("search")
    private Search search;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("core")
    public Core getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(Core core) {
        this.core = core;
    }

    @JsonProperty("graphql")
    public Graphql getGraphql() {
        return graphql;
    }

    @JsonProperty("graphql")
    public void setGraphql(Graphql graphql) {
        this.graphql = graphql;
    }

    @JsonProperty("integration_manifest")
    public IntegrationManifest getIntegrationManifest() {
        return integrationManifest;
    }

    @JsonProperty("integration_manifest")
    public void setIntegrationManifest(IntegrationManifest integrationManifest) {
        this.integrationManifest = integrationManifest;
    }

    @JsonProperty("search")
    public Search getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(Search search) {
        this.search = search;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Resources.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("core");
        sb.append('=');
        sb.append(((this.core == null)?"<null>":this.core));
        sb.append(',');
        sb.append("graphql");
        sb.append('=');
        sb.append(((this.graphql == null)?"<null>":this.graphql));
        sb.append(',');
        sb.append("integrationManifest");
        sb.append('=');
        sb.append(((this.integrationManifest == null)?"<null>":this.integrationManifest));
        sb.append(',');
        sb.append("search");
        sb.append('=');
        sb.append(((this.search == null)?"<null>":this.search));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.core == null)? 0 :this.core.hashCode()));
        result = ((result* 31)+((this.search == null)? 0 :this.search.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.graphql == null)? 0 :this.graphql.hashCode()));
        result = ((result* 31)+((this.integrationManifest == null)? 0 :this.integrationManifest.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Resources) == false) {
            return false;
        }
        Resources rhs = ((Resources) other);
        return ((((((this.core == rhs.core)||((this.core!= null)&&this.core.equals(rhs.core)))&&((this.search == rhs.search)||((this.search!= null)&&this.search.equals(rhs.search))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.graphql == rhs.graphql)||((this.graphql!= null)&&this.graphql.equals(rhs.graphql))))&&((this.integrationManifest == rhs.integrationManifest)||((this.integrationManifest!= null)&&this.integrationManifest.equals(rhs.integrationManifest))));
    }

}
