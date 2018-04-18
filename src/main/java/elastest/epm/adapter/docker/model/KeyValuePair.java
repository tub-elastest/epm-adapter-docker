package elastest.epm.adapter.docker.model;

import java.util.Objects;

/** This entity is a Key-Value pair for storing metadata contained in other entities */
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
public class KeyValuePair {
  private String id = null;

  private String key = null;

  private String value = null;

  public KeyValuePair() {}

  public KeyValuePair(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public KeyValuePair id(String id) {
    this.id = id;
    return this;
  }


  /**
   * Get id
   *
   * @return id
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public KeyValuePair key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Get key
   *
   * @return key
   */
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public KeyValuePair value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   *
   * @return value
   */
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyValuePair keyValuePair = (KeyValuePair) o;
    return Objects.equals(this.id, keyValuePair.id)
        && Objects.equals(this.key, keyValuePair.key)
        && Objects.equals(this.value, keyValuePair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyValuePair {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
