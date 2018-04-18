package elastest.epm.adapter.docker.model;

import elastest.epm.adapter.docker.repository.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Package implements Serializable {

    @Id
    private String id;

    private ArrayList<String> computeIds;

    private ArrayList<String> networkIds;

    @PrePersist
    public void ensureId() {
        id = IdGenerator.createUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getNetworkIds() {
        return networkIds;
    }

    public void setNetworkIds(ArrayList<String> networkIds) {
        this.networkIds = networkIds;
    }

    public ArrayList<String> getComputeIds() {
        return computeIds;
    }

    public void setComputeIds(ArrayList<String> computeIds) {
        this.computeIds = computeIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Key {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    compute ids: ").append(toIndentedString(computeIds)).append("\n");
        sb.append("    network ids: ").append(toIndentedString(networkIds)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
