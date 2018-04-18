package elastest.epm.adapter.docker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Resource Group defines a bundle of VDUs and virtual networks which belongs together. It
 * includes also the Point-of-Presences where the virtual resources have to be allocated.
 */

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-08-03T17:51:47.319+02:00"
)
public class ResourceGroup {
  private String id = null;

  private String name = null;

  private List<VDU> vdus = new ArrayList<VDU>();

  private List<Network> networks = new ArrayList<Network>();

  public ResourceGroup id(String id) {
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

  public ResourceGroup name(String name) {
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

  public ResourceGroup vdus(List<VDU> vdus) {
    this.vdus = vdus;
    return this;
  }

  public ResourceGroup addVdusItem(VDU vdusItem) {
    this.vdus.add(vdusItem);
    return this;
  }

  /**
   * Get vdus
   *
   * @return vdus
   */
  public List<VDU> getVdus() {
    return vdus;
  }

  public void setVdus(List<VDU> vdus) {
    this.vdus = vdus;
  }

  public ResourceGroup networks(List<Network> networks) {
    this.networks = networks;
    return this;
  }

  public ResourceGroup addNetworksItem(Network networksItem) {
    this.networks.add(networksItem);
    return this;
  }

  /**
   * Get networks
   *
   * @return networks
   */
  public List<Network> getNetworks() {
    return networks;
  }

  public void setNetworks(List<Network> networks) {
    this.networks = networks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceGroup resourceGroup = (ResourceGroup) o;
    return Objects.equals(this.id, resourceGroup.id)
        && Objects.equals(this.name, resourceGroup.name)
        && Objects.equals(this.vdus, resourceGroup.vdus)
        && Objects.equals(this.networks, resourceGroup.networks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, vdus, networks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceGroup {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    vdus: ").append(toIndentedString(vdus)).append("\n");
    sb.append("    networks: ").append(toIndentedString(networks)).append("\n");
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
