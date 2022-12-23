
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
    "bookingid",
    "booking"
})
@Generated("jsonschema2pojo")
public class NewBookingResponse {

    @JsonProperty("bookingid")
    public Integer bookingid;
    @JsonProperty("booking")
    public Booking__1 booking;
    @JsonIgnore
    public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("bookingid")
    public Integer getBookingid() {
        return bookingid;
    }

    @JsonProperty("bookingid")
    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    @JsonProperty("booking")
    public Booking__1 getBooking() {
        return booking;
    }

    @JsonProperty("booking")
    public void setBooking(Booking__1 booking) {
        this.booking = booking;
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
        sb.append(NewBookingResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bookingid");
        sb.append('=');
        sb.append(((this.bookingid == null)?"<null>":this.bookingid));
        sb.append(',');
        sb.append("booking");
        sb.append('=');
        sb.append(((this.booking == null)?"<null>":this.booking));
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
        result = ((result* 31)+((this.booking == null)? 0 :this.booking.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.bookingid == null)? 0 :this.bookingid.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NewBookingResponse) == false) {
            return false;
        }
        NewBookingResponse rhs = ((NewBookingResponse) other);
        return ((((this.booking == rhs.booking)||((this.booking!= null)&&this.booking.equals(rhs.booking)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.bookingid == rhs.bookingid)||((this.bookingid!= null)&&this.bookingid.equals(rhs.bookingid))));
    }

}
