/*
 * Model to Explainability communication
 * Model to Explainability communication protocol
 *
 * OpenAPI spec version: 1.0.0
 * Contact: tteofili@redhat.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.kie.trusty.xai.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * FeatureDistribution
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-04T14:59:31.604+02:00")
public class FeatureDistribution {
  @SerializedName("min")
  private BigDecimal min = null;

  @SerializedName("max")
  private BigDecimal max = null;

  @SerializedName("mean")
  private BigDecimal mean = null;

  @SerializedName("stdDev")
  private BigDecimal stdDev = null;

  public FeatureDistribution min(BigDecimal min) {
    this.min = min;
    return this;
  }

   /**
   * Get min
   * @return min
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getMin() {
    return min;
  }

  public void setMin(BigDecimal min) {
    this.min = min;
  }

  public FeatureDistribution max(BigDecimal max) {
    this.max = max;
    return this;
  }

   /**
   * Get max
   * @return max
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getMax() {
    return max;
  }

  public void setMax(BigDecimal max) {
    this.max = max;
  }

  public FeatureDistribution mean(BigDecimal mean) {
    this.mean = mean;
    return this;
  }

   /**
   * Get mean
   * @return mean
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getMean() {
    return mean;
  }

  public void setMean(BigDecimal mean) {
    this.mean = mean;
  }

  public FeatureDistribution stdDev(BigDecimal stdDev) {
    this.stdDev = stdDev;
    return this;
  }

   /**
   * Get stdDev
   * @return stdDev
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getStdDev() {
    return stdDev;
  }

  public void setStdDev(BigDecimal stdDev) {
    this.stdDev = stdDev;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeatureDistribution featureDistribution = (FeatureDistribution) o;
    return Objects.equals(this.min, featureDistribution.min) &&
        Objects.equals(this.max, featureDistribution.max) &&
        Objects.equals(this.mean, featureDistribution.mean) &&
        Objects.equals(this.stdDev, featureDistribution.stdDev);
  }

  @Override
  public int hashCode() {
    return Objects.hash(min, max, mean, stdDev);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeatureDistribution {\n");
    
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    mean: ").append(toIndentedString(mean)).append("\n");
    sb.append("    stdDev: ").append(toIndentedString(stdDev)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

