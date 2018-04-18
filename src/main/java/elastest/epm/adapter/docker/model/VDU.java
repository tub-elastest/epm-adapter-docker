package elastest.epm.adapter.docker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Virtual Deployment Unit (VDU) describes the capabilities of virtualized computing (Containers,
 * VMs) and networking resources.
 */
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
public class VDU {
  private String id = null;

  private String computeId = null;

  private String name = null;

  private String imageName = null;

  private String ip = null;

  private String netName = null;

  private String poPName = null;

  /** Gets or Sets status */
  public enum StatusEnum {
    INITIALIZING("initializing"),

    INITIALIZED("initialized"),

    DEPLOYING("deploying"),

    DEPLOYED("deployed"),

    RUNNING("running"),

    UNDEPLOYING("undeploying"),

    UNDEPLOYED("undeployed"),

    ERROR("error");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  private StatusEnum status = null;

  private List<KeyValuePair> metadata = new ArrayList<KeyValuePair>();

  private List<Event> events = new ArrayList<Event>();

  public VDU id(String id) {
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

  public VDU name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get computeId
   *
   * @return computeId
   */
  public String getComputeId() {
    return computeId;
  }

  public void setComputeId(String computeId) {
    this.computeId = computeId;
  }

  public VDU computeId(String computeId) {
    this.computeId = computeId;
    return this;
  }

  /**
   * Get imageName
   *
   * @return imageName
   */

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public VDU imageName(String imageName) {
    this.imageName = imageName;
    return this;
  }

  /**
   * Get ip
   *
   * @return ip
   */

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public VDU ip(String ip) {
    this.ip = ip;
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

  /**
   * Get netName
   *
   * @return netName
   */

  public String getNetName() {
    return netName;
  }

  public void setNetName(String netName) {
    this.netName = netName;
  }

  public VDU poPName(String poPName) {
    this.poPName = poPName;
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

  public VDU status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public VDU metadata(List<KeyValuePair> metadata) {
    this.metadata = metadata;
    return this;
  }

  public VDU addMetadataItem(KeyValuePair metadataItem) {
    this.metadata.add(metadataItem);
    return this;
  }

  /**
   * Get metadata
   *
   * @return metadata
   */
  public List<KeyValuePair> getMetadata() {
    return metadata;
  }

  public void setMetadata(List<KeyValuePair> metadata) {
    this.metadata = metadata;
  }

  public VDU events(List<Event> events) {
    this.events = events;
    return this;
  }

  public VDU addEventsItem(Event eventsItem) {
    this.events.add(eventsItem);
    return this;
  }

  /**
   * Get events
   *
   * @return events
   */
  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VDU VDU = (VDU) o;
    return Objects.equals(this.id, VDU.id)
        && Objects.equals(this.name, VDU.name)
        && Objects.equals(this.imageName, VDU.imageName)
        && Objects.equals(this.ip, VDU.ip)
        && Objects.equals(this.netName, VDU.netName)
        && Objects.equals(this.poPName, VDU.poPName)
        && Objects.equals(this.status, VDU.status)
        && Objects.equals(this.metadata, VDU.metadata)
        && Objects.equals(this.events, VDU.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, imageName, ip, netName, poPName, status, metadata, events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VDU {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    imageName: ").append(toIndentedString(imageName)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    netName: ").append(toIndentedString(netName)).append("\n");
    sb.append("    poPName: ").append(toIndentedString(poPName)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
