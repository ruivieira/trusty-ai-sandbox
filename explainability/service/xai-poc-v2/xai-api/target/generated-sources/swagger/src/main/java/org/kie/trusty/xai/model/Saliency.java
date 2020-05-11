/*
 * TrustyXAI
 * Trusty XAI explainability API.
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
import java.util.ArrayList;
import java.util.List;
import org.kie.trusty.xai.model.FeatureImportance;

/**
 * Saliency
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-04T14:59:37.449+02:00")
public class Saliency {
  @SerializedName("featureImportances")
  private List<FeatureImportance> featureImportances = null;

  public Saliency featureImportances(List<FeatureImportance> featureImportances) {
    this.featureImportances = featureImportances;
    return this;
  }

  public Saliency addFeatureImportancesItem(FeatureImportance featureImportancesItem) {
    if (this.featureImportances == null) {
      this.featureImportances = new ArrayList<FeatureImportance>();
    }
    this.featureImportances.add(featureImportancesItem);
    return this;
  }

   /**
   * Get featureImportances
   * @return featureImportances
  **/
  @ApiModelProperty(value = "")
  public List<FeatureImportance> getFeatureImportances() {
    return featureImportances;
  }

  public void setFeatureImportances(List<FeatureImportance> featureImportances) {
    this.featureImportances = featureImportances;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Saliency saliency = (Saliency) o;
    return Objects.equals(this.featureImportances, saliency.featureImportances);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureImportances);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Saliency {\n");
    
    sb.append("    featureImportances: ").append(toIndentedString(featureImportances)).append("\n");
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

