package entities;

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
        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdates",
        "additionalneeds"
})
@Generated("jsonschema2pojo")
public class BookingDetails {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("totalprice")
    private Integer totalprice;
    @JsonProperty("depositpaid")
    private Boolean depositpaid;
    @JsonProperty("bookingdates")
    private Bookingdates bookingdates;
    @JsonProperty("additionalneeds")
    private String additionalneeds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("totalprice")
    public Integer getTotalprice() {
        return totalprice;
    }

    @JsonProperty("totalprice")
    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    @JsonProperty("depositpaid")
    public Boolean getDepositpaid() {
        return depositpaid;
    }

    @JsonProperty("depositpaid")
    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    @JsonProperty("bookingdates")
    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    @JsonProperty("bookingdates")
    public void setBookingdates(Bookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    @JsonProperty("additionalneeds")
    public String getAdditionalneeds() {
        return additionalneeds;
    }

    @JsonProperty("additionalneeds")
    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
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
        sb.append(BookingDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("firstname");
        sb.append('=');
        sb.append(((this.firstname == null)?"<null>":this.firstname));
        sb.append(',');
        sb.append("lastname");
        sb.append('=');
        sb.append(((this.lastname == null)?"<null>":this.lastname));
        sb.append(',');
        sb.append("totalprice");
        sb.append('=');
        sb.append(((this.totalprice == null)?"<null>":this.totalprice));
        sb.append(',');
        sb.append("depositpaid");
        sb.append('=');
        sb.append(((this.depositpaid == null)?"<null>":this.depositpaid));
        sb.append(',');
        sb.append("bookingdates");
        sb.append('=');
        sb.append(((this.bookingdates == null)?"<null>":this.bookingdates));
        sb.append(',');
        sb.append("additionalneeds");
        sb.append('=');
        sb.append(((this.additionalneeds == null)?"<null>":this.additionalneeds));
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
        result = ((result* 31)+((this.firstname == null)? 0 :this.firstname.hashCode()));
        result = ((result* 31)+((this.additionalneeds == null)? 0 :this.additionalneeds.hashCode()));
        result = ((result* 31)+((this.bookingdates == null)? 0 :this.bookingdates.hashCode()));
        result = ((result* 31)+((this.totalprice == null)? 0 :this.totalprice.hashCode()));
        result = ((result* 31)+((this.depositpaid == null)? 0 :this.depositpaid.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lastname == null)? 0 :this.lastname.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookingDetails) == false) {
            return false;
        }
        BookingDetails rhs = ((BookingDetails) other);
        return ((((((((this.firstname == rhs.firstname)||((this.firstname!= null)&&this.firstname.equals(rhs.firstname)))&&((this.additionalneeds == rhs.additionalneeds)||((this.additionalneeds!= null)&&this.additionalneeds.equals(rhs.additionalneeds))))&&((this.bookingdates == rhs.bookingdates)||((this.bookingdates!= null)&&this.bookingdates.equals(rhs.bookingdates))))&&((this.totalprice == rhs.totalprice)||((this.totalprice!= null)&&this.totalprice.equals(rhs.totalprice))))&&((this.depositpaid == rhs.depositpaid)||((this.depositpaid!= null)&&this.depositpaid.equals(rhs.depositpaid))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lastname == rhs.lastname)||((this.lastname!= null)&&this.lastname.equals(rhs.lastname))));
    }

}
