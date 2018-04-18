package elastest.epm.adapter.docker.model;
import java.util.Objects;

/** This entity defines the network connectivity and details where the VDUs are connected to. */
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)

public class Network {
  private String id = null;

  private String name = null;

  private String networkId = null;

  private String cidr = null;

  private String poPName = null;

  public Network id(String id) {
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

  public Network name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Network networkId(String networkId) {
    this.networkId = networkId;
    return this;
  }

  /**
   * Get networkId
   *
   * @return networkId
   */
  public String getNetworkId() {
    return networkId;
  }

  public void setNetworkId(String networkId) {
    this.networkId = networkId;
  }

  public Network cidr(String cidr) {
    this.cidr = cidr;
    return this;
  }

  /**
   * Get cidr
   *
   * @return cidr
   */
  public String getCidr() {
    return cidr;
  }

  public void setCidr(String cidr) {
    this.cidr = cidr;
  }

  public Network poPInfoName(String poPInfoName) {
    this.poPName = poPInfoName;
    return this;
  }

  /**
   * Get poPName
   *
   * @return poPName
   */
  public String getPoPName() {
    return poPName;
  }

  public void setPoPName(String poPName) {
    this.poPName = poPName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Network network = (Network) o;
    return Objects.equals(this.id, network.id)
        && Objects.equals(this.name, network.name)
        && Objects.equals(this.cidr, network.cidr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, cidr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Network {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    networkId: ").append(toIndentedString(networkId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    cidr: ").append(toIndentedString(cidr)).append("\n");
    sb.append("    poPName: ").append(toIndentedString(poPName)).append("\n");
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
